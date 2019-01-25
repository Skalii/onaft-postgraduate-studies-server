package skalii.restful.onaftpostgraduatestudiesserver.service


import org.springframework.http.HttpMethod

import skalii.restful.onaftpostgraduatestudiesserver.entity.Degree


interface DegreesService {

    fun get(
            idDegree: Int? = null,
            name: String? = null,
            branch: String? = null,
            idUser: Int? = null,
            idContactInfo: Int? = null,
            phoneNumberUser: String? = null,
            emailUser: String? = null
    ): Degree

    fun getAll(
            name: String? = null,
            branch: String? = null,
            allRecords: Boolean? = false
    ): MutableList<Degree>

    fun save(
            httpMethod: HttpMethod,
            newDegree: Degree,
            findByName: String? = null,
            findByBranch: String? = null
    ): Degree

    fun delete(
            idDegree: Int? = null,
            name: String? = null,
            branch: String? = null
    ): Degree

}