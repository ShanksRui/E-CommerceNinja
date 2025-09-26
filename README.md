Olá, me chamo Luiz!
	
 **Este é um projeto pessoal inspirado no Curso do Nelio,adptado com minhas ideias e tecnologias adicionais que não foram abordadas no curso,como o docker e Render por exemplo, que estou aprendendo por conta própria.**

créditos:https://www.udemy.com/course/java-curso-completo/

**E-CommerceNinja
API REST de e-commerce com temática “ninja”, construída em Spring Boot (Java 17) e preparada para rodar tanto localmente com (DB:H2 para testes) quanto em produção via Render (com Docker + PostgreSQL).** **


**Tecnologias**

Java 17 / JDK 17 (build)  
Spring Boot (Web, Data JPA)  
H2 (testes locais)  
PostgreSQL (produção no Render)  
Docker + multi-stage build (Maven + Temurin JDK)  
Maven  
Postman(para testes dos endPoints  

**Perfis**

test → H2 em memória (seed via TestConfig).
prod → configurado para PostgreSQL (Render).

```
src
  ├─ main
  │   └─ java
  │       └─ com.diciplina.Test_Diciplina
  │           ├─ Config        # TestConfig (seed)
  │           ├─ Entities      # Ninja, Village, Store, Product, Order, ItemOrder, enums
  │           ├─ Repository    # JpaRepository interfaces
  │           ├─ Services      # lógica + exceções customizadas
  │           └─ Resources     # Controllers REST + ExceptionHandler
  └─ resources
      ├─ application.properties
      ├─ application-test.properties
      └─ application-prod.properties
 ```
 
**Endpoints principais**
Exemplo com Ninja  
GET /ninjas → lista todos  
GET /ninjas/{id} → busca por id  
POST /ninjas → cria  
PUT /ninjas/{id} → atualiza  
DELETE /ninjas/{id} → remove  

# Tratamento de Exceções
Apliquei um tratamento de Exceções de forma limpa,organizada(dependendo do tipo se era de nivel resource ou repository) e aplicando boas práticas como codigo HTPP correspondente. 
StandardError(representar o corpo do erro gerado pelo endpoint)  
ExceptionHandler(interceptar as exceções geradas pelos controllers e chamar o metodo correspondente para aplicar o tratamento)  

# Boas práticas aplicadas
Docker para deploy via Render com DockerFile aplicando o build do codigo e build da imagem com multi-stage build.  
Estrutura em camadas: Controller → Service → Repository.  
Tratamento centralizado de exceções com @ControllerAdvice.  
Seed para facilitar testes locais.  
Uso de enum (OrderStatus) para status dos pedidos.  
Uso de @EmbeddedId para modelar relação muitos-para-muitos com atributos (ItemOrderPK).  
