package skalii.restful.onaftpostgraduatestudiesserver.repository


import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.repository.Repository as EmptyRepository
import org.springframework.stereotype.Repository

import skalii.restful.onaftpostgraduatestudiesserver.entity.ContactInfo


@Repository
interface ContactInfoRepository : EmptyRepository<ContactInfo, Int> {


    /** ============================== GET / SELECT ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (contact_info_record(
                          cast_int(:id_contact_info),
                          cast_int(:id_user),
                          cast_text(:phone_number),
                          cast_text(:email)
                      )).*""",
            nativeQuery = true)
    fun find(
            @Param("id_contact_info") idContactInfo: Int? = null,
            @Param("id_user") idUser: Int? = null,
            @Param("phone_number") phoneNumber: String? = null,
            @Param("email") email: String? = null
    ): ContactInfo

    //language=PostgresPLSQL
    @Query(value = "select (contact_info_record(all_records => true)).*",
            nativeQuery = true)
    fun findAll(): MutableList<ContactInfo>


    /** ============================== ADD / INSERT INTO ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (contact_info_insert(
                          cast_text(:#{#contact_info.phoneNumber}),
                          cast_text(:#{#contact_info.email}),
                          cast_text(:#{#contact_info.address})
                      )).*""",
            nativeQuery = true)
    fun add(@Param("contact_info") newContactInfo: ContactInfo): ContactInfo


    /** ============================== SET / UPDATE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (contact_info_update(
                          cast_text(:#{#contact_info.phoneNumber}),
                          cast_text(:#{#contact_info.email}),
                          cast_text(:#{#contact_info.address}),
                          cast_int(:#{#contact_info.idContactInfo}),
                          cast_int(:#{#contact_info.user.idUser}),
                          cast_text(:old_phone_number),
                          cast_text(:old_email)
                      )).*""",
            nativeQuery = true)
    fun set(
            @Param("contact_info") newContactInfo: ContactInfo,
            @Param("old_phone_number") findByPhoneNumber: String? = null,
            @Param("old_email") findByEmail: String? = null
    ): ContactInfo


    /** ============================== DELETE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (contact_info_delete(cast_int(:id_contact_info))).*""",
            nativeQuery = true)
    fun remove(@Param("id_contact_info") idContactInfo: Int): ContactInfo

}