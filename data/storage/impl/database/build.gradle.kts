plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.room)
    alias(libs.plugins.ksp)
}

kotlin {
    androidTarget()

    jvm()

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(projects.common)

            implementation(libs.room.runtime)
            implementation(libs.sqlite.bundled)

            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin)
            implementation(libs.serialization.okio)
        }
    }
}

dependencies {
    add("kspAndroid", libs.room.ksp.compiler)
    add("kspJvm", libs.room.ksp.compiler)
    add("kspIosSimulatorArm64", libs.room.ksp.compiler)
    add("kspIosX64", libs.room.ksp.compiler)
    add("kspIosArm64", libs.room.ksp.compiler)
}

room {
    schemaDirectory("$projectDir/schemas")
}

android {
    namespace = "com.vvl.loyalty_cards.data.storage.impl.database"
}