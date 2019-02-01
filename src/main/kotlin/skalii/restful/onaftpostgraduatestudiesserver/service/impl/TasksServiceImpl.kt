package skalii.restful.onaftpostgraduatestudiesserver.service.impl


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service

import skalii.restful.onaftpostgraduatestudiesserver.entity.Task
import skalii.restful.onaftpostgraduatestudiesserver.repository.SectionsRepository
import skalii.restful.onaftpostgraduatestudiesserver.repository.TasksRepository
import skalii.restful.onaftpostgraduatestudiesserver.repository.UsersRepository
import skalii.restful.onaftpostgraduatestudiesserver.service.TasksService


@Service
class TasksServiceImpl : TasksService {

    @Autowired
    private lateinit var tasksRepository: TasksRepository

    @Autowired
    private lateinit var sectionsRepository: SectionsRepository

    @Autowired //todo change usersRepository
    private lateinit var usersRepository: UsersRepository

    override fun get(
            idTask: Int?,
            number: Int?,
            title: String?,
            idSection: Int?,
            numberSection: Int?,
            titleSection: String?,
            idUser: Int?,
            idContactInfo: Int?,
            phoneNumberUser: String?,
            emailUser: String?
    ) =
            tasksRepository.run {
                val foundIdUser = idUser ?: findIdUser(
                        idContactInfo = idContactInfo,
                        phoneNumber = phoneNumberUser,
                        email = emailUser
                )
                find(
                        idTask,
                        number,
                        title,
                        idSection ?: findIdSection(
                                idUser = foundIdUser,
                                number = numberSection,
                                title = titleSection
                        ),
                        foundIdUser
                )
            }

    override fun getAll(
            idSection: Int?,
            numberSection: Int?,
            titleSection: String?,
            idUser: Int?,
            idContactInfo: Int?,
            phoneNumberUser: String?,
            emailUser: String?
    ) =
            tasksRepository.run {
                val foundIdUser = idUser ?: findIdUser(
                        idContactInfo = idContactInfo,
                        phoneNumber = phoneNumberUser,
                        email = emailUser
                )
                findAll(
                        idSection ?: findIdSection(
                                idUser = foundIdUser,
                                number = numberSection,
                                title = titleSection
                        ),
                        foundIdUser
                )
            }

    override fun save(
            httpMethod: HttpMethod,
            newTask: Task,
            findByNumber: Int?,
            findByTitle: String?,
            findByIdSection: Int?,
            findByNumberSection: Int?,
            findByTitleSection: String?,
            findByIdUser: Int?,
            findByIdContactInfo: Int?,
            findByPhoneNumberUser: String?,
            findByEmailUser: String?

    ) =
            tasksRepository.run {
                val foundIdUser = findByIdUser ?: findIdUser(
                        idContactInfo = findByIdContactInfo,
                        phoneNumber = findByPhoneNumberUser,
                        email = findByEmailUser
                )
                val foundIdSection = findByIdSection ?: findIdSection(
                        idUser = foundIdUser,
                        number = findByNumberSection,
                        title = findByTitleSection
                )
                when {
                    httpMethod.matches("POST") -> {
                        add(
                                newTask,
                                foundIdSection!!
                        )
                    }
                    httpMethod.matches("PUT") -> {
                        set(
                                newTask,
                                foundIdSection,
                                foundIdUser,
                                findByNumber,
                                findByTitle
                        )
                    }
                    else -> {
                        find()
                    }
                }
            }

    override fun saveMark(
            whoMarked: String,
            newTask: Task,
            findByNumber: Int?,
            findByTitle: String?,
            findByIdSection: Int?,
            findByNumberSection: Int?,
            findByTitleSection: String?,
            findByIdStudent: Int?,
            findByIdContactInfoStudent: Int?,
            findByPhoneNumberStudent: String?,
            findByEmailStudent: String?,
            findByIdInstructor: Int?,
            findByIdContactInfoInstructor: Int?,
            findByPhoneNumberInstructor: String?,
            findByEmailInstructor: String?
    ) =
            tasksRepository.run {
                val foundIdStudent = findByIdStudent ?: findIdUser(
                        idContactInfo = findByIdContactInfoStudent,
                        phoneNumber = findByPhoneNumberStudent,
                        email = findByEmailStudent
                )
                val foundIdSection = findByIdSection ?: findIdSection(
                        idUser = foundIdStudent,
                        number = findByNumberSection,
                        title = findByTitleSection
                )
                when (whoMarked) {
                    "student" -> {
                        setMarkStudent(
                                newTask,
                                foundIdSection,
                                foundIdStudent,
                                findByNumber,
                                findByTitle
                        )
                    }
                    "instructor" -> {
                        val foundIdInstructor = findByIdInstructor ?: findIdUser(
                                idContactInfo = findByIdContactInfoInstructor,
                                phoneNumber = findByPhoneNumberInstructor,
                                email = findByEmailInstructor
                        )
                        if (usersRepository.get(
                                        idUser = foundIdStudent
                                ).studyInfo!!.instructor.idUser == foundIdInstructor) {
                            setMarkInstructor(
                                    newTask,
                                    foundIdSection,
                                    foundIdStudent,
                                    findByNumber,
                                    findByTitle
                            )
                        } else {
                            find()
                        }
                    }
                    else -> {
                        find()
                    }
                }
            }

    override fun delete(
            idTask: Int?,
            number: Int?,
            title: String?,
            idSection: Int?,
            numberSection: Int?,
            titleSection: String?,
            idUser: Int?,
            idContactInfo: Int?,
            phoneNumberUser: String?,
            emailUser: String?
    ) =
            tasksRepository.run {
                val foundIdUser = idUser ?: findIdUser(
                        idContactInfo = idContactInfo,
                        phoneNumber = phoneNumberUser,
                        email = emailUser
                )
                remove(
                        idTask ?: find(
                                number = number,
                                title = title,
                                idSection = idSection ?: findIdSection(
                                        idUser = foundIdUser,
                                        number = numberSection,
                                        title = titleSection
                                ),
                                idUser = foundIdUser
                        ).idTask
                )
            }

    override fun deleteAll(
            idSection: Int?,
            number: Int?,
            title: String?,
            idUser: Int?,
            idContactInfo: Int?,
            phoneNumberUser: String?,
            emailUser: String?
    ) =
            tasksRepository.run {
                removeAllBySection(
                        idSection ?: findIdSection(
                                idUser = idUser ?: findIdUser(
                                        idContactInfo = idContactInfo,
                                        phoneNumber = phoneNumberUser,
                                        email = emailUser
                                ),
                                number = number,
                                title = title
                        )!!
                )
            }


    private fun findIdSection(
            idSection: Int? = null,
            idUser: Int? = null,
            number: Int? = null,
            title: String? = null
    ): Int? {
        val foundIdSection = idSection ?: sectionsRepository.find(
                idSection,
                idUser,
                number,
                title
        ).idSection
        return if (foundIdSection == 0) null else foundIdSection
    }

    private fun findIdUser(
            idUser: Int? = null,
            idContactInfo: Int? = null,
            phoneNumber: String? = null,
            email: String? = null
    ): Int? {
        val foundIdUser = idUser ?: usersRepository.get(
                email,
                phoneNumber,
                idUser,
                idContactInfo
        ).idUser
        return if (foundIdUser == 0) null else foundIdUser
    }

}