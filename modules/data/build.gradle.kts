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
    implementation(projects.modules.dataModels)
    api(projects.modules.utilities)

    testImplementation(libs.junit)
    testImplementation(libs.kotlin.test)
    testImplementation(projects.modules.dataTest)

    testImplementation(libs.kotlinx.coroutines.test)
}