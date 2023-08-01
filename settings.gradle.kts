rootProject.name = "title"

include(":base", ":plugin")
includePrefixed("v1_8_R3", "v1_9_R2", "v1_10_R1", "v1_11_R1", "v1_12_R1")

fun includePrefixed(vararg paths: String) {
	for (path in paths) include(":adapt:$path")
}