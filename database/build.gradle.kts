plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.sqlDelight)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.ziemowit.ts.trivia.database"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

sqldelight {
    databases {
        create("TS_Trivia2") {
            packageName.set("com.ziemowit.ts.trivia.database")
            srcDirs.setFrom("src/main/sqldelight")
        }
    }
}

dependencies {

    api(libs.core.ktx)
    api(libs.appcompat)
    api(libs.material)
    api(libs.sqlDelight.android)
    api(libs.sqlDelight.coroutines)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}


//androidComponents {
//    onVariants(selector().all()) { variant ->
//        afterEvaluate {
//            val variantName = variant.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
//            tasks.getByName<KotlinCompile>("ksp${variantName}Kotlin") {
//                setSource(tasks.getByName("generate${variantName}TS_Trivia2Interface").outputs)
//            }
//        }
//    }
//}