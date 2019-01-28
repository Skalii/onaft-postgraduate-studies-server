package skalii.restful.onaftpostgraduatestudiesserver.controller.api


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.POST
import org.springframework.web.bind.annotation.RequestMethod.PUT
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import skalii.restful.onaftpostgraduatestudiesserver.entity.ScientificLinks
import skalii.restful.onaftpostgraduatestudiesserver.service.ScientificLinksService
import skalii.restful.onaftpostgraduatestudiesserver.view.Json.Companion.get


@RequestMapping(
        value = ["/api/scientific-links"],
        produces = [APPLICATION_JSON_UTF8_VALUE])
@RestController
class ScientificLinksRestController {

    @Autowired
    private lateinit var scientificLinksService: ScientificLinksService


    /** ============================== GET requests ============================== */


    @GetMapping(value = ["my{-view}"])
    fun getMy(
            @PathVariable(value = "-view") view: String,
            @AuthenticationPrincipal authUser: UserDetails
    ) =
            get(
                    view,
                    scientificLinksService.get(
                            emailUser = authUser.username
                    )
            )

    @GetMapping(value = ["one{-view}"])
    fun getOne(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "id_scientific_links",
                    required = false) idScientificLinks: Int?,
            @RequestParam(
                    value = "orcid",
                    required = false) orcid: String?,
            @RequestParam(
                    value = "researcherid",
                    required = false) researcherid: String?,
            @RequestParam(
                    value = "google_scholar_id",
                    required = false) googleScholarId: String?,
            @RequestParam(
                    value = "scopus_author_id",
                    required = false) scopusAuthorId: String?
    ) =
            get(
                    view,
                    scientificLinksService.get(
                            idScientificLinks,
                            orcid,
                            researcherid,
                            googleScholarId,
                            scopusAuthorId
                    )
            )

    @GetMapping(value = ["one-by-user{-view}"])
    fun getOneByUser(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?,
            @RequestParam(
                    value = "id_contact_info",
                    required = false) idContactInfo: Int?,
            @RequestParam(
                    value = "phone_number",
                    required = false) phoneNumber: String?,
            @RequestParam(
                    value = "email",
                    required = false) email: String?
    ) =
            get(
                    view,
                    scientificLinksService.get(
                            idUser = idUser,
                            idContactInfo = idContactInfo,
                            phoneNumberUser = phoneNumber,
                            emailUser = email
                    )
            )

    @GetMapping(value = ["all{-view}"])
    fun getAll(@PathVariable(value = "-view") view: String) =
            get(
                    view,
                    scientificLinksService.getAll()
            )


    /** ============================== POST/PUT requests ============================== */


    @PutMapping(value = ["my{-view}"])
    fun saveMy(
            httpMethod: HttpMethod,
            @PathVariable(value = "-view") view: String,
            @RequestBody newScientificLinks: ScientificLinks,
            @AuthenticationPrincipal authUser: UserDetails
    ) =
            get(
                    view,
                    scientificLinksService.save(
                            httpMethod,
                            newScientificLinks,
                            findByEmailUser = authUser.username
                    )
            )

    @RequestMapping(
            value = ["one{-view}"],
            method = [POST, PUT])
    fun saveOne(
            httpMethod: HttpMethod,
            @PathVariable(value = "-view") view: String,
            @RequestBody newScientificLinks: ScientificLinks,
            @RequestParam(
                    value = "find_by_orcid",
                    required = false) findByOrcid: String?,
            @RequestParam(
                    value = "find_by_researcherid",
                    required = false) findByResearcherid: String?,
            @RequestParam(
                    value = "find_by_google_scholar_id",
                    required = false) findByGoogleScholarId: String?,
            @RequestParam(
                    value = "find_by_scopus_author_id",
                    required = false) findByScopusAuthorId: String?,
            @RequestParam(
                    value = "id_user",
                    required = false) findByIdUser: Int?,
            @RequestParam(
                    value = "id_contact_info",
                    required = false) findByIdContactInfo: Int?,
            @RequestParam(
                    value = "phone_number",
                    required = false) findByPhoneNumber: String?,
            @RequestParam(
                    value = "email",
                    required = false) findByEmail: String?
    ) =
            get(
                    view,
                    scientificLinksService.save(
                            httpMethod,
                            newScientificLinks,
                            findByOrcid,
                            findByResearcherid,
                            findByGoogleScholarId,
                            findByScopusAuthorId,
                            findByIdUser,
                            findByIdContactInfo,
                            findByPhoneNumber,
                            findByEmail
                    )
            )


    /** ============================== DELETE requests ============================== */


    @DeleteMapping(value = ["one{-view}"])
    fun deleteOne(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "id_scientific_links",
                    required = false) idScientificLinks: Int?,
            @RequestParam(
                    value = "orcid",
                    required = false) orcid: String?,
            @RequestParam(
                    value = "researcherid",
                    required = false) researcherid: String?,
            @RequestParam(
                    value = "google_scholar_id",
                    required = false) googleScholarId: String?,
            @RequestParam(
                    value = "scopus_author_id",
                    required = false) scopusAuthorId: String?
    ) =
            get(
                    view,
                    scientificLinksService.delete(
                            idScientificLinks,
                            orcid,
                            researcherid,
                            googleScholarId,
                            scopusAuthorId
                    )
            )

    @DeleteMapping(value = ["one-by-user{-view}"])
    fun deleteOneByUser(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?,
            @RequestParam(
                    value = "id_contact_info",
                    required = false) idContactInfo: Int?,
            @RequestParam(
                    value = "phone_number",
                    required = false) phoneNumber: String?,
            @RequestParam(
                    value = "email",
                    required = false) email: String?
    ) = get(
            view,
            scientificLinksService.delete(
                    idUser = idUser,
                    idContactInfo = idContactInfo,
                    phoneNumberUser = phoneNumber,
                    emailUser = email
            )
    )

}