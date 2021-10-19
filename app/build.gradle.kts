import dependencies.*
import dependencies.Dependencies.CONSTRAINT_LAYOUT
import dependencies.Dependencies.MULTIDEX

plugins {
    id("com.android.application")
    id("androidx.navigation.safeargs")
    id("dagger.hilt.android.plugin")
//    id("com.google.gms.google-services")

    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.2")

    defaultConfig {
        applicationId = "com.mahdi.ztd"
        minSdkVersion(17)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
        vectorDrawables.useSupportLibrary = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true
    }

    buildFeatures {

        dataBinding = true

        // for view binding:
        // viewBinding = true
    }

//    signingConfigs {
//        getByName("debug") {
//            keyAlias = "androiddebugkey"
//            keyPassword = "android"
//            storeFile = rootProject.file("debug.keystore")
//            storePassword = "android"
//        }
//    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
        freeCompilerArgs = listOf(
            "-Xuse-experimental=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-Xuse-experimental=kotlinx.coroutines.ObsoleteCoroutinesApi"
        )
    }

}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(platform(Dependencies.FIREBASE_BOM))

    retrofit()
    chuck()

    glide()

    kotlin()
    androidx()
    coroutines()
    material()

    implementation(MULTIDEX)
    implementation(CONSTRAINT_LAYOUT)

    hilt()
    hiltViewModel()

    lifeCycle()
    navigation()
    paging()

    mockito()
    unitTest()

    avLoading()



//    firebaseMessaging()
//    firebaseAnalytics()
//    firebaseInAppMessaging()

}
