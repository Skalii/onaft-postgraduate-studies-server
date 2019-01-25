package skalii.restful.onaftpostgraduatestudiesserver.service.impl


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service

import skalii.restful.onaftpostgraduatestudiesserver.entity.Branch
import skalii.restful.onaftpostgraduatestudiesserver.repository.BranchesRepository
import skalii.restful.onaftpostgraduatestudiesserver.repository.SpecialitiesRepository
import skalii.restful.onaftpostgraduatestudiesserver.repository.UsersRepository
import skalii.restful.onaftpostgraduatestudiesserver.service.BranchesService


@Service
class BranchesServiceImpl : BranchesService {

    @Autowired
    private lateinit var branchesRepository: BranchesRepository

    @Autowired
    private lateinit var specialitiesRepository: SpecialitiesRepository

    @Autowired //todo change userRepository
    private lateinit var usersRepository: UsersRepository

    override fun get(
            idBranch: Int?,
            number: String?,
            name: String?,
            idSpeciality: Int?,
            numberSpeciality: String?,
            nameSpeciality: String?,
            idUser: Int?,
            idContactInfo: Int?,
            phoneNumberUser: String?,
            emailUser: String?
    ) =
            branchesRepository.run {
                if (idBranch != null
                        || number != null
                        || name != null) {
                    find(
                            idBranch,
                            number,
                            name
                    )
                } else if (idSpeciality != null
                        || numberSpeciality != null
                        || nameSpeciality != null) {
                    findBySpeciality(
                            specialitiesRepository.get(
                                    numberSpeciality,
                                    nameSpeciality,
                                    idSpeciality
                            )
                    )
                } else {
                    findByUser(
                            usersRepository.get(
                                    emailUser,
                                    phoneNumberUser,
                                    idUser,
                                    idContactInfo
                            )
                    )
                }
            }

    override fun getAll() = branchesRepository.findAll()

    override fun save(
            httpMethod: HttpMethod,
            newBranch: Branch,
            findByNumber: String?,
            findByName: String?
    ) =
            branchesRepository.run {
                when {
                    httpMethod.matches("POST") -> {
                        add(newBranch)
                    }
                    httpMethod.matches("PUT") -> {
                        set(
                                newBranch,
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
            idBranch: Int?,
            number: String?,
            name: String?,
            idSpeciality: Int?,
            numberSpeciality: String?,
            nameSpeciality: String?
    ) =
            branchesRepository.run {
                if (idBranch != null
                        || number != null
                        || name != null) {
                    remove(
                            find(
                                    idBranch,
                                    number,
                                    name
                            )
                    )
                } else {
                    removeBySpeciality(
                            specialitiesRepository.get(
                                    numberSpeciality,
                                    nameSpeciality,
                                    idSpeciality
                            )
                    )
                }

            }

}