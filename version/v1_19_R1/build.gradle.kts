plugins {
  id("project.publishing-conventions")
}

dependencies {
  api(project(":api"))
  
  compileOnly(libs.nms119)
  implementation(libs.annotations)
}