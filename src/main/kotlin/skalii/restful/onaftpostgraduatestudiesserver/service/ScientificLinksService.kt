package skalii.restful.onaftpostgraduatestudiesserver.service


import org.springframework.http.HttpMethod

import skalii.restful.onaftpostgraduatestudiesserver.entity.ScientificLinks


interface ScientificLinksService {

    fun get(
            idScientificLinks: Int? = null,
            orcid: String? = null,
            researcherid: String? = null,
            googleScholarId: String? = null,
            scopusAuthorId: String? = null,
            idUser: Int? = null,
            idContactInfo: Int? = null,
            phoneNumberUser: String? = null,
            emailUser: String? = null
    ): ScientificLinks

    fun getAll(): MutableList<ScientificLinks>

    fun save(
            httpMethod: HttpMethod,
            newScientificLinks: ScientificLinks,
            findByOrcid: String? = null,
            findByResearcherid: String? = null,
            findByGoogleScholarId: String? = null,
            findByScopusAuthorId: String? = null,
            findByIdUser: Int? = null,
            findByIdContactInfo: Int? = null,
            findByPhoneNumberUser: String? = null,
            findByEmailUser: String? = null
    ): ScientificLinks

    fun delete(
            idScientificLinks: Int? = null,
            orcid: String? = null,
            researcherid: String? = null,
            googleScholarId: String? = null,
            scopusAuthorId: String? = null,
            idUser: Int? = null,
            idContactInfo: Int? = null,
            phoneNumberUser: String? = null,
            emailUser: String? = null
    ): ScientificLinks

}