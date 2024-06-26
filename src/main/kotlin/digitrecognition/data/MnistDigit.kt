package ge.nika.digitrecognition.data

import ge.nika.Value.Companion.asValue
import digitrecognition.io.DigitRecognitionExpectedOutput
import digitrecognition.io.DigitRecognitionTrainingParams
import kotlinx.html.*
import kotlinx.html.stream.appendHTML
import org.jetbrains.kotlinx.dl.dataset.DataBatch
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

        fun DataBatch.toListOfDigits(): List<MnistDigit> {
            return (0..<size).map {
                MnistDigit(
                    pixels = x[it],
                    label = y[it]
                )
            }
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

    fun toTrainingParams(): DigitRecognitionTrainingParams = DigitRecognitionTrainingParams(
        inputLayer = pixels.map { it.toDouble().asValue() },
        expectedOutput = DigitRecognitionExpectedOutput.ofFloat(label),
    )
}