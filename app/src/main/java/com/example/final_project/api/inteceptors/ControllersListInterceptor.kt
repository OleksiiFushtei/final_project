package com.example.final_project.api.inteceptors

import com.orhanobut.hawk.Hawk
import okhttp3.Interceptor
import okhttp3.Response

class ControllersListInterceptor :
    Interceptor {
    override fun intercept(
        chain: Interceptor.Chain
    ): Response {
        if (Hawk.contains(
                "token"
            )
        ) {
            val tokenValue =
                Hawk.get<String>(
                    "token"
                )
            val request =
                chain.request()
                    .newBuilder()
                    .addHeader(
                        "Authorization",
                        "Bearer $tokenValue"
                    )
                    .build()
            return chain.proceed(
                request
            )
        } else {
            return chain.proceed(
                chain.request()
            )
        }
    }
}