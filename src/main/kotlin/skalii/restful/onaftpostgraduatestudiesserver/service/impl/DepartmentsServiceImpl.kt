package skalii.restful.onaftpostgraduatestudiesserver.service.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import skalii.restful.onaftpostgraduatestudiesserver.entity.Department
import skalii.restful.onaftpostgraduatestudiesserver.repository.DepartmentsRepository
import skalii.restful.onaftpostgraduatestudiesserver.repository.UsersRepository
import skalii.restful.onaftpostgraduatestudiesserver.service.DepartmentsService

@Service
class DepartmentsServiceImpl : DepartmentsService {

    @Autowired
    private lateinit var departmentsRepository: DepartmentsRepository

    @Autowired //todo changed userRepository
    private lateinit var usersRepository: UsersRepository

    override fun get(
            idDepartment: Int?,
            name: String?,
            idUser: Int?,
            idContactInfo: Int?,
            phoneNumberUser: String?,
            emailUser: String?
    ) =
            departmentsRepository.run {
                if (idDepartment != null
                        && name != null) {
                    find(
                            idDepartment,
                            name
                    )
                } else {
                    findByUser(
                            usersRepository.get(
                                    emailUser,
                                    phoneNumberUser,
                                    idUser,
                                    idContactInfo
                            )
                    )
                }
            }

    override fun getAll(
            idInstitute: Int?,
            nameInstitute: String?,
            idFaculty: Int?,
            nameFaculty: String?,
            allRecords: Boolean?
    ) =
            departmentsRepository.run {
                if (idInstitute == null
                        && nameInstitute == null
                        && idFaculty == null
                        && nameFaculty == null) {
                    findAll(allRecords = true)
                } else {
                    findAll(
                            idInstitute, //todo ?: institutesService.get(name = nameInstitute).idInstitute
                            idFaculty, //todo ?: facultiesService.get(name = nameFaculty).idFaculty
                            allRecords
                    )
                }
            }

    override fun save(
            httpMethod: HttpMethod,
            newDepartment: Department,
            findByName: String?
    ) =
            departmentsRepository.run {
                when {
                    httpMethod.matches("POST") -> {
                        add(newDepartment)
                    }
                    httpMethod.matches("PUT") -> {
                        set(
                                newDepartment,
                                findByName
                        )
                    }
                    else -> {
                        find()
                    }
                }
            }

    override fun delete(
            idDepartment: Int?,
            name: String?
    ) = departmentsRepository.run {
        remove(
                find(
                        idDepartment,
                        name
                )
        )

    }

}