# Projeto Final Estrelas

Projeto final do estrelas - Ajuda Zupper

Aplicação que simula uma API de consulta de benefícios, permitindo o cadastro de usuários e a vinculação destes ao seu nivel zupper, com isso visualizar seus benefícios de acordo com seu nivel zupper.

## Regras de Negócio

- Não deve ser permitido vincular um usuario não cadastrado a empresa;
- O usuário só pode ter um cadastro por e-mail;
- O usuário só pode se cadastrar usando email com dominio "zup.com.br";
- O usuário precisa ter uma senha forte;
- O usuário somente pode trocar a sua senha;
- Depois de cadastrado como funcionário o usuário pode ver seus benefícios;
- O usuário cadastrar uma atividade física;
- O usuário ver uma atividade física;
- O usuário atualizar uma atividade física;
- O usuário deletar uma atividade física;
- Apenas usuário ADMIN pode excluir qualquer usário;
- Apenas usuário ADMIN pode cadastrar um benefício;
- Apenas usuário ADMIN pode atualizar um benefício;
- Apenas usuário ADMIN pode deletar um benefício;
- Apenas usuário ADMIN pode ver um benefício;
- Apenas usuário ADMIN pode cadastrar um funcionário;
- Apenas usuário ADMIN pode ver todos os funcionários;
- Apenas usuário ADMIN pode deletar um funcionário;
- Apenas usuário ADMIN pode atualizar um funcionário;


## Como Rodar a API localmente

> Pré-requisitos:

- JAVA JDK
- Maven
- MariaDB/MySQL

[Link para instalar o Maven](https://maven.apache.org/download.cgi)

Após instalar as dependencias através do terminal na pasta do projeto voce deve agora ser capaz de iniciar a aplicaçao na IDE.

Será possível testar a aplicaçao em: localhost:8080/

## Tecnologias utilizadas

- JAVA 11
- SpringBoot
- SpringSecurity
- JPA
- Hibernate
- Swagger
- Maven
- JWT

## SERVIÇOS

- Acessar via Swagger, localmente aqui : http://localhost:8080/swagger-ui/index.html#/
- Postman collection: [AjudaZupper.postman_collection.zip](https://github.com/larinovaes/Projeto-Final-Estrelas-Fora-Da-Caixa-Grupo02/blob/747494f3003531d1c99e545569feb9829470f50d/AjudaZupper.postman_collection.zip)

