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

import skalii.restful.onaftpostgraduatestudiesserver.entity.Department
import skalii.restful.onaftpostgraduatestudiesserver.service.DepartmentsService
import skalii.restful.onaftpostgraduatestudiesserver.view.Json.Companion.get


@RequestMapping(
        value = ["/api/departments"],
        produces = [APPLICATION_JSON_UTF8_VALUE])
@RestController
class DepartmentsRestController {

    @Autowired
    private lateinit var departmentsService: DepartmentsService


    /** ============================== GET requests ============================== */


    @GetMapping(value = ["my{-view}"])
    fun getMy(
            @PathVariable(value = "-view") view: String,
            @AuthenticationPrincipal authUser: UserDetails
    ) =
            get(
                    view,
                    departmentsService.get(
                            emailUser = authUser.username
                    )
            )

    @GetMapping(value = ["one{-view}"])
    fun getOne(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "id_department",
                    required = false) idDepartment: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            get(
                    view,
                    departmentsService.get(
                            idDepartment,
                            name
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
                    departmentsService.get(
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
                    value = "id_institute",
                    required = false) idInstitute: Int?,
            @RequestParam(
                    value = "name_institute",
                    required = false) nameInstitute: String?,
            @RequestParam(
                    value = "id_faculty",
                    required = false) idFaculty: Int?,
            @RequestParam(
                    value = "name_faculty",
                    required = false) nameFaculty: String?,
            @RequestParam(
                    value = "all_records",
                    required = false) allRecords: Boolean?
    ) =
            get(
                    view,
                    departmentsService.getAll(
                            idInstitute,
                            nameInstitute,
                            idFaculty,
                            nameFaculty,
                            allRecords
                    )
            )


    /** ============================== POST/PUT requests ============================== */


    @RequestMapping(
            value = ["one{-view}"],
            method = [POST, PUT])
    fun save(
            httpMethod: HttpMethod,
            @PathVariable(value = "-view") view: String,
            @RequestBody newDepartment: Department,
            @RequestParam(
                    value = "name",
                    required = false) findByName: String?
    ) =
            get(
                    view,
                    departmentsService.save(
                            httpMethod,
                            newDepartment,
                            findByName
                    )
            )


    /** ============================== DELETE requests ============================== */


    @DeleteMapping(value = ["one{-view}"])
    fun delete(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "id_department",
                    required = false) idDepartment: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            get(
                    view,
                    departmentsService.delete(
                            idDepartment,
                            name
                    )
            )

}