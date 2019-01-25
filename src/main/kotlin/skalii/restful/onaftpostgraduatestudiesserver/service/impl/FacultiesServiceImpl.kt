package skalii.restful.onaftpostgraduatestudiesserver.service.impl


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service

import skalii.restful.onaftpostgraduatestudiesserver.entity.Faculty
import skalii.restful.onaftpostgraduatestudiesserver.repository.DepartmentsRepository
import skalii.restful.onaftpostgraduatestudiesserver.repository.FacultiesRepository
import skalii.restful.onaftpostgraduatestudiesserver.repository.UsersRepository
import skalii.restful.onaftpostgraduatestudiesserver.service.FacultiesService


@Service
class FacultiesServiceImpl : FacultiesService {

    @Autowired
    private lateinit var facultiesRepository: FacultiesRepository

    @Autowired
    private lateinit var departmentsRepository: DepartmentsRepository

    @Autowired //todo change userRepository
    private lateinit var usersRepository: UsersRepository

    override fun get(
            idFaculty: Int?,
            name: String?,
            idDepartment: Int?,
            nameDepartment: String?,
            idUser: Int?,
            idContactInfo: Int?,
            phoneNumberUser: String?,
            emailUser: String?
    ) =
            facultiesRepository.run {
                if (idFaculty != null
                        || name != null) {
                    find(
                            idFaculty,
                            name
                    )
                } else if (idDepartment != null
                        || nameDepartment != null) {
                    findByDepartment(
                            departmentsRepository.find(
                                    idDepartment,
                                    name
                            )
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
            allRecords: Boolean?
    ) =
            facultiesRepository.run {
                if (idInstitute == null) {
                    findAll(allRecords = true)
                } else {
                    findAll(
                            idInstitute, //todo ?: institutesService.get(name = nameInstitute).idInstitute
                            allRecords
                    )
                }
            }

    override fun save(
            httpMethod: HttpMethod,
            newFaculty: Faculty,
            findByName: String?
    ) =
            facultiesRepository.run {
                when {
                    httpMethod.matches("POST") -> {
                        add(newFaculty)
                    }
                    httpMethod.matches("PUT") -> {
                        set(
                                newFaculty,
                                findByName
                        )
                    }
                    else -> {
                        find()
                    }
                }
            }

    override fun delete(
            idFaculty: Int?,
            name: String?
    ) =
            facultiesRepository.run {
                remove(
                        find(
                                idFaculty,
                                name
                        )
                )
            }

}