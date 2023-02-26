# KEYCLOAK
O KEYCLOCK é o nosso gerenciador de identidade e acesso responsável por controlar o acesso as nossas apis, ele é responsável por gerar tokens que usaremos em uma autenticação OAuth2 em nosso gateway, nele podemos gerar nossos clientes e chaves de acesso para eles. 


**OBS:** Após a instalação do keycloak é necessário importar as configurações do ms-realm para nosso keyclock, para isso basta acessar o painel de admin e criar um novo realm importando o arquivo realm-export.json