plugins {
    id(Plugins.androidApplication)
    kotlin(KotlinPlugins.android)
    kotlin(KotlinPlugins.kapt)
    kotlin(KotlinPlugins.serialization) version Kotlin.version
    id(Plugins.hilt)
}

android {
    namespace = Application.appId
    compileSdk = Application.compileSdk
    defaultConfig {
        applicationId = Application.appId
        minSdk = Application.minSdk
        targetSdk = Application.targetSdk
        versionCode = Application.versionCode
        versionName = Application.versionName
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":shared"))

    // Compose
    implementation(Compose.ui)
    implementation(Compose.uiTooling)
    implementation(Compose.uiToolingPreview)
    implementation(Compose.foundation)
    implementation(Compose.material)
    implementation(Compose.activity)
    implementation(Compose.navigation)
    implementation(Compose.icons)

    // Hilt
    implementation(Hilt.hiltAndroid)
    implementation(Hilt.hiltNavigation)
    kapt(Hilt.hiltCompiler)

    // Coroutines
    implementation(Coroutines.core)
    implementation(Coroutines.android)

    // Coroutine Lifecycle Scopes
    implementation(Coroutines.lifecycleViewModel)
    implementation(Coroutines.lifecycleRuntime)

    // Ktor
    implementation(Ktor.android)

    // Coil
    implementation(Accompanist.coil)

    // Accompanist
    implementation(Accompanist.placeholder)
    implementation(Accompanist.animation)

    // Splash
    implementation(Compose.splash)
}