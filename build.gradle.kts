plugins {
    kotlin("jvm") version "1.9.24"
    `maven-publish`
    kotlin("plugin.serialization") version "1.9.24"
    id("org.jetbrains.dokka") version "0.9.17"
}

group = "com.github.kurenairyu"
version = "0.0.1"

repositories {
    mavenLocal()
    mavenCentral()
}

object Versions {
    const val jackson = "2.18.0"
    const val log4j = "2.17.2"
    const val disruptor = "3.4.4"
    const val ktor = "2.3.12"
}

dependencies {
    api("org.jetbrains.kotlin", "kotlin-reflect")
    api("org.jetbrains.kotlin", "kotlin-stdlib-jdk8")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")

    api("com.fasterxml.jackson.core:jackson-databind:${Versions.jackson}")
    api("com.fasterxml.jackson.module:jackson-module-kotlin:${Versions.jackson}")
    api("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:${Versions.jackson}")
    api("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:${Versions.jackson}")

    api("io.ktor:ktor-client-core-jvm:${Versions.ktor}")
    api("io.ktor:ktor-client-okhttp-jvm:${Versions.ktor}")
    api("io.ktor:ktor-client-encoding-jvm:${Versions.ktor}")

    api(platform("io.projectreactor:reactor-bom:2020.0.18"))
    api("io.projectreactor:reactor-core")

    testImplementation(kotlin("test"))
}

java {
    withJavadocJar()
    withSourcesJar()
}
