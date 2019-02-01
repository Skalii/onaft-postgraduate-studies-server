package skalii.restful.onaftpostgraduatestudiesserver.repository


import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.repository.Repository as EmptyRepository
import org.springframework.stereotype.Repository

import skalii.restful.onaftpostgraduatestudiesserver.entity.StudyInfo


@Repository
interface StudyInfoRepository : EmptyRepository<StudyInfo, Int> {


    /** ============================== GET / SELECT ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (study_info_record(
                          cast_int(:id_study_info),
                          cast_int(:id_user)
                      )).*""",
            nativeQuery = true)
    fun find(
            @Param("id_study_info") idStudyInfo: Int? = null,
            @Param("id_user") idUser: Int? = null
    ): StudyInfo

    //language=PostgresPLSQL
    @Query(value = "select (study_info_record(all_records => true)).*",
            nativeQuery = true)
    fun findAll(): MutableList<StudyInfo>


    /** ============================== ADD / INSERT INTO ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (study_info_insert(
                          cast_int(:#{#study_info.year}),
                          cast_form(:#{#study_info.form.value}),
                          cast_basis(:#{#study_info.basis.value}),
                          cast_text(:#{#study_info.themeTitle}),
                          cast_int(:#{#study_info.instructor.idUser})
                      )).*""",
            nativeQuery = true)
    fun add(@Param("study_info") newStudyInfo: StudyInfo): StudyInfo


    /** ============================== SET / UPDATE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (study_info_update(
                          cast_int(:#{#study_info.year}),
                          cast_form(:#{#study_info.form.value}),
                          cast_basis(:#{#study_info.basis.value}),
                          cast_text(:#{#study_info.themeTitle}),
                          cast_int(:#{#study_info.instructor.idUser}),
                          cast_int(:#{#study_info.idStudyInfo}),
                          cast_int(:id_user)
                      )).*""",
            nativeQuery = true)
    fun set(
            @Param("study_info") newStudyInfo: StudyInfo,
            @Param("id_user") findByIdUser: Int? = null

    ): StudyInfo


    /** ============================== DELETE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (study_info_delete(cast_int(:id_study_info))).*""",
            nativeQuery = true)
    fun remove(@Param("id_study_info") idStudyInfo: Int): StudyInfo

}