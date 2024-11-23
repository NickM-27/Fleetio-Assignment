import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.nick.mowen.fleetio"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.nick.mowen.fleetio"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // load the values from .properties file
        val keystoreFile = project.rootProject.file("gradle.properties")
        val properties = Properties()
        properties.load(keystoreFile.inputStream())

        // save properties to build config
        buildConfigField("String", "ACCOUNT_TOKEN", properties.getProperty("ACCOUNT_TOKEN") ?: "")
        buildConfigField("String", "API_KEY", properties.getProperty("API_KEY") ?: "")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {

    // Core

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.lifecycle)
    implementation(libs.material)

    // Compose

    val composeBom = platform(libs.androidx.compose)
    implementation(composeBom)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.activity)
    implementation(libs.androidx.compose.preview)
    implementation(libs.androidx.compose.navigation)
    debugImplementation(libs.androidx.compose.tooling)

    // Network

    implementation(libs.network.retrofit)
    implementation(libs.network.gson)

    // Testing

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(composeBom)
}