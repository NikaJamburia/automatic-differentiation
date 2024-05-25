package ge.nika

import ge.nika.digitrecognition.MnistDatasetType
import ge.nika.digitrecognition.MnistDigit
import ge.nika.digitrecognition.datasetbrowser.web.MnistBrowserServer
import org.jetbrains.kotlinx.dl.dataset.embedded.fashionMnist

fun main() {

    val digit = MnistDigit.randomFrom(MnistDatasetType.TRAIN)
    println(digit.toHtml())

//    MnistBrowserServer().start()
}