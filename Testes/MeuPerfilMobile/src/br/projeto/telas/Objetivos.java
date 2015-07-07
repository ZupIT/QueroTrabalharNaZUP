package br.projeto.telas;

import br.com.mobile.R;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;



public class Objetivos extends Activity{

	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH) @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.objetivos);
		getActionBar().setTitle("Objetivos e Metas");
		getActionBar().setIcon(R.drawable.objetivos);
	
	
}
	
	@Override
	public void finish() {
		super.finish();
		overridePendingTransition(R.anim.ac_pai_entrando, R.anim.ac_filho_saindo);
	}
}