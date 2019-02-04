package skalii.restful.onaftpostgraduatestudiesserver.repository


import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.repository.Repository as EmptyRepository
import org.springframework.stereotype.Repository

import skalii.restful.onaftpostgraduatestudiesserver.entity.User


@Repository
interface UsersRepository : EmptyRepository<User, Int> {


    /** ============================== GET / SELECT ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (user_record(
                          cast_int(:id_user),
                          cast_text(:email),
                          cast_text(:phone_number),
                          cast_int(:id_contact_info)
                      )).*""",
            nativeQuery = true)
    fun find(
            @Param("id_user") idUser: Int? = null,
            @Param("id_contact_info") idContactInfo: Int? = null,
            @Param("phone_number") phoneNumber: String? = null,
            @Param("email") email: String? = null
    ): User

    //language=PostgresPLSQL
    @Query(value = """select (user_record(
                          _id_degree => cast_int(:id_degree),
                          _id_branch => cast_int(:id_branch),
                          _id_speciality => cast_int(:id_speciality),
                          _id_department => cast_int(:id_department),
                          _id_faculty => cast_int(:id_faculty),
                          _id_institute => cast_int(:id_institute),
                          all_records => cast_bool(:all_records)
                      )).*""",
            nativeQuery = true)
    fun findAll(
            @Param("all_records") allRecords: Boolean? = false,
            @Param("id_degree") idDegree: Int? = null,
            @Param("id_branch") idBranch: Int? = null,
            @Param("id_speciality") idSpeciality: Int? = null,
            @Param("id_department") idDepartment: Int? = null,
            @Param("id_faculty") idFaculty: Int? = null,
            @Param("id_institute") idInstitute: Int? = null
    ): MutableList<User>


    /** ============================== ADD / INSERT INTO ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (user_insert(
                          cast_role(:#{#user.role.value}),
                          cast_text(:hash),
                          cast_text(:#{#user.fullNameUa}),
                          cast_text(:#{#user.fullNameEn}),
                          cast(:#{#user.birthday} as date),
                          cast_family(:#{#user.familyStatus.value}),
                          cast_int(:#{#user.children}),
                          cast_rank(:#{#user.academicRank.value}),
                          cast_int(:#{#user.degree.idDegree}),
                          cast_int(:#{#user.speciality.idSpeciality}),
                          cast_int(:#{#user.department.idDepartment}),
                          cast_text(:#{#user.contactInfo.email}),
                          cast_text(:#{#user.contactInfo.phoneNumber}),
                          cast_text(:#{#user.contactInfo.address}),
                          cast_text(:#{#user.scientificLinks.orcid}),
                          cast_text(:#{#user.scientificLinks.researcherid}),
                          cast_text(:#{#user.scientificLinks.googleScholarId}),
                          cast_text(:#{#user.scientificLinks.scopusAuthorId}),
                          cast_int(:#{#user.studyInfo.year}),
                          cast_form(:#{#user.studyInfo.form.value}),
                          cast_basis(:#{#user.studyInfo.basis.value}),
                          cast_text(:#{#user.studyInfo.themeTitle}),
                          cast_int(:#{#user.studyInfo.instructor.idUser})
                      )).*""",
            nativeQuery = true)
    fun add(
            @Param("user") newUser: User,
            @Param("hash") passwordHash: String
    ): User


    /** ============================== SET / UPDATE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (user_update(
                          cast_role(:#{#user.role.value}),
                          cast_text(:new_password_hash),
                          cast_text(:#{#user.fullNameUa}),
                          cast_text(:#{#user.fullNameEn}),
                          cast(:#{#user.birthday.toString()} as date),
                          cast_family(:#{#user.familyStatus.value}),
                          cast_int(:#{#user.children}),
                          cast_rank(:#{#user.academicRank.value}),
                          cast_int(:#{#user.degree.idDegree}),
                          cast_int(:#{#user.speciality.idSpeciality}),
                          cast_int(:#{#user.department.idDepartment}),
                          cast_int(:#{#user.idUser}),
                          cast_text(:email),
                          cast_text(:phone_number),
                          cast_int(:id_contact_info)
                      )).*""",
            nativeQuery = true)
    fun set(
            @Param("user") newUser: User,
            @Param("new_password_hash") newPasswordHash: String? = null,
            @Param("id_contact_info") findByIdContactInfo: Int? = null,
            @Param("phone_number") findByPhoneNumber: String? = null,
            @Param("email") findByEmail: String? = null
    ): User


    /** ============================== DELETE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (user_delete(cast_int(:id_user))).*""",
            nativeQuery = true)
    fun remove(@Param("id_user") idUser: Int): User


    /** ============================== HASHING ============================== */


    //language=PostgresPLSQL
    @Query(value = "select generate_pass_decrypt(:id_user)",
            nativeQuery = true)
    fun decrypt(@Param("id_user") idUser: Int): String?

}