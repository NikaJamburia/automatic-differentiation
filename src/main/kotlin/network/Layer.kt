package ge.nika.network

import ge.nika.Value

class Layer(
    val numberOfInputsInNeuron: Int,
    val numberOfNeurons: Int,
) {

    private val neurons = (1..numberOfNeurons).map {
        Neuron(numberOfInputs = numberOfInputsInNeuron)
    }

    fun call(inputs: List<Value>): List<Value> {
        require(inputs.size == numberOfInputsInNeuron)
        return neurons.map { it.call(inputs) }
    }
}