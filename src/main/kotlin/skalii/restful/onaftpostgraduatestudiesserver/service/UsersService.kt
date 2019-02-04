package skalii.restful.onaftpostgraduatestudiesserver.service


import org.springframework.http.HttpMethod

import skalii.restful.onaftpostgraduatestudiesserver.entity.User


interface UsersService {

    fun get(
            idUser: Int? = null,
            idContactInfo: Int? = null,
            phoneNumberUser: String? = null,
            emailUser: String? = null,
            idSection: Int? = null,
            idTask: Int? = null,
            numberTask: Int? = null,
            titleTask: String? = null
    ): User

    fun getAll(
            allRecords: Boolean? = false,
            idDegree: Int? = null,
            nameDegree: String? = null,
            branchDegree: String? = null,
            idBranch: Int? = null,
            numberBranch: String? = null,
            nameBranch: String? = null,
            idSpeciality: Int? = null,
            numberSpeciality: String? = null,
            nameSpeciality: String? = null,
            idDepartment: Int? = null,
            nameDepartment: String? = null,
            idFaculty: Int? = null,
            nameFaculty: String? = null,
            idInstitute: Int? = null,
            nameInstitute: String? = null
    ): MutableList<User>

    fun save(
            httpMethod: HttpMethod,
            newUser: User,
            passwordHash: String? = null,
            findByIdContactInfo: Int? = null,
            findByPhoneNumberUser: String? = null,
            findByEmailUser: String? = null
    ): User

    fun delete(
            idUser: Int? = null,
            idContactInfo: Int? = null,
            phoneNumberUser: String? = null,
            emailUser: String? = null
    ): User

    fun decryptPassword(
            idUser: Int? = null,
            idContactInfo: Int? = null,
            phoneNumberUser: String? = null,
            emailUser: String? = null
    ): String?

}