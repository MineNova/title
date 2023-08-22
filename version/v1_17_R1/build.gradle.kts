plugins {
  id("project.publishing-conventions")
}

dependencies {
  api(project(":api"))
  
  compileOnly(libs.nms117)
  implementation(libs.annotations)
}