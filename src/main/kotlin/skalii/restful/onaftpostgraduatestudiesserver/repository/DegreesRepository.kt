package skalii.restful.onaftpostgraduatestudiesserver.repository


import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.repository.Repository as EmptyRepository
import org.springframework.stereotype.Repository

import skalii.restful.onaftpostgraduatestudiesserver.entity.Degree


@Repository
interface DegreesRepository : EmptyRepository<Degree, Int> {


    /** ============================== GET / SELECT ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (degree_record(
                          cast_int(:id_degree),
                          cast_degree(:name),
                          cast_branch(:branch)
                      )).*""",
            nativeQuery = true)
    fun find(
            @Param("id_degree") idDegree: Int? = null,
            @Param("name") name: String? = null,
            @Param("branch") branch: String? = null
    ): Degree

    //language=PostgresPLSQL
    @Query(value = """select (degree_record(
                          _name => cast_degree(:name),
                          _branch => cast_branch(:branch),
                          all_records => cast_bool(:all_records)
                      )).*""",
            nativeQuery = true)
    fun findAll(
            @Param("name") name: String? = null,
            @Param("branch") branch: String? = null,
            @Param("all_records") allRecords: Boolean? = false
    ): MutableList<Degree>


    /** ============================== ADD / INSERT INTO ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (degree_insert(
                          cast_degree(:#{#degree.name.value}),
                          cast_branch(:#{#degree.branch.value})
                      )).*""",
            nativeQuery = true)
    fun add(@Param("degree") newDegree: Degree): Degree


    /** ============================== SET / UPDATE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (degree_update(
                          cast_degree(:#{#degree.name.value}),
                          cast_branch(:#{#degree.branch.value}),
                          cast_int(:#{#degree.idDegree}),
                          cast_degree(:old_name),
                          cast_branch(:old_branch)
                      )).*""",
            nativeQuery = true)
    fun set(
            @Param("degree") newDegree: Degree,
            @Param("old_name") findByName: String? = null,
            @Param("old_branch") findByBranch: String? = null
    ): Degree


    /** ============================== DELETE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (degree_delete(cast_int(:id_degree))).*""",
            nativeQuery = true)
    fun remove(@Param("id_degree") idDegree: Int): Degree

}