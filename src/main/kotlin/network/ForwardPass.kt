package ge.nika.network

import ge.nika.Value

fun MLP.performForwardPasses(
    params: List<ForwardPassParams>
): ForwardPassResult {
    val predictionToTarget = params.associate {
        this.forwardPass(it.inputs).unwrapSingleValue() to it.target
    }

    val loss = predictionToTarget.map { (prediction, target) ->
        loss(target, prediction)
    }.sum()

    loss.gradient = 1.0
    loss.propagateGradientsBackward()

    return ForwardPassResult(
        predictions = predictionToTarget.keys.toList(),
        loss = loss,
    )
}

data class ForwardPassParams(
    val inputs: List<Value>,
    val target: Value
) {
    companion object {
        infix fun List<Value>.withTarget(value: Value): ForwardPassParams =
            ForwardPassParams(this, value)
    }
}

data class ForwardPassResult(
    val predictions: List<Value>,
    val loss: Value
) {
    override fun toString(): String {
        return """
            {
                loss=${loss.data}, 
                predictions=${predictions.map { it.data }}
            }
        """.trimIndent()
    }
}