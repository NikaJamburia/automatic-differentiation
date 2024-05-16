package network

import ge.nika.network.MLP
import io.kotest.assertions.asClue
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class MLPTest {
    @Test
    fun `correctly assigns number of inputs and neurons to each layer`() {
        val mlp = MLP(
            numberOfOriginalInputs = 3,
            layerSizes = listOf(5, 5, 1)
        )

        mlp.layers.size shouldBe 3

        mlp.layers[0].asClue {
            it.numberOfNeurons shouldBe 5
            it.numberOfInputsInNeuron shouldBe 3
        }

        mlp.layers[1].asClue {
            it.numberOfNeurons shouldBe 5
            it.numberOfInputsInNeuron shouldBe 5
        }

        mlp.layers[2].asClue {
            it.numberOfNeurons shouldBe 1
            it.numberOfInputsInNeuron shouldBe 5
        }
    }
}