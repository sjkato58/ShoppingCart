plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation(libs.kotlinx.coroutines)
    implementation(libs.koin)

    testImplementation(libs.junit)
    testImplementation(libs.kotlin.test)

    testImplementation(libs.kotlinx.coroutines.test)
}