package ge.nika

import ge.nika.Value.Companion.asValue
import ge.nika.visualisation.drawToSvg

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
   val a = 2.asValue("a")
   val b = (-3).asValue("b")
   val c = 10.asValue("c")
   val e = a * b; e.label = "e"
   val d = e + c; d.label = "d"
   val f = (-2).asValue("f")
   val outPut = d * f; outPut.label = "Out"
   drawToSvg(outPut)
}