import java.io.FileNotFoundException
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("androidx.navigation.safeargs.kotlin") version "2.7.7"
    id("kotlin-android")
    id("kotlin-parcelize")
}

android {

    val getKeyPropertiesFile = rootProject.file("key.properties")
    val keyProperties = Properties()

    if (getKeyPropertiesFile.exists()) {
        getKeyPropertiesFile.inputStream().use { keyProperties.load(it) }
    } else {
        throw FileNotFoundException("key.properties file not found in root project directory.")
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
        unitTests.isReturnDefaultValues = true
    }

    namespace = "br.com.mobile.marvelcharacters"
    compileSdk = 34

    defaultConfig {
        applicationId = "br.com.mobile.marvelcharacters"
        minSdk = 25
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "API_PUBLIC_KEY", "\"${keyProperties["API_PUBLIC_KEY"]}\"")
        buildConfigField("String", "API_PRIVATE_KEY", "\"${keyProperties["API_PRIVATE_KEY"]}\"")
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
        buildConfig = true
        viewBinding = true
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.junit)
    testImplementation(libs.junit.jupiter)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.koin.android)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.picasso)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.shimmer)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.androidx.core.testing)
}
