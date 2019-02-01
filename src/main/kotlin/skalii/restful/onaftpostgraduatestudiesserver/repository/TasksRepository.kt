package skalii.restful.onaftpostgraduatestudiesserver.repository


import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.repository.Repository as EmptyRepository
import org.springframework.stereotype.Repository

import skalii.restful.onaftpostgraduatestudiesserver.entity.Task


@Repository
interface TasksRepository : EmptyRepository<Task, Int> {


    /** ============================== GET / SELECT ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (task_record(
                          cast_int(:id_task),
                          cast_int(:id_section),
                          cast_int(:id_user),
                          cast_int(:number),
                          cast_text(:title)
                      )).*""",
            nativeQuery = true)
    fun find(
            @Param("id_task") idTask: Int? = null,
            @Param("number") number: Int? = null,
            @Param("title") title: String? = null,
            @Param("id_section") idSection: Int? = null,
            @Param("id_user") idUser: Int? = null
    ): Task

    //language=PostgresPLSQL
    @Query(value = """select (task_record(
                          _id_section => cast_int(:id_section),
                          _id_user => cast_int(:id_user),
                          all_records => true
                      )).*""",
            nativeQuery = true)
    fun findAll(
            @Param("id_section") idSection: Int? = null,
            @Param("id_user") idUser: Int? = null
    ): MutableList<Task>


    /** ============================== ADD / INSERT INTO ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (task_insert(
                          cast_int(:id_section),
                          cast_text(:#{#task.title}),
                          cast_ts(:#{#task.balkline}),
                          cast_ts(:#{#task.deadline}),
                          cast_text(:#{#task.link})
                      )).*""",
            nativeQuery = true)
    fun add(
            @Param("task") newTask: Task,
            @Param("id_section") idSection: Int
    ): Task


    /** ============================== SET / UPDATE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (task_update(
                          cast_int(:#{#task.number}),
                          cast_text(:#{#task.title}),
                          cast(cast(:#{#task.balkline} as character varying) as timestamp with time zone),
                          cast(cast(:#{#task.deadline} as character varying) as timestamp with time zone),
                          new_link => cast_text(:#{#task.link}),
                          _id_task => cast_int(:#{#task.idTask}),
                          _id_section => cast_int(:id_section),
                          _id_user => cast_int(:id_user),
                          task_number => cast_int(:old_number),
                          task_title => cast_text(:old_title)
                      )).*""",
            nativeQuery = true)
    fun set(
            @Param("task") newTask: Task,
            @Param("id_section") findByIdSection: Int? = null,
            @Param("id_user") findByIdUser: Int? = null,
            @Param("old_number") findByNumber: Int? = null,
            @Param("old_title") findByTitle: String? = null
    ): Task

    //language=PostgresPLSQL
    @Query(value = """select (task_update(
                          new_mark_done_user => cast_bool(:#{#task.markDoneUser}),
                          _id_task => cast_int(:#{#task.idTask}),
                          _id_section => cast_int(:id_section),
                          _id_user => cast_int(:id_user),
                          task_number => cast_int(:number),
                          task_title => cast_text(:title)
                      )).*""",
            nativeQuery = true)
    fun setMarkStudent(
            @Param("task") newTask: Task,
            @Param("id_section") findByIdSection: Int? = null,
            @Param("id_user") findByIdUser: Int? = null,
            @Param("number") findByNumber: Int? = null,
            @Param("title") findByTitle: String? = null
    ): Task

    //language=PostgresPLSQL
    @Query(value = """select (task_update(
                          new_mark_done_instructor => cast_bool(:#{#task.markDoneInstructor}),
                          _id_task => cast_int(:#{#task.idTask}),
                          _id_section => cast_int(:id_section),
                          _id_user => cast_int(:id_user),
                          task_number => cast_int(:number),
                          task_title => cast_text(:title)
                      )).*""",
            nativeQuery = true)
    fun setMarkInstructor(
            @Param("task") newTask: Task,
            @Param("id_section") findByIdSection: Int? = null,
            @Param("id_user") findByIdUser: Int? = null,
            @Param("number") findByNumber: Int? = null,
            @Param("title") findByTitle: String? = null
    ): Task


    /** ============================== DELETE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (task_delete(cast_int(:id_task))).*""",
            nativeQuery = true)
    fun remove(@Param("id_task") idTask: Int): Task

    //language=PostgresPLSQL
    @Query(value = """select (task_delete(
                          _id_section => cast_int(:id_section),
                          all_from_section => true
                      )).*""",
            nativeQuery = true)
    fun removeAllBySection(@Param("id_section") idSection: Int): MutableList<Task>

}