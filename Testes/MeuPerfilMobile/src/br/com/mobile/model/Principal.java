package br.com.mobile.model;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import br.com.mobile.R;
import br.projeto.telas.Informacao;
import br.projeto.telas.Motivacoes;
import br.projeto.telas.Objetivos;
import br.projeto.telas.Principais_Conquistas;

public class Principal extends Activity {
	
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH) @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		GridView gridView = (GridView)findViewById(R.id.gridview);
	    gridView.setAdapter(new MyAdapter(this));
	    //mudando titulo e icone da ActionBar
	    getActionBar().setTitle("Principal");
	    getActionBar().setIcon(R.drawable.icon);
	    //evento de click no gridview
	    gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int posicao,
					long arg3) {
				// chamar outra tela pela posição do gridview
				switch (posicao) {
				case 0:
					Intent irTela_Conquistas = new Intent(Principal.this, Principais_Conquistas.class);
					startActivity(irTela_Conquistas);
					//animação entre transição de telas
					overridePendingTransition(R.anim.ac_filho_entrando, R.anim.ac_pai_saindo);
					break;
				case 1:
					Intent irTela_Obj_Metas = new Intent(Principal.this, Objetivos.class);
					startActivity(irTela_Obj_Metas);
					//animação entre transição de telas
					overridePendingTransition(R.anim.ac_filho_entrando, R.anim.ac_pai_saindo);
					break;
				case 2:
					Intent irTela_Motivacoes = new Intent(Principal.this, Motivacoes.class);
					startActivity(irTela_Motivacoes);
					//animação entre transição de telas
					overridePendingTransition(R.anim.ac_filho_entrando, R.anim.ac_pai_saindo);
					break;
				case 3:
					Intent irTela_Informacoes = new Intent(Principal.this, Informacao.class);
					startActivity(irTela_Informacoes);
					//animação entre transição de telas
					overridePendingTransition(R.anim.ac_filho_entrando, R.anim.ac_pai_saindo);
					break;
				default:
					break;
				}
				
			}
		});
	}
}
