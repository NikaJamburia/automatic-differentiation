package ge.nika.digitrecognition

import ge.nika.digitrecognition.data.MnistDigit
import ge.nika.digitrecognition.io.file.loadMlpFromFile
import ge.nika.digitrecognition.io.file.saveToFile
import ge.nika.network.MLP
import ge.nika.operations.ActivationFunction
import java.time.Instant

class DigitRecognitionNetwork(
    private val mlp: MLP = MLP.generate(
        activationFunction = ActivationFunction.Sigmoid,
        numberOfOriginalInputs = 758,
        layerSizes = listOf(15, 10)
    )
) {

    companion object {
        fun fromFile(fileName: String): DigitRecognitionNetwork =
            DigitRecognitionNetwork(loadMlpFromFile(fileName))
    }

    fun train(epochs: Int, batchSize: Int) {
        error("TODO")
    }

    fun saveToFile() {
        mlp.saveToFile("digit-recognition-mlp-${Instant.now()}.json")
    }

    fun evaluate(): String {
        error("TODO")
    }

    fun feedForward(digit: MnistDigit): Int {
        error("TODO")
    }
}