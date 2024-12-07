plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.google.gms.google.services)
//    id("com.google.dagger.hilt.android") version "2.51.1" apply true // Plugin Hilt untuk Android
//    id("kotlin-kapt") // Menggunakan KAPT untuk Hilt
}

android {
    namespace = "com.example.habitance"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.habitance"
        minSdk = 29
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // AndroidX Dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.runtime.livedata)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.constraintlayout.compose)

    // Jetpack Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.ui.text.google.fonts)
    implementation(libs.androidx.material.icons.core)
    implementation(libs.androidx.material.icons.extended)

    // Accompanist (untuk fitur tambahan di Jetpack Compose)
    implementation(libs.accompanist.pager)
    implementation(libs.accompanist.pager.indicators)

    // Firebase
    implementation(libs.firebase.auth)
    implementation(libs.firebase.auth.ktx)
    implementation(libs.firebase.crashlytics.buildtools)
    implementation(libs.firebase.firestore)
    implementation (libs.firebase.firestore.ktx)

    // Coil (untuk image loading)
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    // Material Design
    implementation(libs.material3)
    implementation(libs.androidx.material3.v131)
    implementation(libs.googleid)
    implementation(libs.androidx.tv.material)
    implementation (libs.androidx.material3.v120)
    implementation ("androidx.compose.material3:material3-window-size-class:1.3.1")
    implementation(libs.androidx.foundation.android)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)



    implementation (libs.androidx.core.ktx.v1101)
    implementation (libs.androidx.work.runtime.ktx)


    // Untuk Jetpack Compose ViewModel
    implementation(libs.androidx.hilt.navigation.compose)
}
