package skalii.restful.onaftpostgraduatestudiesserver.service.impl


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service

import skalii.restful.onaftpostgraduatestudiesserver.entity.ContactInfo
import skalii.restful.onaftpostgraduatestudiesserver.repository.ContactInfoRepository
import skalii.restful.onaftpostgraduatestudiesserver.service.ContactInfoService


@Service
class ContactInfoServiceImpl : ContactInfoService {

    @Autowired
    private lateinit var contactInfoRepository: ContactInfoRepository

    override fun get(
            idContactInfo: Int?,
            phoneNumber: String?,
            email: String?,
            idUser: Int?
    ) =
            contactInfoRepository.find(
                    idContactInfo,
                    idUser,
                    phoneNumber,
                    email
            )

    override fun getAll(): MutableList<ContactInfo> = contactInfoRepository.findAll()

    override fun save(
            httpMethod: HttpMethod,
            newContactInfo: ContactInfo,
            findByPhoneNumber: String?,
            findByEmailUser: String?
    ) =
            contactInfoRepository.run {
                when {
                    httpMethod.matches("POST") -> {
                        add(newContactInfo)
                    }
                    httpMethod.matches("PUT") -> {
                        set(
                                newContactInfo,
                                findByPhoneNumber,
                                findByEmailUser
                        )
                    }
                    else -> {
                        find()
                    }
                }
            }

    override fun delete(
            idContactInfo: Int?,
            phoneNumber: String?,
            email: String?,
            idUser: Int?
    ) =
            contactInfoRepository.run {
                remove(
                        find(
                                idContactInfo,
                                idUser,
                                phoneNumber,
                                email
                        )
                )
            }

}