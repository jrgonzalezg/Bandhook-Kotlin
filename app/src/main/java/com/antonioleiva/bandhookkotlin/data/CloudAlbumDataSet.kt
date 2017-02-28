/*
 * Copyright (C) 2016 Alexey Verein
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.antonioleiva.bandhookkotlin.data

import com.antonioleiva.bandhookkotlin.Result
import com.antonioleiva.bandhookkotlin.data.lastfm.LastFmService
import com.antonioleiva.bandhookkotlin.data.lastfm.model.LastFmResponse
import com.antonioleiva.bandhookkotlin.data.mapper.AlbumMapper
import com.antonioleiva.bandhookkotlin.domain.entity.Album
import com.antonioleiva.bandhookkotlin.domain.entity.BizException.AlbumNotFound
import com.antonioleiva.bandhookkotlin.left
import com.antonioleiva.bandhookkotlin.repository.dataset.AlbumDataSet
import com.antonioleiva.bandhookkotlin.right

class CloudAlbumDataSet(val lastFmService: LastFmService) : AlbumDataSet {

    override fun requestAlbum(mbid: String): Result<AlbumNotFound, Album> {
        return lastFmService.requestAlbum(mbid).asResult<AlbumNotFound, LastFmResponse, Album> {
            AlbumMapper().transform(album).fold(
                    { AlbumNotFound(mbid).left<AlbumNotFound, Album>() },
                    { it.right<AlbumNotFound, Album>() }
            )
        }
    }

    override fun requestTopAlbums(artistId: String?, artistName: String?): List<Album> {
        val mbid = artistId ?: ""
        val name = artistName ?: ""

        if (!mbid.isEmpty() || !name.isEmpty()) {
            return lastFmService.requestAlbums(mbid, name).unwrapCall {
                AlbumMapper().transform(topAlbums.albums)
            }
        }

        return emptyList()
    }
}
