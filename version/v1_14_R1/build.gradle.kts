plugins {
  id("project.publishing-conventions")
}

dependencies {
  api(project(":api"))
  
  compileOnly(libs.nms14)
  implementation(libs.annotations)
}
