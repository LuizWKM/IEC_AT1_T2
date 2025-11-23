# Guia de Teste Local

Este documento descreve como testar o projeto localmente.

## Usando Maven Wrapper (Recomendado)

Se você não tem Maven instalado, use o Maven Wrapper:

### Windows (PowerShell)
```powershell
.\mvnw.cmd clean test
.\mvnw.cmd spring-boot:run
.\mvnw.cmd clean package
```

### Linux/Mac
```bash
./mvnw clean test
./mvnw spring-boot:run
./mvnw clean package
```

## Usando Maven (se instalado)

```bash
mvn clean test
mvn spring-boot:run
mvn clean package
```

## Testando as Rotas

### 1. Criar um lutador
```bash
curl -X POST http://localhost:8080/api/fighters \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Bruce Lee",
    "nationality": "Hong Kong",
    "age": 32,
    "martialArtStyle": "Jeet Kune Do",
    "beltRank": "Master",
    "wins": 100,
    "losses": 0,
    "weightClass": "Lightweight"
  }'
```

### 2. Listar todos os lutadores
```bash
curl http://localhost:8080/api/fighters
```

### 3. Buscar lutador por ID
```bash
curl http://localhost:8080/api/fighters/1
```

### 4. Buscar por estilo de luta
```bash
curl http://localhost:8080/api/fighters/style/Jeet%20Kune%20Do
```

### 5. Buscar por nacionalidade
```bash
curl http://localhost:8080/api/fighters/nationality/Brazil
```

### 6. Atualizar lutador
```bash
curl -X PUT http://localhost:8080/api/fighters/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Bruce Lee",
    "nationality": "Hong Kong",
    "age": 33,
    "martialArtStyle": "Jeet Kune Do",
    "beltRank": "Grand Master",
    "wins": 150,
    "losses": 0,
    "weightClass": "Lightweight"
  }'
```

### 7. Deletar lutador
```bash
curl -X DELETE http://localhost:8080/api/fighters/1
```

## Acessar Console H2

Acesse: http://localhost:8080/h2-console

Configurações:
- JDBC URL: `jdbc:h2:mem:martialartsdb`
- Username: `sa`
- Password: _(vazio)_

## Verificar Pipeline CI/CD

Após fazer push para o GitHub, verifique:
1. Acesse: https://github.com/LuizWKM/IEC_AT1_T2/actions
2. Clique no workflow mais recente
3. Verifique se todos os steps passaram
4. Baixe o artefato JAR gerado
