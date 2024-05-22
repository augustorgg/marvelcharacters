package br.com.mobile.marvelcharacters.domain.mapper

import br.com.mobile.marvelcharacters.data.model.CharacterResultResponse
import br.com.mobile.marvelcharacters.data.model.ComicItemResponse
import br.com.mobile.marvelcharacters.data.model.ComicsResponse
import br.com.mobile.marvelcharacters.data.model.EventItemResponse
import br.com.mobile.marvelcharacters.data.model.EventsResponse
import br.com.mobile.marvelcharacters.data.model.MarvelApiDataResponse
import br.com.mobile.marvelcharacters.data.model.MarvelCharactersDetailResponse
import br.com.mobile.marvelcharacters.data.model.SeriesItemResponse
import br.com.mobile.marvelcharacters.data.model.SeriesResponse
import br.com.mobile.marvelcharacters.data.model.StoriesResponse
import br.com.mobile.marvelcharacters.data.model.StoryItemResponse
import br.com.mobile.marvelcharacters.data.model.ThumbnailResponse
import br.com.mobile.marvelcharacters.data.model.UrlResponse
import br.com.mobile.marvelcharacters.domain.model.CharacterResult
import br.com.mobile.marvelcharacters.domain.model.ComicItem
import br.com.mobile.marvelcharacters.domain.model.Comics
import br.com.mobile.marvelcharacters.domain.model.EventItem
import br.com.mobile.marvelcharacters.domain.model.Events
import br.com.mobile.marvelcharacters.domain.model.MarvelApiData
import br.com.mobile.marvelcharacters.domain.model.MarvelCharactersDetail
import br.com.mobile.marvelcharacters.domain.model.Series
import br.com.mobile.marvelcharacters.domain.model.SeriesItem
import br.com.mobile.marvelcharacters.domain.model.Stories
import br.com.mobile.marvelcharacters.domain.model.StoryItem
import br.com.mobile.marvelcharacters.domain.model.Thumbnail
import br.com.mobile.marvelcharacters.domain.model.Url

object MarvelCharactersDetailMapper {

    fun mapFromDataModel(dataModel: MarvelApiDataResponse): MarvelApiData {
        return MarvelApiData(
            data = mapDataModelToDomain(dataModel.data),
        )
    }

    private fun mapDataModelToDomain(dataModel: MarvelCharactersDetailResponse?): MarvelCharactersDetail {
        return MarvelCharactersDetail(
            offset = dataModel?.offset,
            limit = dataModel?.limit,
            total = dataModel?.total,
            count = dataModel?.count,
            results = mapCharacterResult(dataModel?.results)
        )
    }

    private fun mapCharacterResult(characterResult: List<CharacterResultResponse>?): List<CharacterResult>? {
        return characterResult?.map { dataMode ->
            CharacterResult(
                id = dataMode.id,
                name = dataMode.name,
                description = dataMode.description,
                modified = dataMode.modified,
                resourceURI = dataMode.resourceURI,
                urls = dataMode.urls?.map { mapUrl(it) },
                thumbnail = mapThumbnail(dataMode.thumbnail),
                comics = mapComics(dataMode.comics),
                stories = mapStories(dataMode.stories),
                events = mapEvents(dataMode.events),
                series = mapSeries(dataMode.series)
            )
        }
    }

    private fun mapUrl(url: UrlResponse): Url {
        return Url(
            type = url.type,
            url = url.url
        )
    }

    private fun mapThumbnail(thumbnail: ThumbnailResponse?): Thumbnail {
        return Thumbnail(
            path = thumbnail?.path,
            extension = thumbnail?.extension
        )
    }

    private fun mapComics(comics: ComicsResponse?): Comics {
        return Comics(
            available = comics?.available,
            returned = comics?.returned,
            collectionURI = comics?.collectionURI,
            items = comics?.items?.map { mapComicItem(it) }
        )
    }

    private fun mapComicItem(comicItem: ComicItemResponse?): ComicItem {
        return ComicItem(
            resourceURI = comicItem?.resourceURI,
            name = comicItem?.name
        )
    }

    private fun mapStories(stories: StoriesResponse?): Stories {
        return Stories(
            available = stories?.available,
            returned = stories?.returned,
            collectionURI = stories?.collectionURI,
            items = stories?.items?.map { mapStoryItem(it) }
        )
    }

    private fun mapStoryItem(storyItem: StoryItemResponse?): StoryItem {
        return StoryItem(
            resourceURI = storyItem?.resourceURI,
            name = storyItem?.name,
            type = storyItem?.type
        )
    }

    private fun mapEvents(events: EventsResponse?): Events {
        return Events(
            available = events?.available,
            returned = events?.returned,
            collectionURI = events?.collectionURI,
            items = events?.items?.map { mapEventItem(it) }
        )
    }

    private fun mapEventItem(eventItem: EventItemResponse?): EventItem {
        return EventItem(
            resourceURI = eventItem?.resourceURI,
            name = eventItem?.name
        )
    }

    private fun mapSeries(series: SeriesResponse?): Series {
        return Series(
            available = series?.available,
            returned = series?.returned,
            collectionURI = series?.collectionURI,
            items = series?.items?.map { mapSeriesItem(it) }
        )
    }

    private fun mapSeriesItem(seriesItem: SeriesItemResponse?): SeriesItem {
        return SeriesItem(
            resourceURI = seriesItem?.resourceURI,
            name = seriesItem?.name
        )
    }
}
