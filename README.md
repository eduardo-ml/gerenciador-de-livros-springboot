# 📚 Gerenciador de Livros API

![Java](https://img.shields.io/badge/Java-21-blue.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.4-brightgreen.svg)
![Maven](https://img.shields.io/badge/Maven-4.0.0-red.svg)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-gray.svg?logo=postgresql)

API RESTful para gerenciamento de livros, autores e bibliotecas, desenvolvida com as melhores práticas de arquitetura de software e tecnologias modernas do ecossistema Java.

Este projeto não é apenas um CRUD, mas uma demonstração de como construir APIs robustas, manuteníveis e seguras, seguindo uma arquitetura em camadas bem definida.

## ✨ Principais Características

* **Arquitetura em Camadas:** Código organizado nas camadas de Controller, Service e Repository, garantindo a separação de responsabilidades e facilitando a manutenção.
* **Padrão DTO (Data Transfer Object):** Utilização de DTOs para desacoplar a camada da API da camada de persistência de dados, aumentando a segurança e a flexibilidade.
* **Validação de Dados:** Validações robustas na entrada de dados usando `jakarta.validation.constraints` para garantir a integridade das informações.
* **Tratamento de Exceções Centralizado:** Implementação de um `GlobalExceptionHandler` com `@RestControllerAdvice` para lidar com erros de forma consistente e elegante em toda a aplicação.
* **Injeção de Dependências:** Uso do padrão de injeção de dependências do Spring para gerenciar os componentes da aplicação.

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 21
* **Framework:** Spring Boot 3
* **Acesso a Dados:** Spring Data JPA
* **Banco de Dados:** PostgreSQL
* **Build Tool:** Maven
* **Utilitários:** Lombok

## 🚀 Como Executar o Projeto

**Pré-requisitos:**
* Java 21 ou superior
* Maven
* PostgreSQL

**Passos:**

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/eduardo-ml/gerenciador-de-livros-springboot.git
    cd gerenciador-de-livros
    ```

2.  **Configure o banco de dados:**
    * Crie um banco de dados no PostgreSQL chamado `livros_db`.
    * Configure as variáveis de ambiente `DB_USERNAME` e `DB_PASSWORD` com suas credenciais do banco. O projeto as lerá do arquivo `application.properties`.

3.  **Execute a aplicação:**
    ```bash
    ./mvnw spring-boot:run
    ```

A API estará disponível em `http://localhost:8080`.

## 🗺️ Endpoints da API

A API oferece endpoints completos para o gerenciamento de `Livros`, `Autores` e `Bibliotecas`.

* `POST /livros` - Registra um novo livro.
* `GET /livros` - Lista todos os livros.
* `GET /livros/{id}` - Busca um livro por ID.
* `PUT /livros/{id}` - Atualiza um livro existente.
* `DELETE /livros/{id}` - Exclui um livro.

(Endpoints similares estão disponíveis para `/autores` e `/bibliotecas`).