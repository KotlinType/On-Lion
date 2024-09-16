rootProject.name = "new.dark.ktor-sample"

plugins {
    kotlin("jvm") version "2.0.20" apply false
    id("io.ktor.plugin") version "2.3.12" apply false
    id("com.google.devtools.ksp") version "2.0.20-1.0.25" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.20" apply false
}
