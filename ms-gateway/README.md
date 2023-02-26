# MS-GATEWAY

O MS-GATEWAY é o nosso gateway é por meio dele que faremos o acesso aos nossos serviços, ele é responsável por receber as nossas requisições e encaminhar para os devidos serviços, ele também nos permite fazer o load balance que nada mais é do que dividir as nossas requisições entre as várias instâncias de um mesmo serviço minimizando a sobrecarga das nossas aplicações, esse também é o serviço que será exposto para o nosso usuário final.

Nesse serviço fizemos a configuração das nossas urls de redirencionamento, as configuraOes para conectar com o nosso serviço do eurekaservice para buscar os serviços registrados e também as configurações para validar nossa autenticação OAuth2 no nosso gerenciador keycloack.

| Propriedade | Valor                  |
|------|------------------------|
| URL  | http://localhost:8080/ |


[Reference Documentation](https://spring.io/projects/spring-cloud-gateway)