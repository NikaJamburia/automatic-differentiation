plugins {
    kotlin("jvm") version "1.9.23"
}

group = "ge.nika"
version = "1.0-SNAPSHOT"

val kotlinDLVersion by extra { "0.5.2" }
val jupiterVersion by extra { "5.6.3" }


repositories {
    mavenCentral()
}

dependencies {
    implementation("guru.nidi:graphviz-kotlin:0.18.1")
    implementation("com.eclipsesource.j2v8:j2v8_win32_x86_64:4.6.0")

    implementation("org.jetbrains.kotlinx:kotlin-deeplearning-dataset:$kotlinDLVersion")


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