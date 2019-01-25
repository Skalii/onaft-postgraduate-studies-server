package skalii.restful.onaftpostgraduatestudiesserver.service


import org.springframework.http.HttpMethod

import skalii.restful.onaftpostgraduatestudiesserver.entity.Faculty


interface FacultiesService {

    fun get(
            idFaculty: Int? = null,
            name: String? = null,
            idDepartment: Int? = null,
            nameDepartment: String? = null,
            idUser: Int? = null,
            idContactInfo: Int? = null,
            phoneNumberUser: String? = null,
            emailUser: String? = null
    ): Faculty

    fun getAll(
            idInstitute: Int? = null,
            nameInstitute: String? = null,
            allRecords: Boolean? = false
    ): MutableList<Faculty>

    fun save(
            httpMethod: HttpMethod,
            newFaculty: Faculty,
            findByName: String? = null
    ): Faculty

    fun delete(
            idFaculty: Int? = null,
            name: String? = null
    ): Faculty

}