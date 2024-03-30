rootProject.name = "AbyssCraft"
include("Client")

project(":Client").name = "AbyssCraftClientMIA"

pluginManagement {
	repositories {
		maven("https://maven.fabricmc.net/")
		mavenCentral()
		gradlePluginPortal()
	}
}