package ge.nika.digitrecognition.datasetbrowser.web

import ge.nika.digitrecognition.MnistDatasetType
import ge.nika.digitrecognition.datasetbrowser.MnistBrowserService
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.http4k.core.*
import org.http4k.filter.CorsPolicy.Companion.UnsafeGlobalPermissive
import org.http4k.filter.DebuggingFilters
import org.http4k.filter.ServerFilters
import org.http4k.routing.bind
import org.http4k.routing.path
import org.http4k.routing.routes
import org.http4k.server.Netty
import org.http4k.server.asServer

class MnistBrowserServer {

    private val service = MnistBrowserService()

    fun start() {
        val http = DebuggingFilters.PrintRequest()
            .then(ServerFilters.Cors(UnsafeGlobalPermissive))
            .then(routes(
                "digits/{datesetType}/random" bind Method.GET to { req ->

                    val datesetType = req.path("datesetType")
                        ?.let { MnistDatasetType.valueOf(it) }
                        ?: error("No dataset type provided")

                    val digit = service.getRandomDigit(datesetType).toWebDto()
                    Response(Status.OK)
                        .header("content-type", "application/json")
                        .body(digit.toJson())
                }
            ))

        println("Starting mnist browser server")
        http.asServer(Netty(8080)).start()
    }
}

inline fun <reified T> T.toJson(): String =
    Json.encodeToString(this)