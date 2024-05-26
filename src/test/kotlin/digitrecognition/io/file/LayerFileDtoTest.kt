package digitrecognition.io.file

import ge.nika.Value.Companion.asValue
import ge.nika.digitrecognition.io.file.ActivationFunctionType
import ge.nika.digitrecognition.io.file.LayerFileDto
import ge.nika.digitrecognition.io.file.LayerFileDto.Companion.toFileDto
import ge.nika.digitrecognition.io.file.NeuronFileDto
import ge.nika.network.Layer
import ge.nika.network.Neuron
import ge.nika.operations.ActivationFunction
import io.kotest.assertions.asClue
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class LayerFileDtoTest {

    @Test
    fun `can be correctly converted to real layer`() {
        val dto = LayerFileDto(
            numberOfInputsInNeuron = 1,
            numberOfNeurons = 1,
            neuronDtos = listOf(
                NeuronFileDto(
                    activationFunctionType = ActivationFunctionType.SIGMOID,
                    numberOfInputs = 1,
                    weights = listOf(1.2),
                    bias = 0.23
                )
            )
        )

        val layer = dto.toLayer()

        layer.numberOfInputsInNeuron shouldBe 1
        layer.numberOfNeurons shouldBe 1
        layer.neurons.size shouldBe 1
        layer.neurons.first().asClue {
            it.numberOfInputs shouldBe 1
            it.activationFunction shouldBe ActivationFunction.Sigmoid
            it.weights shouldBe listOf(1.2.asValue())
            it.bias shouldBe 0.23.asValue()
        }
    }

    @Test
    fun `can be correctly created from layer`() {
        val layer = Layer.ofNeurons(
            listOf(
                Neuron.withPredefinedWeights(
                    numberOfInputs = 1,
                    activationFunction = ActivationFunction.Tanh,
                    weights = listOf(1.asValue()),
                    bias = 5.asValue()
                )
            )
        )

        val dto = layer.toFileDto()

        dto.numberOfNeurons shouldBe 1
        dto.numberOfInputsInNeuron shouldBe 1
        dto.neuronDtos.size shouldBe 1
        dto.neuronDtos.first().asClue {
            it.activationFunctionType shouldBe ActivationFunctionType.TANH
            it.numberOfInputs shouldBe 1
            it.weights shouldBe listOf(1.0)
            it.bias shouldBe 5.0
        }
    }
}