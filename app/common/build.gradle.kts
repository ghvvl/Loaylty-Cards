plugins {
    id("com.vvl.kmpPlugin")
    id("com.vvl.cmpPlugin")
}

kotlin {
    //jvm()

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "App"
            export(libs.decompose)
            export(libs.essenty)
            export(libs.essenty.backHandler)
            export(libs.essenty.stateKeeper)
            export(projects.features.impl.widget)
            export(libs.deeplinks)
            binaryOption("bundleId", "com.vvl.ylc.app")
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.splash)
            implementation(libs.androidx.activity)
            implementation(libs.koin.android)
        }
        commonMain.dependencies {
            implementation(projects.data.storage.impl.database)
            implementation(projects.data.storage.impl.loyaltyCards)
            implementation(projects.data.storage.impl.widget)
            implementation(projects.features.impl.root)
            implementation(projects.features.impl.loyaltyCardsList)
            implementation(projects.features.impl.loyaltyCardDetails)
            implementation(projects.features.impl.addLoyaltyCard)
            implementation(projects.features.impl.deepLinks)
            implementation(projects.features.impl.widget)
            implementation(projects.features.impl.home)
            implementation(projects.features.impl.widgetsList)
            implementation(projects.features.impl.widgetDetails)
            implementation(projects.features.common)

            implementation(libs.compose.material3)

            implementation(libs.decompose)
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin)
        }
        iosMain.dependencies {
            api(projects.features.impl.widget)
            api(libs.decompose)
            api(libs.essenty)
            api(libs.essenty.backHandler)
            api(libs.essenty.stateKeeper)
            api(libs.deeplinks)
        }
    }
}
