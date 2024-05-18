package ge.nika.digitrecognition

import kotlinx.html.*
import kotlinx.html.stream.appendHTML
import java.io.StringWriter
import kotlin.random.Random

data class MnistDigit(
    val pixels: FloatArray,
    val label: Float
) {
    companion object {
        fun randomFrom(datasetType: MnistDatasetType): MnistDigit {
            val dataSet = MnistDataset.getDataset(datasetType)
            val randomIndex = Random.nextInt(0, dataSet.x.size-1)
            return  MnistDigit(
                pixels = dataSet.x[randomIndex],
                label = dataSet.y[randomIndex]
            )
        }
    }

    fun pixelMatrix(): List<List<Float>> {
        val outerList = buildMap<Int, MutableList<Float>> {
            repeat(28) {
                put(it, mutableListOf())
            }
        }

        pixels.forEachIndexed { index, pixelValue ->
            val rowNumber: Int = if (index == 0) { 0 } else { index/28 }
            outerList[rowNumber]?.add(pixelValue)
        }

        return outerList.values.map { it.toList() }
    }

    fun toHtml(): String {
        val writer = StringWriter().appendHTML().div {
            h3 { +"Label: $label" }
            table {
                attributes["cellspacing"] = "0"
                (0..27).forEach { x ->
                    tr {
                        (0..27).forEach { y ->
                            val index = (x * 28) + y
                            val pxValue = pixels[index]
                            val rgbCode = (255 * pxValue).toInt()
                            td {
                                style = """
                                    background-color: rgb($rgbCode, $rgbCode, $rgbCode);
                                    height: 10px;
                                    width: 10px;
                                """.trimIndent()
                            }
                        }
                    }
                }
            }
        }
        return writer.toString()
    }
}