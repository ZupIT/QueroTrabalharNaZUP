package br.projeto.telas;

import br.com.mobile.R;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;



public class Informacao extends Activity{

	
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH) @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.informacao);
		
		
		getActionBar().setTitle("Informações de Contato");
		getActionBar().setIcon(R.drawable.informacao);
	}
	@Override
	public void finish() {
		super.finish();
		overridePendingTransition(R.anim.ac_pai_entrando, R.anim.ac_filho_saindo);
	}
}
