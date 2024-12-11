package com.example.trab.api


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EndPoints {
    @GET("/escolas/")
    fun getEscolas(): Call<List<Escolas>>
    @GET("/escolas/{id}")
    fun getEscolasById(@Path("id") id: Int): Call<Escolas>
    @GET("/escolas/{id}/cursos")
    fun getCurso(@Path("id") id: Int): Call<List<Curso>>
    @GET("escolas/{escolaId}/cursos/{cursoId}/empresas")
    fun getEmpresas(@Path("escolaId") escolaId: Int, @Path("cursoId") cursoId: Int): Call<List<Empresa>>
    @GET("/escolas/empresas")
    fun getTodasEmpresas(): Call<List<Empresa>>


}