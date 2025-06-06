plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
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
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

//sqldelight {
//    databases {
//        create("TS_Trivia") {
//            packageName.set("com.ziemowit.ts.core.db")
//            srcDirs.setFrom("core/src/main/sqldelight")
//        }
//    }
//}

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

    api(libs.sqlDelight.android)
    api(libs.sqlDelight.coroutines)
    api(libs.timber)
}

//androidComponents {
//    onVariants(selector().all()) { variant ->
//        afterEvaluate {
//            val capName = variant.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
//            tasks.getByName<KotlinCompile>("ksp${capName}Kotlin") {
//                setSource(tasks.getByName("generate${capName}DatabaseInterface").outputs)
//            }
//        }
//    }
//}