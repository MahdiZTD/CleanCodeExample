buildscript {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.0.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.10")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.28.1-alpha")
        classpath("android.arch.navigation:navigation-safe-args-gradle-plugin:1.0.0")
//        classpath("com.google.gms:google-services:4.3.4")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven(url = "https://jitpack.io")
        maven(url = "https://repo.eclipse.org/content/repositories/paho-releases/")
        maven(url = "http://dl.bintray.com/journeyapps/maven")
    }
}

tasks {
    val clean by registering(Delete::class) {
        delete(buildDir)
    }
}