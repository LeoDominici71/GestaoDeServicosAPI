PROJETO SISTEMA DE GESTÃO DE FUNCIONÁRIO


 - Descrição 

A aplicação apresentada é um sistema de gerenciamento de funcionários.

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

 - Detalhes estruturais

Dentro da pasta "management" há a aplicação criada diretamente pelo Spring Boot. Dentro da pasta "applicationInTxt" há a mesma aplicação separada em pastas, porém em bloco de notas. Dentro da pasta "visualizaçãoDeApi" há um arquivo "GestaoFuncionario.postman_collection.json" onde se encontra a collection do Postman para testes. Há também os arquivos "Swagger UI" e "OpenAPI" em txt para visualizar e testar as funcionalidades criadas. Caso prefira, execute a aplicação e acesse http://localhost:8080/swagger-ui.html#, assim você conseguirá acessar o Swagger UI.

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

 - Funcionalidades

As funcionalidades principais incluem:

Listar todos os funcionários.
Buscar um funcionário pelo seu ID.
Deletar um funcionário.
Criar um novo funcionário.
Atualizar um funcionário existente.

A aplicação também lida com exceções, como quando um funcionário não é encontrado ou quando os dados fornecidos não estão de acordo com as regras de validação.

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

- Arquitetura

o projeto está estruturado com o padrão MVC para API REST em Spring Boot. Eu fiz a divisão do projeto em camadas: model, controller, repository e service. Controller sendo a camada de apresentação, o service sendo a camada de lógica de negócios e o repository sendo a camada de persistência.

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

- Configurações do Projeto

Banco de dados : Ele utiliza uma implementação de Map da interface EmployeeDatabase, que simula um banco de dados em memória. 

EmployeeDatabaseImpl : implementa a interface EmployeeDatabase e usa um HashMap para armazenar os funcionários (objetos da classe Employee) e seus IDs.

Service : A classe EmployeeServiceImpl é responsável por implementar a lógica de negócios do projeto e utiliza a implementação de EmployeeDatabase para realizar as operações de CRUD (Create, Read, Update e Delete) nos funcionários.

Controller: A classe EmployeeController é o controlador que lida com as solicitações HTTP enviadas ao servidor.

Model : Ela é composta pelas entidades, como as classes Employee e EmployeeDTO, onde é possível ver a definição dos atributos do objeto Employee, as anotações de validação dos atributos e os métodos que permitem acessar e alterar esses atributos. Além disso, a camada model também contem as classes que representam as mensagens de erro da aplicação, como as classes FieldMessage, StandardError e ValidationError. Essas classes são utilizadas pela camada de controle para retornar respostas HTTP com informações de erro para o cliente da aplicação.

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

- Tratamento de erros e validaçoes

EmployeeNotFoundException : lançada quando não é possível encontrar um funcionário no banco de dados com o ID fornecido. 

DuplicatedEmployeeException : lançada quando a aplicação tenta salvar um funcionário que já existe no banco de dados.

MethodArgumentNotValidException : lançada quando os dados enviados na requisição não atendem aos requisitos definidos nas anotações de validação.

A classe ResourceExceptionHandler é responsável por tratar essas exceções e retornar uma resposta HTTP com um objeto JSON contendo informações sobre o erro de acordo com a classe StandardError que contém os campos comuns a todos os erros, há tambem a classe ValidationError é uma extensão de StandardError e adiciona uma lista de erros de validação para erros do tipo MethodArgumentNotValidException.

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

- Testes

Foram realizados testes na camada service e controller da aplicação utilizando o framework JUnit e a especificação Spring Test. A aplicação possui uma cobertura de testes de 92.4%.

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
- Build

O build do projeto é realizado com o Maven através do arquivo pom.xml.

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

- Swagger UI

Foi realizada a configuração do Swagger UI na classe SwaggerConfig. Executando a aplicação e acessando http://localhost:8080/swagger-ui.html#/ é possível visualizar a API e realizar testes.

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
- Postman

A coleção no Postman foi disponibilizada e exportada para fins de testes.

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

- OpenAPI 

O arquivo OpenAPI também foi disponibilizado para acesso.


