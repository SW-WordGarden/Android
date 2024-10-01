# Kotlin 관련 클래스 보존
-keep class kotlin.** { *; }
-keep class kotlinx.coroutines.** { *; }

# Retrofit2 - Type parameters are used by Retrofit for service method interfaces
-keepclassmembers,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}

# Retrofit과 관련된 클래스들을 난독화하지 않도록 설정
-keep class retrofit2.** { *; }
-keep class retrofit2.converter.gson.** { *; }
-keepattributes Signature

# Gson과 관련된 클래스들을 난독화하지 않도록 설정
-keep class com.google.gson.** { *; }

# 데이터 클래스 난독화하지 않도록 설정
-keep class com.sw.wordgarden.data.dto.** { *; }
-keep class com.sw.wordgarden.domain.entity.** { *; }
-keep class com.sw.wordgarden.presentation.model.** { *; }

# 네비게이션 구성 요소가 사용하는 클래스 예외 처리
-keep class androidx.navigation.fragment.NavHostFragment { *; }

# 데이터 클래스와 제네릭 타입 관련 설정
-keepclassmembers,allowshrinking,allowobfuscation class * {
    @retrofit2.http.* <methods>;
}

# 모든 제네릭 타입의 정보를 보존
-keepattributes Signature

#kakao
-keep class com.kakao.sdk.** { *; }

# missing_rules 규칙
-dontwarn org.bouncycastle.jsse.BCSSLParameters
-dontwarn org.bouncycastle.jsse.BCSSLSocket
-dontwarn org.bouncycastle.jsse.provider.BouncyCastleJsseProvider
-dontwarn org.conscrypt.Conscrypt$Version
-dontwarn org.conscrypt.Conscrypt
-dontwarn org.conscrypt.ConscryptHostnameVerifier
-dontwarn org.openjsse.javax.net.ssl.SSLParameters
-dontwarn org.openjsse.javax.net.ssl.SSLSocket
-dontwarn org.openjsse.net.ssl.OpenJSSE