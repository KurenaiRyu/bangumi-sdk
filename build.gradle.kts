import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    `maven-publish`
}

group = "moe.kurenai.bgm"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
}

object Versions {
    const val jackson = "2.13.3"
    const val log4j = "2.17.2"
    const val disruptor = "3.4.4"
    const val ktor = "2.1.0"
}

dependencies {
    api("org.jetbrains.kotlin", "kotlin-reflect")
    api("org.jetbrains.kotlin", "kotlin-stdlib-jdk8")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

    api("com.fasterxml.jackson.core:jackson-databind:${Versions.jackson}")
    api("com.fasterxml.jackson.module:jackson-module-kotlin:${Versions.jackson}")
    api("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:${Versions.jackson}")
    api("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:${Versions.jackson}")

    api("org.apache.logging.log4j:log4j-core:${Versions.log4j}")
    api("org.apache.logging.log4j:log4j-api:${Versions.log4j}")

    api("io.ktor:ktor-client-core:${Versions.ktor}")
    api("io.ktor:ktor-client-okhttp:${Versions.ktor}")
    api("io.ktor:ktor-client-encoding:${Versions.ktor}")
    api("io.ktor:ktor-client-logging:${Versions.ktor}")

    api(platform("io.projectreactor:reactor-bom:2020.0.18"))
    api("io.projectreactor:reactor-core")

    api("com.lmax:disruptor:${Versions.disruptor}")

    testImplementation(kotlin("test"))
}

java {
    withSourcesJar()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "17"
}

publishing {
    publications {
        repositories {
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/KurenaiRyu/bangumi-sdk")
                credentials {
                    username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
                    password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
                }
            }
        }
    }
    publications {
        register<MavenPublication>("gpr") {
            from(components["java"])
        }
    }
}
