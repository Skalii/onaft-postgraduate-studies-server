package skalii.restful.onaftpostgraduatestudiesserver.repository


import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.repository.Repository as EmptyRepository
import org.springframework.stereotype.Repository

import skalii.restful.onaftpostgraduatestudiesserver.entity.Department
import skalii.restful.onaftpostgraduatestudiesserver.entity.Institute
import skalii.restful.onaftpostgraduatestudiesserver.entity.User


@Repository
interface InstitutesRepository : EmptyRepository<Institute, Int> {


    /** ============================== GET / SELECT ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (institute_record(
                          cast_int(:id_institute),
                          cast_text(:name)
                      )).*""",
            nativeQuery = true)
    fun find(
            @Param("id_institute") idInstitute: Int? = null,
            @Param("name") name: String? = null
    ): Institute

    //language=PostgresPLSQL
    @Query(value = "select (institute_record(cast_int(:#{#department.institute.idInstitute}))).*",
            nativeQuery = true)
    fun findByDepartment(@Param("department") department: Department?): Institute

    //language=PostgresPLSQL
    @Query(value = "select (institute_record(cast_int(:#{#user.department.institute.idInstitute}))).*",
            nativeQuery = true)
    fun findByUser(@Param("user") user: User?): Institute

    //language=PostgresPLSQL
    @Query(value = "select (institute_record(all_records => true)).*",
            nativeQuery = true)
    fun findAll(): MutableList<Institute>


    /** ============================== ADD / INSERT INTO ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (institute_insert(
                          cast_text(:#{#institute.name}),
                          cast_text(:#{#institute.namedAfter}),
                          cast_text(:#{#institute.abbreviation})
                      )).*""",
            nativeQuery = true)
    fun add(@Param("institute") newInstitute: Institute): Institute


    /** ============================== SET / UPDATE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (institute_update(
                          cast_text(:#{#institute.name}),
                          cast_text(:#{#institute.namedAfter}),
                          cast_text(:#{#institute.abbreviation}),
                          cast_int(:#{#institute.idInstitute}),
                          cast_text(:name)
                      )).*""",
            nativeQuery = true)
    fun set(
            @Param("institute") newInstitute: Institute,
            @Param("old_name") findByName: String? = null
    ): Institute


    /** ============================== DELETE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (institute_delete(
                          cast_int(:id_institute),
                          cast_text(:name)
                      )).*""",
            nativeQuery = true)
    fun remove(@Param("institute") newInstitute: Institute): Institute

}