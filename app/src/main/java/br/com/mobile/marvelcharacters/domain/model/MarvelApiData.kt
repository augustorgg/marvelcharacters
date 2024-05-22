package br.com.mobile.marvelcharacters.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class MarvelApiData(
    val code: Int?,
    val status: String?,
    val copyright: String?,
    val attributionText: String?,
    val attributionHtml: String?,
    val data: MarvelCharactersDetail?,
    val etag: String?
)

data class MarvelCharactersDetail(
    val offset: Int?,
    val limit: Int?,
    val total: Int?,
    val count: Int?,
    val results: List<CharacterResult>?
)

@Parcelize
data class CharacterResult(
    val id: Int?,
    val name: String?,
    val description: String?,
    val modified: String?,
    val resourceURI: String?,
    val urls: List<Url>?,
    val thumbnail: Thumbnail?,
    val comics: Comics?,
    val stories: Stories?,
    val events: Events?,
    val series: Series?
) : Parcelable

@Parcelize
data class Url(
    val type: String?,
    val url: String?
) : Parcelable

@Parcelize
data class Thumbnail(
    val path: String?,
    val extension: String?
) : Parcelable

@Parcelize
data class Comics(
    val available: Int?,
    val returned: Int?,
    val collectionURI: String?,
    val items: List<ComicItem>?
) : Parcelable

@Parcelize
data class ComicItem(
    val resourceURI: String?,
    val name: String?
) : Parcelable

@Parcelize
data class Stories(
    val available: Int?,
    val returned: Int?,
    val collectionURI: String?,
    val items: List<StoryItem>?
) : Parcelable

@Parcelize
data class StoryItem(
    val resourceURI: String?,
    val name: String?,
    val type: String?
) : Parcelable

@Parcelize
data class Events(
    val available: Int?,
    val returned: Int?,
    val collectionURI: String?,
    val items: List<EventItem>?
) : Parcelable

@Parcelize
data class EventItem(
    val resourceURI: String?,
    val name: String?
) : Parcelable

@Parcelize
data class Series(
    val available: Int?,
    val returned: Int?,
    val collectionURI: String?,
    val items: List<SeriesItem>?
) : Parcelable

@Parcelize
data class SeriesItem(
    val resourceURI: String?,
    val name: String?
) : Parcelable
