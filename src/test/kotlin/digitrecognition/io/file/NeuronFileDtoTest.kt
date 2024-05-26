package digitrecognition.io.file

import ge.nika.Value.Companion.asValue
import ge.nika.digitrecognition.io.file.ActivationFunctionType
import ge.nika.digitrecognition.io.file.NeuronFileDto
import ge.nika.digitrecognition.io.file.NeuronFileDto.Companion.toFileDto
import ge.nika.network.Neuron
import ge.nika.operations.ActivationFunction
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class NeuronFileDtoTest {

    @Test
    fun `can be correctly converted to real neuron`() {
        val dto = NeuronFileDto(
            activationFunctionType = ActivationFunctionType.SIGMOID,
            numberOfInputs = 3,
            weights = listOf(1.2, 3.4, 5.7),
            bias = 0.23
        )

        val neuron = dto.toNeuron()

        neuron.activationFunction shouldBe ActivationFunction.Sigmoid
        neuron.numberOfInputs shouldBe 3
        neuron.weights shouldBe listOf(1.2, 3.4, 5.7).map { it.asValue() }
        neuron.bias shouldBe 0.23.asValue()
    }

    @Test
    fun `can be correctly created from neuron`() {
        val neuron = Neuron.withPredefinedWeights(
            numberOfInputs = 3,
            activationFunction = ActivationFunction.Tanh,
            weights = listOf(1.asValue(), 2.asValue(), 3.asValue()),
            bias = 5.asValue()
        )

        val dto = neuron.toFileDto()

        dto.activationFunctionType shouldBe ActivationFunctionType.TANH
        dto.numberOfInputs shouldBe 3
        dto.weights shouldBe listOf(1.0, 2.0, 3.0)
        dto.bias shouldBe 5.0
    }
}