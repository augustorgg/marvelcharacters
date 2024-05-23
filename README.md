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
  <img src="app%2Fsrc%2Fmain%2Fres%2Fscreenshot%2FScreenshot_1.jpg" width="300" style="margin-right: 10px;">
  <img src="app%2Fsrc%2Fmain%2Fres%2Fscreenshot%2FScreenshot_2.jpg" width="300" style="margin-right: 10px;">
  <img src="app%2Fsrc%2Fmain%2Fres%2Fscreenshot%2FScreenshot_3.jpg" width="300" style="margin-right: 10px;">
  <img src="app%2Fsrc%2Fmain%2Fres%2Fscreenshot%2FScreenshot_4.jpg" width="300">
</div>

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

## Configuração de Dependências e Estrutura de Arquitetura
# Dependências

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
- AndroidX Core Splashscreen
- Shimmer
- MockK para testes de mocking
- kotlinx.coroutines.test para testes de coroutines
- AndroidX Core Testing

# Estrutura de Arquitetura

### Data
ApiInstanceProvider

A classe `ApiInstanceProvider` é fundamental para configurar e fornecer uma instância da API da Marvel utilizando Retrofit. Este provedor de API gerencia as requisições HTTP, configurando o cliente HTTP com interceptores específicos para autenticação e compressão de dados, garantindo que todas as requisições sejam seguras e eficientes.

## Interceptors

Os interceptores são componentes essenciais na configuração do cliente HTTP (`OkHttpClient`). Eles interceptam e modificam requisições e respostas de rede, garantindo que os dados sejam manipulados corretamente.

# AuthInterceptor

**Função:** Adiciona os parâmetros de autenticação necessários para as requisições à API da Marvel.
**Detalhes:**
- Adiciona a chave pública (apikey), um timestamp (ts), e um hash gerado a partir do timestamp, chave privada, e chave pública a cada requisição.
  **Processo:**
1. Captura a requisição original e a URL.
2. Gera um timestamp atual.
3. Cria um hash MD5.
4. Constrói uma nova URL com os parâmetros de autenticação.
5. Cria e prossegue com a nova requisição.

# GzipInterceptor

**Função:** Gerencia a descompressão de respostas Gzip.
**Detalhes:**
- Verifica se a resposta está comprimida (header "Content-Encoding" é "gzip") e, se estiver, descomprime o corpo da resposta.
  **Processo:**
1. Prossegue com a cadeia de interceptação e obtém a resposta original.
2. Se comprimida, descomprime o corpo da resposta utilizando `GZIPInputStream`.
3. Cria um novo `ResponseBody` descomprimido e substitui o corpo da resposta original.

## DataResult

A classe `DataResult` é uma classe selada que representa o resultado de uma operação de obtenção de dados. Ela pode ser de três tipos:

- **Success:** Representa uma operação bem-sucedida e contém o valor resultante.
- **NetworkError:** Representa um erro de rede, incluindo uma mensagem de erro e o status HTTP.
- **GenericError:** Representa um erro genérico, incluindo uma mensagem de erro, o status HTTP e uma exceção opcional.

## MarvelApiDataResponse

O modelo de dados é representado principalmente pela classe `MarvelApiDataResponse`, que é fundamental para encapsular e estruturar os dados retornados pela API da Marvel. Esta classe, juntamente com suas classes auxiliares, mapeia a estrutura dos dados em objetos Kotlin. Ela inclui objetos para representar detalhes dos personagens, URLs associadas, miniaturas, quadrinhos, histórias, eventos e séries, fornecendo uma representação completa e estruturada dos dados obtidos.

## MarvelCharactersDetailDataSource

A classe `MarvelCharactersDetailDataSource` é responsável por obter os detalhes dos personagens da Marvel a partir da API. Utilizando Retrofit, ela encapsula o resultado da chamada em um objeto `DataResult`, que pode representar sucesso ou diferentes tipos de erros.

- **Propriedades:**
    - `api`: Uma instância da interface `MarvelCharactersApi`.
- **Método `handleApiCall`:**
    - **Função:** Lida com a execução segura das chamadas da API e o tratamento de suas respostas.
    - **Processo:**
        1. Executa a chamada da API.
        2. Verifica se a resposta é bem-sucedida e não nula, retornando `DataResult.Success`.
        3. Caso contrário, retorna `DataResult.NetworkError` ou `DataResult.GenericError` conforme o tipo de erro.

## MarvelCharactersApi

A interface `MarvelCharactersApi` define os endpoints da API da Marvel que o aplicativo pode acessar. Utiliza Retrofit para declarar as requisições HTTP e os endpoints correspondentes.

## MarvelCharacterRepositoryImpl

A classe `MarvelCharacterRepositoryImpl` implementa a interface `MarvelCharacterRepository`, servindo como a ponte entre a camada de dados e a camada de domínio. Ela utiliza um data source (`MarvelCharactersDetailDataSource`) para obter dados da API da Marvel e mapeia esses dados para modelos de domínio.

- **Propriedades:**
    - `dataSource`: Instância de `MarvelCharactersDetailDataSource`.
    - `ioDispatcher`: Um `CoroutineDispatcher` para definir em qual thread as operações de E/S serão executadas.
- **Método `getMarvelCharactersDetail`:**
    - **Função:** Busca os detalhes dos personagens da Marvel utilizando o data source e mapeia os resultados para o modelo de domínio.
    - **Processo:**
        1. Executa a operação de E/S na thread apropriada.
        2. Tenta obter os detalhes dos personagens.
        3. Retorna um `Result` correspondente dependendo do `DataResult`:
        - `Success`: Mapeia e retorna `Result.Success`.
        - `NetworkError`: Loga o erro e retorna `Result.NetworkError`.
        - `GenericError`: Loga o erro e retorna `Result.GenericError`.

## Logger

O `Logger` é um objeto utilitário usado para registrar erros dentro do aplicativo. Ele encapsula o uso da classe `Log` do Android, fornecendo uma maneira consistente e centralizada para logar mensagens de erro.

- **Método `logError`:**
    - Loga uma mensagem de erro juntamente com um `Throwable` opcional, utilizando `Log.e` para registrar erros com a tag definida e a mensagem/`Throwable` fornecidos.

## dataModule

O `dataModule` é um módulo de injeção de dependência definido usando Koin. Ele configura a criação e a injeção das dependências necessárias na camada de dados.

## Configurações:

- `ApiInstanceProvider.marvelCharactersApi`: Fornece a instância da API da Marvel.
- `MarvelCharactersDetailDataSource`: Fornece o data source que obtém os detalhes dos personagens.
- `MarvelCharacterRepositoryImpl`: Fornece a implementação do repositório, registrada como a implementação da interface `MarvelCharacterRepository`.


### Domain

## Configuração de Dependências na Camada de Domínio

Na camada de domínio, a configuração de dependências é essencial para garantir que as classes e componentes necessários estejam disponíveis para execução. Aqui está a configuração específica das dependências no módulo de injeção de dependência (`domainModule`):

- **Dispatchers.IO:** Uma fábrica (factory) que fornece o dispatcher de E/S (`Dispatchers.IO`) para operações assíncronas. É utilizado para realizar operações de E/S de forma eficiente, garantindo que não bloqueiem a thread principal.

- **GetMarvelCharacterUseCase:** Um caso de uso que encapsula a lógica de negócio para obter os detalhes dos personagens da Marvel. É registrado como um singleton (single) no módulo de domínio.

## Mapper de Detalhes dos Personagens da Marvel

O mapper `MarvelCharactersDetailMapper` é responsável por converter objetos de modelo de dados da camada de dados para objetos de modelo de domínio da camada de domínio. Ele desempenha um papel crucial na transformação dos dados obtidos da API da Marvel em estruturas compreensíveis e utilizáveis pelo aplicativo.

## Modelos de Domínio

Os modelos de domínio da Marvel são classes Kotlin que representam as entidades principais manipuladas pelo aplicativo. Esses modelos são projetados para encapsular os detalhes dos personagens, suas histórias em quadrinhos, eventos, séries e muito mais.

## Resultado de Operações

A classe `Result` é uma estrutura fundamental que encapsula o resultado de operações, fornecendo uma maneira eficaz de lidar com diferentes cenários de execução:

- **Success:** Representa uma operação bem-sucedida e contém o valor resultante.
- **NetworkError:** Representa um erro de rede, que pode ocorrer devido a problemas de conexão, timeouts, entre outros. Esse tipo de resultado inclui uma mensagem de erro para descrever o problema encontrado e, opcionalmente, o status HTTP associado.
- **GenericError:** Representa um erro genérico que não se encaixa nas categorias específicas de erro. Isso pode incluir exceções lançadas durante a execução da operação. O `GenericError` também pode conter a exceção original para fins de depuração e tratamento posterior.

## Funções de Extensão

As funções de extensão `onSuccess`, `onNetworkError` e `onGenericError` permitem lidar com diferentes tipos de resultados de forma concisa e direta, simplificando o tratamento de resultados de operações assíncronas.

## Repositório

O repositório `MarvelCharacterRepository` define o contrato para a obtenção dos detalhes dos personagens da Marvel. Ele declara um método para obter os detalhes dos personagens, retornando um resultado que pode ser sucesso ou conter informações de erro.

## Caso de Uso

A interface `GetMarvelCharacterUseCase` declara o contrato para o caso de uso de obtenção dos detalhes dos personagens da Marvel. Ela contém um método que encapsula a lógica de negócio para obter esses detalhes.

## Implementação do Caso de Uso

A classe `GetMarvelCharacterUseCaseImpl` é a implementação concreta do caso de uso. Ela implementa a interface `GetMarvelCharacterUseCase` e define a lógica específica para obter os detalhes dos personagens da Marvel usando o repositório. Essa implementação é executada de forma assíncrona em um despachante específico.


### Presentation

## Configuração de Dependências na Camada de Apresentação

**PresentationModule:**
- Este módulo configura as dependências relacionadas à interface do usuário do aplicativo.

**Componentes:**
- **ViewModel:** Configura a ViewModel utilizada na camada de apresentação.
- **MarvelCharactersViewModel:** Fornece dados sobre os personagens da Marvel para a interface do usuário.
    - **get():** Obtém o repositório necessário para a ViewModel.

Essa configuração permite que a interface do usuário acesse os dados dos personagens da Marvel e proporcione uma experiência interativa.

---

## Adaptadores de Personagens e Detalhes de Personagem

**Propósito:**
- **MarvelCharacterDetailAdapter:** Exibe uma lista de quadrinhos relacionados a um personagem da Marvel.
- **MarvelCharactersAdapter:** Mostra uma lista de personagens da Marvel.

Esses adaptadores garantem a apresentação organizada e interativa dos dados na interface do usuário.

---

## MainActivity

- Esta é a atividade principal do aplicativo, responsável por configurar a interface do usuário e a navegação entre os diferentes destinos.

    - *onCreate():* Este método é chamado quando a atividade é criada, e aqui está o que acontece dentro dele:
        - Infla o layout da atividade usando o ActivityMainBinding.
        - Configura o layout inflado como o conteúdo da atividade.
        - Obtém o NavController do NavHostFragment definido no layout da atividade, que gerencia a navegação entre os fragmentos da aplicação.

Essa configuração inicializa a interface do usuário e prepara a navegação entre os diferentes destinos do aplicativo.

---

## MarvelCharactersFragment

- Este fragmento exibe a lista de personagens da Marvel e lida com as interações do usuário relacionadas a essa lista.

    - *onCreateView():* Este método é chamado quando o fragmento é criado, e aqui está o que acontece dentro dele:
        - Infla o layout do fragmento usando o FragmentMarvelCharactersBinding.
        - Retorna a raiz do layout inflado.

    - *onViewCreated():* Este método é chamado após a criação da view do fragmento, e aqui está o que acontece dentro dele:
        - Configura os botões e ouvintes de clique.
        - Chama o método getMarvelCharactersDetails() do ViewModel para obter os detalhes dos personagens da Marvel.

---

## MarvelCharacterDetailFragment

- Este fragmento exibe os detalhes de um personagem da Marvel, incluindo nome, descrição, lista de quadrinhos e imagem.

    - *onCreateView():* Este método é chamado quando o fragmento é criado, e aqui está o que acontece dentro dele:
        - Infla o layout do fragmento usando o FragmentMarvelCharacterDetailBinding.
        - Retorna a raiz do layout inflado.

    - *onViewCreated():* Este método é chamado após a criação da view do fragmento, e aqui está o que acontece dentro dele:
        - Configura os detalhes do personagem, como nome, descrição e lista de quadrinhos, e os exibe nos respectivos elementos visuais.

---

## MarvelCharactersViewState

- Esta classe define os diferentes estados possíveis da tela que exibe os personagens da Marvel.

    - *Loading:* Indica que a tela está carregando os dados dos personagens da Marvel.
    - *Success:* Indica que os dados dos personagens da Marvel foram carregados com sucesso.
    - *Error:* Indica que ocorreu um erro ao carregar os dados dos personagens da Marvel.

Esses estados são usados para atualizar a interface do usuário de acordo com o resultado do carregamento dos dados.

---

## Extensions.kt

- *show() e hide():* Estas funções estendem a classe View para mostrar e ocultar visualizações.
- *startShimmering() e stopShimmering():* Estas funções estendem ShimmerFrameLayout para iniciar e parar a animação de brilho.
- *getSecureImageUrl():* Uma extensão da classe CharacterResult para obter a URL de imagem de um personagem da Marvel.

---

## MarvelCharactersViewModel

- Neste ViewModel, a classe MarvelCharactersViewModel, temos a lógica para obter os detalhes dos personagens da Marvel.

    - *Construtor:* O ViewModel é inicializado com uma instância de GetMarvelCharacterUseCase.
    - *ViewState LiveData:* Usamos um MutableLiveData para representar o estado da visualização.
    - *getMarvelCharactersDetails():* Esta função é chamada para iniciar a obtenção dos detalhes dos personagens.
    - *Coroutines:* A lógica de negócio é executada em uma coroutine lançada no viewModelScope.
    - *Tratamento de Resultado:* Uma vez que a operação é concluída, tratamos o resultado usando as extensões fornecidas pela classe Result.

Essa estrutura mantém a lógica de negócio separada da camada de apresentação e oferece uma maneira limpa e reativa de lidar com as operações assíncronas e seus resultados.
