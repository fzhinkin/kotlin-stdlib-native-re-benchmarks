import kotlinx.benchmark.gradle.JvmBenchmarkTarget
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    kotlin("multiplatform") version "2.3.20"
    id("org.jetbrains.kotlinx.benchmark") version "0.4.16"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
}

kotlin {
    jvmToolchain(21)

    jvm()
    macosArm64()
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs { nodejs() }
    js { nodejs() }

    sourceSets {
        commonMain {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-benchmark-runtime:0.4.16")
            }
        }
    }
}

benchmark {
    targets {
        register("jvm")
        register("macosArm64")
        register("wasmJs")
    }

    configurations {
        named("main") {
            advanced("jvmForks", 1)
        }
    }
}
