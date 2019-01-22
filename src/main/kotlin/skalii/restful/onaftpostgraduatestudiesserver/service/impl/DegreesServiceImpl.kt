package skalii.restful.onaftpostgraduatestudiesserver.service.impl


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service

import skalii.restful.onaftpostgraduatestudiesserver.entity.Degree
import skalii.restful.onaftpostgraduatestudiesserver.repository.DegreesRepository
import skalii.restful.onaftpostgraduatestudiesserver.repository.UsersRepository
import skalii.restful.onaftpostgraduatestudiesserver.service.DegreesService


@Service
class DegreesServiceImpl : DegreesService {

    @Autowired
    private lateinit var degreesRepository: DegreesRepository

    @Autowired //todo changed userRepository
    private lateinit var usersRepository: UsersRepository

    override fun get(
            idDegree: Int?,
            name: String?,
            branch: String?,
            idUser: Int?,
            idContactInfo: Int?,
            phoneNumber: String?,
            email: String?
    ) =
            if (idDegree != null
                    || ((name != null) && (branch != null))) {
                degreesRepository.find(
                        idDegree,
                        name,
                        branch
                )
            } else {
                degreesRepository.findByUser(
                        usersRepository.get(
                                email,
                                phoneNumber,
                                idUser,
                                idContactInfo
                        )
                )
            }

    override fun getAll(
            name: String?,
            branch: String?,
            allRecords: Boolean?
    ) =
            degreesRepository.run {

                if (name == null
                        && branch == null
                        && allRecords == null) {
                    findAll(allRecords = true)
                } else {
                    findAll(
                            name,
                            branch,
                            allRecords
                    )
                }

            }

    override fun save(
            httpMethod: HttpMethod,
            newDegree: Degree,
            findByName: String?,
            findByBranch: String?
    ) =
            degreesRepository.run {
                when {
                    httpMethod.matches("POST") -> {
                        add(newDegree)
                    }
                    httpMethod.matches("PUT") -> {
                        set(
                                newDegree,
                                findByName,
                                findByBranch
                        )
                    }
                    else -> {
                        find()
                    }
                }
            }

    override fun delete(
            idDegree: Int?,
            name: String?,
            branch: String?
    ) =
            degreesRepository.run {
                remove(
                        find(
                                idDegree,
                                name,
                                branch
                        )
                )
            }

}