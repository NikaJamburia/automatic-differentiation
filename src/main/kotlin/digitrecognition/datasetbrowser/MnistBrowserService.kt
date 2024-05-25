package ge.nika.digitrecognition.datasetbrowser

import ge.nika.digitrecognition.data.MnistDatasetType
import ge.nika.digitrecognition.data.MnistDigit

class MnistBrowserService {
    fun getRandomDigit(datasetType: MnistDatasetType): MnistDigit {
        return MnistDigit.randomFrom(datasetType)
    }
}