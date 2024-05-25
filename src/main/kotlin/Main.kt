package ge.nika

import ge.nika.digitrecognition.data.MnistDatasetType
import ge.nika.digitrecognition.data.MnistDigit

fun main() {

    val digit = MnistDigit.randomFrom(MnistDatasetType.TRAIN)
    println(digit.toHtml())

//    MnistBrowserServer().start()
}