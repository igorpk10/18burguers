# Tech Challenge Fiap - Fase 1

- [Tech Challenge Fiap - Fase 1](#tech-challenge-fiap---fase-1)
    - [Como rodar?](#como-rodar)
    - [Arquitetura](#arquitetura)
    - [Estrutura de pastas](#estrutura-de-pastas)

## Como rodar?

Para rodar, basta ter o Docker instalado em sua máquina e rodar o seguinte comando:

```shell
docker compose up -d
```

Reconstruir a aplicação
```shell
docker-compose build --no-cache
```

Após ter rodado o comando acima, será possível acessar o Swagger por meio do endpoint:

http://localhost:8080/swagger-ui/index.html

## Arquitetura

Para desenvolvimento do projeto, foi utilizado a Arquitetura Hexagonal.

![Arquitetura Hexagonal](docs/images/hexagonal-arch.png)
Fonte: https://community.revelo.com.br/o-que-e-arquitetura-de-software/

## Estrutura de pastas

```
.
└── src/
    ├── application/
    │   ├── exceptions
    │   ├── ports
    │   └── services
    ├── domain/
    │   ├── entities
    │   ├── enums
    │   └── valueobjects
    ├── infrastructure/
    │   └── repositories
    └── userinterface/
        └── controllers
```
