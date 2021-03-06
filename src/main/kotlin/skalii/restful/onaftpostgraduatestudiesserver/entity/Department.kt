package skalii.restful.onaftpostgraduatestudiesserver.entity


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.fasterxml.jackson.annotation.JsonView

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType.EAGER
import javax.persistence.ForeignKey
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.SEQUENCE
import javax.persistence.Id
import javax.persistence.Index
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.OrderBy
import javax.persistence.SequenceGenerator
import javax.persistence.Table
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

import skalii.restful.onaftpostgraduatestudiesserver.view.View.REST
import skalii.restful.onaftpostgraduatestudiesserver.view.View.TREE


@Entity(name = "Department")
@JsonPropertyOrder(
        value = ["id_department", "name", "institute", "faculty", "users"])
@SequenceGenerator(
        name = "departments_seq",
        sequenceName = "departments_id_department_seq",
        schema = "public",
        allocationSize = 1)
@Table(name = "departments",
        schema = "public",
        indexes = [
            Index(name = "departments_pkey",
                    columnList = "id_department",
                    unique = true),
            Index(name = "departments_id_department_uindex",
                    columnList = "id_department",
                    unique = true),
            Index(name = "departments_name_uindex",
                    columnList = "name",
                    unique = true)])
data class Department(

        @Column(name = "id_department",
                nullable = false)
        @GeneratedValue(
                strategy = SEQUENCE,
                generator = "departments_seq")
        @Id
        @get:JsonProperty(value = "id_department")
        @JsonView(REST::class)
        @NotNull
        val idDepartment: Int = 0,

        @Column(name = "name",
                nullable = false,
                length = 200)
        @get:JsonProperty(value = "name")
        @JsonView(REST::class)
        @NotNull
        @Size(max = 200)
        val name: String = ""

) {

    @JoinColumn(
            name = "id_institute",
            nullable = false,
            foreignKey = ForeignKey(name = "departments_institutes_fkey"))
    @JsonIgnoreProperties(value = ["departments"])
    @get:JsonProperty(value = "institute")
    @JsonView(REST::class)
    @ManyToOne(
            targetEntity = Institute::class,
            fetch = EAGER,
            optional = false)
    lateinit var institute: Institute

    @JoinColumn(
            name = "id_faculty",
            nullable = false,
            foreignKey = ForeignKey(name = "departments_faculties_fkey"))
    @JsonIgnoreProperties(value = ["departments"])
    @get:JsonProperty(value = "faculty")
    @JsonView(REST::class)
    @ManyToOne(
            targetEntity = Faculty::class,
            fetch = EAGER,
            optional = false)
    lateinit var faculty: Faculty

    @JsonIgnoreProperties(value = ["department", "students", "sections"])
    @get:JsonProperty(value = "users")
    @JsonView(TREE::class)
    @OneToMany(
            targetEntity = User::class,
            mappedBy = "department")
    @OrderBy
    var users: MutableList<User> = mutableListOf(User())


    constructor() : this(
            0,
            "Невідома кафедра"
    )

    constructor(
            idDepartment: Int = 0,
            name: String = "Невідома кафедра",
            institute: Institute = Institute(),
            faculty: Faculty = Faculty()
    ) : this(
            idDepartment,
            name
    ) {
        this.institute = institute
        this.faculty = faculty
    }

}