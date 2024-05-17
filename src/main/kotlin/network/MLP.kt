package ge.nika.network

import ge.nika.Value

class MLP(
    private val numberOfOriginalInputs: Int,
    layerSizes: List<Int>,
) {
    val layers = buildList<Layer> {
         layerSizes.forEach {
             val lastLayerSize = lastOrNull()?.numberOfNeurons ?: numberOfOriginalInputs
             add(
                 Layer(
                     numberOfInputsInNeuron = lastLayerSize,
                     numberOfNeurons = it
                 )
             )
         }
     }

    fun forwardPass(inputs: List<Value>): List<Value> {
        return layers.fold(inputs) { previousLayerOutputs, layer ->
            layer.forwardPass(previousLayerOutputs)
        }.map {
            it.label = "FWD pass result"
            it
        }
    }

    fun adjustParameters(learningRate: Double) = layers.forEach { it.adjustParameters(learningRate) }
}