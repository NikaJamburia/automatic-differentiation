package ge.nika

import ge.nika.Value.Companion.asValue
import ge.nika.network.MLP
import ge.nika.visualisation.drawToSvg

fun main() {

   val mlp = MLP(
      numberOfOriginalInputs = 3,
      layerSizes = listOf(4, 4, 1)
   )

   val inputs = listOf(
      2.asValue(),
      3.asValue(),
      (-1).asValue()
   )
   val out = mlp.call(inputs)[0]
   println(out)
   out.gradient = 1.0
   out.propagateGradientsBackward()
   drawToSvg(out)
}