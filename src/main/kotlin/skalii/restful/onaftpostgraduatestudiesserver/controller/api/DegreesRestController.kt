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

import skalii.restful.onaftpostgraduatestudiesserver.entity.Degree
import skalii.restful.onaftpostgraduatestudiesserver.service.DegreesService
import skalii.restful.onaftpostgraduatestudiesserver.view.Json.Companion.get


@RequestMapping(
        value = ["/api/degrees"],
        produces = [APPLICATION_JSON_UTF8_VALUE])
@RestController
class DegreesRestController {

    @Autowired
    private lateinit var degreesService: DegreesService


    /** ============================== GET requests ============================== */


    @GetMapping(value = ["my{-view}"])
    fun getMy(
            @PathVariable(value = "-view") view: String,
            @AuthenticationPrincipal authUser: UserDetails
    ) =
            get(
                    view,
                    degreesService.get(
                            emailUser = authUser.username
                    )
            )

    @GetMapping(value = ["one{-view}"])
    fun getOne(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "id_degree",
                    required = false) idDegree: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?,
            @RequestParam(
                    value = "branch",
                    required = false) branch: String?
    ) =
            get(
                    view,
                    degreesService.get(
                            idDegree,
                            name,
                            branch
                    )
            )

    @GetMapping(value = ["one-by-user{-view}"])
    fun getOneByUser(
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
                    degreesService.get(
                            idUser = idUser,
                            idContactInfo = idContactInfo,
                            phoneNumberUser = phoneNumber,
                            emailUser = email
                    )
            )

    @GetMapping(value = ["all{-view}"])
    fun getAll(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "name",
                    required = false) name: String?,
            @RequestParam(
                    value = "branch",
                    required = false) branch: String?,
            @RequestParam(
                    value = "all_records",
                    required = false) allRecords: Boolean?
    ) =
            get(
                    view,
                    degreesService.getAll(
                            name,
                            branch,
                            allRecords
                    )
            )


    /** ============================== POST/PUT requests ============================== */


    @RequestMapping(
            value = ["one{-view}"],
            method = [POST, PUT])
    fun add(
            httpMethod: HttpMethod,
            @PathVariable(value = "-view") view: String,
            @RequestBody newDegree: Degree,
            @RequestParam(
                    value = "find_by_name",
                    required = false) findByName: String?,
            @RequestParam(
                    value = "find_by_branch",
                    required = false) findByBranch: String?
    ) =
            get(
                    view,
                    degreesService.save(
                            httpMethod,
                            newDegree,
                            findByName,
                            findByBranch
                    )
            )


    /** ============================== DELETE requests ============================== */


    @DeleteMapping(value = ["one{-view}"])
    fun delete(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "id_degree",
                    required = false) idDegree: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?,
            @RequestParam(
                    value = "branch",
                    required = false) branch: String?
    ) =
            get(
                    view,
                    degreesService.delete(
                            idDegree,
                            name,
                            branch
                    )
            )

}