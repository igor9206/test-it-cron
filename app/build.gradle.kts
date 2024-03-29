import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)

    kotlin("kapt")
    alias(libs.plugins.daggerHilt)
}

android {
    namespace = "ru.kor.test_it_cron"
    compileSdk = 34

    buildFeatures.buildConfig = true
    buildFeatures.viewBinding = true

    defaultConfig {
        applicationId = "ru.kor.test_it_cron"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val properties = Properties()
        val file = rootProject.file("local.properties")
        if (file.exists()) {
            properties.load(file.inputStream())
        }
        buildConfigField("String", "API_KEY", "\"${properties.getProperty("API_KEY", "")}\"")

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
}

dependencies {
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.recycler.view)
    implementation(libs.dagger.hilt)
    kapt(libs.kapt.hilt)
    implementation(libs.room)
    implementation(libs.room.coroutines)
    implementation(libs.room.pagin)
    kapt(libs.kapt.room)
    implementation(libs.retrofit)
    implementation(libs.paging)
    implementation(libs.swipe.refresh)
    implementation(libs.google.gson)
    implementation(libs.gson.converter)
    implementation(libs.glide)


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}