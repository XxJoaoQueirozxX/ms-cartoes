# MS-AVALIADORCREDITO
O MS-AVALIADORCREDITO é o nosso microserviço de avaliação de crédito responsável por:
- Verificar a situação atual do nosso cliente buscando por informações do usuário bem como os seus cartões cadastrados
- Fazer a avaliação de crédito para do usuário com base em uma regra de negócio, e nos devolver os cartões aprovados para esse cliente bem como o limite aprovado.
- Solicitar a emissão de um cartão para o nosso usuário, por meio dessa solicitação é gerado uma mensagem em nossa fila do RabbitMQ que é então consumida por outro serviço para gerar esse cartão
