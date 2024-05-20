package ge.nika.digitrecognition

import ge.nika.Value
import ge.nika.Value.Companion.asValue
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
            val dataSet = MnistDigitsDataset.getDataset(datasetType)
            val randomIndex = Random.nextInt(0, dataSet.x.size-1)
            return  MnistDigit(
                pixels = dataSet.x[randomIndex],
                label = dataSet.y[randomIndex]
            )
        }
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

    fun toTrainingParams(): MnistTrainingParams = MnistTrainingParams(
        inputLayer = pixels.map { it.toDouble().asValue() },
        expectedOutput = createOutputLayer(),
    )

    private fun createOutputLayer(): List<Value> {
        val zeroList = mutableListOf(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)
        zeroList[label.toInt()] = 1.0
        return zeroList.map { it.asValue() }
    }
}