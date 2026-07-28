# 🧪 Automation Repassa - Testes E2E com Playwright & Java

![Java](https://img.shields.io/badge/Java-21%2B-orange?style=for-the-badge&logo=openjdk)
![Playwright](https://img.shields.io/badge/Playwright-1.61.0-green?style=for-the-badge&logo=playwright)
![JUnit5](https://img.shields.io/badge/JUnit-5-red?style=for-the-badge&logo=junit5)
![Maven](https://img.shields.io/badge/Maven-3.X-blue?style=for-the-badge&logo=apachemaven)
![GitHub Actions](https://img.shields.io/badge/GitHub_Actions-CI%2FCD-blueviolet?style=for-the-badge&logo=githubactions)

Projeto de automação de testes End-to-End (E2E) focado na validação de fluxos críticos de autenticação e comportamentos anti-bot/reCAPTCHA do e-commerce **Repassa**.

Construído utilizando **Java 21**, **Playwright**, **JUnit 5** e seguindo as melhores práticas do padrão **Page Object Model (POM)**.

---

## 📌 Principais Destaques do Projeto

* **Arquitetura Page Object Model (POM):** Separação clara entre a lógica de elementos da tela e os cenários de testes, garantindo manutenibilidade e reutilização do código.
* **Validação de Anti-bot e reCAPTCHA:** Monitoramento de respostas assíncronas do reCAPTCHA e tratamento de fluxos de login com desafios de segurança.
* **Execução Híbrida (Local vs. CI):** Configuração inteligente via variáveis de ambiente (`CI=true`) para alterar a execução do navegador (Headed para desenvolvimento local e Headless para pipelines).
* **Pipeline de CI/CD Automatizada:** Integração contínua via GitHub Actions disparada a cada `push` ou `pull request` no repositório.
* **Evidências de Teste:** Captura automatizada de screenshots inteiras e por elementos específicos em falhas ou etapas de validação.

---

## 🛠️ Tecnologias e Ferramentas

- **Linguagem:** Java 21
- **Framework de Automação:** Playwright Java (v1.61.0)
- **Framework de Testes:** JUnit 5
- **Gerenciador de Dependências:** Apache Maven
- **Gerenciamento de Variáveis:** Dotenv Java
- **Integração Contínua:** GitHub Actions

---

## 🏗️ Estrutura do Projeto

```text
AutomationRepassa/
├── .github/
│   └── workflows/          # Configuração do GitHub Actions (CI/CD)
├── src/
│   ├── main/java/
│   └── test/java/
│       ├── pages/          # Classes do Page Object Model (BasePage, LoginPage, PerfilPage)
│       ├── tests/          # Suítes de testes automatizados (LoginTest)
│       └── utils/          # Utilitários (Gerenciamento de Screenshots)
├── screenShot/             # Evidências geradas durante os testes
├── .env.example            # Modelo de configuração das variáveis de ambiente
├── pom.xml                 # Gerenciamento de dependências Maven
└── README.md

⚙️ Configuração do Ambiente Local
Pré-requisitos
Java JDK 21 instalado

Apache Maven instalado

Git instalado

1. Clonar o repositório
git clone [https://github.com/HKNT/playwright-automation-repassa-brecho.git](https://github.com/HKNT/playwright-automation-repassa-brecho.git)
cd playwright-automation-repassa-brecho

2. Configurar Variáveis de Ambiente
Crie um arquivo .env na raiz do projeto baseado nas suas credenciais:
APP_URL=[https://www.repassa.com.br](https://www.repassa.com.br)
USER_LOGIN=seu_email@exemplo.com
USER_PASSWORD=sua_senha

3. Instalar Dependências e Browsers do Playwright
mvn clean install -DskipTests
mvn exec:java -e -Dexec.classpathScope=test -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install --with-deps"

🚀 Executando os Testes
Para rodar a suíte completa de testes via terminal:
mvn test
Os resultados dos testes e relatórios do Surefire serão gerados na pasta target/surefire-reports.

🔄 Pipeline de CI/CD (GitHub Actions)
A pipeline automatizada realiza os seguintes passos a cada atualização do repositório:

1.Checkout: Baixa o código fonte.

2.Setup JDK: Configura o ambiente Java 21 (Temurin).

3.Build: Baixa dependências e compila o projeto.

4.Playwright Install: Instala os binários dos navegadores nativos para Linux.

5.Test Execution: Executa os testes usando GitHub Secrets para gerenciamento seguro de credenciais.

6.Artifacts Upload: Disponibiliza relatórios e screenshots como artefatos baixáveis após a execução.

👤 Autor
Desenvolvido por Hugo

Projeto criado como portfólio para demonstrar práticas modernas de QA Engineering e automação E2E.
