plugins {
	id("project.publishing-conventions")
  alias(libs.plugins.shadow)
}

dependencies {
	compileOnly(libs.spigot)
  implementation(libs.annotations)
}

tasks {
  shadowJar {
    val version = property("version") as String
    
    archiveFileName.set("title-api-v$version.jar")
    
    destinationDirectory.set(file("$rootDir/bin/"))
    minimize()
    
    relocate("org.jetbrains.annotations", "net.velex.title.annotations")
  }
  
  clean {
    delete("$rootDir/bin/")
  }
}