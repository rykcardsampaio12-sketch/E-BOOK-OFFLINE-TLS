package com.example.data.model

enum class Grade(
    val code: String,
    val displayName: String,
    val tetumName: String,
    val subtitle: String
) {
    GRADE_7("7", "7º Ano", "Klase 7", "Ensino Básico 3º Ciclo"),
    GRADE_8("8", "8º Ano", "Klase 8", "Ensino Básico 3º Ciclo"),
    GRADE_9("9", "9º Ano", "Klase 9", "Ensino Básico 3º Ciclo")
}
