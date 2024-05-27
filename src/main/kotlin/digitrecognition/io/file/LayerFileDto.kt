package ge.nika.digitrecognition.io.file

import ge.nika.digitrecognition.io.file.NeuronFileDto.Companion.toFileDto
import ge.nika.network.Layer
import kotlinx.serialization.Serializable

@Serializable
data class LayerFileDto(
    val numberOfInputsInNeuron: Int,
    val numberOfNeurons: Int,
    val neuronDtos: List<NeuronFileDto>
) {
    companion object {
        fun Layer.toFileDto(): LayerFileDto = LayerFileDto(
            numberOfInputsInNeuron = numberOfInputsInNeuron,
            numberOfNeurons = numberOfNeurons,
            neuronDtos = neurons.map { it.toFileDto() })
    }

    fun toLayer(): Layer {
        val neurons = neuronDtos.map { it.toNeuron() }
        return Layer.ofNeurons(neurons)
    }
}