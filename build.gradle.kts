plugins {
    kotlin("jvm") version "2.0.0"
    id("com.gradleup.shadow") version "8.3.3"
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://oss.sonatype.org/content/groups/public/")
    maven("https://repo.xenondevs.xyz/releases")
    maven("https://repo.panda-lang.org/releases")
    maven("https://jitpack.io")
}

dependencies {
    compileOnly("com.destroystokyo.paper:paper-api:1.16.5-R0.1-SNAPSHOT")

    implementation("com.google.inject:guice:${Version.GUICE}") {
        exclude(module = "error_prone_annotations")
        exclude(module = "jakarta.inject-api")
        exclude(module = "javax.inject")
        exclude(module = "asm")
    }
    implementation("com.google.inject.extensions:guice-assistedinject:${Version.GUICE}") {
        exclude(module = "error_prone_annotations")
    }

    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))

    implementation("org.spongepowered:configurate-yaml:${Version.CONFIG}") {
        exclude(module = "error_prone_annotations")
        exclude(module = "option")
        exclude(module = "checker-qual")
    }
    implementation("org.spongepowered:configurate-extra-kotlin:${Version.CONFIG}") {
        exclude(module = "*")
    }

    implementation("xyz.xenondevs.invui:invui:${Version.INV_UI}") {
        exclude(group = "*")
    }
    implementation("xyz.xenondevs.invui:invui-core:${Version.INV_UI}") {
        exclude(module = "annotations")
    }
    implementation("xyz.xenondevs.invui:inventory-access-r7:${Version.INV_UI}") {
        exclude(module = "annotations")
    }

    implementation("dev.rollczi:litecommands-bukkit:${Version.LITE_COMMANDS}") {
        exclude(module = "annotations")
    }
    implementation("dev.rollczi:litecommands-adventure:${Version.LITE_COMMANDS}") {
        exclude(module = "annotations")
    }

    implementation("io.github.blackbaroness:duration-serializer:2.0.2") {
        exclude(module = "kotlin-stdlib")
    }

    implementation("net.kyori:adventure-text-minimessage:4.20.0") {
        exclude(module = "annotations")
    }

    implementation("net.kyori:adventure-platform-bukkit:4.3.4")
}

kotlin {
    jvmToolchain(16)
}

tasks.shadowJar {
    archiveBaseName.set("Universal")
    archiveVersion.set("2.0")

    archiveClassifier = ""
    exclude("colors.bin", "DebugProbesKt.bin")

    relocate("net.kyori", "ru.lewis.items.__relocated__.kyori")
}

class Version {
    companion object {
        const val LITE_COMMANDS = "3.9.6"
        const val CONFIG = "4.2.0"
        const val INV_UI = "1.45"
        const val GUICE = "5.1.0"
    }
}