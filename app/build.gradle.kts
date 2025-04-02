import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.util.Locale

plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.sqlDelight)
}

android {
    namespace = "com.ziemowit.ts.trivia"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.ziemowit.ts.trivia"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        val CRASHLYTICS_ENABLED = "CRASHLYTICS_ENABLED"

        release {
            isMinifyEnabled = false
            buildConfigField("boolean", CRASHLYTICS_ENABLED, "true")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
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

sqldelight {
    databases {
        create("TS_Trivia2") {
            packageName.set("com.ziemowit.ts.core.db")
            srcDirs.setFrom("core/src/main/sqldelight")
        }
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":ui-common"))

    implementation(libs.coroutines.core)
    implementation(libs.core.splashscreen)
    implementation(libs.coroutines.android)
    implementation(libs.viewmodel.compose)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.hilt)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.androidx.navigation.compose)
    ksp(libs.kotlin.inject.ksp)
    api(libs.kotlin.inject.runtime)
    ksp(libs.hilt.compiler)
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.arch.core.testing)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

androidComponents {
    onVariants(selector().all()) { variant ->
        afterEvaluate {
            val variantName = variant.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
            tasks.getByName<KotlinCompile>("ksp${variantName}Kotlin") {
                setSource(tasks.getByName("generate${variantName}TS_Trivia2Interface").outputs)
            }
        }
    }
}