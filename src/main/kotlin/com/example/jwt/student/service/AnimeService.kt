package com.example.jwt.student.service

import com.example.jwt.student.dto.JikanAnimeResponse
import com.example.jwt.student.dto.UpcomingAnimeDTO
import org.springframework.http.ResponseEntity

interface AnimeService {
    fun getAnimeByID(id: Int): JikanAnimeResponse?
    fun getUpcomingAnime(): List<UpcomingAnimeDTO>
    fun getAnimeImage(url: String): ResponseEntity<ByteArray>

}
