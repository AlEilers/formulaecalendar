package de.ae.formulaecalendar.formulaerest.rest

import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Response
import okhttp3.ResponseBody

/**
 * Created by alexa on 24.07.2017.
 */
class EncodingInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        //get response
        val response = chain.proceed(chain.request())

        //set correct mediatype and create a new body
        val mediaType = MediaType.parse("application/json; charset=iso-8859-1")
        val modifiedBody = ResponseBody.create(mediaType, response.body().bytes())

        //create response with new body
        val modifiedResponse = response.newBuilder()
                .body(modifiedBody)
                .build()

        return modifiedResponse
    }
}