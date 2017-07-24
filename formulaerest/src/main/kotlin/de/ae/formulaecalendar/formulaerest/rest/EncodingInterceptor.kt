package de.ae.formulaecalendar.formulaerest.rest

import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Response
import okhttp3.ResponseBody

/**
 * Created by alexa on 24.07.2017.
 *
 * Set the right encoding to one special request
 */
class EncodingInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        //get response
        val response = chain.proceed(chain.request())

        // This one special request uses an iso-8859-1 encoding while the other requests use utf-8
        // However it is never specified which encoding is used.
        if(response.request().url().toString().equals("http://forix-proxy-prod.formulae.corebine.com/fe_server.php/?championship=2022016")) {
            //set correct mediatype and create a new body
            val mediaType = MediaType.parse("application/json; charset=iso-8859-1")
            val modifiedBody = ResponseBody.create(mediaType, response.body()?.bytes())

            //create response with new body
            val modifiedResponse = response.newBuilder()
                    .body(modifiedBody)
                    .build()

            return modifiedResponse
        }else{
            return response
        }
    }
}