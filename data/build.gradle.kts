plugins {
    id("com.android.library")
    id("com.google.devtools.ksp")
    id("kotlin-kapt")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.socialite.data"
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = AppConfig.jvmTarget
    }
}

dependencies {
    implementation(project(":common"))

    // Hilt
    implementation("com.google.dagger:hilt-android:${Dependencies.hiltVersion}")
    kapt("com.google.dagger:hilt-compiler:${Dependencies.hiltVersion}")

    // Database
    api("androidx.room:room-runtime:${Dependencies.roomVersion}")
    api("androidx.room:room-ktx:${Dependencies.roomVersion}")
    ksp("org.xerial:sqlite-jdbc:${Dependencies.sqliteJdbc}")
    ksp("androidx.room:room-compiler:${Dependencies.roomVersion}")
}
