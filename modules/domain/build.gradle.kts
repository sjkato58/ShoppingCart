plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

dependencies {
    implementation(libs.kotlinx.coroutines)
    implementation(libs.koin)
    implementation(projects.modules.data)
    implementation(projects.modules.dataModels)
    implementation(projects.modules.domainModels)

    testImplementation(libs.junit)
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.koin.test)
    testImplementation(projects.modules.dataTest)

    testImplementation(libs.kotlinx.coroutines.test)
}