plugins {
    alias(libs.plugins.kotlin.multiplatform)
}

kotlin {
    jvm()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Rinku"
            export(libs.deeplinks)
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.features.api.deepLinks)

            implementation(libs.coroutines)
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin)
            api(libs.deeplinks)
        }
    }
}
