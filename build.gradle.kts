plugins {
	java
	id("org.cadixdev.licenser") version "0.6.1"
	id("net.kyori.blossom") version "1.3.1"
}

java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(17))
	}
}

defaultTasks("licenseFormat", "build")

allprojects {
	repositories {
		maven("https://maven.fabricmc.net/")
		mavenCentral()
		gradlePluginPortal()
	}
}

subprojects {
	apply {
		plugin("java")
		plugin("org.cadixdev.licenser")
		plugin("net.kyori.blossom")
	}

	license {
		header(rootProject.file("LICENSE_HEAD"))
		include("**/*.java")
		newLine(true)
	}

	tasks.withType<JavaCompile> {
		options.encoding = "UTF-8"
	}

	tasks.withType<Jar> {
		from("../LICENSE")
		dependsOn(tasks.named("licenseFormat"))
	}

	group = project.group
	version = project.version
	description = project.description
}
