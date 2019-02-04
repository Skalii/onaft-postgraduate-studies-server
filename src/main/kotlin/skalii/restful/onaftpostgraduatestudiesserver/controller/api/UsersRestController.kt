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

import skalii.restful.onaftpostgraduatestudiesserver.entity.User
import skalii.restful.onaftpostgraduatestudiesserver.service.UsersService
import skalii.restful.onaftpostgraduatestudiesserver.view.Json.Companion.get
import skalii.restful.onaftpostgraduatestudiesserver.view.Json.Companion.getUser


@RequestMapping(
        value = ["/api/users"],
        produces = [APPLICATION_JSON_UTF8_VALUE])
@RestController
class UsersRestController {

    @Autowired
    lateinit var usersService: UsersService


    /** ============================== GET requests ============================== */


    @GetMapping(value = ["me{-view}"])
    fun getMe(
            @PathVariable("-view") view: String,
            @AuthenticationPrincipal authUser: UserDetails
    ) =
            getUser(
                    view,
                    usersService.get(
                            emailUser = authUser.username
                    )
            )

    @GetMapping(value = ["my-instructor{-view}"])
    fun getMyInstructor(
            @PathVariable("-view") view: String,
            @AuthenticationPrincipal authUser: UserDetails
    ) =
            getUser(
                    view,
                    usersService.get(
                            emailUser = authUser.username
                    ).studyInfo?.instructor
            )

    @GetMapping(value = ["my-students{-view}"])
    fun getMyStudents(
            @PathVariable("-view") view: String,
            @AuthenticationPrincipal authUser: UserDetails
    ) =
            get(
                    view,
                    usersService.run {
                        val myStudents: MutableList<User> = mutableListOf()
                        get(emailUser = authUser.username)
                                .students.forEach { myStudents.add(it!!.user!!) }
                        return@run myStudents
                    }
            )

    @GetMapping(value = ["one{-view}"])
    fun getOne(
            @PathVariable("-view") view: String,
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
            getUser(
                    view,
                    usersService.get(
                            idUser,
                            idContactInfo,
                            phoneNumber,
                            email
                    )
            )

    @GetMapping(value = ["all{-view}"])
    fun getAll(
            @PathVariable("-view") view: String,
            @RequestParam(
                    value = "all_records",
                    required = false) allRecords: Boolean?,
            @RequestParam(
                    value = "id_degree",
                    required = false) idDegree: Int?,
            @RequestParam(
                    value = "name_degree",
                    required = false) nameDegree: String?,
            @RequestParam(
                    value = "branch_degree",
                    required = false) branchDegree: String?,
            @RequestParam(
                    value = "id_branch",
                    required = false) idBranch: Int?,
            @RequestParam(
                    value = "number_branch",
                    required = false) numberBranch: String?,
            @RequestParam(
                    value = "name_branch",
                    required = false) nameBranch: String?,
            @RequestParam(
                    value = "id_speciality",
                    required = false) idSpeciality: Int?,
            @RequestParam(
                    value = "number_speciality",
                    required = false) numberSpeciality: String?,
            @RequestParam(
                    value = "name_speciality",
                    required = false) nameSpeciality: String?,
            @RequestParam(
                    value = "id_department",
                    required = false) idDepartment: Int?,
            @RequestParam(
                    value = "name_department",
                    required = false) nameDepartment: String?,
            @RequestParam(
                    value = "id_faculty",
                    required = false) idFaculty: Int?,
            @RequestParam(
                    value = "name_faculty",
                    required = false) nameFaculty: String?,
            @RequestParam(
                    value = "id_institute",
                    required = false) idInstitute: Int?,
            @RequestParam(
                    value = "name_institute",
                    required = false) nameInstitute: String?
    ) =
            getUser(
                    view,
                    usersService.getAll(
                            allRecords,
                            idDegree,
                            nameDegree,
                            branchDegree,
                            idBranch,
                            numberBranch,
                            nameBranch,
                            idSpeciality,
                            numberSpeciality,
                            nameSpeciality,
                            idDepartment,
                            nameDepartment,
                            idFaculty,
                            nameFaculty,
                            idInstitute,
                            nameInstitute
                    )
            )


    /** ============================== POST/PUT requests ============================== */


    @PutMapping(value = ["me{-view}"])
    fun saveMe(
            httpMethod: HttpMethod,
            @PathVariable("-view") view: String,
            @RequestBody newUser: User,
            @AuthenticationPrincipal authUser: UserDetails,
            @RequestParam(
                    value = "new_password_hash",
                    required = false) newPasswordHash: String?
    ) =
            get(
                    view,
                    usersService.save(
                            httpMethod,
                            newUser,
                            newPasswordHash,
                            findByEmailUser = authUser.username
                    )
            )

    @RequestMapping(
            value = ["one{-view}"],
            method = [POST, PUT])
    fun saveOne(
            httpMethod: HttpMethod,
            @PathVariable("-view") view: String,
            @RequestBody newUser: User,
            @RequestParam(
                    value = "password_hash",
                    required = false) passwordHash: String,
            @RequestParam(
                    value = "find_by_id_user",
                    required = false) findByIdUser: Int?,
            @RequestParam(
                    value = "find_by_id_contact_info",
                    required = false) findByIdContactInfo: Int?,
            @RequestParam(
                    value = "find_by_phone_number",
                    required = false) findByPhoneNumberUser: String?,
            @RequestParam(
                    value = "find_by_email",
                    required = false) findByEmailUser: String?
    ) =
            usersService.save(
                    httpMethod,
                    newUser,
                    passwordHash,
                    findByIdContactInfo,
                    findByPhoneNumberUser,
                    findByEmailUser
            )


    /** ============================== DELETE requests ============================== */


    @DeleteMapping(value = ["one{-view}"])
    fun deleteOne(
            @PathVariable("-view") view: String,
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
            getUser(
                    view,
                    usersService.run {
                        delete(
                                idUser,
                                idContactInfo,
                                phoneNumber,
                                email
                        )
                    }
            )

}