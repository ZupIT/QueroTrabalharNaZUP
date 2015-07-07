package br.projeto.telas;

import br.com.mobile.R;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

public class Principais_Conquistas extends Activity {
	@SuppressLint("NewApi") @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH) @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.princip_conq);
		
		//mudando titulo e icone da ActionBar
		getActionBar().setTitle("Principais Conquistas");
		getActionBar().setIcon(R.drawable.conquistas2);
		
		/*TextView texto1 = (TextView) findViewById(R.id.texto1);
		TextView texto2 = (TextView) findViewById(R.id.texto2);
		
		texto1.setText(" - Forma��o Superior na faculdade de tecnologia de S�o Jos� do Rio Preto.");
		texto2.setText(" - Inser��o no mercado de trabalho ( atualmente na �rea de TI)");*/
	}
	@Override
	public void finish() {
		super.finish();
		overridePendingTransition(R.anim.ac_pai_entrando, R.anim.ac_filho_saindo);
	}
}

