package ge.nika.digitrecognition.io.file

import ge.nika.digitrecognition.io.file.LayerFileDto.Companion.toFileDto
import ge.nika.network.MLP

data class MLPFileDto(
    val layerDtos: List<LayerFileDto>
) {
    companion object {
        fun MLP.toFileDto(): MLPFileDto = MLPFileDto(
            layers.map { it.toFileDto() }
        )
    }

    fun toMLP(): MLP = MLP.ofLayers(layerDtos.map { it.toLayer() })
}