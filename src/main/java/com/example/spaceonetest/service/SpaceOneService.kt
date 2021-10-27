package com.example.spaceonetest.service

import com.example.spaceonetest.models.Category
import com.example.spaceonetest.models.Product
import com.example.spaceonetest.models.ProductDTO
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private const val BASE_URL = "https://intro.hq-hydra.hibyte.ro/api/scope/amalia/"

private val client =  OkHttpClient.Builder()
    .addInterceptor(BasicAuthInterceptor("admin", "12345678"))
    .build()

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(client)
    .build()

interface SpaceOneService {
    @GET("items/product")
    fun getAllProducts() : Call<List<Product>>

    @GET("items/category")
    fun getAllCategories() : Call<List<Category>>

    @DELETE("item/{id}")
    fun deleteProduct(@Path("id") id : String) : Call<ResponseBody>

    @PUT("item/{id}")
    fun updateItem(@Path("id") id : String, @Body product: Product) : Call<ResponseBody>

    @POST("items/product")
    fun postProduct(@Body productDto: ProductDTO) : Call<ResponseBody>

}

object SpaceOneApi {
    val retrofitService : SpaceOneService by lazy { retrofit.create(SpaceOneService::class.java) }
}

class BasicAuthInterceptor(username: String, password: String): Interceptor {
    private var credentials: String = Credentials.basic(username, password)

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        var request = chain.request()
        request = request.newBuilder().header("Authorization", credentials).build()

        return chain.proceed(request)
    }
}