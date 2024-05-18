package ge.nika

import ge.nika.digitrecognition.MnistDatasetType
import ge.nika.digitrecognition.MnistDigit

fun main() {

    val digit = MnistDigit.randomFrom(MnistDatasetType.TRAIN)
    println(digit.toHtml())

}