plugins {
    kotlin("jvm") version "1.9.23"
}

group = "ge.nika"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("guru.nidi:graphviz-kotlin:0.18.1")
    implementation("com.eclipsesource.j2v8:j2v8_win32_x86_64:4.6.0")

    testImplementation("io.kotest:kotest-assertions-core:5.9.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(11)
}