import org.gradle.kotlin.dsl.maven

plugins {
	`java-library`
}

java.toolchain.languageVersion.set(JavaLanguageVersion.of(8))

repositories {
	mavenLocal()
	mavenCentral()
	maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
	maven("https://repo.codemc.org/repository/nms/")
}