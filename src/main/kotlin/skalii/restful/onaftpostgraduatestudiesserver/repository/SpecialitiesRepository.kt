package skalii.restful.onaftpostgraduatestudiesserver.repository


import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.repository.Repository as EmptyRepository
import org.springframework.stereotype.Repository

import skalii.restful.onaftpostgraduatestudiesserver.entity.Speciality


@Repository
interface SpecialitiesRepository : EmptyRepository<Speciality, Int> {


    /** ============================== GET / SELECT ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (speciality_record(
                          cast_int(:id_speciality),
                          cast_text(:number),
                          cast_text(:name)
                      )).*""",
            nativeQuery = true)
    fun find(
            @Param("id_speciality") idSpeciality: Int? = null,
            @Param("number") number: String? = null,
            @Param("name") name: String? = null
    ): Speciality

    //language=PostgresPLSQL
    @Query(value = """select (speciality_record(
                          all_from_id_branch => cast_int(:id_branch),
                          all_records => cast_bool(:all_records)
                      )).*""",
            nativeQuery = true)
    fun findAll(
            @Param("id_branch") idBranch: Int? = null,
            @Param("all_records") allRecords: Boolean? = false
    ): MutableList<Speciality>

    /** ============================== ADD / INSERT ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (speciality_insert(
                          cast_int(:#{#speciality.branch.idBranch}),
                          cast_text(:#{#speciality.number}),
                          cast_text(:#{#speciality.name})
                      )).*""",
            nativeQuery = true)
    fun add(@Param("speciality") newSpeciality: Speciality): Speciality


    /** ============================== SET / UPDATE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (speciality_update(
                          cast_int(:#{#speciality.branch.idBranch}),
                          cast_text(:#{#speciality.number}),
                          cast_text(:#{#speciality.name}),
                          cast_int(:#{#speciality.idSpeciality}),
                          cast_text(:old_number),
                          cast_text(:old_name)
                      )).*""",
            nativeQuery = true)
    fun set(
            @Param("speciality") newSpeciality: Speciality,
            @Param("old_number") findByNumber: String? = null,
            @Param("old_name") findByName: String? = null
    ): Speciality


    /** ============================== DELETE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (speciality_delete(cast_int(:id_speciality))).*""",
            nativeQuery = true)
    fun remove(@Param("id_speciality") idSpeciality: Int): Speciality

    //language=PostgresPLSQL
    @Query(value = "select (speciality_delete(all_from_id_branch => cast_int(:id_branch))).*",
            nativeQuery = true)
    fun removeAllByBranch(@Param("id_branch") idBranch: Int): MutableList<Speciality>

}