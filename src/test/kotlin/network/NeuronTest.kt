package network

import ge.nika.Value.Companion.asValue
import ge.nika.network.Neuron
import ge.nika.operations.ActivationFunction
import ge.nika.operations.Tanh.Companion.tanh
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class NeuronTest {

    @Test
    fun `cant be initialized with different numbers of inputs and weights`() {
        shouldThrow<IllegalArgumentException> {
            Neuron(
                numberOfInputs = 2,
                weights = listOf(1.asValue()),
                activationFunction = ActivationFunction.Tanh,
            )
        }.message shouldBe "Number of given weights must equal number of inputs!"
    }

    @Test
    fun `can not be called with different number of inputs`() {
        val neuron = Neuron(
            numberOfInputs = 2,
            activationFunction = ActivationFunction.Tanh,
        )

        shouldThrow<IllegalArgumentException> {
            neuron.forwardPass(listOf(1.asValue()))
        }.message shouldBe "Number of given inputs must equal predefined number!"
    }

    @Test
    fun `correctly calculates the output by returning a tanh of activation`() {
        val neuron = Neuron(
            numberOfInputs = 3,
            weights = listOf(
                2.asValue(),
                3.asValue(),
                5.asValue(),
            ),
            bias = 1.asValue(),
            activationFunction = ActivationFunction.Tanh,
        )

        val result = neuron.forwardPass(
            listOf(
                2.asValue(),
                (-3).asValue(),
                10.asValue(),
            )
        )

        result.data shouldBe 46.asValue().tanh().data
    }
}