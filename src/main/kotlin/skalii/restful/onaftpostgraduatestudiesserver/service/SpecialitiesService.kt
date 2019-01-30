package skalii.restful.onaftpostgraduatestudiesserver.service


import org.springframework.http.HttpMethod

import skalii.restful.onaftpostgraduatestudiesserver.entity.Speciality


interface SpecialitiesService {

    fun get(
            idSpeciality: Int? = null,
            number: String? = null,
            name: String? = null,
            idUser: Int? = null,
            idContactInfo: Int? = null,
            phoneNumberUser: String? = null,
            emailUser: String? = null
    ): Speciality

    fun getAll(
            idBranch: Int? = null,
            numberBranch: String? = null,
            nameBranch: String? = null,
            allRecords: Boolean? = false
    ): MutableList<Speciality>

    fun save(
            httpMethod: HttpMethod,
            newSpeciality: Speciality,
            findByNumber: String? = null,
            findByName: String? = null
    ): Speciality

    fun delete(
            idSpeciality: Int? = null,
            number: String? = null,
            name: String? = null
    ): Speciality

    fun deleteAll(
            idBranch: Int? = null,
            numberBranch: String? = null,
            nameBranch: String? = null
    ): MutableList<Speciality>

}