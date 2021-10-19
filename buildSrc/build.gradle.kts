import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
    kotlin("jvm") version "1.3.40"
}

repositories {
    jcenter()
    google()
    maven { setUrl("https://dl.bintray.com/kotlin/kotlin-eap") }
}
dependencies {
    // android gradle plugin, required by custom plugin
    implementation("com.android.tools.build:gradle:4.0.1")

    // kotlin plugin, required by custom plugin
    implementation(kotlin("gradle-plugin", "1.3.72"))
    implementation(kotlin("stdlib-jdk8"))
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}