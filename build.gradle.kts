plugins {
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.serialization") version "1.5.20"
}

group = "ge.nika"
version = "1.0-SNAPSHOT"

val kotlinDLVersion by extra { "0.5.2" }
val jupiterVersion by extra { "5.6.3" }
val http4kVersion by extra { "5.20.0.0" }


repositories {
    mavenCentral()
}

dependencies {
    implementation("guru.nidi:graphviz-kotlin:0.18.1")
    implementation("com.eclipsesource.j2v8:j2v8_win32_x86_64:4.6.0")
    implementation("org.jetbrains.kotlinx:kotlin-deeplearning-dataset:$kotlinDLVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.11.0")

    implementation ("org.http4k:http4k-client-okhttp:$http4kVersion")
    implementation ("org.http4k:http4k-core:$http4kVersion")
    implementation ("org.http4k:http4k-format-kotlinx-serialization:$http4kVersion")
    implementation ("org.http4k:http4k-server-netty:$http4kVersion")

    testImplementation("io.kotest:kotest-assertions-core:5.9.0")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$jupiterVersion")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(11)
}