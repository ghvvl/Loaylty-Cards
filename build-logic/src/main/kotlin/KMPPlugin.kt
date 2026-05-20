import com.android.build.api.dsl.KotlinMultiplatformAndroidLibraryTarget
import com.android.build.gradle.internal.scope.ProjectInfo.Companion.getBaseName
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.the
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import kotlin.jvm.kotlin

class KMPPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        val libs = project.the<LibrariesForLibs>()

        project.plugins.apply(libs.plugins.kotlin.multiplatform.get().pluginId)
        project.plugins.apply(libs.plugins.android.kotlin.multiplatform.library.get().pluginId)

        val targets = project.extensions
            .getByType(KotlinMultiplatformExtension::class.java).targets
        targets.withType(KotlinMultiplatformAndroidLibraryTarget::class.java)
            .configureEach {
                compileSdk = libs.versions.android.compileSdk.get().toInt()
                namespace = "com.vvl.loyalty_cards.${project.displayName.format()}"
            }
    }

    private fun String.format(): String =
        removePrefix("project ':").replace(":", ".").removeSuffix("'")
}