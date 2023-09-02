import org.gradle.kotlin.dsl.maven

plugins {
	`java-library`
}

repositories {
	mavenLocal()
	mavenCentral()
	maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
	maven("https://repo.codemc.org/repository/nms/")
}
