package skalii.restful.onaftpostgraduatestudiesserver.repository


import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.repository.Repository as EmptyRepository

import org.springframework.stereotype.Repository

import skalii.restful.onaftpostgraduatestudiesserver.entity.Department
import skalii.restful.onaftpostgraduatestudiesserver.entity.User


@Repository
interface DepartmentsRepository : EmptyRepository<Department, Int> {


    /** ============================== GET / SELECT ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (department_record(
                          cast_int(:id_department),
                          cast_text(:name)
                      )).*""",
            nativeQuery = true)
    fun find(
            @Param("id_department") idDepartment: Int? = null,
            @Param("name") name: String? = null
    ): Department

    //language=PostgresPLSQL
    @Query(value = "select (department_record(cast_int(:#{#user.department.idDepartment}))).*",
            nativeQuery = true)
    fun findByUser(@Param("user") user: User?): Department

    //language=PostgresPLSQL
    @Query(value = """select (department_record(
                          _id_institute => cast_int(:id_institute),
                          _id_faculty => cast_int(:id_faculty),
                          all_records => cast_bool(:all_records)
                      )).*""",
            nativeQuery = true)
    fun findAll(
            @Param("id_institute") idInstitute: Int? = null,
            @Param("id_faculty") idFaculty: Int? = null,
            @Param("all_records") allRecords: Boolean? = false
    ): MutableList<Department>


    /** ============================== ADD / INSERT INTO ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (department_insert(
                          cast_text(:#{#department.name}),
                          cast_int(:#{#department.institute.idInstitute}),
                          cast_int(:#{#department.faculty.idFaculty})
                      )).*""",
            nativeQuery = true)
    fun add(@Param("department") newDepartment: Department): Department


    /** ============================== SET / UPDATE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (department_update(
                          cast_text(:#{#department.name}),
                          cast_int(:#{#department.institute.idInstitute}),
                          cast_int(:#{#department.faculty.idFaculty}),
                          cast_int(:#{#department.idDepartment}),
                          cast_text(:old_name)
                      )).*""",
            nativeQuery = true)
    fun set(
            @Param("department") newDepartment: Department,
            @Param("old_name") findByName: String? = null
    ): Department


    /** ============================== DELETE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (department_delete(
                          cast_int(:#{#department.idDepartment}),
                          cast_text(:#{#department.name})
                      )).*""",
            nativeQuery = true)
    fun remove(@Param("department") department: Department): Department

}