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

package com.antonioleiva.bandhookkotlin.domain.repository

import com.finecinnamon.Result
import com.antonioleiva.bandhookkotlin.domain.entity.Album
import com.antonioleiva.bandhookkotlin.domain.entity.BizException.AlbumNotFound
import com.antonioleiva.bandhookkotlin.domain.entity.BizException.TopAlbumsNotFound

interface AlbumRepository {
    fun getAlbum(id: String): Result<AlbumNotFound, Album>
    fun getTopAlbums(artistId: String?, artistName: String?): Result<TopAlbumsNotFound, List<Album>>
}
