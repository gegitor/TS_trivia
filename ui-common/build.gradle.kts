@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kapt)
}

android {
    namespace = "com.ziemowit.ts.ui_common"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":core"))

    api(libs.ui)
    api(libs.ui.graphics)
    api(libs.ui.tooling.preview)
    api(libs.material3)

    api(libs.appcompat)
    api(libs.compose.animation)
    api(libs.compose.activity)
    api(platform(libs.compose.bom))
    api(libs.lottie)
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

//    implementation(libs.coroutines.core)
//    implementation(libs.core.splashscreen)
//    implementation(libs.coroutines.android)
//    implementation(libs.viewmodel.compose)
//    implementation(libs.lifecycle.runtime.ktx)
//    implementation(libs.hilt.navigation.compose)
//    implementation(libs.androidx.navigation.compose)

    testImplementation(libs.junit)
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
    androidTestImplementation(platform(libs.compose.bom))
}
