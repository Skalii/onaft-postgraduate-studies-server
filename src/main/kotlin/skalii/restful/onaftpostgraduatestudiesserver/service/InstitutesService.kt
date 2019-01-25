package skalii.restful.onaftpostgraduatestudiesserver.service


import org.springframework.http.HttpMethod

import skalii.restful.onaftpostgraduatestudiesserver.entity.Institute


interface InstitutesService {

    fun get(
            idInstitute: Int? = null,
            name: String? = null,
            idDepartment: Int? = null,
            nameDepartment: String? = null,
            idUser: Int? = null,
            idContactInfo: Int? = null,
            phoneNumberUser: String? = null,
            emailUser: String? = null
    ): Institute

    fun getAll(): MutableList<Institute>

    fun save(
            httpMethod: HttpMethod,
            newInstitute: Institute,
            findByName: String? = null
    ): Institute

    fun delete(
            idInstitute: Int? = null,
            name: String? = null
    ): Institute

}