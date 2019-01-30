package skalii.restful.onaftpostgraduatestudiesserver.service


import org.springframework.http.HttpMethod

import skalii.restful.onaftpostgraduatestudiesserver.entity.StudyInfo


interface StudyInfoService {

    fun get(
            idStudyInfo: Int? = null,
            idUser: Int? = null,
            idContactInfo: Int? = null,
            phoneNumberUser: String? = null,
            emailUser: String? = null
    ): StudyInfo

    fun getAll(): MutableList<StudyInfo>

    fun save(
            httpMethod: HttpMethod,
            newStudyInfo: StudyInfo,
            findByIdUser: Int? = null,
            findByIdContactInfo: Int? = null,
            findByPhoneNumberUser: String? = null,
            findByEmailUser: String? = null
    ): StudyInfo

    fun delete(
            idStudyInfo: Int? = null,
            idUser: Int? = null,
            idContactInfo: Int? = null,
            phoneNumberUser: String? = null,
            emailUser: String? = null
    ): StudyInfo

}