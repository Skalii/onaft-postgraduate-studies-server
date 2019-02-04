package skalii.restful.onaftpostgraduatestudiesserver.service.impl


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service

import skalii.restful.onaftpostgraduatestudiesserver.entity.StudyInfo
import skalii.restful.onaftpostgraduatestudiesserver.repository.StudyInfoRepository
import skalii.restful.onaftpostgraduatestudiesserver.repository.UsersRepository
import skalii.restful.onaftpostgraduatestudiesserver.service.StudyInfoService


@Service
class StudyInfoServiceImpl : StudyInfoService {

    @Autowired
    private lateinit var studyInfoRepository: StudyInfoRepository

    @Autowired
    private lateinit var usersRepository: UsersRepository

    override fun get(
            idStudyInfo: Int?,
            idUser: Int?,
            idContactInfo: Int?,
            phoneNumberUser: String?,
            emailUser: String?
    ) =
            studyInfoRepository.find(
                    idStudyInfo,
                    idUser ?: usersRepository.find(
                            idContactInfo = idContactInfo,
                            phoneNumber = phoneNumberUser,
                            email = emailUser
                    ).idUser
            )

    override fun getAll() = studyInfoRepository.findAll()

    override fun save(
            httpMethod: HttpMethod,
            newStudyInfo: StudyInfo,
            findByIdUser: Int?,
            findByIdContactInfo: Int?,
            findByPhoneNumberUser: String?,
            findByEmailUser: String?
    ) =
            studyInfoRepository.run {
                when {
                    httpMethod.matches("POST") -> {
                        add(newStudyInfo)
                    }
                    httpMethod.matches("PUT") -> {
                        set(
                                newStudyInfo,
                                findByIdUser ?: usersRepository.find(
                                        idContactInfo = findByIdContactInfo,
                                        phoneNumber = findByPhoneNumberUser,
                                        email = findByEmailUser
                                ).idUser
                        )
                    }
                    else -> {
                        find()
                    }
                }
            }

    override fun delete(
            idStudyInfo: Int?,
            idUser: Int?,
            idContactInfo: Int?,
            phoneNumberUser: String?,
            emailUser: String?
    ) =
            studyInfoRepository.run {
                remove(
                        idStudyInfo ?: find(
                                idUser = idUser ?: usersRepository.find(
                                        idContactInfo = idContactInfo,
                                        phoneNumber = phoneNumberUser,
                                        email = emailUser
                                ).idUser
                        ).idStudyInfo
                )
            }

}