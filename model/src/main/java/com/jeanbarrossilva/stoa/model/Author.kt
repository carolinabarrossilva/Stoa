package com.jeanbarrossilva.stoa.model

import java.io.Serializable

data class Author(val name: String): Serializable {
    val books = mutableListOf<Book>()

    companion object {
        val List<Author>.books
            get() = flatMap { author ->
                author.books
            }

        val sample = Author("André Silva").apply {
            val book = Book(
                author = this,
                cover = BookCover(url = "https://storage.googleapis.com/images.uiclap.com/capa/ua6632.jpg"),
                title = "Vá para onde a AÇÃO acontece!",
                subtitle = "Os 5 princípios de uma LIDERANÇA centrada no EXEMPLO",
                description = "O que não faltam por aí são gurus motivacionais, belíssimos seminários e outros eventos sobre " +
                    "liderança e motivação. Mas, de maneira geral, eles são muito mais sobre o estudo da liderança do que sobre a " +
                    "prática. Ouvir e se emocionar com histórias de sucesso e fazer estudos de caso sobre decisões do passado, " +
                    "dizendo como faríamos diferente e melhor, nem de longe se compara com ter que decidir e agir, aqui no mundo " +
                    "real, quando é o seu (...) que está na reta, não é mesmo?\nPor isso, associar o líder a um homem de terno e " +
                    "gravata pode nos tirar do foco do que nos torna líderes e onde a liderança começa. Não espere chegar em " +
                    "funções e altos cargos de chefia para escolher liderar. Os 5 princípios apresentados nesse livro vêm me " +
                    "guiando tanto na vida pessoal, quanto nas diversas funções de chefia e liderança que desempenhei em Unidades " +
                    "operativas e de ensino do Corpo de Fuzileiros Navais e tiveram um papel crucial no comando do 1º Batalhão de " +
                    "Operações Ribeirinhas, localizado na cidade de Manaus, durante à pandemia de COVID-19. Veremos aqui que é na " +
                    "crise que os líderes se revelam, ou não.Dessa forma, acredito que esses princípios também poderão servir de " +
                    "guias para suas decisões e ações, bem como para auxiliar na transição de toda teoria, acumulada com o estudo, " +
                    "para a prática, pois a liderança começa exatamente aí onde você está, com os recursos que dispõe e cercado de " +
                    "medos e incertezas. Aqui você vai conhecer uma liderança simples e que funciona, sem a necessidade de fórmulas " +
                    "mágicas e mirabolantes, falsas promessas, atuações cinematográficas ou pirotecnias. Porém, devo alertar que " +
                    "liderar nunca será uma tarefa fácil, pois demanda mais querença do que crença, além de muito esforço, coragem " +
                    "e paciência. E tudo valerá a pena quando olhar para trás e ver o legado que ajudou a construir pelos lugares " +
                    "por onde passou. Comece HOJE! Lidere a si mesmo, organize-se, adote uma cultura baseada na confiança, seja o " +
                    "EXEMPLO e, por fim, abandone, sempre que possível, o conforto dos escritórios e das salas de aula e " +
                    "acostume-se a estar onde a AÇÃO acontece, pois é lá que a verdadeira liderança começa.\n1. Em um mundo " +
                    "carente de bons exemplos, onde diversas pessoas buscam seus direitos ao mesmo tempo em que negligenciam seus " +
                    "deveres e onde é mais fácil culpar os outros do que assumir responsabilidades: “LIDERE-SE!”\n2. Na era do " +
                    "conhecimento, onde o excesso de informações e de demandas nos desvia de nossos valores e atividade-fim: " +
                    "“MENOS, porém MELHOR.”\n3. Em uma sociedade que vive uma grave crise de confiança e onde os gestores optam " +
                    "por confiar nos processos e controlar as pessoas; onde se tenta ignorar os impactos da vida pessoal na vida " +
                    "profissional; e onde o excesso de normas e burocracias tendem a desumanizar as pessoas: “As PESSOAS em " +
                    "primeiro lugar.”\n4. Em meio às crenças de que erros podem trazer graves impactos na carreira, levando as " +
                    "pessoas a optarem pela inação: “ERROS não são fracassos.”\n5. Por fim, sintetizando tudo isso e mostrando a " +
                    "importância do exemplo, da presença do líder junto aos seus liderados, da exposição aos mesmos riscos e da " +
                    "liderança no combate: “Vá para onde a AÇÃO acontece!”\nJulguei importante incluir dois capítulos extras: o " +
                    "primeiro sobre o Fator Carisma, algo fundamental para o exercício da liderança, mas visto por muitos como algo " +
                    "exclusivo para poucos ou simplesmente midiático e embusteiro. Vamos desmistificar isso! O segundo capítulo " +
                    "extra e que fecha esse livro, tem o papel de nos alertar para que não sejamos vítimas das armadilhas do nosso " +
                    "próprio ego, ou seja, quando começamos a nos achar melhor do que os outros e somos tomados pela arrogância, " +
                    "pela necessidade de controlar tudo e todos e pela paranoia."
            )
            books.add(book)
        }
    }
}