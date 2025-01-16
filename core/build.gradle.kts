@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.sqlDelight)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kapt)
}

android {
    namespace = "com.ziemowit.ts.core"
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

sqldelight {
    databases {
        create("TS_Trivia") {
            packageName.set("com.ziemowit.ts.core.db")
            srcDirs.setFrom("core/src/main/sqldelight")
        }
    }
}

dependencies {

    api(libs.core.ktx)
    api(libs.appcompat)
    api(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    api(libs.ksp)
//    ksp(libs.kotlin.inject.ksp)
//    api(libs.kotlin.inject.runtime)
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

    api(libs.sqlDelight.android)
    api(libs.sqlDelight.coroutines)
    api(libs.timber)
}