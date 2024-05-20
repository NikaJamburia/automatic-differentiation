package ge.nika.digitrecognition.datasetbrowser

import ge.nika.digitrecognition.MnistDatasetType
import ge.nika.digitrecognition.MnistDigit

class MnistBrowserService {
    fun getRandomDigit(datasetType: MnistDatasetType): MnistDigit {
        return MnistDigit.randomFrom(datasetType)
    }
}