package skalii.restful.onaftpostgraduatestudiesserver.service


import org.springframework.http.HttpMethod

import skalii.restful.onaftpostgraduatestudiesserver.entity.Task


interface TasksService {

    fun get(
            idTask: Int? = null,
            number: Int? = null,
            title: String? = null,
            idSection: Int? = null,
            numberSection: Int? = null,
            titleSection: String? = null,
            idUser: Int? = null,
            idContactInfo: Int? = null,
            phoneNumberUser: String? = null,
            emailUser: String? = null
    ): Task

    fun getAll(
            idSection: Int? = null,
            numberSection: Int? = null,
            titleSection: String? = null,
            idUser: Int? = null,
            idContactInfo: Int? = null,
            phoneNumberUser: String? = null,
            emailUser: String? = null
    ): MutableList<Task>

    fun save(
            httpMethod: HttpMethod,
            newTask: Task,
            findByNumber: Int? = null,
            findByTitle: String? = null,
            findByIdSection: Int? = null,
            findByNumberSection: Int? = null,
            findByTitleSection: String? = null,
            findByIdUser: Int? = null,
            findByIdContactInfo: Int? = null,
            findByPhoneNumberUser: String? = null,
            findByEmailUser: String? = null
    ): Task

    fun saveMark(
            whoMarked: String,
            newTask: Task,
            findByNumber: Int? = null,
            findByTitle: String? = null,
            findByIdSection: Int? = null,
            findByNumberSection: Int? = null,
            findByTitleSection: String? = null,
            findByIdStudent: Int? = null,
            findByIdContactInfoStudent: Int? = null,
            findByPhoneNumberStudent: String? = null,
            findByEmailStudent: String? = null,
            findByIdInstructor: Int? = null,
            findByIdContactInfoInstructor: Int? = null,
            findByPhoneNumberInstructor: String? = null,
            findByEmailInstructor: String? = null
    ): Task

    fun delete(
            idTask: Int? = null,
            number: Int? = null,
            title: String? = null,
            idSection: Int? = null,
            numberSection: Int? = null,
            titleSection: String? = null,
            idUser: Int? = null,
            idContactInfo: Int? = null,
            phoneNumberUser: String? = null,
            emailUser: String? = null
    ): Task

    fun deleteAll(
            idSection: Int? = null,
            number: Int? = null,
            title: String? = null,
            idUser: Int? = null,
            idContactInfo: Int? = null,
            phoneNumberUser: String? = null,
            emailUser: String? = null
    ): MutableList<Task>

}