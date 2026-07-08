package com.example.jwt.student.service.Impl

import com.example.jwt.student.dto.BookData
import com.example.jwt.student.dto.BookListResponse
import com.example.jwt.student.dto.BookResponseDTO
import com.example.jwt.student.service.BookService
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import kotlin.jvm.java

@Service
class BookServiceImpl (
    private val restTemplate: RestTemplate
): BookService {

    override fun getAllBook(): List<BookResponseDTO> {
        val url = "https://gutendex.com/books"

        val response = restTemplate.getForObject(
            url,
            BookListResponse::class.java
        )

        return response?.results?.map { toBookResponseDTO(it) } .orEmpty()
    }



    private fun toBookResponseDTO(book: BookData): BookResponseDTO {
        return BookResponseDTO(
            id = book.id,
            title = book.title,
            authors = book.authors?.mapNotNull { it.name },
            summary = book.summaries?.firstOrNull(),
            languages = book.languages,
            downloadCount = book.downloadCount
        )
    }









}