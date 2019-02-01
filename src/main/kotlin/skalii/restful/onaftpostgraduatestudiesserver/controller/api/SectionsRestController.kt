package skalii.restful.onaftpostgraduatestudiesserver.controller.api


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.POST
import org.springframework.web.bind.annotation.RequestMethod.PUT
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import skalii.restful.onaftpostgraduatestudiesserver.entity.Section
import skalii.restful.onaftpostgraduatestudiesserver.service.SectionsService
import skalii.restful.onaftpostgraduatestudiesserver.view.Json.Companion.get


@RequestMapping(
        value = ["/api/sections"],
        produces = [APPLICATION_JSON_UTF8_VALUE])
@RestController
class SectionsRestController {


    @Autowired
    private lateinit var sectionsService: SectionsService

    /** ============================== GET requests ============================== */


    @GetMapping(value = ["my-one{-view}"])
    fun getMyOne(
            @PathVariable(value = "-view") view: String,
            @AuthenticationPrincipal authUser: UserDetails,
            @RequestParam(
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?
    ) =
            get(
                    view,
                    sectionsService.get(
                            number = number,
                            title = title,
                            emailUser = authUser.username
                    )
            )

    @GetMapping(value = ["my-all{-view}"])
    fun getMyAll(
            @PathVariable(value = "-view") view: String,
            @AuthenticationPrincipal authUser: UserDetails
    ) =
            get(
                    view,
                    sectionsService.getAll(
                            emailUser = authUser.username
                    )
            )

    @GetMapping(value = ["one{-view}"])
    fun getOne(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?,
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?,
            @RequestParam(
                    value = "id_contact_info",
                    required = false) idContactInfo: Int?,
            @RequestParam(
                    value = "phone_number",
                    required = false) phoneNumber: String?,
            @RequestParam(
                    value = "email",
                    required = false) email: String?
    ) =
            get(
                    view,
                    sectionsService.get(
                            idSection,
                            number,
                            title,
                            idUser,
                            idContactInfo,
                            phoneNumber,
                            email
                    )
            )

    @GetMapping(value = ["all{-view}"])
    fun getAll(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?,
            @RequestParam(
                    value = "id_contact_info",
                    required = false) idContactInfo: Int?,
            @RequestParam(
                    value = "phone_number",
                    required = false) phoneNumber: String?,
            @RequestParam(
                    value = "email",
                    required = false) email: String?
    ) =
            get(
                    view,
                    sectionsService.getAll(
                            idUser,
                            idContactInfo,
                            phoneNumber,
                            email
                    )
            )


    /** ============================== POST/PUT requests ============================== */


    @RequestMapping(
            value = ["my-one{-view}"],
            method = [POST, PUT])
    fun saveMyOne(
            httpMethod: HttpMethod,
            @PathVariable(value = "-view") view: String,
            @RequestBody newSection: Section,
            @AuthenticationPrincipal authUser: UserDetails,
            @RequestParam(
                    value = "find_by_number",
                    required = false) findByNumber: Int?,
            @RequestParam(
                    value = "find_by_title",
                    required = false) findByTitle: String?
    ) =
            get(
                    view,
                    sectionsService.save(
                            httpMethod,
                            newSection,
                            findByNumber,
                            findByTitle,
                            findByEmailUser = authUser.username
                    )
            )

    @RequestMapping(
            value = ["one{-view}"],
            method = [POST, PUT])
    fun saveOne(
            httpMethod: HttpMethod,
            @PathVariable(value = "-view") view: String,
            @RequestBody newSection: Section,
            @RequestParam(
                    value = "find_by_number",
                    required = false) findByNumber: Int?,
            @RequestParam(
                    value = "find_by_title",
                    required = false) findByTitle: String?,
            @RequestParam(
                    value = "find_by_id_user",
                    required = false) findByIdUser: Int?,
            @RequestParam(
                    value = "find_by_id_contact_info",
                    required = false) findByIdContactInfo: Int?,
            @RequestParam(
                    value = "find_by_phone_number",
                    required = false) findByPhoneNumber: String?,
            @RequestParam(
                    value = "find_by_email",
                    required = false) findByEmail: String?
    ) =
            get(
                    view,
                    sectionsService.save(
                            httpMethod,
                            newSection,
                            findByNumber,
                            findByTitle,
                            findByIdUser,
                            findByIdContactInfo,
                            findByPhoneNumber,
                            findByEmail
                    )
            )


    /** ============================== DELETE requests ============================== */


    @DeleteMapping(value = ["my-one{-view}"])
    fun deleteMyOne(
            @PathVariable(value = "-view") view: String,
            @AuthenticationPrincipal authUser: UserDetails,
            @RequestParam(
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?
    ) =
            get(
                    view,
                    sectionsService.delete(
                            number = number,
                            title = title,
                            emailUser = authUser.username
                    )
            )

    @DeleteMapping(value = ["my-all{-view}"])
    fun deleteMyAll(
            @PathVariable(value = "-view") view: String,
            @AuthenticationPrincipal authUser: UserDetails
    ) =
            get(
                    view,
                    sectionsService.deleteAll(
                            emailUser = authUser.username
                    )
            )

    @DeleteMapping(value = ["one{-view}"])
    fun deleteOne(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?,
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?,
            @RequestParam(
                    value = "id_contact_info",
                    required = false) idContactInfo: Int?,
            @RequestParam(
                    value = "phone_number",
                    required = false) phoneNumber: String?,
            @RequestParam(
                    value = "email",
                    required = false) email: String?
    ) =
            get(
                    view,
                    sectionsService.delete(
                            idSection,
                            number,
                            title,
                            idUser,
                            idContactInfo,
                            phoneNumber,
                            email
                    )
            )

    @DeleteMapping(value = ["all-by-user{-view}"])
    fun deleteAllByUser(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?,
            @RequestParam(
                    value = "id_contact_info",
                    required = false) idContactInfo: Int?,
            @RequestParam(
                    value = "phone_number",
                    required = false) phoneNumber: String?,
            @RequestParam(
                    value = "email",
                    required = false) email: String?
    ) =
            get(
                    view,
                    sectionsService.deleteAll(
                            idUser,
                            idContactInfo,
                            phoneNumber,
                            email
                    )
            )

}