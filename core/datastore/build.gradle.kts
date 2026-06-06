plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "com.baran.calendar.core.datastore"
    compileSdk = 34

    defaultConfig {
        minSdk = 26
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(":core:common"))
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.datastore:datastore-preferences:1.0.0")
}
