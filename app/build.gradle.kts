import org.jetbrains.kotlin.konan.properties.Properties

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
}

// 获取 local.properties 的内容
val properties = Properties()
properties.load(project.rootProject.file("local.properties").inputStream())

android {
    namespace = "com.wsy.laundricompose"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.wsy.laundricompose"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    // 打包命令：./gradlew assemblelaundritestRelease
    // 打包命令：./gradlew assemblelaundriproductionRelease
    signingConfigs {
        create("release") {
            storeFile = file(properties.getProperty("keystroe_storeFile"))
            storePassword = properties.getProperty("keystroe_storePassword")
            keyAlias = properties.getProperty("keystroe_keyAlias")
            keyPassword = properties.getProperty("keystroe_keyPassword")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            signingConfig = signingConfigs.getByName("release")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    flavorDimensions += listOf("default")
    productFlavors {
        // 测试环境
        create("laundritest") {
            dimension = "default"
            buildConfigField("String", "BASE_URL", "\"http://api.admin.landeli.com/\"")
            manifestPlaceholders += mapOf(
                "app_name" to "@string/app_name_test",
//                "app_icon" to "@mipmap/icon_app"
            )
        }
        // 生产环境
        create("laundriproduction") {
            dimension = "default"
            buildConfigField("String", "BASE_URL", "\"http://api.wash.ltd/\"")
            manifestPlaceholders += mapOf(
                "app_name" to "@string/app_name_test",
//                "app_icon" to "@mipmap/icon_app"
            )
        }
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    implementation(libs.navigation.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
    implementation(libs.material)
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.accompanist.navigation.animation)
    implementation(libs.bundles.retrofit)
}