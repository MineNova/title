plugins {
	id("project.publishing-conventions")
}

dependencies {
	api(project(":api"))
 
	compileOnly(libs.nms18)
  implementation(libs.annotations)
}