package ge.nika.network

import ge.nika.Value
import ge.nika.operations.ActivationFunction

class Layer private constructor(
    val numberOfInputsInNeuron: Int,
    val numberOfNeurons: Int,
    val neurons: List<Neuron>,
) {

    companion object {
        fun ofNeurons(neurons: List<Neuron>): Layer {
            val numberOfNeurons = neurons.size
            val numberOfInputsInNeuron = neurons.first().numberOfInputs
            val activationFunction = neurons.first().activationFunction

            require(
                neurons.all { it.numberOfInputs == numberOfInputsInNeuron } &&
                        neurons.all { it.activationFunction == activationFunction }
            ) {
                "All neurons in a layer must have same number of inputs and same activation function!"
            }

            return Layer(
                numberOfInputsInNeuron = numberOfInputsInNeuron,
                numberOfNeurons = numberOfNeurons,
                neurons = neurons,
            )
        }

        fun generate(
            numberOfInputsInNeuron: Int,
            numberOfNeurons: Int,
            activationFunction: ActivationFunction,
        ): Layer {
            val neurons = (1..numberOfNeurons).map {
                Neuron.withRandomWeights(
                    numberOfInputs = numberOfInputsInNeuron,
                    activationFunction = activationFunction,
                )
            }
            return Layer(
                numberOfInputsInNeuron = numberOfInputsInNeuron,
                numberOfNeurons = numberOfNeurons,
                neurons = neurons,
            )
        }
    }

    fun forwardPass(inputs: List<Value>): List<Value> {
        require(inputs.size == numberOfInputsInNeuron)
        return neurons.map { it.forwardPass(inputs) }
    }

    fun adjustParameters(learningRate: Double) = neurons.forEach { it.adjustParameters(learningRate) }
}