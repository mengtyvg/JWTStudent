package com.example.jwt.student.controller

import com.example.jwt.student.dto.JikanAnimeResponse
import com.example.jwt.student.dto.UpcomingAnimeDTO
import com.example.jwt.student.service.AnimeService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/anime")
class AnimeController(
    private val animeService: AnimeService,
) {

    @GetMapping("/{id}")
    fun getAnimeById(@PathVariable id: Int): JikanAnimeResponse? {
        return animeService.getAnimeByID(id)
    }

    @GetMapping("/upcoming")
    fun getUpcomingAnime(): List<UpcomingAnimeDTO> {
        return animeService.getUpcomingAnime()
    }

    @GetMapping("/image")
    fun getAnimeImage(@RequestParam url: String): ResponseEntity<ByteArray> {
        return animeService.getAnimeImage(url)
    }
}
