# Contact API — Spring Boot + Brevo

API REST para envio de mensagens de contato via email, construída com Spring Boot e integrada com a API do Brevo (Sendinblue).
O projeto foi desenvolvido com foco em boas práticas, segurança e deploy em ambiente real.

---

## Visao Geral

Esta API permite que um frontend envie mensagens de contato, que são processadas e encaminhadas por email utilizando um serviço externo (Brevo).

Principais características:

- Separação clara de responsabilidades (Controller → Service → Config)
- Validação de dados na entrada
- Uso de variáveis de ambiente para segurança
- Integração com API externa via RestTemplate
- Deploy containerizado com Docker

---

## Arquitetura

```
Client (Frontend)
       ↓
ContactController
       ↓
EmailService
       ↓
Brevo API (HTTP REST)
```

---

## Tecnologias Utilizadas

- Java 17+
- Spring Boot 4.x
- Spring Web
- Jakarta Validation
- RestTemplate
- Docker
- Render (deploy)
- UptimeRobot (keep-alive)

---

## Estrutura do Projeto

```
src/
 ├── controller/
 │    └── ContactController.java
 ├── service/
 │    └── EmailService.java
 ├── dto/
 │    └── EmailDTO.java
 ├── config/
 │    └── AppConfig.java
```

---

## Endpoint Principal

### POST `/contact`

Envia uma mensagem de contato.

**Request Body**

```json
{
  "email": "user@example.com",
  "message": "Hello, this is a test message"
}
```

**Validações**

- `email`: obrigatório e formato válido
- `message`: obrigatório

**Response — Sucesso**

```json
{
  "status": "Email sent successfully"
}
```

**Response — Erro (exemplo)**

```json
{
  "error": "Invalid email format"
}
```

---

## Health Check

### GET `/health`

Verifica se a API está online.

```json
{
  "status": "API is running"
}
```

---

## Configuração

### Variáveis de Ambiente

```env
BREVO_API_KEY=your_api_key
BREVO_URL=https://api.brevo.com/v3/smtp/email
SENDER_EMAIL=your_verified_email
```

> **Importante:** nunca versionar essas informações no código.

---

## Integração com Brevo

O envio de email é feito via requisição HTTP utilizando `RestTemplate`.

**Fluxo:**

1. Recebe requisição no Controller
2. Valida dados com Jakarta Validation
3. Service monta payload JSON
4. Envia POST para API do Brevo
5. Retorna resposta ao cliente

---

## CORS

Configurado para permitir requisições do frontend:

```java
@CrossOrigin(origins = "https://seu-dominio.com")
```

---

## Deploy

### Docker

A aplicação foi containerizada utilizando Docker.

### Render

- Deploy automático via GitHub
- Build utilizando Dockerfile
- Variáveis de ambiente configuradas no painel

---

## Uptime

Para evitar que o serviço entre em modo sleep, o UptimeRobot realiza ping a cada 5 minutos no endpoint `/health`.

---

## Integração com Frontend

Exemplo de requisição utilizando `fetch`:

```javascript
fetch("https://sua-api.onrender.com/contact", {
  method: "POST",
  headers: {
    "Content-Type": "application/json"
  },
  body: JSON.stringify({
    email: "user@example.com",
    message: "Hello!"
  })
});
```

---

## Boas Práticas Aplicadas

- Separação de camadas (Controller, Service, Config)
- Validação de entrada com mensagens claras
- Uso de DTO para transporte de dados
- Externalização de configurações sensíveis
- API stateless

---

