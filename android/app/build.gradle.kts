import groovy.json.JsonSlurper

plugins {
    id("com.android.application")
    id("kotlin-android")
    // The Flutter Gradle Plugin must be applied after the Android and Kotlin Gradle plugins.
    id("dev.flutter.flutter-gradle-plugin")
}

// Function to read API key from dev.json
fun getApiKey(): String {
    try {
        val jsonFile = file("../../lib/dev.json")
        if (jsonFile.exists()) {
            val jsonSlurper = JsonSlurper()
            val jsonData = jsonSlurper.parse(jsonFile) as Map<*, *>
            return jsonData["GMAP_API_KEY"] as String? ?: ""
        }
    } catch (e: Exception) {
        println("Error reading API key from dev.json: ${e.message}")
    }
    return ""
}

android {
    namespace = "com.example.gmapsample"
    compileSdk = flutter.compileSdkVersion
    ndkVersion = "27.0.12077973"
    
    buildFeatures {
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    defaultConfig {
        // TODO: Specify your own unique Application ID (https://developer.android.com/studio/build/application-id.html).
        applicationId = "com.example.gmapsample"
        // You can update the following values to match your application needs.
        // For more information, see: https://flutter.dev/to/review-gradle-config.
        minSdk = flutter.minSdkVersion
        targetSdk = flutter.targetSdkVersion
        versionCode = flutter.versionCode
        versionName = flutter.versionName
        
        buildConfigField("String", "GMA_API_KEY", "\"${getApiKey()}\"")
        manifestPlaceholders["GMA_API_KEY"] = getApiKey()
    }

    buildTypes {
        release {
            // TODO: Add your own signing config for the release build.
            // Signing with the debug keys for now, so `flutter run --release` works.
            signingConfig = signingConfigs.getByName("debug")
        }
    }
}

flutter {
    source = "../.."
}
