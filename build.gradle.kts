import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.compose.compose

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
    implementation(compose.desktop.currentOs);
    implementation("com.mohamedrejeb.richeditor:richeditor-compose:1.0.0-beta03")
    implementation("org.jetbrains.compose.material3:material3-desktop:1.5.3")
    implementation("org.xerial:sqlite-jdbc:3.42.0.0")
    implementation("org.jetbrains.exposed", "exposed-core", "0.44.0")
    implementation("org.jetbrains.exposed", "exposed-dao", "0.44.0")
    implementation("org.jetbrains.exposed", "exposed-jdbc", "0.44.0")
    implementation("org.xerial:sqlite-jdbc:3.43.2.1")
}


compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            macOS{
                iconFile.set(File("top.svg"));
                targetFormats(TargetFormat.Dmg);
            }

        }

    }
}