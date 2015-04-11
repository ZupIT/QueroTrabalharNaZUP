package com.romulo.dados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBAdapter {
	
	public static final String TAG = "DBAdapter";
	
	private DBHelper dbHelper;
	private SQLiteDatabase db;
	
	public DBAdapter(Context context) {
		dbHelper = new DBHelper(context);
	}
	
	public boolean insert(ContentValues valores, String tabela) {
		try {
			db = dbHelper.getWritableDatabase();
			db.insert(tabela, null, valores);
			return true;
		} catch (Exception e) {
			Log.e(TAG, "Erro: "+e);
			return false;
		}
	}
	
	public boolean comando(String comando) {
		try {
			db = dbHelper.getWritableDatabase();
			db.execSQL(comando);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public Cursor select(String select) {
		db = dbHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery(select, null);
		return cursor;
	}
}
