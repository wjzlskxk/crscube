package crscube.restapi.highschool.util.exception

import crscube.restapi.highschool.data.res.ApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException::class)
    fun  handleIllegalArgumentException(e: IllegalArgumentException): ResponseEntity<ApiResponse<Nothing>> {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(ApiResponse(
                false,
                e.message ?: "인증 오류가 발생하였습니다."
            ))
    }

    @ExceptionHandler(NoSuchElementException::class)
    fun  handleNotFoundException(e: NoSuchElementException): ResponseEntity<ApiResponse<Nothing>> {
        return ResponseEntity.badRequest()
            .body(ApiResponse(
                false,
                e.message ?: "해당하는것을 찾을 수 없습니다."
            ))
    }

    @ExceptionHandler(Exception::class)
    fun  handleGeneralException(e: Exception): ResponseEntity<ApiResponse<Nothing>> {
        return ResponseEntity.badRequest()
            .body(ApiResponse(
                false,
                e.message ?: "서버 오류가 발생하였습니다."
            ))
    }
}