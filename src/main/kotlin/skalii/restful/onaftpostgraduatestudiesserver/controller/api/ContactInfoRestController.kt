package skalii.restful.onaftpostgraduatestudiesserver.controller.api


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.POST
import org.springframework.web.bind.annotation.RequestMethod.PUT
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import skalii.restful.onaftpostgraduatestudiesserver.entity.ContactInfo
import skalii.restful.onaftpostgraduatestudiesserver.service.ContactInfoService
import skalii.restful.onaftpostgraduatestudiesserver.view.Json.Companion.get


@RequestMapping(
        value = ["/api/contact-info"],
        produces = [APPLICATION_JSON_UTF8_VALUE])
@RestController
class ContactInfoRestController {

    @Autowired
    private lateinit var contactInfoService: ContactInfoService


    /** ============================== GET requests ============================== */


    @GetMapping(value = ["my{-view}"])
    fun getMy(
            @AuthenticationPrincipal authUser: UserDetails,
            @PathVariable(value = "-view") view: String
    ) =
            get(
                    view,
                    contactInfoService.get(email = authUser.username)
            )

    @GetMapping(value = ["one{-view}"])
    fun getOne(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "id_contact_info",
                    required = false) idContactInfo: Int?,
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?,
            @RequestParam(
                    value = "phone_number",
                    required = false) phoneNumber: String?,
            @RequestParam(
                    value = "email",
                    required = false) email: String?
    ) =
            get(
                    view,
                    contactInfoService.get(
                            idContactInfo,
                            idUser,
                            phoneNumber,
                            email
                    )
            )

    @GetMapping(value = ["all{-view}"])
    fun getAll(@PathVariable(value = "-view") view: String) =
            get(
                    view,
                    contactInfoService.getAll()
            )


    /** ============================== POST/PUT requests ============================== */


    @RequestMapping(
            value = ["one{-view}"],
            method = [POST, PUT])
    fun save(
            httpMethod: HttpMethod,
            @PathVariable(value = "-view") view: String,
            @RequestBody newContactInfo: ContactInfo,
            @RequestParam(
                    value = "find_by_phone_number",
                    required = false) findByPhoneNumber: String?,
            @RequestParam(
                    value = "find_by_email",
                    required = false) findByEmail: String?
    ) =
            get(
                    view,
                    contactInfoService.save(
                            httpMethod,
                            newContactInfo,
                            findByPhoneNumber,
                            findByEmail
                    )
            )

    @PutMapping(value = ["my{-view}"])
    fun saveMy(
            httpMethod: HttpMethod,
            @PathVariable(value = "-view") view: String,
            @RequestBody newContactInfo: ContactInfo,
            @AuthenticationPrincipal authUser: UserDetails
    ) =
            get(
                    view,
                    contactInfoService.save(
                            httpMethod,
                            newContactInfo,
                            findByEmail = authUser.username
                    )
            )


    /** ============================== DELETE requests ============================== */


    @DeleteMapping(value = ["one{-view}"])
    fun delete(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "id_contact_info",
                    required = false) idContactInfo: Int?,
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?,
            @RequestParam(
                    value = "phone_number",
                    required = false) phoneNumber: String?,
            @RequestParam(
                    value = "email",
                    required = false) email: String?
    ) =
            get(
                    view,
                    contactInfoService.delete(
                            idContactInfo,
                            idUser,
                            phoneNumber,
                            email
                    )
            )

}