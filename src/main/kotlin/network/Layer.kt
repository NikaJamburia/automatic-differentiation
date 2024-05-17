package ge.nika.network

import ge.nika.Value
import ge.nika.operations.ActivationFunction

class Layer(
    val numberOfInputsInNeuron: Int,
    val numberOfNeurons: Int,
    private val activationFunction: ActivationFunction,
) {

    private val neurons = (1..numberOfNeurons).map {
        Neuron(
            numberOfInputs = numberOfInputsInNeuron,
            activationFunction = activationFunction,
        )
    }

    fun forwardPass(inputs: List<Value>): List<Value> {
        require(inputs.size == numberOfInputsInNeuron)
        return neurons.map { it.forwardPass(inputs) }
    }

    fun adjustParameters(learningRate: Double) = neurons.forEach { it.adjustParameters(learningRate) }
}