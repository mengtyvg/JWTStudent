package com.example.jwt.student.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

data class JikanAnimeResponse(
    val data: AnimeData
)

data class AnimeData(
    @JsonProperty("mal_id")
    val malId: Int?,

    val url: String?,
    val title: String?,

    @JsonProperty("title_english")
    val titleEnglish: String?,

    val synopsis: String?,
    //val score: Double?,
    val episodes: Int?,
    val status: String?,
    val images: AnimeImages?
)

data class AnimeImages(
    val jpg: AnimeImage?
)

data class AnimeImage(
    @JsonProperty("image_url")
    val imageUrl: String?,

    @JsonProperty("large_image_url")
    val largeImageUrl: String?,

    @JsonProperty("maximum_image_url")
    val maximumImageUrl: String?
)

//TESTING ALOT OF LIST DTO

@JsonIgnoreProperties(ignoreUnknown = true)
data class UpcomingAnimeResponse(
    val data: List<UpcomingAnimeData>?
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class UpcomingAnimeData(
    @JsonProperty("mal_id")
    val malId: Int?,

    val title: String?,

    @JsonProperty("title_english")
    val titleEnglish: String?,

    val type: String?,
    val episodes: Int?,
    val status: String?,
    //val score: Double?,
    val synopsis: String?,
    val season: String?,
    val year: Int?,
    val images: AnimeImages?,
    val genres: List<AnimeGenre>?
)


@JsonIgnoreProperties(ignoreUnknown = true)
data class AnimeJpg(
    @JsonProperty("image_url")
    val imageUrl: String?
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class AnimeGenre(
    val name: String?
)



// clean dto response to frontend
data class UpcomingAnimeDTO(
    val id: Int?,
    val title: String?,
    val englishTitle: String?,
    val type: String?,
    val episodes: Int?,
    val status: String?,
    //val score: Double?,
    val season: String?,
    val year: Int?,
    val imageUrl: String?,
    val genres: List<String>?
)
