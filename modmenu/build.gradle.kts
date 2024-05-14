plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
}
tasks.register<Copy>("copyNativeLibs") {
    from(fileTree("src/main/jniLibs") {
        include("**/*.so")
    })
    into("src/main/assets")
}
tasks.named("preBuild") {
    dependsOn("copyNativeLibs")
}
android {
    namespace = "com.alex.xposeinject"
    compileSdk = 34
    ndkVersion = "25.2.9519653"
    namespace = "com.alex.xposeinject"
    compileSdk = 34
    defaultConfig {
        ndk.apply {
            abiFilters.add("armeabi-v7a")
            abiFilters.add("arm64-v8a")
            abiFilters.add("x86")
        }
    }


    defaultConfig {
        minSdk = 22
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
    externalNativeBuild {
        ndkBuild {
            path {
                file("src/main/jni/Android.mk")
            }
        }
    }
}



dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
