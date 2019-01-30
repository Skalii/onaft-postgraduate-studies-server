package skalii.restful.onaftpostgraduatestudiesserver.service


import org.springframework.http.HttpMethod

import skalii.restful.onaftpostgraduatestudiesserver.entity.Section


interface SectionsService {

    fun get(
            idSection: Int? = null,
            number: Int? = null,
            title: String? = null,
            idUser: Int? = null,
            idContactInfo: Int? = null,
            phoneNumberUser: String? = null,
            emailUser: String? = null,
            idTask: Int? = null,
            numberTask: Int? = null,
            titleTask: String? = null
    ): Section

    fun getAll(
            idUser: Int? = null,
            idContactInfo: Int? = null,
            phoneNumberUser: String? = null,
            emailUser: String? = null
    ): MutableList<Section>

    fun save(
            httpMethod: HttpMethod,
            newSection: Section,
            findByNumber: Int? = null,
            findByTitle: String? = null,
            findByIdUser: Int? = null,
            findByIdContactInfo: Int? = null,
            findByPhoneNumberUser: String? = null,
            findByEmailUser: String? = null
    ): Section

    fun delete(
            idSection: Int? = null,
            number: Int? = null,
            title: String? = null,
            idUser: Int? = null,
            idContactInfo: Int? = null,
            phoneNumberUser: String? = null,
            emailUser: String? = null
    ): Section

    fun deleteAll(
            idUser: Int? = null,
            idContactInfo: Int? = null,
            phoneNumberUser: String? = null,
            emailUser: String? = null
    ): MutableList<Section>

}