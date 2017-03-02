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

package com.antonioleiva.bandhookkotlin.repository

import com.antonioleiva.bandhookkotlin.NonEmptyList
import com.antonioleiva.bandhookkotlin.Result
import com.antonioleiva.bandhookkotlin.ResultT.firstSuccessIn
import com.antonioleiva.bandhookkotlin.ResultT.raiseError
import com.antonioleiva.bandhookkotlin.domain.entity.Artist
import com.antonioleiva.bandhookkotlin.domain.entity.BizException.*
import com.antonioleiva.bandhookkotlin.domain.repository.ArtistRepository
import com.antonioleiva.bandhookkotlin.repository.datasource.ArtistDataSource

class ArtistRepositoryImpl(val dataSources: List<ArtistDataSource>) : ArtistRepository {

    override fun get(id: String): Result<ArtistNotFound, Artist> =
            firstSuccessIn(
                    fa = dataSources,
                    acc = raiseError<ArtistNotFound, Artist>(ArtistNotFound(id)),
                    f = { it.get(id) }
            )

    override fun getRecommendedArtists(): Result<RecomendationsNotFound, NonEmptyList<Artist>> =
            firstSuccessIn(
                    fa = dataSources,
                    acc = raiseError<RecomendationsNotFound, NonEmptyList<Artist>>(RecomendationsNotFound),
                    f = { it.requestRecommendedArtists() }
            )


}
