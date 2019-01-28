package skalii.restful.onaftpostgraduatestudiesserver.repository


import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.repository.Repository as EmptyRepository
import org.springframework.stereotype.Repository

import skalii.restful.onaftpostgraduatestudiesserver.entity.ScientificLinks
import skalii.restful.onaftpostgraduatestudiesserver.entity.User


@Repository
interface ScientificLinksRepository : EmptyRepository<ScientificLinks, Int> {


    /** ============================== GET / SELECT ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (scientific_links_record(
                          cast_int(:id_scientific_links),
                          _orcid => cast_text(:orcid),
                          _researcherid => cast_text(:researcherid),
                          _google_scholar_id => cast_text(:google_scholar_id),
                          _scopus_author_id => cast_text(:scopus_author_id)
                      )).*""",
            nativeQuery = true)
    fun find(
            @Param("id_scientific_links") idScientificLinks: Int? = null,
            @Param("orcid") orcid: String? = null,
            @Param("researcherid") researcherid: String? = null,
            @Param("google_scholar_id") googleScholarId: String? = null,
            @Param("scopus_author_id") scopusAuthorId: String? = null
    ): ScientificLinks

    //language=PostgresPLSQL
    @Query(value = """select (scientific_links_record(
                          _id_user => cast_text(:#{#user.idUser})
                      )).*""",
            nativeQuery = true)
    fun findByUser(@Param("user") user: User): ScientificLinks

    //language=PostgresPLSQL
    @Query(value = "select (scientific_links_record(all_records => true)).*",
            nativeQuery = true)
    fun findAll(): MutableList<ScientificLinks>


    /** ============================== ADD / INSERT INTO ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (scientific_links_insert(
                          cast_text(:#{#scientific_links.orcid}),
                          cast_text(:#{#scientific_links.researcherid}),
                          cast_text(:#{#scientific_links.googleScholarId}),
                          cast_text(:#{#scientific_links.scopusAuthorId})
                      )).*""",
            nativeQuery = true)
    fun add(@Param("scientific_links") newScientificLinks: ScientificLinks): ScientificLinks


    /** ============================== SET / UPDATE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (scientific_links_update(
                          cast_text(:#{#scientific_links.orcid}),
                          cast_text(:#{#scientific_links.researcherid}),
                          cast_text(:#{#scientific_links.googleScholarId}),
                          cast_text(:#{#scientific_links.scopusAuthorId}),
                          cast_int(:#{#scientific_links.idScientificLinks}),
                          _orcid => cast_text(:old_orcid),
                          _researcherid => cast_text(:old_researcherid),
                          _google_scholar_id => cast_text(:old_google_scholar_id),
                          _scopus_author_id => cast_text(:old_scopus_author_id)
                      )).*""",
            nativeQuery = true)
    fun set(
            @Param("scientific_links") newScientificLinks: ScientificLinks,
            @Param("old_orcid") findByOrcid: String? = null,
            @Param("old_researcherid") findByResearcherid: String? = null,
            @Param("old_google_scholar_id") findByGoogleScholarId: String? = null,
            @Param("old_scopus_author_id") findByScopusAuthorId: String? = null
    ): ScientificLinks

    //language=PostgresPLSQL
    @Query(value = """select (scientific_links_update(
                          cast_text(:#{#scientific_links.orcid}),
                          cast_text(:#{#scientific_links.researcherid}),
                          cast_text(:#{#scientific_links.googleScholarId}),
                          cast_text(:#{#scientific_links.scopusAuthorId}),
                          cast_int(:#{#scientific_links.idScientificLinks}),
                          cast_int(:#{#user.idUser})
                      )).*""",
            nativeQuery = true)
    fun setByUser(
            @Param("scientific_links") newScientificLinks: ScientificLinks,
            @Param("user") findByUser: User
    ): ScientificLinks


    /** ============================== DELETE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (scientific_links_delete(
                          cast_int(:#{#scientific_links.idScientificLinks}),
                          _orcid =>  cast_text(:#{#scientific_links.orcid}),
                          _researcherid => cast_text(:#{#scientific_links.researcherid}),
                          _google_scholar_id => cast_text(:#{#scientific_links.googleScholarId}),
                          _scopus_author_id => cast_text(:#{#scientific_links.scopusAuthorId})
                      )).*""",
            nativeQuery = true)
    fun remove(@Param("scientific_links") scientificLinks: ScientificLinks): ScientificLinks

    //language=PostgresPLSQL
    @Query(value = """select (scientific_links_delete(
                          cast_int(:#{#user.idUser})
                      )).*""",
            nativeQuery = true)
    fun removeByUser(@Param("user") user: User): ScientificLinks

}