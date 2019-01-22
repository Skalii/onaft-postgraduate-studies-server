package skalii.restful.onaftpostgraduatestudiesserver.controller.api


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import skalii.restful.onaftpostgraduatestudiesserver.entity.Speciality
import skalii.restful.onaftpostgraduatestudiesserver.repository.ContactInfoRepository
import skalii.restful.onaftpostgraduatestudiesserver.repository.SpecialitiesRepository
import skalii.restful.onaftpostgraduatestudiesserver.repository.UsersRepository
import skalii.restful.onaftpostgraduatestudiesserver.service.BranchesService
import skalii.restful.onaftpostgraduatestudiesserver.view.Json


@RequestMapping(
        value = ["/api/specialities"],
        produces = [APPLICATION_JSON_UTF8_VALUE])
@RestController
class SpecialitiesRestController(
        val contactInfoRepository: ContactInfoRepository,
        val specialitiesRepository: SpecialitiesRepository,
        val usersRepository: UsersRepository
) {

    @Autowired
    lateinit var branchesService: BranchesService


    /** ============================== GET requests ============================== */


    @GetMapping(value = ["my{-view}"])
    fun getMy(
            @PathVariable(value = "-view") view: String,
            @AuthenticationPrincipal authUser: UserDetails
    ) =
            Json.get(
                    view,
                    specialitiesRepository.get(
                            usersRepository.get(
                                    authUser.username
                            )
                    )
            )

    @GetMapping(value = ["one{-view}"])
    fun getOne(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "id_speciality",
                    required = false) idSpeciality: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: String?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            Json.get(
                    view,
                    specialitiesRepository.get(
                            number,
                            name,
                            idSpeciality
                    )
            )

    @GetMapping(value = ["one-by-user{-view}"])
    fun getOneByUser(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?,
            @RequestParam(
                    value = "email",
                    required = false) email: String?,
            @RequestParam(
                    value = "phone_number",
                    required = false) phoneNumber: String?
    ) =
            Json.get(
                    view,
                    specialitiesRepository.get(
                            usersRepository.get(
                                    email,
                                    phoneNumber,
                                    idUser
                            )
                    )
            )

    @GetMapping(value = ["all{-view}"])
    fun getAll(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "all_records",
                    required = false) allRecords: Boolean?,
            @RequestParam(
                    value = "id_branch",
                    required = false) idBranch: Int?,
            @RequestParam(
                    value = "number_branch",
                    required = false) numberBranch: String?,
            @RequestParam(
                    value = "name_branch",
                    required = false) nameBranch: String?
    ) =
            Json.get(
                    view,
                    specialitiesRepository.getAll(
                            allRecords,
                            idBranch ?: branchesService.get(
                                    number = numberBranch,
                                    name = nameBranch
                            ).idBranch
                    )
            )


    /** ============================== POST requests ============================== */


    @PostMapping(value = ["one{-view}"])
    fun add(
            @PathVariable(value = "-view") view: String,
            @RequestBody newSpeciality: Speciality
    ) =
            Json.get(
                    view,
                    specialitiesRepository.add(
                            newSpeciality.fixInitializedAdd(
                                    branchesService
                            ).number,
                            newSpeciality.name,
                            newSpeciality.branch.idBranch
                    )
            )


    /** ============================== PUT requests ============================== */


    @PutMapping(value = ["one{-view}"])
    fun set(
            @PathVariable(value = "-view") view: String,
            @RequestBody changedSpeciality: Speciality,
            @RequestParam(
                    value = "id_speciality",
                    required = false) idSpeciality: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: String?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            Json.get(
                    view,
                    specialitiesRepository.set(
                            changedSpeciality.fixInitializedSet(
                                    specialitiesRepository,
                                    branchesService
                            ),
                            number,
                            name,
                            idSpeciality
                    )
            )


    /** ============================== DELETE requests ============================== */


    @DeleteMapping(value = ["one{-view}"])
    fun delete(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "id_speciality",
                    required = false) idSpeciality: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: String?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            Json.get(
                    view,
                    specialitiesRepository.delete(
                            number,
                            name,
                            idSpeciality
                    )
            )

    @DeleteMapping(value = ["all-by-branch{-view}"])
    fun deleteAll(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "id_branch",
                    required = false) idBranch: Int?,
            @RequestParam(
                    value = "number_branch",
                    required = false) numberBranch: String?,
            @RequestParam(
                    value = "name_branch",
                    required = false) nameBranch: String?
    ) =
            Json.get(
                    view,
                    specialitiesRepository.deleteAll(
                            branchesService.get(
                                    idBranch,
                                    numberBranch,
                                    nameBranch
                            )
                    )
            )

}