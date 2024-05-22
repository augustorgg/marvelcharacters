package br.com.mobile.marvelcharacters.domain.model

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
)

data class Url(
    val type: String?,
    val url: String?
)

data class Thumbnail(
    val path: String?,
    val extension: String?
)

data class Comics(
    val available: Int?,
    val returned: Int?,
    val collectionURI: String?,
    val items: List<ComicItem>?
)

data class ComicItem(
    val resourceURI: String?,
    val name: String?
)

data class Stories(
    val available: Int?,
    val returned: Int?,
    val collectionURI: String?,
    val items: List<StoryItem>?
)

data class StoryItem(
    val resourceURI: String?,
    val name: String?,
    val type: String?
)

data class Events(
    val available: Int?,
    val returned: Int?,
    val collectionURI: String?,
    val items: List<EventItem>?
)

data class EventItem(
    val resourceURI: String?,
    val name: String?
)

data class Series(
    val available: Int?,
    val returned: Int?,
    val collectionURI: String?,
    val items: List<SeriesItem>?
)

data class SeriesItem(
    val resourceURI: String?,
    val name: String?
)
