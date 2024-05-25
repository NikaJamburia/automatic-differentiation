package ge.nika.digitrecognition.data

import org.jetbrains.kotlinx.dl.dataset.OnHeapDataset
import org.jetbrains.kotlinx.dl.dataset.embedded.mnist

object MnistDigitsDataset {
    private val mnist = mnist()
    val train: OnHeapDataset = mnist.first
    val test: OnHeapDataset = mnist.second

    fun getDataset(type: MnistDatasetType): OnHeapDataset = when(type) {
        MnistDatasetType.TRAIN -> train
        MnistDatasetType.TEST -> test
    }
}

enum class MnistDatasetType {
    TRAIN, TEST
}