package com.example.jwt.student.service

import com.example.jwt.student.dto.BookListResponse
import com.example.jwt.student.dto.BookResponseDTO

interface BookService {
    fun getAllBook(): List<BookResponseDTO>
}