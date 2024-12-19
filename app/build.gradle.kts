import com.android.build.gradle.BaseExtension

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.google.services)
}

android {
    namespace = "com.mtfuji.sakura.shoppingcart"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.mtfuji.sakura.shoppingcart"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    configureSourceSets()
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.coroutines)
    implementation(libs.kotlinx.serialization.json)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.config)
    implementation(libs.koin)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
    implementation(libs.material)

    implementation(projects.modules.data)
    implementation(projects.modules.dataModels)
    implementation(projects.modules.domain)
    implementation(projects.modules.domainModels)
    implementation(projects.modules.firebase)
    implementation(projects.modules.features.home)
    implementation(projects.modules.features.productlist)
    implementation(projects.modules.m3compose)
    implementation(projects.modules.utilities)

    testImplementation(libs.junit)
    testImplementation(libs.junit.jupiter)
    testImplementation(kotlin("test"))
    testImplementation(libs.kotlin.test)
    testImplementation(libs.firebase.config)
    testImplementation(libs.kotlinx.coroutines.test)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

private fun BaseExtension.configureSourceSets() {
    sourceSets {
        getByName("main") {
            java.srcDirs("src/main/java")
        }
        getByName("test") {
            java.srcDirs("src/test/java", "src/main/java")
        }
        getByName("androidTest"){
            java.srcDirs("src/androidTest/java")
        }
    }
}