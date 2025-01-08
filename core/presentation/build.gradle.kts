plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.dd2d.core.presentation"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.androidx.activity.compose) // Compose와 통합된 Activity 관련 기능
    implementation(platform(libs.androidx.compose.bom)) // Compose BOM (버전 관리)
    implementation(libs.androidx.ui) // Compose UI 관련 라이브러리
    implementation(libs.androidx.ui.graphics) // Compose 그래픽 관련 기능
    implementation(libs.androidx.ui.tooling.preview) // Compose Preview 기능
    implementation(libs.androidx.material3) // Compose용 Material 3 UI 컴포넌트
    androidTestImplementation(platform(libs.androidx.compose.bom)) // Compose 테스트 관련 BOM
    androidTestImplementation(libs.androidx.ui.test.junit4) // Compose UI 테스트용 JUnit4 지원
    debugImplementation(libs.androidx.ui.tooling) // Compose Tooling 지원 (디버그 모드)
    debugImplementation(libs.androidx.ui.test.manifest) // Compose 테스트를 위한 Manifest 지원

    implementation(project(":core:core"))
}