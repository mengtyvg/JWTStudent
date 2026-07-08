package com.example.jwt.student.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class BookListResponse(
    val count: Int?,
    val next: String?,
    val previous: String?,
    val results: List<BookData>?
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class BookData(
    val id: Int?,
    val title: String?,
    val authors: List<Person>?,
    val summaries: List<String>?,
    val languages: List<String>?,

    @JsonProperty("download_count")
    val downloadCount: Int?
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Person(
    val name: String?,

    @JsonProperty("birth_year")
    val birthYear: Int?,

    @JsonProperty("death_year")
    val deathYear: Int?
)


data class BookResponseDTO(
    val id: Int?,
    val title: String?,
    val authors: List<String>?,
    val summary: String?,
    val languages: List<String>?,
    val downloadCount: Int?
)