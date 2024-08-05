# Totem Express

---

Este é o nosso projeto para a pós graduação em **Software Architecture da FIAP**.
O "Totem Express" tem como objetivo ser um sistema de gestão interna de restaurante, sendo o ponto de conexão entre os clientes e a cozinha.

#### Tecnologias

![java](https://img.shields.io/badge/Java_22-000?style=for-the-badge&logo=oracle&logoColor=white)
![spring](https://img.shields.io/badge/Spring_3-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![[mysql]](https://img.shields.io/badge/Mysql_8.4-316192?style=for-the-badge&logo=mysql&logoColor=white)
![docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)

Para desenvolver o projeto utilizamos as seguintes técnologias:

- **Java 22** como linguagem de programação backend.
- **Spring Boot 3** como framework web.
- **MySQL 8.4** como banco de dados relacional
- **Docker** como gerenciador de containers.

### Como utilizar?

Para utilizar o "**Totem Express**" siga os seguintes comandos:

1) Faça o download do repositório 

```shell
git clone https://github.com/geggr/software-architecture-fiap.git
```

2) Entre no projeto

```shell
cd software-architecture-fiap/
```

3) Com o [`Docker`](https://docs.docker.com/desktop/) instalado em sua máquina execute o seguinte comando

```shell
docker compose up
```

4) Após isso [basta apenas acessar a rota principal para ter acesso a documentação](http://localhost:8080)

### Rotas da Aplicação 

| Endpoint                  | Método HTTP | Parâmetros                 | Descrição                     |
|---------------------------|-------------|----------------------------|-------------------------------|
| `/api/users/`             | `POST`      |                            | Cria um usuário comum no sistema         
| `/api/users?document=?`   | `GET`       | Documento (CPF) do usuário | Retorna o usuário cadastrado com aquele CPF 
| `/api/product/`           | `POST`      |                            | Cria um produto no sistema               
| `/api/product/`           | `PUT`       |                            | Atualiza um produto no sistema
| `/api/product/`           | `DELETE`    |                            | Remove um produto do sistema            
| `/api/product/{category}` | `GET`       | Categoria                  | Lista produtos de determinada categoria no sistema
| `/api/order/create`       | `POST`      |                            | Cria um pedido no sistema
| `/api/order/list`         | `GET`       |                            | Lista pedidos cadastrados no sistema


