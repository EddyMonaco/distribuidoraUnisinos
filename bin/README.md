# Sistema de Controle de Estoque

Este projeto é um sistema de backend para controle de estoque, desenvolvido com Java e Spring Boot. A API RESTful permite gerenciar produtos, usuários (internos e externos), áreas de armazenamento e o histórico completo de movimentações de estoque, como entradas, saídas e transferências.

# ✨ Funcionalidades Principais
Gestão de Produtos:
- Cadastro, consulta, atualização e exclusão de produtos.
  
Controle de Usuários:
 - Usuários Internos: Funcionários da distribuidora, como Supervisores e Vendedores.
 - Usuários Externos: Parceiros de negócio, como Clientes e Fornecedores.
   
Movimentação de Estoque:
 - Registro de entradas de produtos no estoque.
 - Registro de saídas (vendas, perdas, etc.).
 - Registro de transferências de produtos entre diferentes áreas do armazém.
 - Rastreabilidade: Histórico completo de todas as movimentações por produto ou por período.
 - Gestão de Armazém: Cadastro de diferentes áreas de estoque.
   
# 🛠️ Tecnologias Utilizadas
Backend:
 - Java 21
 - Spring Boot 3+
 - Spring Web: Para a criação da API REST.
 - Spring Data JPA: Para a persistência de dados.
 - WildFly27+: Implementação da JPA.
Banco de Dados:
 - PostgreSQL: Banco de dados relacional.
Documentação da API:
 - SpringDoc OpenAPI (Swagger): Para documentação interativa dos endpoints.
   
# 🏛️ Arquitetura
O projeto segue uma arquitetura em camadas (Layered Architecture) para garantir a separação de responsabilidades e facilitar a manutenção:
 - Controller: Camada responsável por expor os endpoints da API REST, receber as requisições HTTP e retornar as respostas.
 - Service: Contém a lógica de negócio principal da aplicação. Orquestra as operações e transações.
 - Repository: Camada de acesso a dados, que utiliza o Spring Data JPA para interagir com o banco de dados.
 - Model (Entities): Representa as entidades do banco de dados através de classes Java anotadas com JPA.
 - DTO (Data Transfer Object): Objetos que carregam dados entre as camadas, especialmente entre o Controller e o cliente, garantindo que o modelo de domínio não seja exposto diretamente.
   
   <img width="636" height="1850" alt="Diagrama de arquitetura-2025-10-09-233505" src="https://github.com/user-attachments/assets/5d27f3b9-250e-4771-9bdd-43c7bdaf6087" />

[Link da arquittura]([url](https://www.mermaidchart.com/app/projects/3aed8884-4002-479b-952b-e55e7f245475/diagrams/8f5079ac-ffc7-45ed-9abd-f291c5b37821/share/invite/eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkb2N1bWVudElEIjoiOGY1MDc5YWMtZmZjNy00NWVkLTlhYmQtZjI5MWM1YjM3ODIxIiwiYWNjZXNzIjoiVmlldyIsImlhdCI6MTc2MDA1MjkxNH0.dBhGwqapTv2cemFvs65JasH-dNfna4YhJJPZPkhvsBk))

# 🚀 Como Executar o Projeto
Pré-requisitos
JDK 21 ou superior.
Maven 3.8 ou superior.
PostgreSQL instalado e em execução. (JDBC instalado e importado dentro do projeto)

1. Clone o Repositório
Bash
git clone https://github.com/seu-usuario/seu-repositorio.git
cd seu-repositorio
2. Configure o Banco de Dados
Crie um banco de dados no PostgreSQL para o projeto.
SQL
CREATE DATABASE estoque_db;
Abra o arquivo src/main/resources/application.properties.
Configure as propriedades de conexão com o seu banco de dados:
properties

# Configuração do PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/estoque_db
spring.datasource.username=seu_usuario_postgres
spring.datasource.password=sua_senha_postgres

# Configuração do WildFly
Precisa baixar o arquivo
Precisa instalar o Jboss
Não é necessario mas ajuda criar um web-info para especificar algumas configurações de servidor 

3. Execute a Aplicação
A aplicação estará disponível em http://localhost:8080.

4. Popule o Banco com Dados de Teste (Opcional )
Para facilitar os testes, você pode executar o script SQL de massa de dados.
Execute-o diretamente no seu banco de dados estoque_db usando uma ferramenta como DBeaver ou pgAdmin.

📄 Documentação da API (Swagger)
Com a aplicação em execução, você pode acessar a documentação interativa da API via Swagger UI no seguinte endereço:
URL da Documentação: http://localhost:8080/swagger-ui.html
Lá, você poderá ver todos os endpoints disponíveis, seus parâmetros, e até mesmo testá-los diretamente pelo navegador.
