import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    dependencies {
        implementation(projects.app.common)
        implementation(compose.desktop.currentOs)
    }
}

compose.desktop {
    application {
        mainClass = "com.vvl.loyalty_cards.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.vvl.ylc"
            packageVersion = "1.0.0"
        }
    }
}

