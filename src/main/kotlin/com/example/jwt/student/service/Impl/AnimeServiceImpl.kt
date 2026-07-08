package com.example.jwt.student.service.Impl

import com.example.jwt.student.dto.JikanAnimeResponse
import com.example.jwt.student.service.AnimeService
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import com.example.jwt.student.dto.UpcomingAnimeDTO
import com.example.jwt.student.dto.UpcomingAnimeData
import com.example.jwt.student.dto.UpcomingAnimeResponse
import org.springframework.web.server.ResponseStatusException
import java.net.URI


@Service
class AnimeServiceImpl(
    private val restTemplate: RestTemplate
) : AnimeService {

    override fun getAnimeByID(id: Int): JikanAnimeResponse? {
        val url = "https://api.jikan.moe/v4/anime/$id"


        return restTemplate.getForObject(
            url,
            JikanAnimeResponse::class.java
        )
    }

    override fun getUpcomingAnime(): List<UpcomingAnimeDTO> {
        return try {
            val url = "https://api.jikan.moe/v4/seasons/upcoming"

            val response = restTemplate.getForObject(
                url,
                UpcomingAnimeResponse::class.java
            )

            return response?.data?.map { it.toDTO() } ?: emptyList()


        } catch (ex: Exception) {
            emptyList()
        }
    }



    private fun UpcomingAnimeData.toDTO(): UpcomingAnimeDTO {
        return UpcomingAnimeDTO(
            id = this.malId,
            title = this.title,
            englishTitle = this.titleEnglish,
            type = this.type,
            episodes = this.episodes,
            status = this.status,
            //score = this.score,
            season = this.season,
            year = this.year,
            imageUrl = this.images?.jpg?.imageUrl,
            genres = this.genres?.mapNotNull { it.name }
        )


    }

    override fun getAnimeImage(url: String): ResponseEntity<ByteArray> {
        val uri = URI(url)

        if (uri.scheme != "https" || uri.host != "cdn.myanimelist.net") {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Unsupported image host")
        }

        val headers = HttpHeaders()
        headers.set(
            HttpHeaders.USER_AGENT,
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36"
        )
        headers.set(HttpHeaders.REFERER, "https://myanimelist.net/")
        headers.accept = listOf(MediaType.IMAGE_JPEG, MediaType.IMAGE_PNG, MediaType.ALL)

        val response = restTemplate.exchange(
            uri,
            HttpMethod.GET,
            HttpEntity<Void>(headers),
            ByteArray::class.java
        )

        return ResponseEntity
            .status(response.statusCode)
            .header(HttpHeaders.CACHE_CONTROL, "public, max-age=86400")
            .contentType(response.headers.contentType ?: MediaType.IMAGE_JPEG)
            .body(response.body)
    }

}
