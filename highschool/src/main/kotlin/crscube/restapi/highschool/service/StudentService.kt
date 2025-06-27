package crscube.restapi.highschool.service

import crscube.restapi.highschool.data.entity.Student
import crscube.restapi.highschool.data.req.StudentCreateReq
import crscube.restapi.highschool.data.req.StudentUpdateReq
import crscube.restapi.highschool.data.res.StudentRes
import crscube.restapi.highschool.repository.StudentRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
@Transactional
class StudentService(
    private val studentRepository: StudentRepository
) {
    fun createStudent(request: StudentCreateReq): StudentRes {
        // 중복 학번 체크
        studentRepository.findByStudentNumber(request.studentNumber)?.let {
            throw IllegalArgumentException("이미 존재하는 학번입니다: ${request.studentNumber}")
        }

        val student = Student(
            studentNumber = request.studentNumber,
            name = request.name,
            grade = request.grade ?: 1,
            classNumber = request.classNumber ?: 1,
            birthday = request.birthday,
            email = request.email,
            address = request.address
        )

        val savedStudent = studentRepository.save(student)
        return convertToResponse(savedStudent)
    }

    @Transactional
    fun getStudent(id: Long): StudentRes {
        val student = studentRepository.findByIdOrNull(id) ?: throw NoSuchElementException("학생을 찾을 수 없습니다. :$id")
        return convertToResponse(student)
    }

    @Transactional
    fun getStudents(
        name: String?,
        grade: Int?,
        classNumber: Int?
    ): List<StudentRes> {
        val students = studentRepository.findStudentsWithFilters(name, grade, classNumber)
        return students.map { convertToResponse(it) }
    }

    fun updateStudent(id: Long, request: StudentUpdateReq): StudentRes {
        val student = studentRepository.findByIdOrNull(id) ?: throw NoSuchElementException("학생을 찾을 수 없습니다. :$id")

        val updatedStudent = student.copy(
            name = request.name ?: student.name,
            grade = request.grade ?: student.grade,
            classNumber = request.classNumber ?: student.classNumber,
            birthday = request.birthday ?: student.birthday,
            email = request.email ?: student.email,
            phoneNumber = request.phoneNumber ?: student.phoneNumber,
            address = request.address ?: student.address,
            updatedAt = LocalDateTime.now()
        )

        val savedStudent = studentRepository.save(updatedStudent)
        return convertToResponse(savedStudent)
    }

    fun deleteStudent(id: Long) {
        val student = studentRepository.findByIdOrNull(id) ?: throw NoSuchElementException("학생을 찾을 수 없습니다. : $id")
        studentRepository.delete(student)
    }

    private fun convertToResponse(student: Student): StudentRes {
        return StudentRes(
            id = student.id,
            studentNumber = student.studentNumber,
            name = student.name,
            grade = student.grade,
            classNumber = student.classNumber,
            birthDate = student.birthday,
            email = student.email,
            phoneNumber = student.phoneNumber,
            address = student.address,
            createdAt = student.createdAt,
            updatedAt = student.updatedAt
        )
    }
}