import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm") version "1.9.0"
    id("org.jetbrains.compose") version "1.5.3"
}

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

dependencies {
    implementation(compose.desktop.currentOs);

    implementation("org.jetbrains.compose.material3:material3-desktop:1.5.3")
}
compose.desktop {
    application {
        mainClass = "MainKt"
    }
}