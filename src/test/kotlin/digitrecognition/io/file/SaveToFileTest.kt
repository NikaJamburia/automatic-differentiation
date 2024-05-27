package digitrecognition.io.file

import ge.nika.Value.Companion.asValue
import ge.nika.digitrecognition.io.file.loadMlpFromFile
import ge.nika.digitrecognition.io.file.saveToFile
import ge.nika.network.Layer
import ge.nika.network.MLP
import ge.nika.network.Neuron
import ge.nika.operations.ActivationFunction
import io.kotest.assertions.asClue
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class SaveToFileTest {

    @Test
    fun `mlp can be saved and read from file`() {
        val fileName = "test.json"
        val mlp = MLP.ofLayers(
            listOf(Layer.ofNeurons(
                listOf(
                    Neuron.withPredefinedWeights(
                        numberOfInputs = 1,
                        activationFunction = ActivationFunction.Tanh,
                        weights = listOf(0.5.asValue()),
                        bias = 1.0.asValue()
                    )
                )
            ))
        )

        mlp.saveToFile(fileName)

        val mlpFromFile = loadMlpFromFile(fileName)

        mlpFromFile.layers.size shouldBe 1
        mlpFromFile.layers.first().asClue {
            it.numberOfNeurons shouldBe 1
            it.numberOfInputsInNeuron shouldBe 1
            it.neurons.size shouldBe 1
            it.neurons.first().asClue { neuron ->
                neuron.numberOfInputs shouldBe 1
                neuron.activationFunction shouldBe ActivationFunction.Tanh
                neuron.weights shouldBe listOf(0.5.asValue())
                neuron.bias shouldBe 1.0.asValue()
            }
        }
    }
}