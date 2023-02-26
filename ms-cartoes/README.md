# MS-CARTOES
O MS-CARTOES é o nosso microserviço de cartões responsável por:
- Cadastrar novos modelos de cartões
- Buscar cartões de crédito disponiveis por limite de renda
- Buscar cartões de um cliente especifico
- Consumir as mensagens de solicitação de emissão de cartão em nossa fila do RabbitMQ e realizar a emissão desses cartões