package skalii.restful.onaftpostgraduatestudiesserver.service.impl


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service

import skalii.restful.onaftpostgraduatestudiesserver.entity.Section
import skalii.restful.onaftpostgraduatestudiesserver.repository.SectionsRepository
import skalii.restful.onaftpostgraduatestudiesserver.repository.UsersRepository
import skalii.restful.onaftpostgraduatestudiesserver.service.SectionsService


@Service
class SectionsServiceImpl : SectionsService {

    @Autowired
    private lateinit var sectionsRepository: SectionsRepository

    @Autowired //todo change userRepository
    private lateinit var usersRepository: UsersRepository

    override fun get(
            idSection: Int?,
            number: Int?,
            title: String?,
            idUser: Int?,
            idContactInfo: Int?,
            phoneNumberUser: String?,
            emailUser: String?
    ) =
            sectionsRepository.find(
                    idSection,
                    idUser ?: usersRepository.get(
                            emailUser,
                            phoneNumberUser,
                            idContactInfo = idContactInfo
                    ).idUser,
                    number,
                    title
            )

    override fun getAll(
            idUser: Int?,
            idContactInfo: Int?,
            phoneNumberUser: String?,
            emailUser: String?
    ) =
            sectionsRepository.findAllByUser(
                    idUser ?: usersRepository.get(
                            emailUser,
                            phoneNumberUser,
                            idContactInfo = idContactInfo
                    ).idUser
            )

    override fun save(
            httpMethod: HttpMethod,
            newSection: Section,
            findByNumber: Int?,
            findByTitle: String?,
            findByIdUser: Int?,
            findByIdContactInfo: Int?,
            findByPhoneNumberUser: String?,
            findByEmailUser: String?
    ) =
            sectionsRepository.run {
                val foundIdUser = findByIdUser ?: usersRepository.get(
                        findByEmailUser,
                        findByPhoneNumberUser,
                        idContactInfo = findByIdContactInfo
                ).idUser
                when {
                    httpMethod.matches("POST") -> {
                        add(
                                newSection,
                                foundIdUser
                        )
                    }
                    httpMethod.matches("PUT") -> {
                        set(
                                newSection,
                                findByNumber,
                                findByTitle,
                                foundIdUser
                        )
                    }
                    else -> {
                        find()
                    }
                }
            }

    override fun delete(
            idSection: Int?,
            number: Int?,
            title: String?,
            idUser: Int?,
            idContactInfo: Int?,
            phoneNumberUser: String?,
            emailUser: String?
    ) =
            sectionsRepository.run {
                remove(
                        idSection ?: find(
                                idUser = idUser ?: usersRepository.get(
                                        emailUser,
                                        phoneNumberUser,
                                        idContactInfo = idContactInfo
                                ).idUser,
                                number = number,
                                title = title
                        ).idSection
                )
            }

    override fun deleteAll(
            idUser: Int?,
            idContactInfo: Int?,
            phoneNumberUser: String?,
            emailUser: String?
    ) =
            sectionsRepository.removeAllByUser(
                    idUser ?: usersRepository.get(
                            emailUser,
                            phoneNumberUser,
                            idContactInfo = idContactInfo
                    ).idUser
            )

}