package skalii.restful.onaftpostgraduatestudiesserver.repository


import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.repository.Repository as EmptyRepository
import org.springframework.stereotype.Repository

import skalii.restful.onaftpostgraduatestudiesserver.entity.Branch
import skalii.restful.onaftpostgraduatestudiesserver.entity.Speciality
import skalii.restful.onaftpostgraduatestudiesserver.entity.User


@Repository
interface BranchesRepository : EmptyRepository<Branch, Int> {


    /** ============================== GET / SELECT ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (branch_record(
                          cast_int(:id_branch),
                          cast_text(:number),
                          cast_text(:name)
                      )).*""",
            nativeQuery = true)
    fun find(
            @Param("id_branch") idBranch: Int? = null,
            @Param("number") number: String? = null,
            @Param("name") name: String? = null
    ): Branch

    //language=PostgresPLSQL
    @Query(value = "select (branch_record(cast_int(:#{#speciality.branch.idBranch}))).*",
            nativeQuery = true)
    fun findBySpeciality(@Param("speciality") speciality: Speciality?): Branch

    //language=PostgresPLSQL
    @Query(value = "select (branch_record(cast_int(:#{#user.speciality.branch.idBranch}))).*",
            nativeQuery = true)
    fun findByUser(@Param("user") user: User?): Branch

    //language=PostgresPLSQL
    @Query(value = "select (branch_record(all_records => true)).*",
            nativeQuery = true)
    fun findAll(): MutableList<Branch>


    /** ============================== ADD / INSERT INTO ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (branch_insert(
                          cast_text(:#{#branch.number}),
                          cast_text(:#{#branch.name})
                      )).*""",
            nativeQuery = true)
    fun add(@Param("branch") newBranch: Branch): Branch


    /** ============================== SET / UPDATE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (branch_update(
                          cast_text(:#{#branch.number}),
                          cast_text(:#{#branch.name}),
                          cast_int(:#{#branch.idBranch}),
                          cast_text(:old_number),
                          cast_text(:old_name)
                      )).*""",
            nativeQuery = true)
    fun set(
            @Param("branch") newBranch: Branch,
            @Param("old_number") findByNumber: String? = null,
            @Param("old_name") findByName: String? = null
    ): Branch


    /** ============================== DELETE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (branch_delete(
                          cast_int(:#{#branch.idBranch}),
                          cast_text(:#{#branch.number}),
                          cast_text(:#{#branch.name})
                      )).*""",
            nativeQuery = true)
    fun remove(@Param("branch") branch: Branch): Branch

    //language=PostgresPLSQL
    @Query(value = "select (branch_delete(cast_int(:#{#speciality.branch.idBranch}))).*",
            nativeQuery = true)
    fun removeBySpeciality(@Param("speciality") speciality: Speciality?): Branch

}