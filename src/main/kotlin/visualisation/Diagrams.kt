package ge.nika.visualisation

import ge.nika.Value
import guru.nidi.graphviz.attribute.*
import guru.nidi.graphviz.attribute.Records.rec
import guru.nidi.graphviz.engine.Format
import guru.nidi.graphviz.engine.Graphviz
import guru.nidi.graphviz.engine.GraphvizV8Engine
import guru.nidi.graphviz.graph
import guru.nidi.graphviz.model.Factory
import guru.nidi.graphviz.model.Node
import java.io.File
import java.util.UUID
import java.util.UUID.randomUUID

fun drawToSvg(
    value: Value,
    fileName: String = "default"
) {
    val graph = graph(directed = true) {
        graph[Rank.dir(Rank.RankDir.LEFT_TO_RIGHT)]
        node[Shape.RECORD]
    }
    val traced = traceOperations(value).toMap()
    linkNodes(value, traced).forEach { graph.add(it) }
    println("Diagram generated! Writing to file")

    Graphviz
        .useEngine(listOf(GraphvizV8Engine()))

    Graphviz
        .fromGraph(graph)
        .render(Format.SVG)
        .toFile(File("diagrams/$fileName.svg"))
}

private fun linkNodes(value: Value, nodes: Map<Value, Node>): List<Node> {
    val node = nodes[value]!!
    return buildList {
        if (value.isCalculated) {
            val operationNode = Factory.node(randomUUID().toString())
                .with(Records.of(rec(value.parentOperationName)))
                .with(Shape.CIRCLE)
            add(operationNode.link(node))
            value.parents.forEach { operand ->
                val operandNode = nodes[operand]!!
                add(operandNode.link(operationNode))
                addAll(linkNodes(operand, nodes))
            }

        }
    }
}

private fun traceOperations(value: Value): List<Pair<Value, Node>> {
    return buildList {
        val node = Factory.node("${value.data}").with(Shape.RECORD).with(getRecordsFor(value))
        add(value to node)
        addAll(value.parents.flatMap { traceOperations(it) })
    }

}

private fun getRecordsFor(value: Value): Attributes<ForNode> {
    val recs = buildList {
        if (value.label != null) {
            add(rec(value.label!!))
        }
        add(rec("data: ${value.data}"))
        add(rec("grad: ${value.gradient}"))
    }.toTypedArray()

    return Records.of(*recs)
}