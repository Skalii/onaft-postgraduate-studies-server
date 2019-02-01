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

import skalii.restful.onaftpostgraduatestudiesserver.entity.Task
import skalii.restful.onaftpostgraduatestudiesserver.service.TasksService
import skalii.restful.onaftpostgraduatestudiesserver.view.Json.Companion.get


@RequestMapping(
        value = ["/api/tasks"],
        produces = [APPLICATION_JSON_UTF8_VALUE])
@RestController
class TasksRestController {

    @Autowired
    private lateinit var tasksService: TasksService


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
                    required = false) title: String?,
            @RequestParam(
                    value = "number_section",
                    required = false) numberSection: Int?,
            @RequestParam(
                    value = "title_section",
                    required = false) titleSection: String?
    ) =
            get(
                    view,
                    tasksService.get(
                            number = number,
                            title = title,
                            numberSection = numberSection,
                            titleSection = titleSection,
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
                    tasksService.getAll(
                            emailUser = authUser.username
                    )
            )

    @GetMapping(value = ["my-all-by-section{-view}"])
    fun getMyAllBySection(
            @PathVariable(value = "-view") view: String,
            @AuthenticationPrincipal authUser: UserDetails,
            @RequestParam(
                    value = "number_section",
                    required = false) numberSection: Int?,
            @RequestParam(
                    value = "title_section",
                    required = false) titleSection: String?
    ) =
            get(
                    view,
                    tasksService.getAll(
                            numberSection = numberSection,
                            titleSection = titleSection,
                            emailUser = authUser.username
                    )
            )

    @GetMapping(value = ["one{-view}"])
    fun getOne(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "id_task",
                    required = false) idTask: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?,
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?,
            @RequestParam(
                    value = "number_section",
                    required = false) numberSection: Int?,
            @RequestParam(
                    value = "title_section",
                    required = false) titleSection: String?,
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
                    tasksService.get(
                            idTask,
                            number,
                            title,
                            idSection,
                            numberSection,
                            titleSection,
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
                    value = "id_section",
                    required = false) idSection: Int?,
            @RequestParam(
                    value = "number_section",
                    required = false) numberSection: Int?,
            @RequestParam(
                    value = "title_section",
                    required = false) titleSection: String?,
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
                    tasksService.getAll(
                            idSection,
                            numberSection,
                            titleSection,
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
            @AuthenticationPrincipal authUser: UserDetails,
            @RequestBody newTask: Task,
            @RequestParam(
                    value = "find_by_number",
                    required = false) findByNumber: Int?,
            @RequestParam(
                    value = "find_by_title",
                    required = false) findByTitle: String?,
            @RequestParam(
                    value = "find_by_id_section",
                    required = false) findByIdSection: Int?,
            @RequestParam(
                    value = "find_by_number_section",
                    required = false) findByNumberSection: Int?,
            @RequestParam(
                    value = "find_by_title_section",
                    required = false) findByTitleSection: String?
    ) =
            get(
                    view,
                    tasksService.save(
                            httpMethod,
                            newTask,
                            findByNumber,
                            findByTitle,
                            findByIdSection,
                            findByNumberSection,
                            findByTitleSection,
                            findByEmailUser = authUser.username
                    )
            )

    @RequestMapping(
            value = ["one{-view}"],
            method = [POST, PUT])
    fun saveOne(
            httpMethod: HttpMethod,
            @PathVariable(value = "-view") view: String,
            @RequestBody newTask: Task,
            @RequestParam(
                    value = "find_by_number",
                    required = false) findByNumber: Int?,
            @RequestParam(
                    value = "find_by_title",
                    required = false) findByTitle: String?,
            @RequestParam(
                    value = "find_by_id_section",
                    required = false) findByIdSection: Int?,
            @RequestParam(
                    value = "find_by_number_section",
                    required = false) findByNumberSection: Int?,
            @RequestParam(
                    value = "find_by_title_section",
                    required = false) findByTitleSection: String?,
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
            get(
                    view,
                    tasksService.save(
                            httpMethod,
                            newTask,
                            findByNumber,
                            findByTitle,
                            findByIdSection,
                            findByNumberSection,
                            findByTitleSection,
                            findByIdUser,
                            findByIdContactInfo,
                            findByPhoneNumberUser,
                            findByEmailUser
                    )

            )

    @PutMapping(value = ["mark-{who}{-view}"])
    fun saveMark(
            @PathVariable(value = "who") whoMarked: String,
            @PathVariable(value = "-view") view: String,
            @RequestBody newTask: Task,
            @AuthenticationPrincipal authUser: UserDetails,
            @RequestParam(
                    value = "find_by_number",
                    required = false) findByNumber: Int?,
            @RequestParam(
                    value = "find_by_title",
                    required = false) findByTitle: String?,
            @RequestParam(
                    value = "find_by_id_section",
                    required = false) findByIdSection: Int?,
            @RequestParam(
                    value = "find_by_number_section",
                    required = false) findByNumberSection: Int?,
            @RequestParam(
                    value = "find_by_title_section",
                    required = false) findByTitleSection: String?,
            @RequestParam(
                    value = "find_by_id_student",
                    required = false) findByIdStudent: Int?,
            @RequestParam(
                    value = "find_by_id_contact_info_student",
                    required = false) findByIdContactInfoStudent: Int?,
            @RequestParam(
                    value = "find_by_phone_number_student",
                    required = false) findByPhoneNumberStudent: String?,
            @RequestParam(
                    value = "find_by_email_student",
                    required = false) findByEmailStudent: String?,
            @RequestParam(
                    value = "find_by_id_instructor",
                    required = false) findByIdInstructor: Int?,
            @RequestParam(
                    value = "find_by_id_contact_info_instructor",
                    required = false) findByIdContactInfoInstructor: Int?,
            @RequestParam(
                    value = "find_by_phone_number_instructor",
                    required = false) findByPhoneNumberInstructor: String?,
            @RequestParam(
                    value = "find_by_email_instructor",
                    required = false) findByEmailInstructor: String?
    ) =
            tasksService.saveMark(
                    whoMarked,
                    newTask,
                    findByNumber,
                    findByTitle,
                    findByIdSection,
                    findByNumberSection,
                    findByTitleSection,
                    findByIdStudent,
                    findByIdContactInfoStudent,
                    findByPhoneNumberStudent,
                    findByEmailStudent,
                    findByIdInstructor,
                    findByIdContactInfoInstructor,
                    findByPhoneNumberInstructor,
                    findByEmailInstructor
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
                    required = false) title: String?,
            @RequestParam(
                    value = "number_section",
                    required = false) numberSection: Int?,
            @RequestParam(
                    value = "title_section",
                    required = false) titleSection: String?
    ) =
            get(
                    view,
                    tasksService.delete(
                            number = number,
                            title = title,
                            numberSection = numberSection,
                            titleSection = titleSection,
                            emailUser = authUser.username
                    )
            )

    @DeleteMapping(value = ["my-all-by-section{-view}"])
    fun deleteMyAllBySection(
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
                    tasksService.deleteAll(
                            number = number,
                            title = title,
                            emailUser = authUser.username
                    )
            )

    @DeleteMapping(value = ["one{-view}"])
    fun deleteOne(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "id_task",
                    required = false) idTask: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?,
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?,
            @RequestParam(
                    value = "number_section",
                    required = false) numberSection: Int?,
            @RequestParam(
                    value = "title_section",
                    required = false) titleSection: String?,
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
                    tasksService.delete(
                            idTask,
                            number,
                            title,
                            idSection,
                            numberSection,
                            titleSection,
                            idUser,
                            idContactInfo,
                            phoneNumber,
                            email
                    )
            )

    @GetMapping(value = ["all-by-section{-view}"])
    fun deleteAllBySection(
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
                    tasksService.deleteAll(
                            idSection,
                            number,
                            title,
                            idUser,
                            idContactInfo,
                            phoneNumber,
                            email
                    )
            )

}