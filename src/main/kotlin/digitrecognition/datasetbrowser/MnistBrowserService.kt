package ge.nika.digitrecognition.datasetbrowser

import ge.nika.digitrecognition.DigitRecognitionNetwork
import ge.nika.digitrecognition.data.MnistDatasetType
import ge.nika.digitrecognition.data.MnistDigit
import ge.nika.digitrecognition.datasetbrowser.web.dto.GuessDigitRequest
import ge.nika.digitrecognition.datasetbrowser.web.dto.GuessDigitResponse

class MnistBrowserService(
    val digitRecognitionNetwork: DigitRecognitionNetwork,
) {
    fun getRandomDigit(datasetType: MnistDatasetType): MnistDigit {
        return MnistDigit.randomFrom(datasetType)
    }

    fun guessDigit(request: GuessDigitRequest): GuessDigitResponse {
        val digit = MnistDigit(
            pixels = request.pixels.toFloatArray(),
            label = request.label,
        )
        val result = digitRecognitionNetwork.feedForward(digit)
        return GuessDigitResponse(result.guessedDigit, result.guessedActivations)
    }
}