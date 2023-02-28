# Meu-Julius

Uma API para sistema de gastos pessoais.

## Endpoints

- Despesas
  - [Cadastrar](#cadastrar-despesas)
  - Apagar
  - Listar todas
  - Alterar
  - [Mostrar detalhes](#detalhes-da-despesa)

- Contas
  - Cadastrar
  - Apagar
  - Listar todas
  - Alterar
  - Mostrar detalhes 

---

### Cadastrar Despesas

`POST` /meujulius/api/despesa

**Campos da Requisição**

| campo | tipo | obrigatório | descrição
|:-----:|:----:|:-----------:|:--------:
| valor | decimal | sim | o valor da despesa, deve ser maior que zero
| data | data | sim | a data da despesa
| conta_id | int | sim | o id da conta previamente cadastrada
| categoria_id | int | sim | o id de uma categoria previamente cadastrada
| descricao | texto | não | um texto sobre a despesa

**Exemplo de corpo de requisição**

```json
{
    "valor": 100.00,
    "data": "2023-02-27",
    "conta_id": 1,
    "categoria_id": 1,
    "descricao": "cinema - homem aranha"
}
```

**Respostas**

| código | descrição
| - | -
| 201 | a despesa foi cadastrada com sucesso
| 400 | campos inválidos

---

### Detalhes da Despesa

`GET` /meujulius/api/despesa/{id}

**Exemplo de corpo de requisição**

```json
{
    "valor": 100.00,
    "data": "2023-02-27",
    "conta": {
        "conta_id": 1,
        "nome": "itaú"
    },
    "categoria": {
        "categoria_id": 1,
        "nome": "lazer"
    },
    "descricao": "cinema - homem aranha"
}
```

**Respostas**

| código | descrição
| - | -
| 200 | dados da despesa retornados
| 404 | não existe despesa com o id informado

