package skalii.restful.onaftpostgraduatestudiesserver.service.impl


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service

import skalii.restful.onaftpostgraduatestudiesserver.entity.ScientificLinks
import skalii.restful.onaftpostgraduatestudiesserver.repository.ScientificLinksRepository
import skalii.restful.onaftpostgraduatestudiesserver.repository.UsersRepository
import skalii.restful.onaftpostgraduatestudiesserver.service.ScientificLinksService


@Service
class ScientificLinksServiceImpl : ScientificLinksService {

    @Autowired
    private lateinit var scientificLinksRepository: ScientificLinksRepository

    @Autowired
    private lateinit var usersRepository: UsersRepository

    override fun get(
            idScientificLinks: Int?,
            orcid: String?,
            researcherid: String?,
            googleScholarId: String?,
            scopusAuthorId: String?,
            idUser: Int?,
            idContactInfo: Int?,
            phoneNumberUser: String?,
            emailUser: String?
    ) =
            scientificLinksRepository.run {
                if (idScientificLinks != null
                        || orcid != null
                        || researcherid != null
                        || googleScholarId != null
                        || scopusAuthorId != null) {
                    find(
                            idScientificLinks,
                            orcid,
                            researcherid,
                            googleScholarId,
                            scopusAuthorId
                    )
                } else {
                    findByUser(
                            usersRepository.find(
                                    idUser,
                                    idContactInfo,
                                    phoneNumberUser,
                                    emailUser
                            ).idUser
                    )
                }
            }

    override fun getAll() = scientificLinksRepository.findAll()

    override fun save(
            httpMethod: HttpMethod,
            newScientificLinks: ScientificLinks,
            findByOrcid: String?,
            findByResearcherid: String?,
            findByGoogleScholarId: String?,
            findByScopusAuthorId: String?,
            findByIdUser: Int?,
            findByIdContactInfo: Int?,
            findByPhoneNumberUser: String?,
            findByEmailUser: String?
    ) =
            scientificLinksRepository.run {
                when {
                    httpMethod.matches("POST") -> {
                        add(newScientificLinks)
                    }
                    httpMethod.matches("PUT") -> {
                        if (findByIdUser != null
                                || findByIdContactInfo != null
                                || findByPhoneNumberUser != null
                                || findByEmailUser != null) {
                            setByUser(
                                    newScientificLinks,
                                    findByIdUser ?: usersRepository.find(
                                            idContactInfo = findByIdContactInfo,
                                            phoneNumber = findByPhoneNumberUser,
                                            email = findByEmailUser
                                    ).idUser
                            )
                        } else {
                            set(
                                    newScientificLinks,
                                    findByOrcid,
                                    findByResearcherid,
                                    findByGoogleScholarId,
                                    findByScopusAuthorId
                            )
                        }
                    }
                    else -> {
                        find()
                    }
                }
            }

    override fun delete(
            idScientificLinks: Int?,
            orcid: String?,
            researcherid: String?,
            googleScholarId: String?,
            scopusAuthorId: String?,
            idUser: Int?,
            idContactInfo: Int?,
            phoneNumberUser: String?,
            emailUser: String?
    ) =
            scientificLinksRepository.run {
                if (idScientificLinks != null
                        || orcid != null
                        || researcherid != null
                        || googleScholarId != null
                        || scopusAuthorId != null) {
                    remove(
                            idScientificLinks ?: find(
                                    orcid = orcid,
                                    researcherid = researcherid,
                                    googleScholarId = googleScholarId,
                                    scopusAuthorId = scopusAuthorId
                            ).idScientificLinks
                    )
                } else {
                    removeByUser(
                            idUser ?: usersRepository.find(
                                    idContactInfo = idContactInfo,
                                    phoneNumber = phoneNumberUser,
                                    email = emailUser
                            ).idUser
                    )
                }
            }

}