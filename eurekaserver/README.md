# EUREKASERVER

O eurekaserver é o nosso Discovery Server, um serviço responsável por registrar as nossas instÂncias de outros
microserviços para que os nossos microserviços consigam se comunicar entre si sem precisar saber onde cada um deles está
hospedado diretamente, quando um microserviço é instanciado ele comunica-se com o nosso register service informando o seu
tipo, endereço, nome e seu status(ativo), por meio desse registro podemos saber por que url(endereço) acessar as nossas
instâncias, seus status (UP/DOWN) e ver quantas instâncias temos para cada um desses serviços

O Eureka também nos fornece uma painel por onde podemos verificar essas informações, para isso com o nosso serviço
eurekaserver de pé basta acessarmos a url http://localhost:8761/ e informar as credencias de acesso a essa url, o nosso
serviço de discovery conta com uma proteção para impedir que qualquer serviço se registre nele sem as devidas
permissões, essas credências de acesso são informadas no nosso application.yml e na nossa arquitetura usando docker são
pegas de variaveis de ambiente passadas no momento da criação do container, caso tenha seguido os comandos presentes no
README.md do projeto para criar os seus containers o seu usuario e senhas devem ser esses

![Painel eureka](https://media.discordapp.net/attachments/867089237973925909/1079520412079247400/image.png?width=1710&height=905)


| Propriedade | Valor                  |
|-------------|------------------------|
| Painel URL  | http://localhost:8761/ |
| User        | ms-eureka-user         |
| Password    | WL9yum3aQp             |

[Reference Documentation](https://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-eureka-server.html)