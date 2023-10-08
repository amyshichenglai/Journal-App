import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm") version "1.9.10" // Update to Kotlin Compiler 1.9.10
    id("org.jetbrains.compose") version "1.5.1" // Update to Compose version 1.5.1
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

val ktor_version: String by project

dependencies {
    implementation(compose.desktop.currentOs)
    implementation("io.vavr:vavr:0.10.4")
    implementation("io.ktor:ktor-client-core:2.3.5") // Update to Ktor version 2.3.5
    implementation("io.ktor:ktor-client-cio:2.3.5") // Update to Ktor version 2.3.5
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.9.10") // Update to Kotlin 1.9.10 and JDK 8 variant

}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "application"
            packageVersion = "1.0.0"
        }
    }
}
