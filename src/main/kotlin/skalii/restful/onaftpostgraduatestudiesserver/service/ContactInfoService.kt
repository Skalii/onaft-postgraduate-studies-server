package skalii.restful.onaftpostgraduatestudiesserver.service


import org.springframework.http.HttpMethod
import skalii.restful.onaftpostgraduatestudiesserver.entity.ContactInfo


interface ContactInfoService {

    fun get(
            idContactInfo: Int? = null,
            idUser: Int? = null,
            phoneNumber: String? = null,
            email: String? = null
    ): ContactInfo

    fun getAll(): MutableList<ContactInfo>

    fun save(
            httpMethod: HttpMethod,
            newContactInfo: ContactInfo,
            findByPhoneNumber: String? = null,
            findByEmail: String? = null
    ): ContactInfo

    fun delete(
            idContactInfo: Int? = null,
            idUser: Int? = null,
            phoneNumber: String? = null,
            email: String? = null
    ): ContactInfo

}