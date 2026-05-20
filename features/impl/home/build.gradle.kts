plugins {
    id("com.vvl.kmpPlugin")
    id("com.vvl.cmpPlugin")
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    jvm()

    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            api(projects.features.api.home)
            implementation(projects.features.api.root)
            implementation(projects.features.common)

            implementation(libs.compose.components.resources)
            implementation(libs.compose.material3)

            implementation(libs.decompose)
            implementation(libs.decompose.compose)
            implementation(libs.decompose.compose.experimental)
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin)
        }
    }
}