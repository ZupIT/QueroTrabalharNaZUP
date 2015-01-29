#!flask/bin/python
# -*- coding: utf-8 -*-

from flask import Flask, jsonify, abort, request, g
import sqlite3, validacao as v

app = Flask(__name__)

@app.before_request
def before_request():
	g.db = sqlite3.connect("banco.db") # Conecta ao banco toda vez que executar uma query.

@app.teardown_request
def teardown_request(exception):
	if hasattr(g, 'db'):
		g.db.close() # Fecha conexão automaticamente após query.


# Método chamado ao receber um http GET request no contexto indicado em route.
# Para testar utilizando o curl, basta executar o seguinte comando: curl -i http://localhost:5000/rest-api/busca/cep
@app.route('/rest-api/busca/<string:cep>', methods=['GET'])
def buscarEndereco(cep):
	if (v.validaGet(cep)): # Se o formato da string cep estiver ok.
		endereco = g.db.execute("SELECT * FROM dados WHERE cep = ?", [cep]).fetchall()

		if len(endereco) == 0: # Se retornar uma lista vazia...
			return jsonify(status = "ERRO", mensagem = "O CEP informado não foi encontrado")
		else:
			return jsonify(status = "SUCESSO", cep = endereco[0][0], endereco = endereco[0][1], bairro = endereco[0][2], cidade = endereco[0][3], estado = endereco[0][4])
	else: # Se o valor de CEP não passar pelas regras de validação...
		return jsonify(status="ERRO", mensagem="O CEP informado é inválido")

# Método chamado ao receber um http POST request no contexto indicado em route.
# Para testar com a ferramenta curl, basta executar o comando indicado abaixo,
# preenchendo o json com os dados necessários.
# curl -i -H "Content-Type: application/json" -X POST -d '{"cep":"15067433","endereco":"Nome da Rua","bairro":"Nome do Bairro","cidade":"São José do Rio Preto","estado":"SP"}' http://localhost:5000/rest-api/cadastro
@app.route('/rest-api/cadastro', methods=['POST'])
def criarEndereco():
	if not request.json: # Se o mimetype não for application/json retorna None e aborta.
		abort(400)

	if v.validaPostCep(request): # Se o cep estiver no formato valido.
		try:
			# Adicionar na base de dados.
			aux = g.db.execute("INSERT INTO dados VALUES (?, ?, ?, ?, ?)", (request.json['cep'], request.json['endereco'], request.json['bairro'], request.json['cidade'], request.json['estado']))
			g.db.commit()
		except sqlite3.IntegrityError as e: # Se o cep já estiver cadastrado.
			return jsonify(status = "ERRO", mensagem = "O endereço informado já foi cadastrado")
	    
		return jsonify(status = "SUCESSO", mensagem = "O endereço foi cadastrado com sucesso")
	else:
		return jsonify(status = "ERRO", mensagem = "CEP inválido")


if __name__ == '__main__':
    app.run(debug=True)

# By the way: Any conforming JSON interpreter will correctly unescape this sequence again and give you back the actual character.
