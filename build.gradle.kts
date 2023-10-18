plugins {
    kotlin("jvm") version "1.9.0"
    id("org.jetbrains.compose") version "1.5.3"
}

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    maven("https://github.com/MohamedRejeb/Compose-Rich-Editor")
    google()
}

dependencies {
    implementation("com.mohamedrejeb.richeditor:richeditor-compose:1.0.0-beta03")
    implementation(compose.desktop.currentOs);
    implementation("org.jetbrains.compose.material3:material3-desktop:1.5.3")
}
compose.desktop {
    application {
        mainClass = "MainKt"
    }
}