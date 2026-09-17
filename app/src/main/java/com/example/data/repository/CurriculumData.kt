package com.example.data.repository

import com.example.data.model.Book
import com.example.data.model.Chapter
import com.example.data.model.Grade

object CurriculumData {
    val books: List<Book> = listOf(
        // GRADE 7
        Book(
            id = "tetum_7",
            title = "Lian Tétum 7º Ano: Gramátika no Ortografia",
            subjectId = "tetum",
            grade = Grade.GRADE_7,
            sizeMb = 3.8,
            totalPages = 48,
            editionYear = "2024",
            publisher = "Ministério da Educação Timor-Leste",
            description = "Livru ofisiál ba alunu sira iha 7º ano atu aprende ortografia padronizada, konstrusaun fraze, no testu esplikativu.",
            remotePdfUrl = "https://storage.googleapis.com/edulibrary-tl/eb3/tetum_g7.pdf",
            chapters = listOf(
                Chapter("Kapítulu 1: Istória Lian Tétum", 1),
                Chapter("Kapítulu 2: Ortografia Padronizada INL", 12),
                Chapter("Kapítulu 3: Tipu Substantivu no Verbu", 24),
                Chapter("Kapítulu 4: Hakerek Parágrafu no Redasaun", 36)
            )
        ),
        Book(
            id = "portugues_7",
            title = "Língua Portuguesa 7º Ano: Gramática e Leitura",
            subjectId = "portugues",
            grade = Grade.GRADE_7,
            sizeMb = 4.2,
            totalPages = 52,
            editionYear = "2024",
            publisher = "Ministério da Educação Timor-Leste",
            description = "Manual escolar de Língua Portuguesa para o 7º Ano. Textos literários, classes de palavras e compreensão leitora.",
            remotePdfUrl = "https://storage.googleapis.com/edulibrary-tl/eb3/portugues_g7.pdf",
            chapters = listOf(
                Chapter("Unidade 1: O Texto Narrativo e Contos Tradicionais", 1),
                Chapter("Unidade 2: Classes de Palavras: Nomes e Adjetivos", 14),
                Chapter("Unidade 3: A Estrutura da Frase Simples", 28),
                Chapter("Unidade 4: Produção Textual e Diálogos", 42)
            )
        ),
        Book(
            id = "ingles_7",
            title = "English for Timor-Leste Grade 7: Foundations",
            subjectId = "ingles",
            grade = Grade.GRADE_7,
            sizeMb = 3.5,
            totalPages = 40,
            editionYear = "2024",
            publisher = "Ministério da Educação Timor-Leste",
            description = "Introductory English textbook covering greetings, classroom language, family vocabulary, and basic present tense.",
            remotePdfUrl = "https://storage.googleapis.com/edulibrary-tl/eb3/english_g7.pdf",
            chapters = listOf(
                Chapter("Unit 1: Welcome & Introductions", 1),
                Chapter("Unit 2: My School and Classroom", 10),
                Chapter("Unit 3: Family and Daily Routines", 20),
                Chapter("Unit 4: Simple Present & Questions", 30)
            )
        ),
        Book(
            id = "matematica_7",
            title = "Matemática 7º Ano: Números e Geometria Básica",
            subjectId = "matematica",
            grade = Grade.GRADE_7,
            sizeMb = 4.6,
            totalPages = 60,
            editionYear = "2024",
            publisher = "Ministério da Educação Timor-Leste",
            description = "Números inteiros e racionais, operações fundamentais, proporções, equações de 1º grau e figuras geométricas.",
            remotePdfUrl = "https://storage.googleapis.com/edulibrary-tl/eb3/matematica_g7.pdf",
            chapters = listOf(
                Chapter("Capítulo 1: Conjuntos e Números Racionais", 1),
                Chapter("Capítulo 2: Potenciação e Raiz Quadrada", 16),
                Chapter("Capítulo 3: Equações do 1º Grau", 32),
                Chapter("Capítulo 4: Retas, Ângulos e Triângulos", 48)
            )
        ),
        Book(
            id = "ciencias_naturais_7",
            title = "Ciências Naturais 7º Ano: A Célula e a Vida",
            subjectId = "ciencias_naturais",
            grade = Grade.GRADE_7,
            sizeMb = 4.1,
            totalPages = 48,
            editionYear = "2024",
            publisher = "Ministério da Educação Timor-Leste",
            description = "A estrutura dos seres vivos, microbiologia introdutória e a rica biodiversidade terrestre e marinha de Timor-Leste.",
            remotePdfUrl = "https://storage.googleapis.com/edulibrary-tl/eb3/ciencias_g7.pdf",
            chapters = listOf(
                Chapter("Capítulo 1: A Célula como Unidade Fundamental", 1),
                Chapter("Capítulo 2: Os Reinos dos Seres Vivos", 14),
                Chapter("Capítulo 3: Biodiversidade Marinha e Recifes de Coral", 28),
                Chapter("Capítulo 4: Água e Ciclo Hidrológico em Timor", 40)
            )
        ),
        Book(
            id = "ciencias_sociais_7",
            title = "Ciências Sociais 7º Ano: Geografia de Timor-Leste",
            subjectId = "ciencias_sociais",
            grade = Grade.GRADE_7,
            sizeMb = 4.4,
            totalPages = 46,
            editionYear = "2024",
            publisher = "Ministério da Educação Timor-Leste",
            description = "Geografia física dos 14 municípios de Timor-Leste, montanha Ramelau, clima tropical e recursos naturais.",
            remotePdfUrl = "https://storage.googleapis.com/edulibrary-tl/eb3/sociais_g7.pdf",
            chapters = listOf(
                Chapter("Capítulo 1: Localização e Fronteiras de Timor-Leste", 1),
                Chapter("Capítulo 2: Relevo, Rios e o Monte Tatamailau", 12),
                Chapter("Capítulo 3: Clima, Monções e Agricultura Local", 24),
                Chapter("Capítulo 4: Os Municípios e População", 36)
            )
        ),
        Book(
            id = "educacao_civica_7",
            title = "Educação Cívica 7º Ano: Direitos e Cidadania",
            subjectId = "educacao_civica",
            grade = Grade.GRADE_7,
            sizeMb = 3.2,
            totalPages = 38,
            editionYear = "2024",
            publisher = "Ministério da Educação Timor-Leste",
            description = "A Declaração Universal dos Direitos Humanos, a Constituição da RDTL e o dever cívico na escola e na família.",
            remotePdfUrl = "https://storage.googleapis.com/edulibrary-tl/eb3/civica_g7.pdf",
            chapters = listOf(
                Chapter("Capítulo 1: O que é Cidadania?", 1),
                Chapter("Capítulo 2: A Constituição da RDTL e Valores", 10),
                Chapter("Capítulo 3: Respeito Mútuo e Solidariedade", 20),
                Chapter("Capítulo 4: Proteção do Bem Comum e Escola", 30)
            )
        ),
        Book(
            id = "educacao_fisica_7",
            title = "Educação Física 7º Ano: Condição e Desporto",
            subjectId = "educacao_fisica",
            grade = Grade.GRADE_7,
            sizeMb = 3.1,
            totalPages = 36,
            editionYear = "2024",
            publisher = "Ministério da Educação Timor-Leste",
            description = "Aquecimento, flexibilidade, regras de futebol escolar, voleibol e jogos cooperativos tradicionais timorenses.",
            remotePdfUrl = "https://storage.googleapis.com/edulibrary-tl/eb3/fisica_g7.pdf",
            chapters = listOf(
                Chapter("Unidade 1: Aquecimento e Saúde Corporal", 1),
                Chapter("Unidade 2: Futsal e Futebol de Campo", 10),
                Chapter("Unidade 3: Voleibol e Trabalho em Equipa", 20),
                Chapter("Unidade 4: Jogos Tradicionais de Timor-Leste", 28)
            )
        ),
        Book(
            id = "bem_estar_7",
            title = "Bem-Estar 7º Ano: Nutrição e Higiene Saudável",
            subjectId = "bem_estar",
            grade = Grade.GRADE_7,
            sizeMb = 3.0,
            totalPages = 34,
            editionYear = "2024",
            publisher = "Ministério da Educação Timor-Leste",
            description = "Guia prático para a saúde do adolescente: higiene bucal, alimentação rica em produtos locais e prevenção de febre dengue.",
            remotePdfUrl = "https://storage.googleapis.com/edulibrary-tl/eb3/bem_estar_g7.pdf",
            chapters = listOf(
                Chapter("Capítulo 1: Higiene Pessoal e Saúde da Pele", 1),
                Chapter("Capítulo 2: Alimentação Tradicional e Nutritiva", 10),
                Chapter("Capítulo 3: Prevenção de Doenças Tropicais (Dengue e Malária)", 20),
                Chapter("Capítulo 4: Sono Reparador e Bem-Estar Mental", 28)
            )
        ),
        Book(
            id = "educacao_artistica_7",
            title = "Educação Artística 7º Ano: O Tais e Artes Visuais",
            subjectId = "educacao_artistica",
            grade = Grade.GRADE_7,
            sizeMb = 3.9,
            totalPages = 42,
            editionYear = "2024",
            publisher = "Ministério da Educação Timor-Leste",
            description = "Estudo dos padrões e significados do Tais tradicional dos municípios, técnicas de desenho e cores primárias.",
            remotePdfUrl = "https://storage.googleapis.com/edulibrary-tl/eb3/artes_g7.pdf",
            chapters = listOf(
                Chapter("Capítulo 1: Cores, Linhas e Geometria nas Artes", 1),
                Chapter("Capítulo 2: O Tais de Timor-Leste: Património UNESCO", 12),
                Chapter("Capítulo 3: Cerâmica e Escultura Tradicional", 24),
                Chapter("Capítulo 4: Música Tradicional e Instrumentos Nativos", 34)
            )
        ),

        // GRADE 8
        Book(
            id = "tetum_8",
            title = "Lian Tétum 8º Ano: Literatura no Lian Tékniku",
            subjectId = "tetum",
            grade = Grade.GRADE_8,
            sizeMb = 4.0,
            totalPages = 50,
            editionYear = "2024",
            publisher = "Ministério da Educação Timor-Leste",
            description = "Hakerek karta ofisiál, artigu opiniaun no analiza poezia husi poeta timorense sira.",
            remotePdfUrl = "https://storage.googleapis.com/edulibrary-tl/eb3/tetum_g8.pdf",
            chapters = listOf(
                Chapter("Kapítulu 1: Poezia no Ai-knanoik Timorense", 1),
                Chapter("Kapítulu 2: Konstrusaun Testu Argumentativu", 14),
                Chapter("Kapítulu 3: Lian Tétum Tékniku no Ofisiál", 28),
                Chapter("Kapítulu 4: Hakerek Karta no Relatóriu", 40)
            )
        ),
        Book(
            id = "portugues_8",
            title = "Língua Portuguesa 8º Ano: Texto Argumentativo",
            subjectId = "portugues",
            grade = Grade.GRADE_8,
            sizeMb = 4.3,
            totalPages = 56,
            editionYear = "2024",
            publisher = "Ministério da Educação Timor-Leste",
            description = "Desenvolvimento da escrita argumentativa, sintaxe da oração composta e literatura lusófona.",
            remotePdfUrl = "https://storage.googleapis.com/edulibrary-tl/eb3/portugues_g8.pdf",
            chapters = listOf(
                Chapter("Unidade 1: Crónicas e Reportagens", 1),
                Chapter("Unidade 2: Orações Coordenadas e Subordinadas", 16),
                Chapter("Unidade 3: Figuras de Estilo e Poesia da CPLP", 32),
                Chapter("Unidade 4: O Debate e a Argumentação Escolar", 46)
            )
        ),
        Book(
            id = "ingles_8",
            title = "English for Timor-Leste Grade 8: Developing Skills",
            subjectId = "ingles",
            grade = Grade.GRADE_8,
            sizeMb = 3.7,
            totalPages = 44,
            editionYear = "2024",
            publisher = "Ministério da Educação Timor-Leste",
            description = "Past simple tense, talking about historical events, reading stories and everyday English conversations.",
            remotePdfUrl = "https://storage.googleapis.com/edulibrary-tl/eb3/english_g8.pdf",
            chapters = listOf(
                Chapter("Unit 1: Past Events & Timor History", 1),
                Chapter("Unit 2: Health & Sports Vocabulary", 12),
                Chapter("Unit 3: Describing Places and Travel", 24),
                Chapter("Unit 4: Future Plans and Predictions", 34)
            )
        ),
        Book(
            id = "matematica_8",
            title = "Matemática 8º Ano: Álgebra e Geometria",
            subjectId = "matematica",
            grade = Grade.GRADE_8,
            sizeMb = 4.8,
            totalPages = 64,
            editionYear = "2024",
            publisher = "Ministério da Educação Timor-Leste",
            description = "Sistemas de equações lineares, polinómios, Teorema de Pitágoras e áreas de polígonos regulares.",
            remotePdfUrl = "https://storage.googleapis.com/edulibrary-tl/eb3/matematica_g8.pdf",
            chapters = listOf(
                Chapter("Capítulo 1: Polinómios e Produtos Notáveis", 1),
                Chapter("Capítulo 2: Sistemas de Equações com Duas Incógnitas", 18),
                Chapter("Capítulo 3: Teorema de Pitágoras e Aplicações", 36),
                Chapter("Capítulo 4: Áreas e Perímetros de Figuras Planas", 50)
            )
        ),
        Book(
            id = "ciencias_naturais_8",
            title = "Ciências Naturais 8º Ano: Ecossistemas e Energia",
            subjectId = "ciencias_naturais",
            grade = Grade.GRADE_8,
            sizeMb = 4.3,
            totalPages = 50,
            editionYear = "2024",
            publisher = "Ministério da Educação Timor-Leste",
            description = "Cadeias tróficas, conservação das florestas tropicais de Timor, fontes de energia renováveis e termodinâmica básica.",
            remotePdfUrl = "https://storage.googleapis.com/edulibrary-tl/eb3/ciencias_g8.pdf",
            chapters = listOf(
                Chapter("Capítulo 1: Relações Ecológicas e Teias Alimentares", 1),
                Chapter("Capítulo 2: A Conservação das Florestas de Timor-Leste", 14),
                Chapter("Capítulo 3: Formas e Transformações de Energia", 28),
                Chapter("Capítulo 4: Calor, Temperatura e Equilíbrio Térmico", 40)
            )
        ),
        Book(
            id = "ciencias_sociais_8",
            title = "Ciências Sociais 8º Ano: A Luta pela Independência",
            subjectId = "ciencias_sociais",
            grade = Grade.GRADE_8,
            sizeMb = 4.7,
            totalPages = 54,
            editionYear = "2024",
            publisher = "Ministério da Educação Timor-Leste",
            description = "A história heroica da resistência timorense (Frente Armada, Clandestina e Diplomática) e a restauração da independência em 2002.",
            remotePdfUrl = "https://storage.googleapis.com/edulibrary-tl/eb3/sociais_g8.pdf",
            chapters = listOf(
                Chapter("Capítulo 1: O Período Colonial e Primeiras Resistências", 1),
                Chapter("Capítulo 2: A Invasão e a Luta das Falintil", 14),
                Chapter("Capítulo 3: O Massacre de Santa Cruz e a Diplomacia", 28),
                Chapter("Capítulo 4: A Consulta Popular de 1999 e a Restauração", 42)
            )
        ),
        Book(
            id = "educacao_civica_8",
            title = "Educação Cívica 8º Ano: Fraternidade e Ética",
            subjectId = "educacao_civica",
            grade = Grade.GRADE_8,
            sizeMb = 3.4,
            totalPages = 40,
            editionYear = "2024",
            publisher = "Ministério da Educação Timor-Leste",
            description = "Fraternidade humana, convivência pacífica em comunidades diversas, resolução não-violenta de conflitos e ética pública.",
            remotePdfUrl = "https://storage.googleapis.com/edulibrary-tl/eb3/civica_g8.pdf",
            chapters = listOf(
                Chapter("Capítulo 1: O Documento sobre a Fraternidade Humana", 1),
                Chapter("Capítulo 2: Diálogo Intercultural e Respeito Religioso", 12),
                Chapter("Capítulo 3: Mediação e Solução Pacífica de Conflitos", 22),
                Chapter("Capítulo 4: Ética no Uso da Tecnologia e Redes Sociais", 32)
            )
        ),
        Book(
            id = "educacao_fisica_8",
            title = "Educação Física 8º Ano: Táticas e Atletismo",
            subjectId = "educacao_fisica",
            grade = Grade.GRADE_8,
            sizeMb = 3.2,
            totalPages = 38,
            editionYear = "2024",
            publisher = "Ministério da Educação Timor-Leste",
            description = "Corrida de velocidade e resistência, salto em comprimento, basquetebol e noções de primeiros socorros desportivos.",
            remotePdfUrl = "https://storage.googleapis.com/edulibrary-tl/eb3/fisica_g8.pdf",
            chapters = listOf(
                Chapter("Unidade 1: Atletismo Escolar: Corridas e Saltos", 1),
                Chapter("Unidade 2: Fundamentos Básicos do Basquetebol", 12),
                Chapter("Unidade 3: Ginástica de Solo e Equilíbrio", 22),
                Chapter("Unidade 4: Prevenção de Lesões e Primeiros Socorros", 30)
            )
        ),
        Book(
            id = "bem_estar_8",
            title = "Bem-Estar 8º Ano: Saúde Emocional e Amizade",
            subjectId = "bem_estar",
            grade = Grade.GRADE_8,
            sizeMb = 3.1,
            totalPages = 36,
            editionYear = "2024",
            publisher = "Ministério da Educação Timor-Leste",
            description = "Compreensão das emoções na adolescência, combate ao bullying, construção de amizades fortes e hábitos de vida ativa.",
            remotePdfUrl = "https://storage.googleapis.com/edulibrary-tl/eb3/bem_estar_g8.pdf",
            chapters = listOf(
                Chapter("Capítulo 1: Emoções e Autoestima no 3º Ciclo", 1),
                Chapter("Capítulo 2: Relacionamentos Positivos e Empatia", 10),
                Chapter("Capítulo 3: Superação do Bullying e Espaço Escolar Seguro", 20),
                Chapter("Capítulo 4: Gestão do Tempo e Redução do Stress", 28)
            )
        ),
        Book(
            id = "educacao_artistica_8",
            title = "Educação Artística 8º Ano: Música e Tradição",
            subjectId = "educacao_artistica",
            grade = Grade.GRADE_8,
            sizeMb = 3.6,
            totalPages = 40,
            editionYear = "2024",
            publisher = "Ministério da Educação Timor-Leste",
            description = "Instrumentos tradicionais timorenses como o Kakalo'uk e Babadok, canções folclóricas e danças comemorativas.",
            remotePdfUrl = "https://storage.googleapis.com/edulibrary-tl/eb3/artes_g8.pdf",
            chapters = listOf(
                Chapter("Capítulo 1: O Som e a Notação Musical Básica", 1),
                Chapter("Capítulo 2: O Babadok e Instrumentos Nacionais", 10),
                Chapter("Capítulo 3: Canções Tradicionais das Regiões de Timor", 20),
                Chapter("Capítulo 4: Danças Tradicionais (Tebedai e Likurai)", 30)
            )
        ),

        // GRADE 9
        Book(
            id = "tetum_9",
            title = "Lian Tétum 9º Ano: Komunikasaun no Redasaun",
            subjectId = "tetum",
            grade = Grade.GRADE_9,
            sizeMb = 4.1,
            totalPages = 52,
            editionYear = "2024",
            publisher = "Ministério da Educação Timor-Leste",
            description = "Preparasaun ba ezame nasionál Ensino Básico. Hakerek ensaiu krítiku, sintaxe avansada no komunikasaun formál.",
            remotePdfUrl = "https://storage.googleapis.com/edulibrary-tl/eb3/tetum_g9.pdf",
            chapters = listOf(
                Chapter("Kapítulu 1: Estrutura Testu Argumentativu Avansadu", 1),
                Chapter("Kapítulu 2: Sintaxe no Pontuasaun Lian Tétum", 14),
                Chapter("Kapítulu 3: Analiza Testu Literáriu Timorense", 28),
                Chapter("Kapítulu 4: Preparasaun Ezame Nasionál 3º Siklu", 42)
            )
        ),
        Book(
            id = "portugues_9",
            title = "Língua Portuguesa 9º Ano: Literatura e Preparação de Exame",
            subjectId = "portugues",
            grade = Grade.GRADE_9,
            sizeMb = 4.5,
            totalPages = 60,
            editionYear = "2024",
            publisher = "Ministério da Educação Timor-Leste",
            description = "Preparação para o Exame Nacional do 3º Ciclo. Revisão gramatical abrangente, dissertação expositiva e obras de autores timorenses.",
            remotePdfUrl = "https://storage.googleapis.com/edulibrary-tl/eb3/portugues_g9.pdf",
            chapters = listOf(
                Chapter("Unidade 1: O Ensaio Expositivo e Crítico", 1),
                Chapter("Unidade 2: Revisão de Sintaxe e Regência Verbal", 16),
                Chapter("Unidade 3: Autores Timorenses de Expressão Portuguesa", 32),
                Chapter("Unidade 4: Guia de Preparação para o Exame Nacional", 46)
            )
        ),
        Book(
            id = "ingles_9",
            title = "English for Timor-Leste Grade 9: Towards Secondary",
            subjectId = "ingles",
            grade = Grade.GRADE_9,
            sizeMb = 3.9,
            totalPages = 48,
            editionYear = "2024",
            publisher = "Ministério da Educação Timor-Leste",
            description = "Equipping students for secondary school entrance: complex paragraphs, modal verbs, global issues and debate vocabulary.",
            remotePdfUrl = "https://storage.googleapis.com/edulibrary-tl/eb3/english_g9.pdf",
            chapters = listOf(
                Chapter("Unit 1: Global Issues and Environment", 1),
                Chapter("Unit 2: Modals: Advice, Obligation, and Ability", 14),
                Chapter("Unit 3: Science and Future Technology", 26),
                Chapter("Unit 4: Preparation for National Assessment", 38)
            )
        ),
        Book(
            id = "matematica_9",
            title = "Matemática 9º Ano: Álgebra e Geometria Espacial",
            subjectId = "matematica",
            grade = Grade.GRADE_9,
            sizeMb = 5.0,
            totalPages = 68,
            editionYear = "2024",
            publisher = "Ministério da Educação Timor-Leste",
            description = "Equações do 2º grau, funções lineares e quadráticas, trigonometria elementar e volumes de sólidos geométricos.",
            remotePdfUrl = "https://storage.googleapis.com/edulibrary-tl/eb3/matematica_g9.pdf",
            chapters = listOf(
                Chapter("Capítulo 1: Equações de 2º Grau e Fórmula de Bhaskara", 1),
                Chapter("Capítulo 2: Funções e Gráficos Cartesianos", 18),
                Chapter("Capítulo 3: Relações Trigonométricas no Triângulo Retângulo", 36),
                Chapter("Capítulo 4: Volume e Área de Prismas, Pirâmides e Cilindros", 52)
            )
        ),
        Book(
            id = "ciencias_naturais_9",
            title = "Ciências Naturais 9º Ano: Química e Física Fundamental",
            subjectId = "ciencias_naturais",
            grade = Grade.GRADE_9,
            sizeMb = 4.6,
            totalPages = 54,
            editionYear = "2024",
            publisher = "Ministério da Educação Timor-Leste",
            description = "Tabela periódica, ligações químicas simples, eletricidade estática e corrente, óptica e leis do movimento de Newton.",
            remotePdfUrl = "https://storage.googleapis.com/edulibrary-tl/eb3/ciencias_g9.pdf",
            chapters = listOf(
                Chapter("Capítulo 1: A Tabela Periódica e Elementos Químicos", 1),
                Chapter("Capítulo 2: Reações Químicas e Ligações Simples", 14),
                Chapter("Capítulo 3: Eletricidade, Circuitos e Segurança Doméstica", 28),
                Chapter("Capítulo 4: Leis de Newton e Forças da Natureza", 42)
            )
        ),
        Book(
            id = "ciencias_sociais_9",
            title = "Ciências Sociais 9º Ano: Timor no Mundo e ASEAN",
            subjectId = "ciencias_sociais",
            grade = Grade.GRADE_9,
            sizeMb = 4.8,
            totalPages = 56,
            editionYear = "2024",
            publisher = "Ministério da Educação Timor-Leste",
            description = "O papel de Timor-Leste no Sudeste Asiático (ASEAN), na CPLP e na ONU, economia global e desenvolvimento sustentável.",
            remotePdfUrl = "https://storage.googleapis.com/edulibrary-tl/eb3/sociais_g9.pdf",
            chapters = listOf(
                Chapter("Capítulo 1: Timor-Leste e a Integração na ASEAN", 1),
                Chapter("Capítulo 2: Relações Internacionais: CPLP e Nações Unidas", 14),
                Chapter("Capítulo 3: Recursos Marítimos e o Tratado do Mar de Timor", 28),
                Chapter("Capítulo 4: Objetivos de Desenvolvimento Sustentável (ODS)", 42)
            )
        ),
        Book(
            id = "educacao_civica_9",
            title = "Educação Cívica 9º Ano: Democracia e Construção da Paz",
            subjectId = "educacao_civica",
            grade = Grade.GRADE_9,
            sizeMb = 3.5,
            totalPages = 42,
            editionYear = "2024",
            publisher = "Ministério da Educação Timor-Leste",
            description = "Instituições democráticas de Timor-Leste (Presidência, Parlamento, Governo e Tribunais), eleições livres e paz duradoura.",
            remotePdfUrl = "https://storage.googleapis.com/edulibrary-tl/eb3/civica_g9.pdf",
            chapters = listOf(
                Chapter("Capítulo 1: Os Órgãos de Soberania da RDTL", 1),
                Chapter("Capítulo 2: O Processo Eleitoral e a Participação Juvenil", 12),
                Chapter("Capítulo 3: Justiça, Transparência e Combate à Corrupção", 24),
                Chapter("Capítulo 4: Preservação da Paz Social e Não-Violência", 34)
            )
        ),
        Book(
            id = "educacao_fisica_9",
            title = "Educação Física 9º Ano: Liderança e Saúde Desportiva",
            subjectId = "educacao_fisica",
            grade = Grade.GRADE_9,
            sizeMb = 3.3,
            totalPages = 38,
            editionYear = "2024",
            publisher = "Ministério da Educação Timor-Leste",
            description = "Organização de torneios escolares, arbitragem desportiva, preparação física para exames e hábitos duradouros de saúde.",
            remotePdfUrl = "https://storage.googleapis.com/edulibrary-tl/eb3/fisica_g9.pdf",
            chapters = listOf(
                Chapter("Unidade 1: Organização e Arbitragem em Torneios", 1),
                Chapter("Unidade 2: Treino de Força e Resistência Saudável", 12),
                Chapter("Unidade 3: Orientação e Caminhadas ao Ar Livre", 22),
                Chapter("Unidade 4: O Desporto como Ponte Comunitária", 30)
            )
        ),
        Book(
            id = "bem_estar_9",
            title = "Bem-Estar 9º Ano: Projetos de Vida e Cidadania Saudável",
            subjectId = "bem_estar",
            grade = Grade.GRADE_9,
            sizeMb = 3.2,
            totalPages = 36,
            editionYear = "2024",
            publisher = "Ministério da Educação Timor-Leste",
            description = "Planeamento para o Ensino Secundário ou Técnico-Vocacional, escolhas vocacionais, resiliência psicológica e cidadania saudável.",
            remotePdfUrl = "https://storage.googleapis.com/edulibrary-tl/eb3/bem_estar_g9.pdf",
            chapters = listOf(
                Chapter("Capítulo 1: Planeamento para o Ensino Secundário", 1),
                Chapter("Capítulo 2: Autoconhecimento e Competências Pessoais", 10),
                Chapter("Capítulo 3: Tomada de Decisão Saudável e Pressão Social", 20),
                Chapter("Capítulo 4: Liderança Jovem na Comunidade", 28)
            )
        ),
        Book(
            id = "educacao_artistica_9",
            title = "Educação Artística 9º Ano: Teatro e Património Nacional",
            subjectId = "educacao_artistica",
            grade = Grade.GRADE_9,
            sizeMb = 3.8,
            totalPages = 42,
            editionYear = "2024",
            publisher = "Ministério da Educação Timor-Leste",
            description = "Artes performativas, teatro comunitário para sensibilização social, preservação dos monumentos históricos e património timorense.",
            remotePdfUrl = "https://storage.googleapis.com/edulibrary-tl/eb3/artes_g9.pdf",
            chapters = listOf(
                Chapter("Capítulo 1: O Teatro Escolar e Representação Cénica", 1),
                Chapter("Capítulo 2: Roteiros Teatrais sobre a Vida Comunitária", 12),
                Chapter("Capítulo 3: Monumentos Históricos e Arquitetura Tradicional (Uma Lulik)", 24),
                Chapter("Capítulo 4: Projeto Artístico de Conclusão do 3º Ciclo", 34)
            )
        )
    )

    fun getBooksForGrade(grade: Grade): List<Book> = books.filter { it.grade == grade }
    fun getBooksForSubject(subjectId: String, grade: Grade): List<Book> =
        books.filter { it.subjectId == subjectId && it.grade == grade }
    fun getBookById(bookId: String): Book? = books.find { it.id == bookId }
}
