package crscube.restapi.highschool.data.entity

import jakarta.persistence.*
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "student")
data class Student (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true)
    @NotBlank()
    val studentNumber: Int,

    @Column(nullable = false)
    @NotBlank()
    val name: String,

    @Column(nullable = false)
    @Min(1)
    @Max(3)
    val grade: Int,

    @Column(nullable = false)
    val classNumber: Int,

    @Column(nullable = false)
    val birthday: LocalDate,

    @Column
    val email: String? = null,

    @Column
    @Pattern(regexp = "^01[016789]-\\d{3,4}-\\d{4}$", message = "올바른 전화번호 형식이어야 합니다")
    val phoneNumber: String? = null,

    @Column
    val address: String? = null,

    @Column(nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false)
    val updatedAt: LocalDateTime = LocalDateTime.now()
)
