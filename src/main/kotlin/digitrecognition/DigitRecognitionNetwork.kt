package ge.nika.digitrecognition

import ge.nika.digitrecognition.data.MnistDigit
import ge.nika.network.MLP
import ge.nika.operations.ActivationFunction

class DigitRecognitionNetwork(
    private val mlp: MLP = MLP(
        activationFunction = ActivationFunction.Sigmoid,
        numberOfOriginalInputs = 758,
        layerSizes = listOf(15, 10)
    )
) {

    companion object {
        fun fromFile(fileName: String): DigitRecognitionNetwork {
            error("TODO")
        }
    }

    fun train(epochs: Int, batchSize: Int) {
        error("TODO")
    }

    fun saveToFile(fileName: String): String {
        error("TODO")
    }

    fun evaluate(): String {
        error("TODO")
    }

    fun feedForward(digit: MnistDigit): Int {
        error("TODO")
    }
}