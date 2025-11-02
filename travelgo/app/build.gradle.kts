plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("org.jetbrains.kotlin.plugin.compose") // 👈 ESTA ES LA NUEVA LÍNEA CLAVE
}

android {
    namespace = "com.example.travelgo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.travelgo"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // URL base para la API (Xano)
        buildConfigField(
            "String",
            "API_BASE_URL",
            "\"https://x8ki-letl-twmt.n7.xano.io/api:Rfm_61dW\""
        )
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Core de Android
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.3")
    implementation("androidx.activity:activity-compose:1.9.3")

    // Compose BOM (control de versiones)
    implementation(platform("androidx.compose:compose-bom:2024.09.02"))
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.09.02"))

    // Jetpack Compose
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    // Navegación Compose
    implementation("androidx.navigation:navigation-compose:2.8.3")

    // ViewModel Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.3")

    // Retrofit + Gson (para APIs)
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")

    // Coil (imágenes)
    implementation("io.coil-kt:coil-compose:2.6.0")

    // Room (base de datos local)
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")

    // DataStore (almacenamiento local)
    implementation("androidx.datastore:datastore-preferences:1.1.1")

    // Tests
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")

    // Debug
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // OkHttp Logging Interceptor (para ver logs de las peticiones)
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")


    // Iconos extendidos de Material
    implementation("androidx.compose.material:material-icons-extended:1.7.4")

}
