package ge.nika.network

import ge.nika.Value
import ge.nika.operations.ActivationFunction

class MLP private constructor(
    val layers: List<Layer>
) {
    companion object {
        fun ofLayers(layers: List<Layer>): MLP = MLP(layers)

        fun generate(
            activationFunction: ActivationFunction = ActivationFunction.Tanh,
            numberOfOriginalInputs: Int,
            layerSizes: List<Int>,
        ): MLP {
            val layers = buildList<Layer> {
                layerSizes.forEach {
                    val lastLayerSize = lastOrNull()?.numberOfNeurons ?: numberOfOriginalInputs
                    add(
                        Layer.generate(
                            numberOfInputsInNeuron = lastLayerSize,
                            numberOfNeurons = it,
                            activationFunction = activationFunction
                        )
                    )
                }
            }
            return MLP(layers)
        }
    }

    fun forwardPass(inputs: List<Value>): List<Value> {
        return layers.fold(inputs) { previousLayerOutputs, layer ->
            layer.forwardPass(previousLayerOutputs)
        }
    }

    fun adjustParameters(learningRate: Double) = layers.forEach { it.adjustParameters(learningRate) }
}