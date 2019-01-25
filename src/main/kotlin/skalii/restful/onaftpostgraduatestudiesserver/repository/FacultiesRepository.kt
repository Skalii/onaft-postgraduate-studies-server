package skalii.restful.onaftpostgraduatestudiesserver.repository


import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.repository.Repository as EmptyRepository
import org.springframework.stereotype.Repository

import skalii.restful.onaftpostgraduatestudiesserver.entity.Department
import skalii.restful.onaftpostgraduatestudiesserver.entity.Faculty
import skalii.restful.onaftpostgraduatestudiesserver.entity.User


@Repository
interface FacultiesRepository : EmptyRepository<Faculty, Int> {


    /** ============================== GET / SELECT ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (faculty_record(
                          cast_int(:id_faculty),
                          cast_text(:name)
                      )).*""",
            nativeQuery = true)
    fun find(
            @Param("id_faculty") idFaculty: Int? = null,
            @Param("name") name: String? = null
    ): Faculty

    //language=PostgresPLSQL
    @Query(value = "select (faculty_record(cast_int(:#{#department.faculty.idFaculty}))).*",
            nativeQuery = true)
    fun findByDepartment(@Param("department") department: Department?): Faculty

    //language=PostgresPLSQL
    @Query(value = "select (faculty_record(cast_int(:#{#user.department.faculty.idFaculty}))).*",
            nativeQuery = true)
    fun findByUser(@Param("user") user: User?): Faculty

    //language=PostgresPLSQL
    @Query(value = """select (faculty_record(
                          _id_institute => cast_int(:id_institute),
                          all_records => cast_bool(:all_records)
                      )).*""",
            nativeQuery = true)
    fun findAll(
            @Param("id_institute") idInstitute: Int? = null,
            @Param("all_records") allRecords: Boolean? = false
    ): MutableList<Faculty>


    /** ============================== ADD / INSERT INTO ============================== */


    //language=PostgresPLSQL
    @Query(value = "select (faculty_insert(cast_text(:#{#faculty.name}))).*",
            nativeQuery = true)
    fun add(@Param("faculty") newFaculty: Faculty): Faculty


    /** ============================== SET / UPDATE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (faculty_update(
                          cast_text(:#{#faculty.name}),
                          cast_int(:#{#faculty.idFaculty}),
                          cast_text(:old_name)
                      )).*""",
            nativeQuery = true)
    fun set(
            @Param("faculty") newFaculty: Faculty,
            @Param("old_name") findByName: String? = null
    ): Faculty


    /** ============================== DELETE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (faculty_delete(
                          cast_int(:#{#faculty.idFaculty}),
                          cast_text(:#{#faculty.name})
                      )).*""",
            nativeQuery = true)
    fun remove(@Param("faculty") faculty: Faculty): Faculty

}