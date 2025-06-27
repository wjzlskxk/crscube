package crscube.restapi.highschool.repository

import crscube.restapi.highschool.data.entity.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface StudentRepository  : JpaRepository<Student, Long> {
    fun findByStudentNumber(studentNumber: String): Student?

    @Query("SELECT s FROM Student s WHERE " +
                "(:name IS NULL OR s.name LIKE %:name%) AND " +
                "(:grade IS NULL OR s.grade = :grade) AND " +
                "(:classNumber IS NULL OR s.classNumber = :classNumber)"
    )
    fun findStudentsWithFilters(
        @Param("name") name: String?,
        @Param("grade") grade: Int?,
        @Param("classNumber") classNumber: Int?
    ): List<Student>
}