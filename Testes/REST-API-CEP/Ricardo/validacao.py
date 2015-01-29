# -*- coding: utf-8 -*-

import re

# Expressão regular para checagem do formato de cep, fornecido pelo usuário.
cepFormat = re.compile("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]")

def validaGet(cep):
	if (len(cep) == 8 and cepFormat.match(cep)): # Garante 8 caracteres, sendo estes apenas digitos de 0 a 9.
		return True
	else:
		return False

def validaPostCep(request):
	if (len(request.json['cep']) == 8 and cepFormat.match(request.json['cep'])):
		return True
	else:
		return False 
