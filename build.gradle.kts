plugins {
    id("org.jetbrains.kotlin.jvm") version "2.1.0"
}

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(21)
}
