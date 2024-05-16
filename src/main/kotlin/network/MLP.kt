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

    fun call(inputs: List<Value>): List<Value> {
        return layers.fold(inputs) { previousLayerOutputs, layer ->
            layer.call(previousLayerOutputs)
        }
    }
}