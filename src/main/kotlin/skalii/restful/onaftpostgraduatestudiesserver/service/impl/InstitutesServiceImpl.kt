package skalii.restful.onaftpostgraduatestudiesserver.service.impl


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service

import skalii.restful.onaftpostgraduatestudiesserver.entity.Institute
import skalii.restful.onaftpostgraduatestudiesserver.repository.DepartmentsRepository
import skalii.restful.onaftpostgraduatestudiesserver.repository.InstitutesRepository
import skalii.restful.onaftpostgraduatestudiesserver.repository.UsersRepository
import skalii.restful.onaftpostgraduatestudiesserver.service.InstitutesService


@Service
class InstitutesServiceImpl : InstitutesService {

    @Autowired
    private lateinit var institutesRepository: InstitutesRepository

    @Autowired
    private lateinit var departmentsRepository: DepartmentsRepository

    @Autowired //todo change userRepository
    private lateinit var usersRepository: UsersRepository

    override fun get(
            idInstitute: Int?,
            name: String?,
            idDepartment: Int?,
            nameDepartment: String?,
            idUser: Int?,
            idContactInfo: Int?,
            phoneNumberUser: String?,
            emailUser: String?
    ) =
            institutesRepository.run {
                if (idInstitute != null
                        || name != null) {
                    find(
                            idInstitute,
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

    override fun getAll() =
            institutesRepository.findAll()

    override fun save(
            httpMethod: HttpMethod,
            newInstitute: Institute,
            findByName: String?
    ) =
            institutesRepository.run {
                when {
                    httpMethod.matches("POST") -> {
                        add(newInstitute)
                    }
                    httpMethod.matches("PUT") -> {
                        set(
                                newInstitute,
                                findByName
                        )
                    }
                    else -> find()
                }
            }

    override fun delete(
            idInstitute: Int?,
            name: String?
    ) = institutesRepository.run {
        remove(
                find(
                        idInstitute,
                        name
                )
        )
    }

}