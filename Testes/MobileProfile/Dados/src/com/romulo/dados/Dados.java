package com.romulo.dados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

class Dados {
	
	private static final String TAG = "Dados";
	private int id;
	private String nome;
	private String descricao;
	private byte[] foto;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public byte[] getFoto() {
		return foto;
	}
	
	public void setFoto(byte[] bs) {
		this.foto = bs;
	}
	
	public Dados[] BuscaTodosObjDados(Context context) {
		DBAdapter dba = new DBAdapter(context);
		Cursor cdados = dba.select("select * from dados");
		
		if(cdados.getCount() > 0) {
			Dados dados[] = new Dados[cdados.getCount()];
			cdados.moveToFirst();
			
			for(int x = 0; x < cdados.getCount(); x++) {
				dados[x] = new Dados();
				dados[x].setId(cdados.getInt(0));
				dados[x].setNome(cdados.getString(1));
				dados[x].setDescricao(cdados.getString(2));
				dados[x].setFoto(cdados.getBlob(3));
				cdados.moveToNext();
			}
			return dados;
		} else {
			return null;
		}
	}
	
	public boolean Inserir(Dados dados, Context context) {
		try {
			DBAdapter dba = new DBAdapter(context);
			
			ContentValues val = new ContentValues();
			val.put("nome", dados.getNome());
			val.put("descricao", dados.getDescricao());
			val.put("foto", dados.getFoto());
			
			dba.insert(val, "dados");
			
			//dba.comando("insert into dados (nome, descricao) values (" +
			//		"'"+dados.getNome()+"'" +
			//		", '"+dados.getDescricao()+"')");
			
			return true;
		} catch (Exception e) {
			Log.e(TAG, "Erro ao inserir! Detalhes: "+e);
			return false;
		}
	}
	
	public boolean SalvarDados(Dados dados, Context context) {
		try {
			DBAdapter dba = new DBAdapter(context);
			dba.comando("update dados set " +
					"nome = '"+dados.getNome()+"'" +
					", descricao = '"+dados.getDescricao()+"' " +
					"where id = "+dados.getId());
			
			return true;
		} catch (Exception e) {
			Log.e(TAG, "Erro ao salvar! Detalhes: "+e);
			return false;
		}
	}
	
	public boolean Excluir(Dados dados, Context context) {
		try {
			DBAdapter dba = new DBAdapter(context);
			dba.comando("delete from dados where id = "+dados.getId());
			return true;
		} catch (Exception e) {
			Log.e(TAG, "Erro ao excluir! Detalhes: "+e);
			return false;
		}
	}
}