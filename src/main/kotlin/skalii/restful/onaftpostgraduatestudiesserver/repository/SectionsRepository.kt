package skalii.restful.onaftpostgraduatestudiesserver.repository


import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.repository.Repository as EmptyRepository
import org.springframework.stereotype.Repository

import skalii.restful.onaftpostgraduatestudiesserver.entity.Section


@Repository
interface SectionsRepository : EmptyRepository<Section, Int> {


    /** ============================== GET / SELECT ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (section_record(
                          cast_int(:id_section),
                          cast_int(:id_user),
                          cast_int(:number),
                          cast_text(:title)
                      )).*""",
            nativeQuery = true)
    fun find(
            @Param("id_section") idSection: Int? = null,
            @Param("id_user") idUser: Int? = null,
            @Param("number") number: Int? = null,
            @Param("title") title: String? = null
    ): Section

    //language=PostgresPLSQL
    @Query(value = """select (section_record(
                          _id_user => cast_int(:id_user),
                          all_records => true
                      )).*""",
            nativeQuery = true)
    fun findAllByUser(@Param("id_user") idUser: Int): MutableList<Section>


    /** ============================== ADD / INSERT INTO ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (section_insert(
                          cast_int(:id_user),
                          cast_int(:#{#section.number}),
                          cast_text(:#{#section.title})
                      )).*""",
            nativeQuery = true)
    fun add(
            @Param("section") newSection: Section,
            @Param("id_user") idUser: Int
    ): Section


    /** ============================== SET / UPDATE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (section_update(
                          cast_int(:#{#section.number}),
                          cast_text(:#{#section.title}),
                          cast_int(:#{#section.idSection}),
                          cast_int(:id_user),
                          cast_int(:old_number),
                          cast_text(:old_title)
                      )).*""",
            nativeQuery = true)
    fun set(
            @Param("section") newSection: Section,
            @Param("old_number") findByNumber: Int? = null,
            @Param("old_title") findByTitle: String? = null,
            @Param("id_user") findByIdUser: Int
    ): Section


    /** ============================== DELETE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (section_delete(cast_int(:id_section))).*""",
            nativeQuery = true)
    fun remove(@Param("id_section") idSection: Int): Section

    //language=PostgresPLSQL
    @Query(value = """select (section_delete(
                          _id_user => cast_int(:id_user),
                          all_from_user => true)).*""",
            nativeQuery = true)
    fun removeAllByUser(@Param("id_user") idUser: Int): MutableList<Section>

}