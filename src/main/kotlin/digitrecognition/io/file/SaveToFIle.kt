package ge.nika.digitrecognition.io.file

import ge.nika.digitrecognition.io.file.MLPFileDto.Companion.toFileDto
import ge.nika.network.MLP
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

private val jsonsDirectory: String = "savednetworks"
private val json = Json { prettyPrint = true }

fun MLP.saveToFile(fileName: String) {
    val content = json.encodeToString(this.toFileDto())
    File("$jsonsDirectory/$fileName").writeText(content)
}

fun loadMlpFromFile(fileName: String): MLP {
    val file = File("$jsonsDirectory/$fileName")
    val dto = json.decodeFromString<MLPFileDto>(file.readText())
    return dto.toMLP()
}