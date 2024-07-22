package com.sw.wordgarden.app

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import com.sw.wordgarden.R
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WordGardenApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        // Kakao Sdk 초기화
        KakaoSdk.init(this, getString(R.string.kakao_native_key))
    }
}