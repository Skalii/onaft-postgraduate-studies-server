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

import skalii.restful.onaftpostgraduatestudiesserver.entity.StudyInfo
import skalii.restful.onaftpostgraduatestudiesserver.service.StudyInfoService
import skalii.restful.onaftpostgraduatestudiesserver.view.Json.Companion.get


@RequestMapping(
        value = ["/api/study-info"],
        produces = [APPLICATION_JSON_UTF8_VALUE])
@RestController
class StudyInfoRestController {

    @Autowired
    private lateinit var studyInfoService: StudyInfoService


    /** ============================== GET requests ============================== */


    @GetMapping(value = ["my{-view}"])
    fun getMy(
            @PathVariable(value = "-view") view: String,
            @AuthenticationPrincipal authUser: UserDetails
    ) =
            get(
                    view,
                    studyInfoService.get(
                            emailUser = authUser.username
                    )
            )

    @GetMapping(value = ["one{-view}"])
    fun getOne(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "id_study_info",
                    required = false) idStudyInfo: Int?,
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
                    studyInfoService.get(
                            idStudyInfo,
                            idUser,
                            idContactInfo,
                            phoneNumber,
                            email
                    )
            )

    @GetMapping(value = ["all{-view}"])
    fun getAll(@PathVariable(value = "-view") view: String) =
            get(
                    view,
                    studyInfoService.getAll()
            )


    /** ============================== POST/PUT requests ============================== */


    @PutMapping(value = ["my{-view}"])
    fun saveMy(
            httpMethod: HttpMethod,
            @PathVariable(value = "-view") view: String,
            @RequestBody newStudyInfo: StudyInfo,
            @AuthenticationPrincipal authUser: UserDetails
    ) =
            get(
                    view,
                    studyInfoService.save(
                            httpMethod,
                            newStudyInfo,
                            findByEmailUser = authUser.username
                    )
            )

    @RequestMapping(
            value = ["one{-view}"],
            method = [POST, PUT])
    fun saveOne(
            httpMethod: HttpMethod,
            @PathVariable(value = "-view") view: String,
            @RequestBody newStudyInfo: StudyInfo,
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
                    studyInfoService.save(
                            httpMethod,
                            newStudyInfo,
                            findByIdUser,
                            findByIdContactInfo,
                            findByPhoneNumber,
                            findByEmail
                    )
            )


    /** ============================== DELETE requests ============================== */


    @DeleteMapping(value = ["one{-view}"])
    fun deleteOne(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "id_study_info",
                    required = false) idStudyInfo: Int?,
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
                    studyInfoService.delete(
                            idStudyInfo,
                            idUser,
                            idContactInfo,
                            phoneNumber,
                            email
                    )
            )

}