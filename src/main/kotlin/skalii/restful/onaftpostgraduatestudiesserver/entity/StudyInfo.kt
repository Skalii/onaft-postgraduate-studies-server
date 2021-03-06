package skalii.restful.onaftpostgraduatestudiesserver.entity


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.fasterxml.jackson.annotation.JsonView

import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.Entity
import javax.persistence.FetchType.EAGER
import javax.persistence.ForeignKey
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.SEQUENCE
import javax.persistence.Id
import javax.persistence.Index
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToOne
import javax.persistence.SequenceGenerator
import javax.persistence.Table
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

import skalii.restful.onaftpostgraduatestudiesserver.entity.enum.StudyBasis
import skalii.restful.onaftpostgraduatestudiesserver.entity.enum.StudyForm
import skalii.restful.onaftpostgraduatestudiesserver.view.View.REST
import skalii.restful.onaftpostgraduatestudiesserver.view.View.TREE
import skalii.restful.onaftpostgraduatestudiesserver.view.View.INSTRUCTOR_TREE
import skalii.restful.onaftpostgraduatestudiesserver.view.View.STUDENT_TREE


@Entity(name = "StudyInfo")
@JsonPropertyOrder(
        value = [
            "id_study_info", "year", "form", "basis",
            "theme_title", "instructor", "user"])
@SequenceGenerator(
        name = "study_info_seq",
        sequenceName = "study_info_id_study_info_seq",
        schema = "public",
        allocationSize = 1)
@Table(name = "study_info",
        schema = "public",
        indexes = [
            Index(name = "study_info_pkey",
                    columnList = "id_study_info",
                    unique = true),
            Index(name = "study_info_id_study_info_uindex",
                    columnList = "id_study_info",
                    unique = true)])
data class StudyInfo(

        @Column(name = "id_study_info",
                nullable = false)
        @GeneratedValue(
                strategy = SEQUENCE,
                generator = "study_info_seq")
        @Id
        @get:JsonProperty(value = "id_study_info")
        @JsonView(REST::class)
        @NotNull
        val idStudyInfo: Int = 0,

        @Column(name = "year",
                nullable = false)
        @get:JsonProperty(value = "year")
        @JsonView(REST::class)
        @NotNull
        val year: Int = 0,

        @Column(name = "form",
                nullable = false)
        @Convert(converter = StudyForm.Companion.EnumConverter::class)
        @get:JsonProperty(value = "form")
        @JsonView(REST::class)
        @NotNull
        val form: StudyForm = StudyForm.EMPTY,

        @Column(name = "basis",
                nullable = false)
        @Convert(converter = StudyBasis.Companion.EnumConverter::class)
        @get:JsonProperty(value = "basis")
        @JsonView(REST::class)
        @NotNull
        val basis: StudyBasis = StudyBasis.EMPTY,

        @Column(name = "theme_title",
                nullable = false,
                length = 200)
        @get:JsonProperty(value = "theme_title")
        @NotNull
        @Size(max = 200)
        @JsonView(REST::class)
        val themeTitle: String = ""

) {

    @JoinColumn(
            name = "id_instructor",
            foreignKey = ForeignKey(name = "study_info_instructors_fkey"))
    @JsonIgnoreProperties(value = ["study_info", "students", "sections"])
    @get:JsonProperty(value = "instructor")
    @JsonView(STUDENT_TREE::class)
    @ManyToOne(
            targetEntity = User::class,
            fetch = EAGER,
            optional = false)
    lateinit var instructor: User

    @JsonIgnoreProperties(value = ["study_info", "students", "sections"])
    @get:JsonProperty(value = "user")
    @JsonView(TREE::class, STUDENT_TREE::class, INSTRUCTOR_TREE::class)
    @OneToOne(
            targetEntity = User::class,
            fetch = EAGER,
            optional = false,
            mappedBy = "studyInfo")
    var user: User? = null


    constructor() : this(
            0,
            1,
            StudyForm.FULL_TIME,
            StudyBasis.CONTRACT,
            "Невідома тема роботи"
    )

    constructor(
            idStudyInfo: Int = 0,
            year: Int = 1,
            form: StudyForm = StudyForm.FULL_TIME,
            basis: StudyBasis = StudyBasis.CONTRACT,
            themeTitle: String = "Невідома тема роботи",
            instructor: User = User(),
            user: User? = null
    ) : this(
            idStudyInfo,
            year,
            form,
            basis,
            themeTitle
    ) {
        this.instructor = instructor
        this.user = user
    }


    override fun toString() =
            """StudyInfo(
                idStudyInfo=$idStudyInfo,
                year=$year,
                form=${form.value},
                basis=${basis.value},
                themeTitle=$themeTitle
                )""".trimMargin()

}