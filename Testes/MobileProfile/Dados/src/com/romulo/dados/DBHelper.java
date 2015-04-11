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
	 * Roda somente uma vez quando não existe nenhum banco criado
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			db.execSQL("CREATE TABLE DADOS (id integer primary key autoincrement, nome TEXT, descricao TEXT, foto BLOB)");
			
			Bitmap bmp = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.foto);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);
			byte[] foto =  stream.toByteArray();
			
			//Apresentação
			ContentValues values = new ContentValues();
			values.put("nome", "Apresentação");
			values.put("descricao", "Olá! Eu sou o cara aí da foto de baixo! Nesta opção estou trabalhando com uma imagem recuperada do banco de dados SQLite. \n\n" +
									"Moro atualmente em Bebedouro e trabalho em Monte Azul Paulista. Estou buscando crescimento pessoal e profissional em uma cidade maior, onde terei mais opções e oportunidades de crescimento e aprendizado.\n\n" +
									"Navegue pelos itens do Spinner na parte superior para ver mais informações!");
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
									"Adquiri o hábito de leitura;\n" +
									"Conheci a Avenida Paulista em São Paulo;\n" +
									"Conheci a praia, Fortaleza-CE logo de primeira!;\n" +
									"Realizei o sonho de ter uma Kawasaki (mesmo que seja uma ninjinha XD);\n" +
									"Conheci a Serra da Canastra em MG;\n" +
									"Comprei meu carro;\n" +
									"Conheci amigos pra vida toda;\n" +
									"Conheci a pessoa da minha vida;\n" +
									"Já toquei guitarra em uma banda de Viking Metal;\n" +
									"Tomei as rédeas da minha vida!\n" +
									"Tive uma infância feliz...");
			db.insert("dados", null, values);
			
			//Conquistas Profissionais
			values.clear();
			values.put("nome", "Principais Conquistas Profissionais");
			values.put("descricao", "\n(Os mais recentes em cima)\n" +
									"Fiz curso de especialização em SQL Server;\n" +
									"Assumi a responsabilidade de todos os sistemas desenvolvidos internamente, bem como os bancos de dados em SQL Server e MySQL na empresa Montecitrus;\n" +
									"Desenvolvi para Android os aplicativos de uso em campo para coleta de informações e apontamento de dados;\n" +
									"Desenvolvi um sistema localizador (rastreador) para Android para monitorar os funcionários de campo, posteriormente evoluido para capturar estatísticas de tempo de " +
									"viagens e permanência de caminhões em fazendas e fábricas;\n" +
									"Me formei em Sistemas de Informação (2012);\n" +
									"Consegui encontrar utilidade para o meu projeto de TCC na empresa (rastreador Android do item de cima);\n" +
									"Entrei na Montecitrus como Analista Desenvolvedor para programar em Delphi;\n" +
									"Iniciei um setor responsável só por conversão de dados de outros sistemas na implantação do SistemaBIG na empresa BIG Sistemas (antiga BIG Automação);\n" +
									"Consegui o cargo de Líder de suporte, comandando e auxiliando varios outros membros do suporte helpdesk;\n" +
									"Entrei na empresa BIG Automação (posteriormente mudou para BIG Sistemas) como Suporte helpdesk;\n" +
									"Fui para o setor de informática auxiliar na infra da empresa Reis Advogados Associados;\n" +
									"Entrei na empresa Reis Advogados Associados como digitador ou 'faz tudo' de escritório;\n" +
									"Fiz curso de técnico em informática na ETEC;\n" +
									"Fiz curso de hardware, manutenção e montagens de computadores.");
			db.insert("dados", null, values);
			
			//Objetivos Pessoais
			values.clear();
			values.put("nome", "Objetivos Pessoais");
			values.put("descricao", "\nAlcançar a independência financeira;\n" +
									"Aprender mais sobre investimentos;\n" +
									"Fazer alguma atividade de caridade;\n" +
									"Comprar uma moto maior;\n" +
									"Conhecer outros países;\n" +
									"E mais alguns ainda não lembrados ou planejados...");
			db.insert("dados", null, values);
			
			//Objetivos Profissionais
			values.clear();
			values.put("nome", "Objetivos Profissionais");
			values.put("descricao", "\nTrabalhar cercado por tecnologia, porém, com espaço para entender e participar de processos de diversas áreas;\n" +
									"Ter participação de grande importância nos projetos da empresa;\n" +
									"Aplicar meus conhecimentos e experiências profissionais;\n" +
									"Sempre melhorar;\n" +
									"E mais alguns ainda não traçados...");
			db.insert("dados", null, values);
			
			//Planos futuros/Interesses
			values.clear();
			values.put("nome", "Planos futuros/Interesses");
			values.put("descricao", "\nTirar certificação SQL Server;\n" +
									"Tirar certificação Oracle;\n" +
									"Aprender a programar para iOS;\n" +
									"Aprender Ruby;\n" +
									"Relembrar C# em algum projeto;\n" +
									"Fazer algum projeto com arduino;\n");
			db.insert("dados", null, values);
			
			//Sobre o app
			values.clear();
			values.put("nome", "Sobre o app");
			values.put("descricao", "\nOs itens do aplicativo demoraram mais para ser preenchidos do que o aplicativo para ser desenvolvido;\n\n" +
									"É um app simples, porém, com a intenção maior de mostrar o conceito de orientação a objetos, uso de " +
									"componentes nativos (Spinners, EditText, TextView, Layouts, etc), classe de acesso ao banco de dados e " +
									"outras coisas importantes para um desenvolvedor Android;\n\n" +
									"É possível cadastrar novos itens ou excluir os existentes que são pré-cadastrados marcando o checkbox 'Editar/Adicionar'.\n\n" +
									"\n\nDesenvolvido por Rômulo Contro na esperança de ser contratado na Zup!" +
									"\n\n\nCya o/");
			db.insert("dados", null, values);
			
			
		} catch (Exception e) {
			Log.e(TAG, "Erro ao criar o banco! "+e);
		}
	}
	
	/**
	 * Força a achar o caminho correto do banco, ou criar caso não tenha (implementação feita somente para evitar bug)
	 */
	@Override
	public void onOpen(SQLiteDatabase db) {
		if(!db.isOpen()) {
			SQLiteDatabase.openDatabase(db.getPath(), null, SQLiteDatabase.NO_LOCALIZED_COLLATORS | 
															SQLiteDatabase.CREATE_IF_NECESSARY);
		}
	}
	
	/**
	 * Roda toda vez que a versão do banco muda (DB_VERSAO)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.beginTransaction();
		try {
			//Implementar alterações do banco
			
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
		}
		
	}
	
}
