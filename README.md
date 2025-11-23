# Martial Arts API - Sistema de Gerenciamento de Lutadores 🥋

[![CI/CD Pipeline](https://github.com/LuizWKM/IEC_AT1_T2/actions/workflows/ci-cd.yml/badge.svg)](https://github.com/LuizWKM/IEC_AT1_T2/actions/workflows/ci-cd.yml)
[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

## 📋 Descrição

API REST desenvolvida com Spring Boot para gerenciamento de lutadores e informações sobre artes marciais. O projeto implementa integração contínua e entrega contínua (CI/CD) através do GitHub Actions.

### ✨ Funcionalidades

- ✅ Cadastro, consulta, atualização e exclusão de lutadores
- ✅ Busca por estilo de luta marcial
- ✅ Busca por nacionalidade
- ✅ Validação de dados
- ✅ Banco de dados H2 em memória
- ✅ Testes unitários completos
- ✅ Pipeline CI/CD automatizado

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Data JPA**
- **H2 Database**
- **Maven**
- **JUnit 5**
- **Mockito**
- **Lombok**
- **GitHub Actions**

## 📡 Endpoints da API

### Lutadores (Fighters)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/fighters` | Lista todos os lutadores |
| GET | `/api/fighters/{id}` | Busca lutador por ID |
| POST | `/api/fighters` | Cria novo lutador |
| PUT | `/api/fighters/{id}` | Atualiza lutador existente |
| DELETE | `/api/fighters/{id}` | Deleta um lutador |
| GET | `/api/fighters/style/{style}` | Busca lutadores por estilo de luta |
| GET | `/api/fighters/nationality/{nationality}` | Busca lutadores por nacionalidade |

### Exemplo de JSON (Fighter)

```json
{
  "name": "Bruce Lee",
  "nationality": "Hong Kong",
  "age": 32,
  "martialArtStyle": "Jeet Kune Do",
  "beltRank": "Master",
  "wins": 100,
  "losses": 0,
  "weightClass": "Lightweight"
}
```

## 🛠️ Como Executar o Projeto

### Pré-requisitos

- Java 17 ou superior
- Maven 3.6 ou superior

### Passos

1. **Clone o repositório**
```bash
git clone https://github.com/LuizWKM/IEC_AT1_T2.git
cd IEC_AT1_T2
```

2. **Compile o projeto**
```bash
mvn clean install
```

3. **Execute a aplicação**
```bash
mvn spring-boot:run
```

4. **Acesse a aplicação**
- API: http://localhost:8080/api/fighters
- Console H2: http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:martialartsdb`
  - Username: `sa`
  - Password: _(deixe em branco)_

## 🧪 Executar Testes

```bash
mvn test
```

## 📦 Gerar Artefato JAR

```bash
mvn clean package
```

O arquivo JAR será gerado em: `target/martial-arts-api-1.0.0.jar`

Para executar o JAR:
```bash
java -jar target/martial-arts-api-1.0.0.jar
```

## 🔄 CI/CD Pipeline

O projeto utiliza GitHub Actions para automação de CI/CD. O workflow é acionado automaticamente em:

- Push para branches `main` ou `develop`
- Pull requests para branch `main`

### Etapas do Pipeline

1. ✅ Checkout do código
2. ✅ Configuração do JDK 17
3. ✅ Execução dos testes
4. ✅ Build do projeto
5. ✅ Upload do artefato JAR

O artefato gerado fica disponível para download por 30 dias na aba **Actions** do GitHub.

## 📝 Estrutura do Projeto

```
IEC_AT1_T2/
├── .github/
│   └── workflows/
│       └── ci-cd.yml          # Configuração GitHub Actions
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/iec/martialarts/
│   │   │       ├── controller/
│   │   │       │   └── FighterController.java
│   │   │       ├── model/
│   │   │       │   └── Fighter.java
│   │   │       ├── repository/
│   │   │       │   └── FighterRepository.java
│   │   │       ├── service/
│   │   │       │   └── FighterService.java
│   │   │       └── MartialArtsApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── com/iec/martialarts/
│               ├── controller/
│               │   └── FighterControllerTest.java
│               ├── service/
│               │   └── FighterServiceTest.java
│               └── MartialArtsApplicationTests.java
├── pom.xml
└── README.md
```

## 🧑‍💻 Autor

**Luiz Ricardo**

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 🎯 Requisitos Atendidos

- [x] Projeto Spring Boot criado
- [x] Mínimo de 3 rotas implementadas (7 rotas criadas)
- [x] Testes unitários para todas as rotas
- [x] Workflow CI/CD no GitHub Actions
- [x] Pipeline sem uso de Docker (apenas actions)
- [x] Geração de artefato .jar no processo de CD
Criar projeto Spring Boot com CI/CD