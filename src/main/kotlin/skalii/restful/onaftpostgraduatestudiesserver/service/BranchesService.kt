package skalii.restful.onaftpostgraduatestudiesserver.service


import org.springframework.http.HttpMethod

import skalii.restful.onaftpostgraduatestudiesserver.entity.Branch


interface BranchesService {

    fun get(
            idBranch: Int? = null,
            number: String? = null,
            name: String? = null,
            idSpeciality: Int? = null,
            numberSpeciality: String? = null,
            nameSpeciality: String? = null,
            idUser: Int? = null,
            idContactInfo: Int? = null,
            phoneNumberUser: String? = null,
            emailUser: String? = null
    ): Branch

    fun getAll(): MutableList<Branch>

    fun save(
            httpMethod: HttpMethod,
            newBranch: Branch,
            findByNumber: String? = null,
            findByName: String? = null
    ): Branch

    fun delete(
            idBranch: Int? = null,
            number: String? = null,
            name: String? = null,
            idSpeciality: Int? = null,
            numberSpeciality: String? = null,
            nameSpeciality: String? = null
    ): Branch

}