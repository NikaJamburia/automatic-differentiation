package ge.nika.digitrecognition

class DigitRecognitionNetworkOutput {
}

fun MnistDigit.toOutputLayer(): List<Double> {
    val zeroList = mutableListOf<Double>(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)
    zeroList[label.toInt()] = 1.0
    return zeroList.toList()
}