# -*- coding: utf-8 -*-

#Este script deve ser usado para criar a base de dados juntamente
#com a tabela que será utilizada.

import sqlite3

conn = sqlite3.connect("banco.db")
cursor = conn.cursor()
cursor.execute("CREATE TABLE dados(cep text PRIMARY KEY, endereco text, bairro text, cidade text, estado text);")
conn.commit()
conn.close()
