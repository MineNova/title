plugins {
	id("project.publishing-conventions")
  alias(libs.plugins.shadow)
}

dependencies {
	api(project(":api"))
  api(project(":v1_8_R3"))
  api(project(":v1_16_R3"))
  api(project(":v1_17_R1"))
  api(project(":v1_18_R1"))
  api(project(":v1_19_R1"))
  
	compileOnly(libs.spigot)
  implementation(libs.annotations)
}

tasks {
  shadowJar {
    val version = property("version") as String
    
    archiveFileName.set("title-implementation-v$version.jar")
    
    destinationDirectory.set(file("$rootDir/bin/"))
    minimize()
    
    relocate("org.jetbrains.annotations", "net.velex.title.annotations")
  }
  
  clean {
    delete("$rootDir/bin/")
  }
}
