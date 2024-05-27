package ge.nika.digitrecognition.datasetbrowser.web.dto

data class GuessDigitRequest(
    val pixels: List<Float>,
    val label: Float,
)

data class GuessDigitResponse(
    val guessedDigit: Int,
    val guessedActivations: List<Double>
)