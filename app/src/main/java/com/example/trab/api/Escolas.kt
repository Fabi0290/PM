package com.example.trab.api

data class Escolas(
    val id_escola: Int,
    val nome_escola: String,
    val sigla: String,
    val cursos: List<Curso>
)

data class Curso(
    val id_curso: Int,
    val nome_curso: String,
    val sigla: String,
    val setor: String,
    val coordenador:String,
    val num_alunos: Int,
    val num_estagios:Int,
    val empresas: List<Empresa>
)

data class Empresa(
    val id_empresa: Int,
    val nome: String,
    val descricao: String,
    val cidade: String,
    val latitude: Double,
    val longitude: Double,
    val num_alunos: Int,
    val num_vagas: Int,
    val taxa_aceitacao: Int,
    val setor:String,
    val ano_criacao:Int,
    val duracao_estagio:String
)




