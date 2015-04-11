package com.romulo.dados;

import java.io.ByteArrayOutputStream;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
	
	private static final String DB_NOME = "dados";
	private static final int DB_VERSAO = 1;
	private static final String TAG = "DBHelper";
	
	Context ctx;
	
	public DBHelper(Context context) {
		super(context, DB_NOME, null, DB_VERSAO);
		ctx = context;
	}
	
	/**
	 * Cria as tabelas no banco
	 * Roda somente uma vez quando n�o existe nenhum banco criado
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			db.execSQL("CREATE TABLE DADOS (id integer primary key autoincrement, nome TEXT, descricao TEXT, foto BLOB)");
			
			Bitmap bmp = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.foto);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);
			byte[] foto =  stream.toByteArray();
			
			//Apresenta��o
			ContentValues values = new ContentValues();
			values.put("nome", "Apresenta��o");
			values.put("descricao", "Ol�! Eu sou o cara a� da foto de baixo! Nesta op��o estou trabalhando com uma imagem recuperada do banco de dados SQLite. \n\n" +
									"Moro atualmente em Bebedouro e trabalho em Monte Azul Paulista. Estou buscando crescimento pessoal e profissional em uma cidade maior, onde terei mais op��es e oportunidades de crescimento e aprendizado.\n\n" +
									"Navegue pelos itens do Spinner na parte superior para ver mais informa��es!");
			values.put("foto", foto);
			db.insert("dados", null, values);
			
			//Dados pessoais
			values.clear();
			values.put("nome", "Dados Pessoais");
			values.put("descricao", "Idade: 23\n" +
									"Telefones: (17)99149-3518 / (17)3342-7836\n" +
									"E-mail: romulo.contro@hotmail.com\n" +
									"Facebook: https://www.facebook.com/romulocontro\n" +
									"Linkedin: https://www.linkedin.com/in/romulocontro");
			db.insert("dados", null, values);
			
			//Conquistas Pessoais
			values.clear();
			values.put("nome", "Principais Conquistas Pessoais");
			values.put("descricao", "\n(Os mais recentes em cima)\n" +
									"Adquiri o h�bito de leitura;\n" +
									"Conheci a Avenida Paulista em S�o Paulo;\n" +
									"Conheci a praia, Fortaleza-CE logo de primeira!;\n" +
									"Realizei o sonho de ter uma Kawasaki (mesmo que seja uma ninjinha XD);\n" +
									"Conheci a Serra da Canastra em MG;\n" +
									"Comprei meu carro;\n" +
									"Conheci amigos pra vida toda;\n" +
									"Conheci a pessoa da minha vida;\n" +
									"J� toquei guitarra em uma banda de Viking Metal;\n" +
									"Tomei as r�deas da minha vida!\n" +
									"Tive uma inf�ncia feliz...");
			db.insert("dados", null, values);
			
			//Conquistas Profissionais
			values.clear();
			values.put("nome", "Principais Conquistas Profissionais");
			values.put("descricao", "\n(Os mais recentes em cima)\n" +
									"Fiz curso de especializa��o em SQL Server;\n" +
									"Assumi a responsabilidade de todos os sistemas desenvolvidos internamente, bem como os bancos de dados em SQL Server e MySQL na empresa Montecitrus;\n" +
									"Desenvolvi para Android os aplicativos de uso em campo para coleta de informa��es e apontamento de dados;\n" +
									"Desenvolvi um sistema localizador (rastreador) para Android para monitorar os funcion�rios de campo, posteriormente evoluido para capturar estat�sticas de tempo de " +
									"viagens e perman�ncia de caminh�es em fazendas e f�bricas;\n" +
									"Me formei em Sistemas de Informa��o (2012);\n" +
									"Consegui encontrar utilidade para o meu projeto de TCC na empresa (rastreador Android do item de cima);\n" +
									"Entrei na Montecitrus como Analista Desenvolvedor para programar em Delphi;\n" +
									"Iniciei um setor respons�vel s� por convers�o de dados de outros sistemas na implanta��o do SistemaBIG na empresa BIG Sistemas (antiga BIG Automa��o);\n" +
									"Consegui o cargo de L�der de suporte, comandando e auxiliando varios outros membros do suporte helpdesk;\n" +
									"Entrei na empresa BIG Automa��o (posteriormente mudou para BIG Sistemas) como Suporte helpdesk;\n" +
									"Fui para o setor de inform�tica auxiliar na infra da empresa Reis Advogados Associados;\n" +
									"Entrei na empresa Reis Advogados Associados como digitador ou 'faz tudo' de escrit�rio;\n" +
									"Fiz curso de t�cnico em inform�tica na ETEC;\n" +
									"Fiz curso de hardware, manuten��o e montagens de computadores.");
			db.insert("dados", null, values);
			
			//Objetivos Pessoais
			values.clear();
			values.put("nome", "Objetivos Pessoais");
			values.put("descricao", "\nAlcan�ar a independ�ncia financeira;\n" +
									"Aprender mais sobre investimentos;\n" +
									"Fazer alguma atividade de caridade;\n" +
									"Comprar uma moto maior;\n" +
									"Conhecer outros pa�ses;\n" +
									"E mais alguns ainda n�o lembrados ou planejados...");
			db.insert("dados", null, values);
			
			//Objetivos Profissionais
			values.clear();
			values.put("nome", "Objetivos Profissionais");
			values.put("descricao", "\nTrabalhar cercado por tecnologia, por�m, com espa�o para entender e participar de processos de diversas �reas;\n" +
									"Ter participa��o de grande import�ncia nos projetos da empresa;\n" +
									"Aplicar meus conhecimentos e experi�ncias profissionais;\n" +
									"Sempre melhorar;\n" +
									"E mais alguns ainda n�o tra�ados...");
			db.insert("dados", null, values);
			
			//Planos futuros/Interesses
			values.clear();
			values.put("nome", "Planos futuros/Interesses");
			values.put("descricao", "\nTirar certifica��o SQL Server;\n" +
									"Tirar certifica��o Oracle;\n" +
									"Aprender a programar para iOS;\n" +
									"Aprender Ruby;\n" +
									"Relembrar C# em algum projeto;\n" +
									"Fazer algum projeto com arduino;\n");
			db.insert("dados", null, values);
			
			//Sobre o app
			values.clear();
			values.put("nome", "Sobre o app");
			values.put("descricao", "\nOs itens do aplicativo demoraram mais para ser preenchidos do que o aplicativo para ser desenvolvido;\n\n" +
									"� um app simples, por�m, com a inten��o maior de mostrar o conceito de orienta��o a objetos, uso de " +
									"componentes nativos (Spinners, EditText, TextView, Layouts, etc), classe de acesso ao banco de dados e " +
									"outras coisas importantes para um desenvolvedor Android;\n\n" +
									"� poss�vel cadastrar novos itens ou excluir os existentes que s�o pr�-cadastrados marcando o checkbox 'Editar/Adicionar'.\n\n" +
									"\n\nDesenvolvido por R�mulo Contro na esperan�a de ser contratado na Zup!" +
									"\n\n\nCya o/");
			db.insert("dados", null, values);
			
			
		} catch (Exception e) {
			Log.e(TAG, "Erro ao criar o banco! "+e);
		}
	}
	
	/**
	 * For�a a achar o caminho correto do banco, ou criar caso n�o tenha (implementa��o feita somente para evitar bug)
	 */
	@Override
	public void onOpen(SQLiteDatabase db) {
		if(!db.isOpen()) {
			SQLiteDatabase.openDatabase(db.getPath(), null, SQLiteDatabase.NO_LOCALIZED_COLLATORS | 
															SQLiteDatabase.CREATE_IF_NECESSARY);
		}
	}
	
	/**
	 * Roda toda vez que a vers�o do banco muda (DB_VERSAO)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.beginTransaction();
		try {
			//Implementar altera��es do banco
			
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
		}
		
	}
	
}
