plugins {
    kotlin("jvm") version "2.0.10"
}

group = "de.v85n"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    // https://kotest.io/docs/assertions/assertions.html
    testImplementation("io.kotest:kotest-assertions-core:5.9.1")

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}