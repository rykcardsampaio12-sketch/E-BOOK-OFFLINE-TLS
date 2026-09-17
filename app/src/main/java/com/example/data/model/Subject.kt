package com.example.data.model

import androidx.compose.ui.graphics.Color

data class Subject(
    val id: String,
    val namePt: String,
    val nameTet: String,
    val iconName: String,
    val colorPrimary: Color,
    val colorContainer: Color,
    val description: String,
    val order: Int
)

object CurriculumSubjects {
    val subjects = listOf(
        Subject(
            id = "tetum",
            namePt = "Língua Tétum",
            nameTet = "Lian Tétum",
            iconName = "language",
            colorPrimary = Color(0xFFDC2626),
            colorContainer = Color(0xFFFEE2E2),
            description = "Ortografia padronizada, gramátika, literatura timorense no komunikasaun.",
            order = 1
        ),
        Subject(
            id = "portugues",
            namePt = "Língua Portuguesa",
            nameTet = "Lian Portugés",
            iconName = "menu_book",
            colorPrimary = Color(0xFFEA580C),
            colorContainer = Color(0xFFFFEDD5),
            description = "Compreensão e expressão escrita, leitura de clássicos, gramática e vocabulário.",
            order = 2
        ),
        Subject(
            id = "ingles",
            namePt = "Língua Inglesa",
            nameTet = "Lian Inglés",
            iconName = "translate",
            colorPrimary = Color(0xFF2563EB),
            colorContainer = Color(0xFFDBEAFE),
            description = "Foundational English dialogue, grammar, reading comprehension and vocabulary.",
            order = 3
        ),
        Subject(
            id = "matematica",
            namePt = "Matemática",
            nameTet = "Matemátika",
            iconName = "calculate",
            colorPrimary = Color(0xFF0D9488),
            colorContainer = Color(0xFFCCFBF1),
            description = "Álgebra, geometria plana e espacial, estatística, probabilidade e equações.",
            order = 4
        ),
        Subject(
            id = "ciencias_naturais",
            namePt = "Ciências Naturais",
            nameTet = "Siénsia Naturál",
            iconName = "biotech",
            colorPrimary = Color(0xFF059669),
            colorContainer = Color(0xFFD1FAE5),
            description = "Biologia celular, ecossistemas de Timor-Leste, física fundamental e química.",
            order = 5
        ),
        Subject(
            id = "ciencias_sociais",
            namePt = "Ciências Sociais (História e Geografia)",
            nameTet = "Siénsia Sosiál (Istória no Jeografia)",
            iconName = "public",
            colorPrimary = Color(0xFF9333EA),
            colorContainer = Color(0xFFF3E8FF),
            description = "História da Luta e Independência de Timor-Leste, geografia mundial e relevo local.",
            order = 6
        ),
        Subject(
            id = "educacao_civica",
            namePt = "Educação Cívica (Fraternidade Humana, Ética e Moral)",
            nameTet = "Edukasaun Sívika (Fraternidade Umana, Étika)",
            iconName = "handshake",
            colorPrimary = Color(0xFFD97706),
            colorContainer = Color(0xFFFEF3C7),
            description = "Cidadania ativa, direitos humanos, fraternidade universal, reconciliação e moral.",
            order = 7
        ),
        Subject(
            id = "educacao_fisica",
            namePt = "Educação Física",
            nameTet = "Edukasaun Fízika",
            iconName = "sports_soccer",
            colorPrimary = Color(0xFFE11D48),
            colorContainer = Color(0xFFFFE4E6),
            description = "Desenvolvimento motor, desporto coletivo, atletismo, jogos tradicionais e saúde.",
            order = 8
        ),
        Subject(
            id = "bem_estar",
            namePt = "Bem-Estar",
            nameTet = "Moris Di'ak (Bem-Estar)",
            iconName = "favorite",
            colorPrimary = Color(0xFF0284C7),
            colorContainer = Color(0xFFE0F2FE),
            description = "Saúde mental, nutrição saudável, higiene pessoal e resiliência comunitária.",
            order = 9
        ),
        Subject(
            id = "educacao_artistica",
            namePt = "Educação Artística",
            nameTet = "Edukasaun Artístika",
            iconName = "palette",
            colorPrimary = Color(0xFF7C3AED),
            colorContainer = Color(0xFFEDE9FE),
            description = "Tais tradicionais timorenses, artes visuais, música local e teatro escolar.",
            order = 10
        )
    )

    fun getSubjectById(id: String): Subject? = subjects.find { it.id == id }
}
