plugins {
    alias(libs.plugins.kotlin.multiplatform)
}

kotlin {
    jvm()

    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(projects.common)

            implementation(libs.decompose)
            implementation(libs.coroutines)
        }
    }
}