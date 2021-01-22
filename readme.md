Instruções de como executar os testes

Pré-condições:
- Possuir o Docker instalado
- Possuir o JDK 8 e Maven instalados e configurados.

- Necessário possuir o ServeRest rodando localmente.
 * Para rodar o ServeRest utilizando o docker é necessário executar o comando abaixo para criação do container do Serverest:
 docker run -p 3000:3000 --name serverest paulogoncalvesbh/serverest:latest
- Baixar o projeto "restassured-test" e na raiz do projeto, onde está localizado o arquivo pom.xml executar o comando:
mvn clean test

OBS : Para execução em ambientes Linux pode ser necessário dar permissão de escrita no diretório do projeto.
ex: sudo chmod 755 -Rfv cucumber-project/