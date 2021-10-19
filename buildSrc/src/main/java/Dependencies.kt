package dependencies

import org.gradle.api.artifacts.dsl.DependencyHandler


//region Here You Can Find every dependencies in whole project
object Dependencies {

    //@formatter:off

    //region kotlin dependencies
    const val KOTLIN =                              "org.jetbrains.kotlin:kotlin-stdlib:${Version.KOTLIN_VERSION}"
    //endregion

    //region androidx dependencies
    const val ANDROID_APPCOMPAT =                   "androidx.appcompat:appcompat:${Version.APP_COMPAT_VERSION}"
    const val ARCH_CORE_KTX =                       "androidx.core:core-ktx:${Version.CORE_KTX}"
    const val ARCH_CORE_TEST =                      "android.arch.core:core-testing:${Version.CORE_TEST_VERSION}"
    //endregion

    //region material dependencies
    const val MATERIAL =                            "com.google.android.material:material:${Version.MATERIAL_VERSION}"
    //endregion

    //region Hilt dependencies
    const val HILT_ANDROID =                        "com.google.dagger:hilt-android:${Version.HILT_VERSION}"
    const val HILT_ANDROID_COMPILER =               "com.google.dagger:hilt-android-compiler:${Version.HILT_VERSION}"
    const val HILT_VIEW_MODEL =                     "androidx.hilt:hilt-lifecycle-viewmodel:${Version.HILT_VIEW_MODEL_VERSION}"
    const val HILT_VIEW_MODEL_COMPILER =            "androidx.hilt:hilt-compiler:${Version.HILT_VIEW_MODEL_VERSION}"
    //endregion

    //region multiDex dependencies
    const val MULTIDEX=                             "androidx.multidex:multidex:${Version.MULTIDEX_VERSION}"
    //endregion

    //region view dependencies such as constraint layout
    const val CONSTRAINT_LAYOUT =                   "androidx.constraintlayout:constraintlayout:${Version.CONSTRAINT_VERSION}"
    //endregion

    //region lifecycle components dependencies
    const val LIVE_DATA =                           "androidx.lifecycle:lifecycle-livedata-ktx:${Version.LIFECYCLE_VERSION}"
    const val VIEW_MODEL=                           "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.LIFECYCLE_VERSION}"
    const val LIFECYCLE_RUNTIME =                   "androidx.lifecycle:lifecycle-runtime-ktx:${Version.LIFECYCLE_VERSION}"
    //endregion

    //region image loader dependencies
    const val GLIDE_COMPILER =                      "com.github.bumptech.glide:compiler:${Version.GLIDE_VERSION}"
    const val GLIDE =                               "com.github.bumptech.glide:glide:${Version.GLIDE_VERSION}"
    //endregion

    //region navigation component dependencies
    const val NAVIGATION_FRAGMENT=                  "androidx.navigation:navigation-fragment-ktx:${Version.NAVIGATION_VERSION}"
    const val NAVIGATION_UI =                       "androidx.navigation:navigation-ui-ktx:${Version.NAVIGATION_VERSION}"
    const val NAVIGATION_TEST =                     "androidx.navigation:navigation-testing:${Version.NAVIGATION_VERSION}"
    //endregion

    //region paging dependencies
    const val PAGING=                               "androidx.paging:paging-runtime-ktx:${Version.PAGING_VERSION}"
    //endregion

    //region json parser dependencies
    const val GSON =                                "com.google.code.gson:gson:${Version.GSON_VERSION}"
    //endregion

    //region Kotlin coroutines dependencies
    const val COROUTINE_CORE =                      "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.COROUTINE_VERSION}"
    const val COROUTINE_TEST =                      "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.COROUTINE_VERSION}"
    const val COROUTINES_ANDROID =                  "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.COROUTINE_VERSION}"

    //endregion

    //region retrofit dependencies
    const val RETROFIT =                            "com.squareup.retrofit2:retrofit:${Version.RETROFIT_VERSION}"
    const val RETROFIT_GSON =                       "com.squareup.retrofit2:converter-gson:${Version.RETROFIT_VERSION}"
    const val RETROFIT_HTTP_LOGGER =                "com.squareup.okhttp3:okhttp:${Version.RETROFIT_LOGGER_VERSION}"
    const val RETROFIT_HTTP_LOGGER_INTERCEPTOR =    "com.squareup.okhttp3:logging-interceptor:${Version.RETROFIT_LOGGER_VERSION}"
    //endregion

    //region test implementations

    //region mockito dependencies
    const val MOCKITO_KOTLIN =                       "com.nhaarman.mockitokotlin2:mockito-kotlin:${Version.MOCKITO_KOTLIN_VERSION}"
    const val MOCKITO_CORE =                         "org.mockito:mockito-core:${Version.MOCKITO_VERSION}"
    const val MOCKITO_INLINE =                       "org.mockito:mockito-inline:${Version.MOCKITO_INLINE_VERSION}"
    const val MOCKITO_DEX_MAKER =                    "com.linkedin.dexmaker:dexmaker-mockito:${Version.MOCKITO_DEX_MAKER_VERSION}"
    //endregion

    //region junit dependencies
    const val JUNIT =                               "junit:junit:${Version.JUNIT_VERSION}"
    const val JUNIT_ANDROIDX =                      "androidx.test.ext:junit:${Version.JUNIT_ANDROIDX_VERSION}"
    //endregion

    //region ui test dependencies
    const val ESPRESSO =                            "androidx.test.espresso:espresso-core:${Version.ESPRESSO_VERSION}"
    const val ANDROIDX_TEST_CORE =                  "androidx.test:core:${Version.ANDROIDX_TEST_CORE_VERSION}"
    //endregion
    //endregion

    //region chuck interceptor
    const val CHUCK =                               "com.readystatesoftware.chuck:library:${Version.CHUCK}"
    //endregion

    //region firebase
    const val FIREBASE_BOM =                        "com.google.firebase:firebase-bom:${Version.FIREBASE_VERSION}"
    const val FIREBASE_MESSAGING =                  "com.google.firebase:firebase-messaging-ktx"
    const val FIREBASE_ANALYTICS =                  "com.google.firebase:firebase-analytics-ktx"
    const val FIREBASE_IN_APP_MESSAGING =           "com.google.firebase:firebase-inappmessaging-display-ktx"
    //endregion

    //region loading view
    const val AV_LOADING =                          "com.wang.avi:library:${Version.AV_LOADING}"
    //endregion

    //endregion

    //@formatter:on
}
//endregion

//region dependencies function implementations
fun DependencyHandler.kotlin() {
    implementation(Dependencies.KOTLIN)
}

fun DependencyHandler.androidx() {
    implementation(Dependencies.ANDROID_APPCOMPAT)
    implementation(Dependencies.ARCH_CORE_KTX)
    testImplementation(Dependencies.ARCH_CORE_TEST)
}

fun DependencyHandler.material() {
    implementation(Dependencies.MATERIAL)
}

fun DependencyHandler.coroutines() {
    implementation(Dependencies.COROUTINES_ANDROID)
    implementation(Dependencies.COROUTINE_CORE)
    testImplementation(Dependencies.COROUTINE_TEST)
}

fun DependencyHandler.hilt() {
    implementation(Dependencies.HILT_ANDROID)
    kapt(Dependencies.HILT_ANDROID_COMPILER)
}

fun DependencyHandler.hiltViewModel() {
    implementation(Dependencies.HILT_VIEW_MODEL)
    kapt(Dependencies.HILT_VIEW_MODEL_COMPILER)
}

fun DependencyHandler.avLoading() {
    implementation(Dependencies.AV_LOADING)
}

fun DependencyHandler.retrofit() {
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RETROFIT_GSON)
    implementation(Dependencies.RETROFIT_HTTP_LOGGER)
    implementation(Dependencies.RETROFIT_HTTP_LOGGER_INTERCEPTOR)
}

fun DependencyHandler.lifeCycle() {
    implementation(Dependencies.LIVE_DATA)
    implementation(Dependencies.VIEW_MODEL)
    implementation(Dependencies.LIFECYCLE_RUNTIME)
}

fun DependencyHandler.glide() {
    kapt(Dependencies.GLIDE_COMPILER)
    implementation(Dependencies.GLIDE)
}

fun DependencyHandler.navigation() {
    implementation(Dependencies.NAVIGATION_FRAGMENT)
    implementation(Dependencies.NAVIGATION_UI)
    testImplementation(Dependencies.NAVIGATION_TEST)
}

fun DependencyHandler.paging() {
    implementation(Dependencies.PAGING)
}

fun DependencyHandler.mockito() {
    testImplementation(Dependencies.MOCKITO_KOTLIN)
    testImplementation(Dependencies.MOCKITO_CORE)
    testImplementation(Dependencies.MOCKITO_INLINE)

    androidTestImplementation(Dependencies.MOCKITO_CORE)
    androidTestImplementation(Dependencies.MOCKITO_DEX_MAKER)
}

fun DependencyHandler.unitTest() {
    testImplementation(Dependencies.JUNIT)
    androidTestImplementation(Dependencies.JUNIT_ANDROIDX)
    androidTestImplementation(Dependencies.ESPRESSO)
    androidTestImplementation(Dependencies.ANDROIDX_TEST_CORE)
}

fun DependencyHandler.chuck() {
    implementation(Dependencies.CHUCK)
}

fun DependencyHandler.firebaseMessaging() {
    implementation(Dependencies.FIREBASE_MESSAGING)
}

fun DependencyHandler.firebaseAnalytics() {
    implementation(Dependencies.FIREBASE_ANALYTICS)
}

fun DependencyHandler.firebaseInAppMessaging() {
    implementation(Dependencies.FIREBASE_IN_APP_MESSAGING)
}

//endregion


fun DependencyHandler.implementation(depName: String) {
    add("implementation", depName)
}

private fun DependencyHandler.kapt(depName: String) {
    add("kapt", depName)
}

private fun DependencyHandler.compileOnly(depName: String) {
    add("compileOnly", depName)
}

private fun DependencyHandler.api(depName: String) {
    add("api", depName)
}

private fun DependencyHandler.testImplementation(depName: String) {
    add("testImplementation", depName)
}

private fun DependencyHandler.androidTestImplementation(depName: String) {
    add("androidTestImplementation", depName)
}