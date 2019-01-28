package skalii.restful.onaftpostgraduatestudiesserver.service


import org.springframework.http.HttpMethod
import skalii.restful.onaftpostgraduatestudiesserver.entity.ContactInfo


interface ContactInfoService {

    fun get(
            idContactInfo: Int? = null,
            phoneNumber: String? = null,
            email: String? = null,
            idUser: Int? = null
    ): ContactInfo

    fun getAll(): MutableList<ContactInfo>

    fun save(
            httpMethod: HttpMethod,
            newContactInfo: ContactInfo,
            findByPhoneNumber: String? = null,
            findByEmailUser: String? = null
    ): ContactInfo

    fun delete(
            idContactInfo: Int? = null,
            phoneNumber: String? = null,
            email: String? = null,
            idUser: Int? = null
    ): ContactInfo

}