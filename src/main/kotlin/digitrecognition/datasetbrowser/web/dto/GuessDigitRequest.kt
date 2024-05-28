package ge.nika.digitrecognition.datasetbrowser.web.dto

import kotlinx.serialization.Serializable

@Serializable
data class GuessDigitRequest(
    val pixels: List<Float>,
    val label: Float,
)

@Serializable
data class GuessDigitResponse(
    val guessedDigit: Int,
    val guessedActivations: List<Double>
)