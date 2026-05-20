import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.the

class CMPPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        val libs = project.the<LibrariesForLibs>()

        project.plugins.apply(libs.plugins.compose.compiler.get().pluginId)
        project.plugins.apply(libs.plugins.compose.multiplatform.get().pluginId)
    }
}