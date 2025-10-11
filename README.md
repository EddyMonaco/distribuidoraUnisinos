
# MEU CARO GILBERT APROVEITE A JORNADA DA DOCUMENTAÇÃO, POR AQUI VOCÊ ENTENDERÁ TODOS OS CONCEITOS NECESSÁRIOS PARA ENTENDER ESSE TRABALHO


![shyliliy](https://github.com/user-attachments/assets/49ce5b81-a11c-4504-83d4-47daa01101b0)


# Sistema de Controle de Estoque

Este projeto é um sistema de backend para controle de estoque, a idéia era conseguir gerar o controle de um Galpão Logistico onde se dão entrada e saída de produtos, a aplicação foi desenvolvido com Java e Spring Boot. A API RESTful permite gerenciar produtos, usuários (internos e externos), áreas de armazenamento e o histórico completo de movimentações de estoque, como entradas, saídas e transferências.É interessante ressaltar que a parte de segurança não foi aplicada nesse trabalho.

<img width="662" height="654" alt="image" src="https://github.com/user-attachments/assets/0f75a0e9-b616-4a68-bd72-e94d6d3f21ee" />


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
 - Atualização de movimentação.
   
# 🛠️ Tecnologias Utilizadas
Backend:
 - Java 21
 - Spring Boot 3+
 - Spring Web: Para a criação da API REST.
 - Spring Data JPA: Para a persistência de dados.
 - Hibernate: Implementação da JPA.
 - WildFly27+
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
   
<img width="636" height="1850" alt="Diagrama de arquitetura-2025-10-10-200157" src="https://github.com/user-attachments/assets/16508ce5-8122-4dc4-ade3-d9fa52035280" />

#  Diagrama de classe

<img width="1771" height="2832" alt="image" src="https://github.com/user-attachments/assets/b6ce2ad8-0ea9-4d93-a2ef-198bebeb4907" />



# 🚀 Como Executar o Projeto
Pré-requisitos:
 - JDK 21 ou superior.
 - Maven 3.8 ou superior.
 - PostgreSQL instalado e em execução. (JDBC instalado e importado dentro do projeto)
 - Jboss tools para gerenciamento do wildFly27+
 - Pacote do wildFly27+
 - Jdbc do postGree instalado no path build do projeto

 - 1. <b>Clone o Repositório</b>

  -- git clone https://github.com/seu-usuario/seu-repositorio.git

 - 2. <b>Configure o Banco de Dados</b>

    Crie um banco de dados no PostgreSQL para o projeto.
    Abra o arquivo src/main/resources/application.properties.
    Configure as propriedades de conexão com o seu banco de dados:
    properties
    
    # Configuração do PostgreSQL
    app.version=@project.version@
    spring.application.name=unisinos
    spring.datasource.url=jdbc\:postgresql\://localhost\:5432/nome-do-banco
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha
    spring.datasource.driver-class-name=org.postgresql.Driver
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    
    # Configuração do WildFly
     - Suba a janela servidores em uma nova janela windown no eclipse.
    <img width="1139" height="583" alt="image" src="https://github.com/user-attachments/assets/97aea9ef-d30e-44e7-a41e-90b04681a3ce" />
    
    <img width="439" height="636" alt="image" src="https://github.com/user-attachments/assets/7c48c31a-2b65-4225-99fd-2fae2a85040c" />
    
    Aqui você vai especificar o server que está dentro do pacote do jboss. Importante ressaltar que o pacote baixado do WildFly27+ precisa ser referenciado. Olhar no youtube caso se perder aqui.
    
     - Nesse ponto não é essencial mas está no projeto um pacote dentro de src->main->webapp->WEB-INF->jboss-deployment-structure.xml que ajuda o spring a reconhecer o wildfly.

3. Execute a Aplicação
A aplicação estará disponível em http://localhost:8080.
- Nesse momento é recomendado:
  - Dar um project->run as -> maven_clean.
  - Dar um project->run as -> maven_install.
  - Project Maven Update.
  - Começar o server com o botão direito e modo debug.
    
5. Popule o Banco com Dados de Teste (Opcional )
Para facilitar os testes, você pode executar o script SQL de massa de dados.
Execute-o diretamente no seu banco de dados estoque_db usando uma ferramenta como DBeaver ou pgAdmin.

📄 Documentação da API (Swagger)
Com a aplicação em execução, você pode acessar a documentação interativa da API via Swagger UI no seguinte endereço:
URL da Documentação: [http://localhost:8080/swagger-ui.html](http://localhost:8080/distribuicaounisinos/swagger-ui/index.html)
Lá, você poderá ver todos os endpoints disponíveis, seus parâmetros, e até mesmo testá-los diretamente pelo navegador.


![11](https://github.com/user-attachments/assets/1fda9634-f553-4ede-abe6-3e88d9c201e2)

