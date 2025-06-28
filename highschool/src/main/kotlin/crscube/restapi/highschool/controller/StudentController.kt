package crscube.restapi.highschool.controller

import crscube.restapi.highschool.data.req.StudentCreateReq
import crscube.restapi.highschool.data.req.StudentUpdateReq
import crscube.restapi.highschool.data.res.ApiResponse
import crscube.restapi.highschool.data.res.StudentRes
import crscube.restapi.highschool.service.StudentService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/students")
class StudentController(
    private val studentService: StudentService
) {

    @PostMapping
    fun createStudent(
        @RequestHeader("Authorization") authorization: String,
        @Valid @RequestBody req: StudentCreateReq
    ): ResponseEntity<ApiResponse<StudentRes>> {
        val student = studentService.createStudent(req)
        return ResponseEntity.status(201).body(
            ApiResponse(
                true,
                "학생이 성공적으로 등록되었습니다.",
                student
            )
        )
    }

    @GetMapping("/{id}")
    fun getStudent(
        @RequestHeader("Authorization") authorization: String,
        @PathVariable id: Long
    ): ResponseEntity<ApiResponse<StudentRes>> {
        val student = studentService.getStudent(id)
        return ResponseEntity.ok(
            ApiResponse(
                true,
                "학생 정보를 성공적으로 조회하였습니다.",
                student
            )
        )
    }

    @GetMapping
    fun getStudents(
        @RequestParam(required = false) name: String?,
        @RequestParam(required = false) grade: Int?,
        @RequestParam(required = false) classNumber: Int?,
    ): ResponseEntity<ApiResponse<List<StudentRes>>> {
        val students = studentService.getStudents(name, grade, classNumber)
        return  ResponseEntity.ok(
            ApiResponse(
                true,
                "학생 정보를 성공적으로 조회했습니다.",
                students
            )
        )
    }

    @PatchMapping("/{id}")
    fun updateStudent(
        @RequestHeader("Authorization") authorization: String,
        @PathVariable id: Long,
        @Valid @RequestBody req: StudentUpdateReq
    ):  ResponseEntity<ApiResponse<StudentRes>> {
        val student = studentService.updateStudent(id, req)
        return ResponseEntity.ok(
            ApiResponse(
                true,
                "학생 정보를 성공적으로 수정했습니다.",
                student
            )
        )
    }

    @DeleteMapping("/{id}")
    fun delteStudent(
        @RequestHeader("Authorization") authorization: String,
        @PathVariable id: Long
    ): ResponseEntity<ApiResponse<Void>> {
        val student = studentService.deleteStudent(id)
        return ResponseEntity.ok(
            ApiResponse(
                true,
                "학생을 성공적으로 삭제하였습니다.",
            )
        )
    }

}