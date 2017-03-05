/*
 * Copyright (C) 2015 Antonio Leiva
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

package com.antonioleiva.bandhookkotlin.repository.datasource

import com.antonioleiva.bandhookkotlin.domain.entity.Artist
import com.antonioleiva.bandhookkotlin.domain.entity.BizException
import com.antonioleiva.bandhookkotlin.domain.entity.BizException.ArtistNotFound
import com.finecinnamon.NonEmptyList
import com.finecinnamon.Result

interface ArtistDataSource {

    fun get(id: String): Result<ArtistNotFound, Artist>
    fun requestRecommendedArtists(): Result<BizException.RecomendationsNotFound, NonEmptyList<Artist>>

}
