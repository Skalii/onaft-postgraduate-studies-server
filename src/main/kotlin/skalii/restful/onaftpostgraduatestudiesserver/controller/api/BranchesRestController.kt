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

import skalii.restful.onaftpostgraduatestudiesserver.entity.Branch
import skalii.restful.onaftpostgraduatestudiesserver.service.BranchesService
import skalii.restful.onaftpostgraduatestudiesserver.view.Json.Companion.get


@RequestMapping(
        value = ["/api/branches"],
        produces = [APPLICATION_JSON_UTF8_VALUE])
@RestController
class BranchesRestController {

    @Autowired
    private lateinit var branchesService: BranchesService


    /** ============================== GET requests ============================== */


    @GetMapping(value = ["my{-view}"])
    fun getMy(
            @PathVariable(value = "-view") view: String,
            @AuthenticationPrincipal authUser: UserDetails
    ) =
            get(
                    view,
                    branchesService.get(
                            emailUser = authUser.username
                    )
            )

    @GetMapping(value = ["one{-view}"])
    fun getOne(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "id_branch",
                    required = false) idBranch: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: String?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            get(
                    view,
                    branchesService.get(
                            idBranch,
                            number,
                            name
                    )
            )

    @GetMapping(value = ["one-by-speciality{-view}"])
    fun getOneBySpeciality(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "id_speciality",
                    required = false) idSpeciality: Int?,
            @RequestParam(
                    value = "number_speciality",
                    required = false) numberSpeciality: String?,
            @RequestParam(
                    value = "name_speciality",
                    required = false) nameSpeciality: String?
    ) =
            get(
                    view,
                    branchesService.get(
                            idSpeciality = idSpeciality,
                            numberSpeciality = numberSpeciality,
                            nameSpeciality = nameSpeciality
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
                    branchesService.get(
                            idUser = idUser,
                            idContactInfo = idContactInfo,
                            phoneNumberUser = phoneNumber,
                            emailUser = email
                    )
            )

    @GetMapping(value = ["all{-view}"])
    fun getAll(@PathVariable(value = "-view") view: String) =
            get(
                    view,
                    branchesService.getAll()
            )


    /** ============================== POST/PUT requests ============================== */


    @RequestMapping(
            value = ["one{-view}"],
            method = [POST, PUT])
    fun save(
            httpMethod: HttpMethod,
            @PathVariable(value = "-view") view: String,
            @RequestBody newBranch: Branch,
            @RequestParam(
                    value = "find_by_number",
                    required = false) findByNumber: String?,
            @RequestParam(
                    value = "find_by_name",
                    required = false) findByName: String?
    ) =
            get(
                    view,
                    branchesService.save(
                            httpMethod,
                            newBranch,
                            findByNumber,
                            findByName
                    )
            )


    /** ============================== DELETE requests ============================== */


    @DeleteMapping(value = ["one{-view}"])
    fun delete(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "id_branch",
                    required = false) idBranch: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: String?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            get(
                    view,
                    branchesService.delete(
                            idBranch,
                            number,
                            name
                    )
            )

    @DeleteMapping(value = ["one-by-speciality{-view}"])
    fun deleteBySpeciality(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "id_speciality",
                    required = false) idSpeciality: Int?,
            @RequestParam(
                    value = "number_speciality",
                    required = false) numberSpeciality: String?,
            @RequestParam(
                    value = "name_speciality",
                    required = false) nameSpeciality: String?
    ) =
            get(
                    view,
                    branchesService.delete(
                            idSpeciality = idSpeciality,
                            numberSpeciality = numberSpeciality,
                            nameSpeciality = nameSpeciality
                    )
            )

}