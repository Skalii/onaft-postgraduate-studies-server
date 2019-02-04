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

    @Autowired
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
                    idUser ?: usersRepository.find(
                            idContactInfo = idContactInfo,
                            phoneNumber = phoneNumberUser,
                            email = emailUser
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
                    idUser ?: usersRepository.find(
                            idContactInfo = idContactInfo,
                            phoneNumber = phoneNumberUser,
                            email = emailUser
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
                val foundIdUser = findByIdUser ?: usersRepository.find(
                        idContactInfo = findByIdContactInfo,
                        phoneNumber = findByPhoneNumberUser,
                        email = findByEmailUser
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
                                idUser = idUser ?: usersRepository.find(
                                        idContactInfo = idContactInfo,
                                        phoneNumber = phoneNumberUser,
                                        email = emailUser
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
                    idUser ?: usersRepository.find(
                            idContactInfo = idContactInfo,
                            phoneNumber = phoneNumberUser,
                            email = emailUser
                    ).idUser
            )

}