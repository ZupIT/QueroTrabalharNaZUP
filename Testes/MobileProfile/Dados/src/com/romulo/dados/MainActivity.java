package com.romulo.dados;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private static final String TAG = "MainActivity";
	
	EditText edtNome, edtDesc;
	Button btnInserir, btnExcluir, btnSair;
	Spinner spinDados;
	TextView txtNome, txtDescricao;
	CheckBox chkEditar, chkInserir;
	LinearLayout layoutEditar;
	ImageView img;
	
	Dados dados, arrDados[];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		edtNome = (EditText) findViewById(R.id.edtNome);
		edtDesc = (EditText) findViewById(R.id.edtDesc);
		btnInserir = (Button) findViewById(R.id.btnInserir);
		btnExcluir = (Button) findViewById(R.id.btnExcluir);
		btnSair = (Button) findViewById(R.id.btnSair);
		spinDados = (Spinner) findViewById(R.id.spinDados);
		txtNome = (TextView) findViewById(R.id.txtNome);
		txtDescricao = (TextView) findViewById(R.id.txtDescricao);
		chkEditar = (CheckBox) findViewById(R.id.chkEditar);
		chkInserir = (CheckBox) findViewById(R.id.chkInserir);
		layoutEditar = (LinearLayout) findViewById(R.id.LayoutInserir);
		img = (ImageView) findViewById(R.id.img);
		
		
		CarregaSpinner();
		
		spinDados.setOnItemSelectedListener(new OnItemSelectedListener() {
			
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				dados = arrDados[position];
				txtNome.setText(dados.getNome());
				txtDescricao.setText(dados.getDescricao());
				chkEditar.setChecked(false);
				
				try {
					ByteArrayInputStream is = new ByteArrayInputStream(dados.getFoto());
					Bitmap bmp = BitmapFactory.decodeStream(is);
					img.setImageBitmap(bmp);
				} catch (Exception e) {
					img.setImageResource(android.R.color.transparent);
					Log.i(TAG, "Sem Imagem");
				}
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
		});
		
		btnInserir.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dados.setNome(edtNome.getText().toString());
				dados.setDescricao(edtDesc.getText().toString());
				dados.setFoto(null);
				
				if (chkInserir.isChecked()) {
					if (dados.Inserir(dados, MainActivity.this)) {
						Toast.makeText(MainActivity.this, "Inserido com sucesso!", Toast.LENGTH_LONG).show();
					} else {
						Toast.makeText(MainActivity.this, "Erro ao inserir!", Toast.LENGTH_LONG).show();
					}
					CarregaSpinner();
					spinDados.setSelection(spinDados.getCount()-1);
				} else {
					int pos = (int) spinDados.getSelectedItemId();
					if (dados.SalvarDados(dados, MainActivity.this)) {
						Toast.makeText(MainActivity.this, "Alterado com sucesso!", Toast.LENGTH_LONG).show();
					} else {
						Toast.makeText(MainActivity.this, "Erro ao alterar!", Toast.LENGTH_LONG).show();
					}
					CarregaSpinner();
					spinDados.setSelection(pos);
				}
				
				edtNome.setText("");
				edtDesc.setText("");
				
				chkEditar.setChecked(false);
			}
		});
		
		btnExcluir.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (dados.Excluir(dados, MainActivity.this)) {
					Toast.makeText(MainActivity.this, "Excluido com sucesso!", Toast.LENGTH_LONG).show();
				} else {
					Toast.makeText(MainActivity.this, "Erro ao excluir!", Toast.LENGTH_LONG).show();
				}
				try {
					CarregaSpinner();
					spinDados.setSelection(0);
				} catch (Exception e) {
					Log.i(TAG, "Spinner vazio!");
				}
			}
		});
		
		btnSair.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		chkEditar.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (chkEditar.isChecked()) {
					layoutEditar.setVisibility(View.VISIBLE);
					
					//pequeno procedimento técnico não padronizado (gambiarra) para rodar o
					//evento do checkedchangelistener e não precisar repetir código ;)
					chkInserir.setChecked(true);
					chkInserir.setChecked(false);
				} else {
					layoutEditar.setVisibility(View.GONE);
					edtNome.setText("");
					edtDesc.setText("");
				}
			}
		});
		
		chkInserir.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (chkInserir.isChecked()) {
					edtNome.setText("");
					edtDesc.setText("");
				} else {
					try {
						edtNome.setText(dados.getNome());
						edtDesc.setText(dados.getDescricao());
					} catch (Exception e) {
						Log.e(TAG, "Erro ao carregar informações do objeto! Detalhes: "+e);
					}
				}
			}
		});
	}
	
	public void CarregaSpinner() {
		dados = new Dados();
		arrDados = dados.BuscaTodosObjDados(this);
		
		try {
			List<String> listaDados = new ArrayList<String>();
			
			for(int i = 0; i < arrDados.length; i++) {
				listaDados.add(arrDados[i].getNome());
			}
			
			ArrayAdapter<String> arrayDados = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaDados);
			arrayDados.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spinDados.setAdapter(arrayDados);
		} catch (Exception e) {
			ArrayAdapter<String> arrayVazio = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
			spinDados.setAdapter(arrayVazio);
			chkEditar.setChecked(false);
			txtNome.setText("");
			txtDescricao.setText("");
			Log.i(TAG, "Array vazio para o spinner.");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
