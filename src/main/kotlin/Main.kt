package ge.nika

import ge.nika.digitrecognition.DigitRecognitionNetwork
import ge.nika.digitrecognition.datasetbrowser.MnistBrowserService
import ge.nika.digitrecognition.datasetbrowser.web.MnistBrowserServer

fun main() {
    val train = false


    if (train) {
        val network = DigitRecognitionNetwork.new(
            layers = listOf(30, 10)
        )
        network.train(
            epochs = 3,
            batchSize = 10,
        )
        println(network.saveToFile())
    } else {
        val filename = "digit-recognition-mlp-1716847863298.json"
        val network = DigitRecognitionNetwork.fromFile(filename)
        val service = MnistBrowserService(network)
        MnistBrowserServer(service).start()
    }
}