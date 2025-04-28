plugins {
    id("fabric-loom") version "1.10-SNAPSHOT"
}

loom {
    accessWidenerPath = file("src/main/resources/abysscraftclient.accesswidener")
}

dependencies {
    // To change the versions see the gradle.properties file
    minecraft("com.mojang:minecraft:${providers.gradleProperty("minecraft_version").get()}")
    mappings("net.fabricmc:yarn:${providers.gradleProperty("yarn_mappings").get()}:v2")
    modImplementation("net.fabricmc:fabric-loader:${providers.gradleProperty("loader_version").get()}")

    // Fabric API. This is technically optional, but you probably want it anyway.
    modImplementation("net.fabricmc.fabric-api:fabric-api:${providers.gradleProperty("fabric_version").get()}")
}

blossom {
    val src = "src/main/java/net/playl/abysscraft/Client.java"
    replaceToken("{version}", project.version, src)
    replaceToken("{modid}", project.name, src)
}

tasks {
    processResources {
        val props = mapOf(
            "version" to project.version,
            "github_url" to providers.gradleProperty("githubUrl").get(),
            "description" to project.description,
            "loader_version" to providers.gradleProperty("loader_version").get(),
            "minecraft_version" to providers.gradleProperty("minecraft_version").get(),
        )
        filesMatching("fabric.mod.json") {
            expand(props)
        }

    }
}