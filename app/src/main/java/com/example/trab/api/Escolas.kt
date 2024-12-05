package com.example.trab.api

data class Empresa(
    val idEmpresa: Int,
    val nome: String,
    val descricao: String,
    val cidade: String,
    val latitude: Double,
    val longitude: Double,
    val numAlunos: Int,
    val numVagas: Int
)

data class Curso(
    val idCurso: Int,
    val nomeCurso: String,
    val sigla: String,
    val empresas: List<Empresa>
)

data class Escolas(
    val idEscola: Int,
    val nomeEscola: String,
    val sigla: String,
    val cursos: List<Curso>
)
