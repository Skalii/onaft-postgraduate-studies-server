package skalii.restful.onaftpostgraduatestudiesserver.entity


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.fasterxml.jackson.annotation.JsonView

import java.time.Instant
import java.util.Date

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
import javax.persistence.SequenceGenerator
import javax.persistence.Table
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

import skalii.restful.onaftpostgraduatestudiesserver.view.View.REST
import skalii.restful.onaftpostgraduatestudiesserver.view.View.TREE


@Entity(name = "Task")
@JsonPropertyOrder(
        value = [
            "id_task", "number", "title", "balkline", "deadline",
            "mark_done_user", "mark_done_instructor",
            "timestamp_done_user", "timestamp_done_instructor", "section"])
@SequenceGenerator(
        name = "tasks_seq",
        sequenceName = "tasks_id_task_seq",
        schema = "public",
        allocationSize = 1)
@Table(name = "tasks",
        schema = "public",
        indexes = [
            Index(name = "tasks_pkey",
                    columnList = "id_task",
                    unique = true),
            Index(name = "tasks_id_task_uindex",
                    columnList = "id_task",
                    unique = true),
            Index(name = "tasks_id_section_number_title_uindex",
                    columnList = "id_section,number,title",
                    unique = true)])
data class Task(

        @Column(name = "id_task",
                nullable = false)
        @GeneratedValue(
                strategy = SEQUENCE,
                generator = "tasks_seq")
        @Id
        @get:JsonProperty(value = "id_task")
        @JsonView(REST::class)
        @NotNull
        val idTask: Int = 0,

        @Column(name = "number",
                nullable = false)
        @get:JsonProperty(value = "number")
        @JsonView(REST::class)
        @NotNull
        val number: Int = 0,

        @Column(name = "title",
                nullable = false,
                length = 500)
        @get:JsonProperty(value = "title")
        @JsonView(REST::class)
        @NotNull
        @Size(max = 500)
        val title: String = "",

        @Column(name = "balkline",
                nullable = false)
        @get:JsonFormat(
                pattern = "yyyy-MM-dd HH:mm:ss",
                shape = JsonFormat.Shape.STRING,
                timezone = "Europe/Kiev")
        @get:JsonProperty(value = "balkline")
        @JsonView(REST::class)
        @NotNull
        val balkline: Date = Date.from(Instant.now()),

        @Column(name = "deadline",
                nullable = false)
        @get:JsonFormat(
                pattern = "yyyy-MM-dd HH:mm:ss",
                shape = JsonFormat.Shape.STRING,
                timezone = "Europe/Kiev")
        @get:JsonProperty(value = "deadline")
        @JsonView(REST::class)
        @NotNull
        val deadline: Date = Date.from(Instant.now()).also { it.time = it.time + 2592000000 },

        @Column(name = "mark_done_user")
        @get:JsonProperty(value = "mark_done_user")
        @JsonView(REST::class)
        val markDoneUser: Boolean? = false,

        @Column(name = "mark_done_instructor")
        @get:JsonProperty(value = "mark_done_instructor")
        @JsonView(REST::class)
        val markDoneInstructor: Boolean?,

        @Column(name = "link",
                length = 1000)
        @get:JsonProperty(value = "link")
        @JsonView(REST::class)
        @Size(max = 1000)
        val link: String? = "",

        @Column(name = "timestamp_done_user")
        @get:JsonFormat(
                pattern = "yyyy-MM-dd HH:mm:ss",
                shape = JsonFormat.Shape.STRING,
                timezone = "Europe/Kiev")
        @get:JsonProperty(value = "timestamp_done_user")
        @JsonView(REST::class)
        val timestampDoneUser: Date?,

        @Column(name = "timestamp_done_instructor")
        @get:JsonFormat(
                pattern = "yyyy-MM-dd HH:mm:ss",
                shape = JsonFormat.Shape.STRING,
                timezone = "Europe/Kiev")
        @get:JsonProperty(value = "timestamp_done_instructor")
        @JsonView(REST::class)
        var timestampDoneInstructor: Date?

) {

    @JoinColumn(
            name = "id_section",
            nullable = false,
            foreignKey = ForeignKey(name = "tasks_sections_fkey"))
    @JsonIgnoreProperties(value = ["tasks"])
    @get:JsonProperty(value = "section")
    @JsonView(TREE::class)
    @ManyToOne(
            targetEntity = Section::class,
            fetch = EAGER,
            optional = false)
    lateinit var section: Section


    constructor() : this(
            0,
            1,
            "Нове завдання",
            Date.from(Instant.now()),
            Date.from(Instant.now()).also { it.time = it.time + 2592000000 },
            false,
            false,
            "Невідоме посилання",
            null,
            null)

    constructor(
            idTask: Int = 0,
            number: Int = 1,
            title: String = "Нове завдання",
            balkline: Date = Date.from(Instant.now()),
            deadline: Date = Date.from(Instant.now()).also { it.time = it.time + 2592000000 },
            markDoneUser: Boolean? = false,
            markDoneInstructor: Boolean? = false,
            link: String? = "Невідоме посилання",
            timestampDoneUser: Date? = null,
            timestampDoneInstructor: Date? = null,
            section: Section = Section()
    ) : this(
            idTask,
            number,
            title,
            balkline,
            deadline,
            markDoneUser,
            markDoneInstructor,
            link,
            timestampDoneUser,
            timestampDoneInstructor
    ) {
        this.section = section
    }

}