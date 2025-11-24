# OrbiWork API – Quarkus (DDD + PostgreSQL)

API Restful desenvolvida para o projeto **Domain Driven Design Using Java**, utilizando o framework **Quarkus**, padrão **DAO + BO + Resource**, integração com banco **PostgreSQL em nuvem (Render)**, Swagger e arquitetura limpa.

A API foi pensada para ser consumida pelo Front-End da disciplina **Front-End Design Engineering**, compondo o ecossistema **OrbiWork**.

---

## 🔧 Tecnologias Utilizadas

- **Java 17**
- **Quarkus 3.29.4**
- **JAX-RS (RESTEasy)** – Endpoints REST
- **JDBC + PostgreSQL Driver**
- **CDI (Quarkus Arc)** – Injeção de dependência (BO, DAO, etc.)
- **Swagger / SmallRye OpenAPI**
- **Docker** – Empacotamento para deploy
- **PostgreSQL (Render)** – Banco de dados em nuvem

---

## 🏛 Arquitetura em Camadas (DDD simplificado)

```text
src/main/java/br/com/orbiwork/
│
├── model/        → Entidades de domínio (Usuario, Trilha, Curso)
├── dao/          → Acesso a dados (CRUD com JDBC + ConnectionFactory)
├── bo/           → Regras de negócio, validações, orquestração de DAOs
├── resources/    → Endpoints REST (JAX-RS / Quarkus RESTEasy)
├── config/       → Configurações de CORS
└── exception/    → Exceções de negócio + mapeamento global para HTTP
```

Camadas principais:

- **Model**  
  Representa as entidades de domínio: `Usuario`, `Trilha`, `Curso`.

- **DAO (Data Access Object)**  
  Responsável por fazer o CRUD via JDBC, utilizando `ConnectionFactory` para abrir conexões com o PostgreSQL.

- **BO (Business Object)**  
  Onde ficam as **regras de negócio** e **validações** (ex.: campos obrigatórios, mensagens de erro amigáveis, verificação de existência antes de atualizar/deletar).

- **Resource**  
  Exposição das funcionalidades via **API Restful**, seguindo os padrões REST (verbos HTTP, status de retorno, etc).

- **Exception**  
  Contém exceções customizadas (`BusinessException`, `DatabaseException`, `NotFoundException`) e os respectivos handlers anotados com `@Provider`, que retornam JSON padronizado em caso de erros.

---

## 🌐 Configuração de Banco de Dados (PostgreSQL – Render)

A aplicação utiliza as seguintes variáveis de ambiente para se conectar ao banco:

- `DB_URL` – URL JDBC do PostgreSQL (Render)
- `DB_USER` – Usuário do banco
- `DB_PASSWORD` – Senha do banco

No **Render**, essas variáveis são configuradas na aba **Environment** do serviço.

No ambiente local, você pode:

1. Criar um banco PostgreSQL local **OU**
2. Conectar diretamente no banco do Render (usando a External Database URL).

### Arquivo `application.properties`

```properties
# Host obrigatório no Render
quarkus.http.host=0.0.0.0

# Swagger
quarkus.smallrye-openapi.path=/openapi
quarkus.swagger-ui.path=/swagger
quarkus.swagger-ui.always-include=true

# Tipo do banco
quarkus.datasource.db-kind=postgresql

# URL / usuário / senha (usando env vars, com defaults do Render)
quarkus.datasource.jdbc.url=${DB_URL:jdbc:postgresql://dpg-XXXX.ohio-postgres.render.com:5432/orbiwork_db?sslmode=require}
quarkus.datasource.username=${DB_USER:orbiwork_db_user}
quarkus.datasource.password=${DB_PASSWORD:senha_aqui}

# Script para criar tabelas TRILHA, CURSO, USUARIO
quarkus.datasource.jdbc.initialization-script-path=schema.sql
```

> O arquivo `schema.sql` é executado automaticamente na inicialização para garantir que as tabelas **TRILHA**, **CURSO** e **USUARIO** existam no banco.

---

## 🚀 Como rodar em modo Dev (Local)

### Pré-requisitos

- Java 17 instalado
- Maven (ou o `mvnw` do próprio projeto)
- Banco PostgreSQL acessível (local ou cloud)

### Passo a passo

1. Configure as variáveis de ambiente (se necessário):

   **Windows (PowerShell):**

   ```powershell
   $env:DB_URL="jdbc:postgresql://SEU_HOST:5432/orbiwork_db?sslmode=require"
   $env:DB_USER="orbiwork_db_user"
   $env:DB_PASSWORD="sua_senha"
   ```

2. Rode em modo Dev:

   ```bash
   ./mvnw quarkus:dev
   ```

3. Acesse:

    - Dev UI: **http://localhost:8080/q/dev**
    - Swagger: **http://localhost:8080/swagger**

---

## 📦 Build e Execução (JAR)

### Gerar build normal:

```bash
./mvnw package
```

Arquivos gerados em:

```text
target/quarkus-app/
```

### Rodar o JAR:

```bash
java -jar target/quarkus-app/quarkus-run.jar
```

---

## 🐳 Docker

O projeto já possui um **Dockerfile** preparado para build e execução em produção.

### Build da imagem

```bash
docker build -t orbiwork-api .
```

### Executar localmente com Docker

```bash
docker run -p 8080:8080 \
  -e DB_URL="jdbc:postgresql://SEU_HOST:5432/orbiwork_db?sslmode=require" \
  -e DB_USER="orbiwork_db_user" \
  -e DB_PASSWORD="sua_senha" \
  orbiwork-api
```

> Em produção (Render), essas variáveis são configuradas diretamente na plataforma.

---

## 📚 Swagger (Documentação da API)

A documentação Swagger/OpenAPI está disponível em:

- UI: **`/swagger`**  
  👉 Ex.: `http://localhost:8080/swagger`

- OpenAPI JSON: **`/openapi`**  
  👉 Ex.: `http://localhost:8080/openapi`

---

## 🧪 Endpoints Principais

### 🧍 Usuários

| Método | Rota           | Descrição                |
|--------|----------------|--------------------------|
| GET    | `/usuarios`    | Lista usuários           |
| GET    | `/usuarios/{id}` | Busca usuário por ID   |
| POST   | `/usuarios`    | Cria usuário             |
| PUT    | `/usuarios/{id}` | Atualiza usuário       |
| DELETE | `/usuarios/{id}` | Remove usuário         |

### 🎓 Trilhas

| Método | Rota           | Descrição                 |
|--------|----------------|---------------------------|
| GET    | `/trilhas`     | Lista trilhas             |
| GET    | `/trilhas/{id}` | Busca trilha por ID      |
| POST   | `/trilhas`     | Cria trilha               |
| PUT    | `/trilhas/{id}` | Atualiza trilha          |
| DELETE | `/trilhas/{id}` | Remove trilha            |

### 📘 Cursos

| Método | Rota           | Descrição                 |
|--------|----------------|---------------------------|
| GET    | `/cursos`      | Lista cursos              |
| GET    | `/cursos/{id}` | Busca curso por ID        |
| POST   | `/cursos`      | Cria curso                |
| PUT    | `/cursos/{id}` | Atualiza curso            |
| DELETE | `/cursos/{id}` | Remove curso              |

---

## ✅ Requisitos do Enunciado Atendidos

- ✔ **Camada Model** com todas as classes necessárias alinhadas ao domínio OrbiWork
- ✔ **Camada DAO e BO** com:
    - CRUD completo (Create, Read, Update, Delete)
    - Uso de `ConnectionFactory` para gerenciamento de conexões
    - Regras de negócio e validações (camada BO)
- ✔ **Camada Resource**:
    - Endpoints REST anotados com JAX-RS
    - Uso correto de verbos HTTP e códigos de status
    - Configuração de **CORS** liberando acesso ao front-end
- ✔ **Boas Práticas**:
    - Organização em pacotes por responsabilidade
    - Tratamento adequado de exceções (BusinessException, NotFoundException, DatabaseException)
    - Padrões de projeto: DAO, camadas separadas (Model, DAO, BO, Resource)
- ✔ **Deploy da Aplicação**:
    - Preparada para deploy em **Render** usando Docker
    - Integração com banco **PostgreSQL em nuvem**

---

## 🔗 Integração com o Front-End

A API foi construída para ser consumida pelo front-end da disciplina **Front-End Design Engineering**, permitindo:

- Listagem de trilhas, cursos e usuários
- Criação e manutenção dos registros via interface web
- Consumo fácil via fetch/axios apontando para a URL de deploy da API (Render)

---

## 👨‍💻 Autores

- **Raphael Gomes Mancera – RM 562279**
- **Equipe OrbiWork**

---

## 📄 Licença

Projeto desenvolvido para fins **acadêmicos**, no contexto da disciplina **Domain Driven Design Using Java (FIAP)**.  
Uso externo deve citar a fonte e os autores.
