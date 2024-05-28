package ge.nika.digitrecognition

import ge.nika.Value
import ge.nika.Value.Companion.asValue
import ge.nika.digitrecognition.data.FeedForwardResult
import ge.nika.digitrecognition.data.MnistDigit
import ge.nika.digitrecognition.data.MnistDigit.Companion.toListOfDigits
import ge.nika.digitrecognition.data.MnistDigitsDataset
import ge.nika.digitrecognition.io.file.loadMlpFromFile
import ge.nika.digitrecognition.io.file.saveToFile
import ge.nika.network.MLP
import ge.nika.network.sum
import ge.nika.operations.ActivationFunction
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import java.time.Instant

class DigitRecognitionNetwork(
    private val mlp: MLP
) {

    companion object {
        fun fromFile(fileName: String): DigitRecognitionNetwork =
            DigitRecognitionNetwork(loadMlpFromFile(fileName))

        fun new(layers: List<Int>): DigitRecognitionNetwork = DigitRecognitionNetwork(
            MLP.generate(
                activationFunction = ActivationFunction.Sigmoid,
                numberOfOriginalInputs = 784,
                layerSizes = layers
            )
        )
    }

    fun train(epochs: Int, batchSize: Int) {
        repeat(epochs) { epoch ->
            var batchNumber = 0

            val learningRate = 3.0//1.0 - 0.9*epoch/epochs
            val batches = MnistDigitsDataset.train.shuffle().batchIterator(batchSize)

            batches.forEach { batch ->
                batchNumber++

                val losses = runBlocking {
                    batch!!
                        .toListOfDigits()
                        .map { async { mlp.getLoss(it) } }
                        .awaitAll()
                }

                val totalBatchLoss = losses.sum() * (1.0 / batchSize).asValue()
                mlp.adjustAccordingTo(totalBatchLoss, learningRate)

                println("Epoch: ${epoch+1}. Batch No:$batchNumber. Loss: ${totalBatchLoss.data}")
            }
        }
    }

    fun saveToFile(): String {
        val fileName = "digit-recognition-mlp-${Instant.now().toEpochMilli()}.json"
        mlp.saveToFile(fileName)
        return fileName
    }

    fun evaluate(): String {
        error("TODO")
    }

    fun feedForward(digit: MnistDigit): FeedForwardResult {
        val params = digit.toTrainingParams()
        val result = mlp.forwardPass(params.inputLayer)
        println(params.inputLayer)
        println(params.expectedOutput.calculateLoss(result))
        return FeedForwardResult.from(result)
    }

    private fun MLP.getLoss(digit: MnistDigit): Value {
        val params = digit.toTrainingParams()
        val forwardPassResults = forwardPass(params.inputLayer)
        return params.expectedOutput.calculateLoss(forwardPassResults)
    }

    private fun MLP.adjustAccordingTo(loss: Value, learningRate: Double) {
        loss.incrementGradientBy(1.0)
        loss.propagateGradientsBackward()
        adjustParameters(learningRate)
    }
}