package com.example.projetointegrador.data.repository

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

object NetworkRetrofit {

    fun getService(): MoviesService {
        val logging = HttpLoggingInterceptor() //Isso se refere ao logging-interceptor, apenas para ver no logcat tudo relacionado à API.
        logging.level = HttpLoggingInterceptor.Level.BODY //Isso se refere ao logging-interceptor, apenas para ver no logcat tudo relacionado à API.

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging) //Isso se refere ao logging-interceptor, apenas para ver no logcat tudo relacionado à API.
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url()

            val url = originalHttpUrl.newBuilder() //Essa parte é uma interceptação da URL para colocar os dados desejados.
                .addQueryParameter("api_key", "185c64f24b7f5ec02ec2097ed8094898") //O parâmetro (aqui como "api_key") precisa estar como consta na documentação da API. No caso do TMDB, é "api_key".
                .addQueryParameter("language", "pt-BR") //Esse comando deixa as informações em português (título, sinopse, etc).
                .build() //Aqui a URL é construída.

            chain.proceed(original.newBuilder().url(url).build()) //Aqui se finaliza a construção e atribui a URL à chamada em si.
        }

        //Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/") //Inserir a URL base do TMDB
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //Com o rxjava, o Retrofit deixa de ser o responsável pelo “onResponse” e “onFailure”, sendo essa função agora do rxjava.
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()

        return retrofit.create(MoviesService::class.java)
    }

}