package skalii.restful.onaftpostgraduatestudiesserver.service.impl


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service

import skalii.restful.onaftpostgraduatestudiesserver.entity.User
import skalii.restful.onaftpostgraduatestudiesserver.repository.BranchesRepository
import skalii.restful.onaftpostgraduatestudiesserver.repository.DegreesRepository
import skalii.restful.onaftpostgraduatestudiesserver.repository.DepartmentsRepository
import skalii.restful.onaftpostgraduatestudiesserver.repository.FacultiesRepository
import skalii.restful.onaftpostgraduatestudiesserver.repository.InstitutesRepository
import skalii.restful.onaftpostgraduatestudiesserver.repository.SpecialitiesRepository
import skalii.restful.onaftpostgraduatestudiesserver.repository.TasksRepository
import skalii.restful.onaftpostgraduatestudiesserver.repository.UsersRepository
import skalii.restful.onaftpostgraduatestudiesserver.service.UsersService


@Service
class UsersServiceImpl : UsersService {

    @Autowired
    private lateinit var usersRepository: UsersRepository

    @Autowired
    private lateinit var branchesRepository: BranchesRepository

    @Autowired
    private lateinit var degreesRepository: DegreesRepository

    @Autowired
    private lateinit var departmentsRepository: DepartmentsRepository

    @Autowired
    private lateinit var facultiesRepository: FacultiesRepository

    @Autowired
    private lateinit var institutesRepository: InstitutesRepository

    @Autowired
    private lateinit var specialitiesRepository: SpecialitiesRepository

    @Autowired
    private lateinit var tasksRepository: TasksRepository

    override fun get(
            idUser: Int?,
            idContactInfo: Int?,
            phoneNumberUser: String?,
            emailUser: String?,
            idSection: Int?,
            idTask: Int?,
            numberTask: Int?,
            titleTask: String?
    ) =
            usersRepository.run {
                var foundUser = find(
                        idUser,
                        idContactInfo,
                        phoneNumberUser,
                        emailUser
                )
                if (foundUser.idUser == 0) {
                    foundUser = find(
                            tasksRepository.find(
                                    idTask,
                                    numberTask,
                                    titleTask,
                                    idSection
                            ).section.user.idUser
                    )
                }
                return@run foundUser
            }

    override fun getAll(
            allRecords: Boolean?,
            idDegree: Int?,
            nameDegree: String?,
            branchDegree: String?,
            idBranch: Int?,
            numberBranch: String?,
            nameBranch: String?,
            idSpeciality: Int?,
            numberSpeciality: String?,
            nameSpeciality: String?,
            idDepartment: Int?,
            nameDepartment: String?,
            idFaculty: Int?,
            nameFaculty: String?,
            idInstitute: Int?,
            nameInstitute: String?
    ) =
            usersRepository.run {
                if (idDegree == null
                        && nameDegree == null
                        && branchDegree == null
                        && idBranch == null
                        && numberBranch == null
                        && nameBranch == null
                        && idSpeciality == null
                        && numberSpeciality == null
                        && nameSpeciality == null
                        && idDepartment == null
                        && nameDepartment == null
                        && idFaculty == null
                        && nameFaculty == null
                        && idInstitute == null
                        && nameInstitute == null) {
                    findAll(allRecords = true)
                } else {
                    findAll(
                            allRecords,
                            idDegree ?: degreesRepository.find(
                                    name = nameDegree,
                                    branch = branchDegree
                            ).idDegree,
                            idBranch ?: branchesRepository.find(
                                    number = numberBranch,
                                    name = nameBranch
                            ).idBranch,
                            idSpeciality ?: specialitiesRepository.find(
                                    number = numberSpeciality,
                                    name = nameSpeciality
                            ).idSpeciality,
                            idDepartment ?: departmentsRepository.find(
                                    name = nameDepartment
                            ).idDepartment,
                            idFaculty ?: facultiesRepository.find(
                                    name = nameFaculty
                            ).idFaculty,
                            idInstitute ?: institutesRepository.find(
                                    name = nameInstitute
                            ).idInstitute
                    )
                }
            }

    override fun save(
            httpMethod: HttpMethod,
            newUser: User,
            passwordHash: String?,
            findByIdContactInfo: Int?,
            findByPhoneNumberUser: String?,
            findByEmailUser: String?
    ) =
            usersRepository.run {
                when {
                    httpMethod.matches("POST") -> {
                        add(newUser, passwordHash!!)
                    }
                    httpMethod.matches("PUT") -> {
                        set(
                                newUser,
                                passwordHash,
                                findByIdContactInfo,
                                findByPhoneNumberUser,
                                findByEmailUser
                        )
                    }
                    else -> {
                        find()
                    }
                }
            }

    override fun delete(
            idUser: Int?,
            idContactInfo: Int?,
            phoneNumberUser: String?,
            emailUser: String?
    ) =
            usersRepository.run {
                remove(
                        idUser ?: find(
                                idContactInfo = idContactInfo,
                                phoneNumber = phoneNumberUser,
                                email = emailUser
                        ).idUser
                )
            }

    override fun decryptPassword(
            idUser: Int?,
            idContactInfo: Int?,
            phoneNumberUser: String?,
            emailUser: String?
    ) =
            usersRepository.run {
                decrypt(
                        idUser ?: usersRepository.find(
                                idContactInfo = idContactInfo,
                                phoneNumber = phoneNumberUser,
                                email = emailUser
                        ).idUser
                )
            }

}