package skalii.restful.onaftpostgraduatestudiesserver.service.impl


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service

import skalii.restful.onaftpostgraduatestudiesserver.entity.Faculty
import skalii.restful.onaftpostgraduatestudiesserver.repository.DepartmentsRepository
import skalii.restful.onaftpostgraduatestudiesserver.repository.FacultiesRepository
import skalii.restful.onaftpostgraduatestudiesserver.repository.InstitutesRepository
import skalii.restful.onaftpostgraduatestudiesserver.repository.UsersRepository
import skalii.restful.onaftpostgraduatestudiesserver.service.FacultiesService


@Service
class FacultiesServiceImpl : FacultiesService {

    @Autowired
    private lateinit var facultiesRepository: FacultiesRepository

    @Autowired
    private lateinit var departmentsRepository: DepartmentsRepository

    @Autowired
    private lateinit var institutesRepository: InstitutesRepository

    @Autowired
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
                    find(
                            departmentsRepository.find(
                                    idDepartment,
                                    name
                            ).faculty.idFaculty
                    )
                } else {
                    find(
                            usersRepository.find(
                                    idUser,
                                    idContactInfo,
                                    phoneNumberUser,
                                    emailUser
                            ).department.faculty.idFaculty
                    )
                }
            }

    override fun getAll(
            idInstitute: Int?,
            nameInstitute: String?,
            allRecords: Boolean?
    ) =
            facultiesRepository.run {
                if (idInstitute == null
                        && nameInstitute == null) {
                    findAll(allRecords = true)
                } else {
                    findAll(
                            idInstitute ?: institutesRepository.find(name = nameInstitute).idInstitute,
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
                        idFaculty ?: find(
                                name = name
                        ).idFaculty
                )
            }

}