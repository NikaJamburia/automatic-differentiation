package ge.nika.network

import ge.nika.Value.Companion.asValue
import ge.nika.network.ForwardPassParams.Companion.withTarget

/**
 * This object is a small demonstration of the MLP capabilities. It iterates 100 times with fixed learning rate
 */
object Demo {

    fun run() {
        val mlp = MLP(
            numberOfOriginalInputs = 3,
            layerSizes = listOf(4, 4, 1)
        )

        val inputs: List<ForwardPassParams> =
            listOf(
                values(2.0, 3.0, -1.0) withTarget 1.asValue(),
                values(3.0, -1.0, 0.5) withTarget (-1).asValue(),
                values(0.5, 1.0, 1.0) withTarget (-1).asValue(),
                values(1.0, 1.0, -1.0) withTarget 1.asValue(),
            )
        val learningRate = 0.01

        repeat(100) {
            val result = mlp.performForwardPasses(inputs)
            println("Result No.$it: $result")
            mlp.adjustParameters(learningRate)
        }
    }
}