import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.21"
    `maven-publish`
    kotlin("plugin.serialization") version "1.8.21"
    id("org.jetbrains.dokka") version "0.9.17"
}

group = "com.github.kurenairyu"
version = "0.0.1"

repositories {
    mavenLocal()
    mavenCentral()
}

object Versions {
    const val jackson = "2.14.1"
    const val log4j = "2.17.2"
    const val disruptor = "3.4.4"
    const val ktor = "2.3.0"
}

dependencies {
    api("org.jetbrains.kotlin", "kotlin-reflect")
    api("org.jetbrains.kotlin", "kotlin-stdlib-jdk8")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")

    api("com.fasterxml.jackson.core:jackson-databind:${Versions.jackson}")
    api("com.fasterxml.jackson.module:jackson-module-kotlin:${Versions.jackson}")
    api("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:${Versions.jackson}")
    api("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:${Versions.jackson}")

    api("io.ktor:ktor-client-core:${Versions.ktor}")
    api("io.ktor:ktor-client-okhttp:${Versions.ktor}")
    api("io.ktor:ktor-client-encoding:${Versions.ktor}")

    api(platform("io.projectreactor:reactor-bom:2020.0.18"))
    api("io.projectreactor:reactor-core")

    testImplementation(kotlin("test"))
}

java {
    withJavadocJar()
    withSourcesJar()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "17"
}
