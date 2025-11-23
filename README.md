
# Orbiwork API – Quarkus

API Restful desenvolvida para o projeto **Domain Driven Design Using Java**, utilizando o framework **Quarkus**, padrão **DAO + BO + Resource**, integração com banco **Oracle**, Swagger e arquitetura limpa.

---

## 🚀 Como rodar em modo Dev

O Quarkus possui o modo de desenvolvimento com live reload:

```bash
./mvnw quarkus:dev
```

A Dev UI estará disponível em:

👉 **http://localhost:8080/q/dev**

---

## 📦 Build e execução do projeto

### Gerar build normal:

```bash
./mvnw package
```

O build ficará em:

```
target/quarkus-app/
```

### Executar:

```bash
java -jar target/quarkus-app/quarkus-run.jar
```

---

## 🧱 Gerar Uber-Jar (jar completo)

```bash
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

Rodar:

```bash
java -jar target/*-runner.jar
```

---

## ❄ Gerar Native Image

```bash
./mvnw package -Dnative
```

Ou usando container:

```bash
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

Rodar o executável:

```bash
./target/orbiwork-api-1.0.0-SNAPSHOT-runner
```

---

## 📚 Swagger (Documentação da API)

A documentação Swagger/OpenAPI está disponível automaticamente em:

👉 **http://localhost:8080/swagger**

E o JSON do OpenAPI em:

👉 **http://localhost:8080/openapi**

---

## 🏛 Estrutura da Aplicação (Camadas)

```
src/main/java/br/com/orbiwork/
│
├── model/        → Entidades (Usuario, Trilha, Curso)
├── dao/          → Classe DAO (CRUD + JDBC + ConnectionFactory)
├── bo/           → Regras de negócio + validações
├── resource/     → Endpoints REST com JAX-RS
├── config/       → CORS + conexão Oracle
└── exception/    → Handler global de exceções
```

---

## 🧪 Endpoints Principais

### 🧍 Usuários

| Método | Rota            | Descrição                |
|-------|-----------------|--------------------------|
| GET   | /usuarios       | Lista usuários           |
| GET   | /usuarios/{id}  | Busca usuário por ID     |
| POST  | /usuarios       | Cria usuário             |
| PUT   | /usuarios/{id}  | Atualiza usuário         |
| DELETE| /usuarios/{id}  | Remove usuário           |

### 🎓 Trilhas

| Método | Rota            |
|-------|------------------|
| GET   | /trilhas         |
| GET   | /trilhas/{id}    |
| POST  | /trilhas         |
| PUT   | /trilhas/{id}    |
| DELETE| /trilhas/{id}    |

### 📘 Cursos

| Método | Rota            |
|-------|------------------|
| GET   | /cursos          |
| GET   | /cursos/{id}     |
| POST  | /cursos          |
| PUT   | /cursos/{id}     |
| DELETE| /cursos/{id}     |

---

## 🎯 Objetivo do Projeto

Fornecer uma API completa e funcional para integração com o Front-End da disciplina **Front-End Design Engineering**, atendendo os critérios do enunciado:

✔ Camada Model  
✔ DAO + BO + ConnectionFactory  
✔ CRUD completo  
✔ Validações  
✔ Exception Global  
✔ Swagger  
✔ CORS liberado  
✔ Organização por boas práticas  
✔ Pronta para deploy em Render/Railway

---

## 📤 Deploy

O projeto pode ser facilmente publicado no:

- Render
- Railway
- Fly.io
- Docker + VPS
- Azure App Service

---

## 👨‍💻 Autores

- Raphael Gomes Mancera
- Equipe Orbiwork

---

## 📄 Licença

Este projeto é de uso acadêmico.
