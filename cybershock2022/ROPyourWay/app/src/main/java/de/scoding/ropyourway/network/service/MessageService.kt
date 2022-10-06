package de.scoding.ropyourway.network.service

import de.scoding.ropyourway.model.EncodedMessage
import retrofit2.Response
import retrofit2.http.GET

interface MessageService {

    @GET("/messages")
    suspend fun getMessages() : Response<List<EncodedMessage>>
}