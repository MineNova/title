plugins {
  id("project.publishing-conventions")
  id("io.papermc.paperweight.userdev") version("1.5.5")
}

dependencies {
  api(project(":api"))
  
  paperDevBundle(libs.versions.paperweight119)
  implementation(libs.annotations)
}