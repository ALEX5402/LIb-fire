plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)

    id("com.google.devtools.ksp")
}


android {
    namespace = "com.alex.xposeinject"
    compileSdk = 34
    ndkVersion = "25.2.9519653"
    namespace = "com.alex.xposeinject"
    compileSdk = 34


    defaultConfig {
        ndk.apply{
            abiFilters.add("armeabi-v7a")
            abiFilters.add("arm64-v8a")
            abiFilters.add("x86")
        }

        multiDexEnabled = false
        applicationId = "com.alex.xposeinject"
        minSdk = 22
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }


    ksp {
        arg("room.schemaLocation", "$projectDir/schemas")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        release {
         /*   splits {

                abi {
                    isEnable = true
                    reset()
                    include(
                        "armeabi-v7a", "x86",
                        "arm64-v8a")
                    isUniversalApk = false
                }
            }*/
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
    buildFeatures{
        buildConfig = true
        viewBinding = true
    }
    kotlinOptions {
        jvmTarget = "17"

        freeCompilerArgs = listOf(
            "-Xno-param-assertions",
            "-Xno-call-assertions",
            "-Xno-receiver-assertions"
        )
    }
}

dependencies {
    implementation(libs.yukkihook)
    implementation(project(":modmenu"))
    compileOnly(libs.xposed.api)
    ksp(libs.ksp.xposed)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

}