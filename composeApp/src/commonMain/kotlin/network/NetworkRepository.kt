package network

import data.HeroesResponse
import data.LeaderBoardResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.URLProtocol
import io.ktor.http.encodedPath
import io.ktor.http.parameters
import io.ktor.http.path
import kotlinx.coroutines.flow.Flow
import utils.Constants

class NetworkRepository(private val httpClient: HttpClient) {
    suspend fun getHeroes(): Flow<NetWorkResult<List<HeroesResponse>>> {
        return toResultFlow {
            val response = httpClient.get {
                url {
                    protocol = URLProtocol.HTTPS
                    host = Constants.BASE_URL_WITHOUT_HTTPS
                    path("heroes.json")
                }
            }.body<List<HeroesResponse>>()
            NetWorkResult.Success(response)
        }
    }

    suspend fun getHeroDetail(heroName : String): Flow<NetWorkResult<HeroesResponse>> {
        return toResultFlow {
            val response = httpClient.get {
                url {
                    protocol = URLProtocol.HTTPS
                    host = Constants.BASE_URL_WITHOUT_HTTPS
                    encodedPath = "heroes/$heroName.json"

                }
            }.body<HeroesResponse>()
            NetWorkResult.Success(response)
        }
    }

    suspend fun getBuilds(heroName : String): Flow<NetWorkResult<LeaderBoardResponse>> {
        return toResultFlow {
            val response = httpClient.get {
                url {
                    protocol = URLProtocol.HTTPS
                    host = Constants.BASE_URL_WITHOUT_HTTPS
                    encodedPath = "builds"

                }
            }.body<LeaderBoardResponse>()
            NetWorkResult.Success(response)
        }
    }

}