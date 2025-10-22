plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    compileSdk = AppConfig.compileSdk
    namespace = "com.socialite.solite_pos_mvp"

    defaultConfig {
        applicationId = "com.socialite.solite_pos_mvp"
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = Versions.getVersionCode()
        versionName = Versions.getVersionName()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = AppConfig.jvmTarget
    }
    composeOptions {
        kotlinCompilerExtensionVersion = AppConfig.kotlinCompilerExtensionVersion
    }
    buildFeatures {
        compose = true
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":common"))

    // UI
    implementation("com.google.android.material:material:${Dependencies.materialVersion}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${Dependencies.lifecycleVersion}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${Dependencies.lifecycleVersion}")
    implementation("androidx.appcompat:appcompat:${Dependencies.appCompatVersion}")

    // Compose
    implementation(platform("androidx.compose:compose-bom:${Dependencies.composeBomVersion}"))
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.material:material")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:${Dependencies.lifecycleVersion}")
    implementation("androidx.activity:activity-compose:${Dependencies.activityComposeVersion}")
    implementation("androidx.navigation:navigation-compose:${Dependencies.navigationComposeVersion}")

    // Hilt
    implementation("com.google.dagger:hilt-android:${Dependencies.hiltVersion}")
    implementation("androidx.hilt:hilt-navigation-compose:${Dependencies.hiltNavigationComposeVersion}")
    kapt("com.google.dagger:hilt-compiler:${Dependencies.hiltVersion}")

    // Test
    testImplementation("junit:junit:${Dependencies.junitVersion}")
    androidTestImplementation("androidx.test.ext:junit:${Dependencies.junitExtVersion}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${Dependencies.espressoVersion}")
}

kapt {
    correctErrorTypes = true
}
