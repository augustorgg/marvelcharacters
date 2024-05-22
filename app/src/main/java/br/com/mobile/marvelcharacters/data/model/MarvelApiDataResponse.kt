package br.com.mobile.marvelcharacters.data.model

data class MarvelApiDataResponse(
    val data: MarvelCharactersDetailResponse?
)

data class MarvelCharactersDetailResponse(
    val offset: Int?,
    val limit: Int?,
    val total: Int?,
    val count: Int?,
    val results: List<CharacterResultResponse>?
)

data class CharacterResultResponse(
    val id: Int?,
    val name: String?,
    val description: String?,
    val modified: String?,
    val resourceURI: String?,
    val urls: List<UrlResponse>?,
    val thumbnail: ThumbnailResponse?,
    val comics: ComicsResponse?,
    val stories: StoriesResponse?,
    val events: EventsResponse?,
    val series: SeriesResponse?
)

data class UrlResponse(
    val type: String?,
    val url: String?
)

data class ThumbnailResponse(
    val path: String?,
    val extension: String?
)

data class ComicsResponse(
    val available: Int?,
    val returned: Int?,
    val collectionURI: String?,
    val items: List<ComicItemResponse>?
)

data class ComicItemResponse(
    val resourceURI: String?,
    val name: String?
)

data class StoriesResponse(
    val available: Int?,
    val returned: Int?,
    val collectionURI: String?,
    val items: List<StoryItemResponse>?
)

data class StoryItemResponse(
    val resourceURI: String?,
    val name: String?,
    val type: String?
)

data class EventsResponse(
    val available: Int?,
    val returned: Int?,
    val collectionURI: String?,
    val items: List<EventItemResponse>?
)

data class EventItemResponse(
    val resourceURI: String?,
    val name: String?
)

data class SeriesResponse(
    val available: Int?,
    val returned: Int?,
    val collectionURI: String?,
    val items: List<SeriesItemResponse>?
)

data class SeriesItemResponse(
    val resourceURI: String?,
    val name: String?
)
