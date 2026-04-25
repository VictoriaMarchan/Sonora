


O **Sonora** é um aplicativo reprodutor de música para Android focado em oferecer uma experiência visual altamente estética e fluida. Desenvolvido inteiramente utilizando **Jetpack Compose** e a linguagem **Kotlin**, o projeto demonstra a criação de uma interface moderna de áudio aliada a animações imersivas e gerenciamento de estado global.

---

##  Funcionalidades Principais

* **Interface Declarativa Avançada:** UI/UX 100% construída com Jetpack Compose, utilizando fontes personalizadas (Pixel Art, Cursiva, Serif) e gradientes retro-pink.
* **Reprodução de Áudio Nativa:** Integração profunda com a API `MediaPlayer` do Android para reprodução de faixas em tempo real diretamente da pasta `res/raw`.
* **Player Imersivo (Vinyl Player):** Tela de reprodução detalhada com animações de rotação infinita (disco de vinil sincronizado com o status de play/pause), barra de progresso dinâmica e cálculos de tempo em tempo real.
* **Navegação Fluida:** Arquitetura de navegação utilizando `NavHost` do Compose para transições sem engasgos entre Home, Busca, Biblioteca e Player, incluindo um **Mini Player Global** persistente através das telas.
* **Busca em Tempo Real:** Motor de pesquisa programático que filtra iterativamente listas profundas de álbuns e faixas, respondendo a cada tecla digitada pelo usuário.

---

##  Telas do Aplicativo

* `HomeScreen`: Painel de descoberta com abas dinâmicas ("All", "For You", "Daily Mood") e listas horizontais/verticais.
* `SearchScreen`: Sistema de busca instantânea e grade de exploração de gêneros musicais.
* `PlaylistScreen`: Visualização completa de um álbum, detalhando as faixas, duração e informações do artista.
* `VinylPlayerScreen`: O coração visual do app, simulando um toca-discos com controles de mídia completos.

---

##  Tecnologias e Arquitetura

Este projeto foi construído utilizando as melhores práticas do desenvolvimento Android moderno:

* **Linguagem:** Kotlin
* **UI Toolkit:** Jetpack Compose (Material Design 3)
* **Navegação:** Navigation Compose
* **Mídia:** `android.media.MediaPlayer` nativo
* **Animação:** Compose Animation APIs (`rememberInfiniteTransition`, `AnimatedVisibility`, `Crossfade`)
* **Arquitetura:** Baseada em componentes (Presentation Layer separada do Model/Repository Layer de Mock Data). Mapeamento de estado `TrackInfo` para gerenciar a música atual em reprodução globalmente.

---


