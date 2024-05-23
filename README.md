# Marvel Characters

O Marvel Characters App é um aplicativo desenvolvido para fornecer aos usuários uma lista de heróis da Marvel, obtida através da API da Marvel. O aplicativo utiliza a arquitetura MVVM com Clean Architecture para garantir uma estrutura de código organizada e de fácil manutenção.

## Funcionalidades
- Exibe uma lista de heróis da Marvel, incluindo seus nomes e imagens.
- Utiliza o efeito Shimmer para indicar o carregamento de dados, proporcionando uma experiência visual elegante enquanto os dados são carregados.
- Fornece detalhes sobre cada herói, como sua descrição e em quais revistas em quadrinhos o personagem apareceu.
- Apresenta uma experiência visual agradável com o uso do framework de design Material Components.
- Trata erros de conexão e exibe mensagens informativas ao usuário em caso de falhas.
- Inclui um botão de WhatsApp para comunicação direta com o desenvolvedor, oferecendo suporte e feedback rápido.


## Capturas de Tela
<div style="display: flex;">
  <img src="app%2Fsrc%2Fmain%2Fres%2Fdrawable%2Fscreenshot_1.webp" width="300" style="margin-right: 10px;">
  <img src="app%2Fsrc%2Fmain%2Fres%2Fdrawable%2Fscreenshot_2.webp" width="300" style="margin-right: 10px;">
  <img src="app%2Fsrc%2Fmain%2Fres%2Fdrawable%2Fscreenshot_3.webp" width="300" style="margin-right: 10px;">
  <img src="app%2Fsrc%2Fmain%2Fres%2Fdrawable%2Fscreenshot_4.webp" width="300">
</div>

## Dependências

O projeto faz uso das seguintes dependências:

- AndroidX Core KTX
- AndroidX AppCompat
- Material Components for Android
- AndroidX Constraint Layout
- AndroidX Navigation Component
- AndroidX Work Runtime
- JUnit
- JUnit Jupiter
- AndroidX JUnit
- Espresso Core
- Koin para Android
- Retrofit
- Converter Gson
- Picasso
- Shimmer
- MockK para testes de mocking
- kotlinx.coroutines.test para testes de coroutines
- AndroidX Core Testing
- Ktlint para análise estática de código

## Requisitos

Para executar o aplicativo, é necessário fornecer as chaves de API necessárias para fazer solicitações à API da Marvel. Essas chaves devem ser configuradas no arquivo `key.properties`. Certifique-se de que o arquivo `key.properties` está presente no projeto e contém as seguintes chaves:

`API_PUBLIC_KEY="SUA_CHAVE_PUBLICA"`
<br>
`API_PRIVATE_KEY="SUA_CHAVE_PRIVADA"`

## Como usar

1. Clone este repositório para o seu ambiente de desenvolvimento.
2. Certifique-se de fornecer as chaves de API corretas no arquivo `key.properties`.
3. Abra o projeto no Android Studio.
4. Execute o aplicativo em um dispositivo ou emulador Android.
