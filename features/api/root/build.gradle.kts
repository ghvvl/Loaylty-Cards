plugins {
    alias(libs.plugins.kotlin.multiplatform)
}

kotlin {
    jvm()
    
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            api(projects.features.api.home)
            api(projects.features.api.loyaltyCardDetails)
            api(projects.features.api.addLoyaltyCard)
            api(projects.features.api.widgetDetails)

            implementation(libs.decompose)
        }
    }
}