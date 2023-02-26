# PROJETO AVALIADOR DE CRÉDITO

Projeto de simulador de avaliação de crédito para emissão de cartões criado em Java utilizando springboot e springcloud seguindo arquitetura de microserviços, para os Objetivos Keyrus 2022.



## TECNOLOGIAS UTILIZADAS
 - Java
 - Maven
 - Spring Boot
 - Spring Cloud
 - Keycloak
 - RabbitMQ
 - H2
 - Docker
 - Git

## MICRO SERVIÇOS
- [eurekaserver](https://github.com/XxJoaoQueirozxX/ms-cartoes/tree/master/eurekaserver)
- [ms-gateway](https://github.com/XxJoaoQueirozxX/ms-cartoes/tree/master/ms-gateway)
- [ms-clientes](https://github.com/XxJoaoQueirozxX/ms-cartoes/tree/master/ms-clientes)
- [ms-cartoes](https://github.com/XxJoaoQueirozxX/ms-cartoes/tree/master/ms-cartoes)
- [ms-avaliadorcredito](https://github.com/XxJoaoQueirozxX/ms-cartoes/tree/master/ms-avaliadorcredito)

## SERVIÇOS EXTERNOS
- [RabbitMQ](https://www.rabbitmq.com/)
- [Keycloak](https://www.keycloak.org/)


## COMO FAZER O BUILD DAS IMAGENS DOCKER
Todos os serviços criados nesse projeto possuem um arquivo Dockerfile para que seja possível construir imagens dessas aplicações e criar os respectivos containers de forma mais fácil, abaixo tem um tutorial de como criar cada uma dessas imagens.

**OBS: Para realizar esse processo é necessário ter o docker instalado em sua máquina, para mais informações de como instala-lo [Clique aqui](https://docs.docker.com/get-started/)**

**1 -** Primeiramente é necessário fazer o clone do repositorio de código para sua máquina local, para fazer isso basta abrir um terminal na pasta que deseja clonar o código e digitar o comando abaixo

    git clone https://github.com/XxJoaoQueirozxX/ms-cartoes.git

**2 -** Com o repositório clonado é preciso entrar na pasta que foi gerada

    cd ms-cartoes/

**3 -** Agora que você já está na pasta raiz do projeto, basta rodar os comandos a seguir para criar as imagens

### EUREKASERVER
    docker build --tag ms-eureka:1.0 eurekaserver\

### MS-CLIENTES
    docker build --tag ms-clientes:1.0 ms-clientes/

### MS-CARTOES
    docker build --tag ms-cartoes:1.0 ms-cartoes/

### MS-AVALIADORCREDITO
    docker build --tag ms-avaliadorcredito:1.0 ms-avaliadorcredito/

### MS-GATEWAY
    docker build --tag ms-gateway:1.0 ms-gateway/

## CRIANDO NOSSOS CONTAINERS
Agora com as imagens criadas vamos utilizar do Docker para criar os containers com nossas aplicações, para isso basta seguir o tutorial abaixo.

### CRIANDO NOSSA NETWORK
Para que nossos serviços consigam se comunicar entre si dentro dos containers precisamos primeiramente criar uma network para isso basta rodar o seguinte comando

    docker network create ms-network

**OBS:** Afim de evitar erros é recomendado que os comandos abaixo sejam executados na ordem com a qual foram colocados

### KEYCLOAK
    docker run --name ms-keycloak -p 8081:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin --network ms-network -d quay.io/keycloak/keycloak:20.0.5 start-dev
**OBS:** Após criar o container do keycloak é necessário fazer a configuração do realm para que o serviço de gateway possa validar a autorização da requisição, para mais detalhes sobre essa configuração [clique aqui](https://github.com/XxJoaoQueirozxX/ms-cartoes/tree/master/keycloak)

### RABBITMQ
    docker run --network ms-network --name msrabbitmq -p 5672:5672 -p 15672:15672 -d rabbitmq:3.11-management
**OBS:** Após a criação do nosso container do RabbitMQ é necessário acessar o admin dele pela url http:localhost:15762 com o usuario e senha guest e então criar uma queue com o nome **cards-issuance** , que será usado por nossos serviços de ms-cartoes e ms-avaliador-credito

### EUREKASERVER
    docker run --name ms-eureka -p 8761:8761 --network ms-network -e EUREKA_USER=ms-eureka-user -e EUREKA_PASSWORD=WL9yum3aQp -d ms-eureka:1.0
### MS-CLIENTES
    docker run --name ms-clientes --network ms-network -e EUREKA_SERVER=ms-eureka -e EUREKA_PORT=8761 -e EUREKA_USER=ms-eureka-user -e EUREKA_PASSWORD=WL9yum3aQp -d ms-clientes:1.0
### MS-CARTOES
    docker run --name ms-cartoes --network ms-network -e EUREKA_SERVER=ms-eureka -e EUREKA_PORT=8761 -e EUREKA_USER=ms-eureka-user -e EUREKA_PASSWORD=WL9yum3aQp -e RABBIT_MQ_SERVER=msrabbitmq -e RABBIT_MQ_PORT=5672 -e RABBIT_MQ_USER=guest -e RABBIT_MQ_PASSWORD=guest -d ms-cartoes:1.0
### MS-AVALIADORCREDITO
    docker run --name ms-avaliadorcredito --network ms-network -e EUREKA_SERVER=ms-eureka -e EUREKA_PORT=8761 -e EUREKA_USER=ms-eureka-user -e EUREKA_PASSWORD=WL9yum3aQp -e RABBIT_MQ_SERVER=msrabbitmq -e RABBIT_MQ_PORT=5672 -e RABBIT_MQ_USER=guest -e RABBIT_MQ_PASSWORD=guest -d ms-avaliadorcredito:1.0
### MS-GATEWAY
    docker run --name ms-gateway --network ms-network -p 8080:8080 -e EUREKA_SERVER=ms-eureka -e EUREKA_PORT=8761 -e EUREKA_USER=ms-eureka-user -e EUREKA_PASSWORD=WL9yum3aQp -e KEYCLOAK_SERVER=ms-keycloak -e KEYCLOAK_PORT=8080 -e KEYCLOACK_REALM=ms-realm  -d ms-gateway:1.0

**OBS:** Os serviços **MS-CLIENTES**, **MS-CARTOES**, **MS-AVALIADOCREDITO** estão configurados para suportar Load Balance, então é possível instanciar mais de um container deles para dividir as requisições para isso basta rodar o comando do que deseja novamente (Lembrando que só pode haver um container por name então antes de rodar é preciso subistiuir o name enviado no parametro --name no comando antes de executalo)