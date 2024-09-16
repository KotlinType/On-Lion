
val kotlin_version: String by project
val logback_version: String by project
val exposed_version: String by project
val dagger_version: String by project

plugins {
    kotlin("jvm")
    id("io.ktor.plugin")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.plugin.serialization")
}

group = "net.dark"
version = "0.0.1"

application {
    mainClass.set("net.dark.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    google()
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap") }
    maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots") }
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-websockets-jvm")
    implementation("io.ktor:ktor-server-content-negotiation-jvm")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm")

    implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-dao:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
    implementation("org.postgresql:postgresql:42.7.4")

    implementation("com.google.dagger:dagger:$dagger_version")
    ksp("com.google.dagger:dagger-compiler:$dagger_version")

    implementation("io.ktor:ktor-auth-jwt:1.6.8")
    implementation("org.mindrot:jbcrypt:0.4")

    implementation("io.github.cdimascio:dotenv-kotlin:6.2.2")

    implementation("org.apache.commons:commons-email:1.6.0")

    implementation("io.ktor:ktor-server-cio-jvm")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-test-host-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}
