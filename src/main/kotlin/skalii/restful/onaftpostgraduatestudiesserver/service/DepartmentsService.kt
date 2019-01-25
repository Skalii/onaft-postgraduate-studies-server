package skalii.restful.onaftpostgraduatestudiesserver.service


import org.springframework.http.HttpMethod

import skalii.restful.onaftpostgraduatestudiesserver.entity.Department


interface DepartmentsService {

    fun get(
            idDepartment: Int? = null,
            name: String? = null,
            idUser: Int? = null,
            idContactInfo: Int? = null,
            phoneNumberUser: String? = null,
            emailUser: String? = null
    ): Department

    fun getAll(
            idInstitute: Int? = null,
            nameInstitute: String? = null,
            idFaculty: Int? = null,
            nameFaculty: String? = null,
            allRecords: Boolean? = false
    ): MutableList<Department>

    fun save(
            httpMethod: HttpMethod,
            newDepartment: Department,
            findByName: String? = null
    ): Department

    fun delete(
            idDepartment: Int? = null,
            name: String? = null
    ): Department

}