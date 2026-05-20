plugins {
    id("com.vvl.kmpPlugin")
    id("com.vvl.cmpPlugin")
}

kotlin {
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(projects.data.storage.api.widget)

            api(projects.features.api.widgetsList)
            implementation(projects.features.api.root)
            implementation(projects.features.api.widget)

            implementation(libs.compose.components.resources)
            implementation(libs.compose.material3)

            implementation(libs.decompose)
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin)
        }
    }
}