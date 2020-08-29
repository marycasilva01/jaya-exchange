# jaya-exchange

- O projeto encontrasse no Heroku no endereço: https://convertes.herokuapp.com/
- A documentação dos end points via Swagger estão no endereço: https://convertes.herokuapp.com/swagger-ui.html#/

# Projeto

O projeto executa conversão de valores entre duas moedas.

# Stack
 - Java 11
 - SpringBoot
 - Lombok
 - Banco de dados H2
 - Junit 
 - Jococo
 
 # Camadas
  - Controller - Camada onde é definida as rotas dos end points da aplicação.
  - Service    - Camada de serviço da aplicação.
  - Repository - Camada de acesso ao banco de dados da aplicação.


# Iniciando
Para executar o projeto, será necessário instalar os seguintes programas:

- JDK 11: Necessário para executar o projeto Java
- Maven: Necessário para realizar o build do projeto Java
- IDE (Ex: Eclipse): Para desenvolvimento do projeto

# Build

Para construir o projeto com o Maven, executar os comando abaixo:

```shell
mvn clean install
```

O comando irá baixar todas as dependências do projeto e criar um diretório *target* com os artefatos construídos, que incluem o arquivo jar do projeto. Além disso, serão executados os testes unitários, e se algum falhar, o Maven exibirá essa informação no console.

