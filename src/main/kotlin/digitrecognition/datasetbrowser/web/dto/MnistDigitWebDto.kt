package ge.nika.digitrecognition.datasetbrowser.web.dto

import ge.nika.digitrecognition.data.MnistDigit
import kotlinx.serialization.Serializable

@Serializable
data class MnistDigitWebDto (
    val pixels: List<Float>,
    val label: Float,
    val html: String,
)

fun MnistDigit.toWebDto(): MnistDigitWebDto = MnistDigitWebDto(
    pixels = pixels.toList(),
    label = label,
    html = toHtml()
)