<h1 align="center">
  <img src="link_para_seu_logo.png" alt="Logo do Seu App" width="200">
  <br>
  Seven Food
</h1>

<p align="center">Simplifique sua experiência de pedidos em restaurantes com o nosso aplicativo de autoatendimento, projetado para oferecer conveniência e eficiência durante suas visitas.</p>

## 🍔 Facilidade de Pedidos

Aproveite a conveniência de fazer pedidos diretamente de um totem de autoatendimento. Nosso aplicativo permite que você selecione facilmente itens do menu, personalize suas escolhas e faça pedidos rapidamente, sem a necessidade de esperar na fila.

### Personalização Sob Demanda

Quer adicionar ou remover ingredientes de seu hambúrguer? Ou talvez prefira escolher o tipo de queijo ou o molho em seu sanduíche? Nosso aplicativo oferece opções de personalização para garantir que seu pedido seja preparado exatamente como você deseja.

## 💳 Pagamento Rápido e Seguro

Além de simplificar o processo de pedidos, nosso aplicativo oferece opções de pagamento rápidas e seguras. Pague com facilidade diretamente no totem de autoatendimento e evite a espera na fila do caixa.

## 📍 Localização Fácil

Localize facilmente o totem de atendimento mais próximo usando nosso aplicativo. Com apenas alguns toques, encontre o restaurante mais próximo de você e faça seu pedido antes mesmo de chegar.

## 🚀 Como Começar

1. Localize um totem de atendimento disponível em nosso restaurante.
2. Abra o aplicativo e selecione os itens desejados no menu.
3. Personalize seu pedido de acordo com suas preferências.
4. Realize o pagamento de forma rápida e segura diretamente no totem.
5. Aguarde a notificação para retirar seu pedido no balcão de retirada.

## 📧 Contato

Se você tiver alguma dúvida ou precisar de suporte, entre em contato conosco em:

- Email: seuemail@exemplo.com
- Website: www.seusite.com


## 🚀 Como Instalar e Executar

Siga estas etapas simples para instalar e executar o aplicativo em seu dispositivo:

### Pré-requisitos

Certifique-se de ter o seguinte configurado em seu sistema:

- Ambiente de desenvolvimento Java verssão 17 instalado
- Docker e Docker Compose instalados

## Stack utilizada

* Java 17
* Spring boot 3
* Intellij
* PostGres (PGAdmin)
* Docker && Docker Compose

### 🛠️ Passos de Instalação

1. Faça um clone do repositório para o seu ambiente local usando o seguinte comando:
   ```sh
   git clone https://github.com/fiapg70/tech-challenge-fase-1.git

### Docker Compose

Utilize o comando `docker compose up` para "construir" (*build*) e subir o servidor local, expondo a porta 3000 em `localhost`. Além do container da `api` também subirá o serviço `db` com o banco de dados de desenvolvimento.

**IMPORTANTE:** Esta API está programada para ser acessada a partir de `http://localhost:9991/api` e o banco de dados utiliza a porta `5432`. Certifique-se de que não existam outros recursos ocupando as portas `5432` / `16543` e `9991` antes de subir o projeto.

Para derrubar o serviço, execute o comando `docker compose down`.

### Configurações

Para rodar o projeto deverã colocar as variaveis de ambiente:

DATABASE_PASSWORD=Postgres2019!;DATABASE_URL=jdbc:postgresql://localhost:5432/sevenfood;DATABASE_USERNAME=postgres

ou fazer um .env com essas configurações:

DATABASE_PASSWORD=Postgres2019!
DATABASE_URL=jdbc:postgresql://localhost:5432/sevenfood
DATABASE_USERNAME=postgres

### Rodando a aplicação sem IDE ou em VM ou EC2.

1. Faça na pasta principal rodar o docker, nele contém postgres a compilação do Dockerfile da API e ngnix commo nginx reverse proxy.
   ```sh
   docker-compose up -d

### Endpoints

Esta API fornece documentação no padrão OpenAPI.
Os endpoints disponíveis, suas descrições e dados necessários para requisição podem ser consultados e testados em ```http://localhost:9991/api/swagger-ui/index.html```.

O repositório do projeto também fornece uma coleção do Postman para testes em todos os endpoints.

Collection do postman: ![Collection Postman](postman/sevenfood.postman_collection.json)

## Desenvolvimento do projeto

### Diagramas de fluxo

Foram utilizadas técnicas de Domain Driven Design para definição dos fluxos:

- Realização do pedido e pagamento
  ![diagrama do fluxo de pedido e pagamento](docs/domain-storytelling/images/pedido-pagamento.png)

- Preparação e entrega do pedido
  ![diagrama do fluxo de preparação e entrega](docs/domain-storytelling/images/preparo-retirada.png)

- Diagrama com a separação dos contextos delimitados
  ![diagrama dos contextos delimitados](docs/domain-storytelling/images/contextos-delimitados.png)