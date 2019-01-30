package skalii.restful.onaftpostgraduatestudiesserver.service.impl


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service

import skalii.restful.onaftpostgraduatestudiesserver.entity.Speciality
import skalii.restful.onaftpostgraduatestudiesserver.repository.BranchesRepository
import skalii.restful.onaftpostgraduatestudiesserver.repository.SpecialitiesRepository
import skalii.restful.onaftpostgraduatestudiesserver.repository.UsersRepository
import skalii.restful.onaftpostgraduatestudiesserver.service.SpecialitiesService


@Service
class SpecialitiesServiceImpl : SpecialitiesService {

    @Autowired
    private lateinit var specialitiesRepository: SpecialitiesRepository

    @Autowired
    private lateinit var branchesRepository: BranchesRepository

    @Autowired //todo change usersRepository
    private lateinit var usersRepository: UsersRepository

    override fun get(
            idSpeciality: Int?,
            number: String?,
            name: String?,
            idUser: Int?,
            idContactInfo: Int?,
            phoneNumberUser: String?,
            emailUser: String?
    ) =
            specialitiesRepository.run {
                if (idSpeciality != null
                        || number != null
                        || name != null) {
                    find(
                            idSpeciality,
                            number,
                            name
                    )
                } else {
                    find(
                            usersRepository.get(
                                    emailUser,
                                    phoneNumberUser,
                                    idUser,
                                    idContactInfo
                            ).speciality.idSpeciality
                    )
                }
            }

    override fun getAll(
            idBranch: Int?,
            numberBranch: String?,
            nameBranch: String?,
            allRecords: Boolean?
    ) =
            specialitiesRepository.run {
                if (idBranch == null
                        && numberBranch == null
                        && nameBranch == null) {
                    findAll(allRecords = true)
                } else {
                    findAll(
                            idBranch ?: branchesRepository.find(
                                    number = numberBranch,
                                    name = nameBranch
                            ).idBranch,
                            allRecords
                    )
                }
            }

    override fun save(
            httpMethod: HttpMethod,
            newSpeciality: Speciality,
            findByNumber: String?,
            findByName: String?
    ) =
            specialitiesRepository.run {
                when {
                    httpMethod.matches("POST") -> {
                        add(newSpeciality)
                    }
                    httpMethod.matches("PUT") -> {
                        set(
                                newSpeciality,
                                findByNumber,
                                findByName
                        )
                    }
                    else -> {
                        find()
                    }
                }
            }

    override fun delete(
            idSpeciality: Int?,
            number: String?,
            name: String?
    ) =
            specialitiesRepository.run {
                remove(
                        idSpeciality ?: find(
                                number = number,
                                name = name
                        ).idSpeciality
                )
            }

    override fun deleteAll(
            idBranch: Int?,
            numberBranch: String?,
            nameBranch: String?
    ) =
            specialitiesRepository.removeAllByBranch(
                    idBranch ?: branchesRepository.find(
                            number = numberBranch,
                            name = nameBranch
                    ).idBranch
            )

}