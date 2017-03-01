import org.gradle.script.lang.kotlin.compile
import org.gradle.script.lang.kotlin.dependencies
import org.gradle.script.lang.kotlin.project

dependencies {
    compile(project(":kotlin-akka-extensions"))
    compile("org.jetbrains.kotlin:kotlin-stdlib:1.1.0")
}
