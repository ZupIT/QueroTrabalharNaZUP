REST API - Busca e cadastro de endereços
=======================================

Elaborar e codificar uma API REST contendo serviços web que efetuem a busca de endereço por cep e cadastro de um endereço.

Requisitos:
-----------

###Busca por CEP:

**Contexto:** /rest-api/busca/{*cepEscolhido*}

**Método:** *GET*

**Requisição:**
Envia o parâmetro *cepEscolhido*

O parâmetro deve conter apenas 8 dígitos em formato String. 

Ex. Para o CEP 00000-000 considere "00000000".

**Resposta:**
Retorna um JSON nos seguintes formatos:

- Caso Sucesso:
```json
{
    "status": "SUCESSO",
    "cep": "00000-000",
    "endereco": "Rua Joao Da Silva",
    "bairro": "Vila Da Silva",
    "cidade": "Cidade do Interior",
    "estado": "SP"
}
```
- Caso Erro "CEP não encontrado":
```json
{
    "status": "ERRO",
    "mensagem": "O CEP informado não foi encontrado"
}
```
- Caso Erro "CEP inválido":
```json
{
    "status": "ERRO",
    "mensagem": "O CEP informado é inválido"
}
```

###Cadastro de endereço:

**Contexto:** /rest-api/cadastro

**Método:** *POST*

**Requisição:**
Envia um *JSON* contendo as informações de cadastro:
```json
{
    "cep": "00000-000",
    "endereco": "Rua Joao Da Silva",
    "bairro": "Vila Da Silva",
    "cidade": "Cidade do Interior",
    "estado": "SP"
}
```
**Resposta:**
Retorna um *JSON* nos seguintes formatos:

-  **Caso Sucesso:**
```json
{
    "status": "SUCESSO",
    "mensagem": "O endereço foi cadastrado com sucesso."
}
```

-  **Caso Erro "Endereço já cadastrado":**
```json
{
    "status": "ERRO",
    "mensagem": "O endereço informado já foi cadastrado."
}
```


Instruções
----------

- Proponha e codifique a solução na linguagem de sua preferência.
- *Não utilize soluções prontas* (como por exemplo a API dos Correios).
- Crie sua propria solução do ínicio, inclusive a estrutura do banco de dados.
- Justifique o uso das tecnologias escolhidas para a solução do problema (Linguagem, Frameworks, Banco de dados).
- Se aplicável, crie testes unitátios para os serviços.
- Faça um *pull request* da sua solução neste repositório.

###Atenção:
- Não é necessário interface gráfica para este problema, mas se julgar conveniente poderá fazê-la.
- Não é necessário hospedar a solução sem nenhum servidor, apenas submeta o código através do pull request.

###Sugestões de pesquisa:
- Protocolo HTTP: Requests/Responses.
- Aplicações web e RESTful web services na linguagem escolhida.
- Servidor de aplicação e servidor HTTP.
- Banco de dados (ex. MySQL) e Mapeamento objeto-relacional (ex. Hibernate).
