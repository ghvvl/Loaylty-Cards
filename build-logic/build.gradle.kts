import org.gradle.accessors.dm.LibrariesForLibs

plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.gradle)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

gradlePlugin {
    plugins {
        register("kmpPlugin") {
            id = "com.vvl.kmpPlugin"
            implementationClass = "KMPPlugin"
        }
        register("cmpPlugin") {
            id = "com.vvl.cmpPlugin"
            implementationClass = "CMPPlugin"
        }
    }
}