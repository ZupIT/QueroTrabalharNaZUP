package my.curriculo.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

//COMO CHAMAR ESSA CLASSE
//EditText cpf = (EditText) findViewById(R.id.txtCPF);
//cpf.addTextChangedListener(Mask.insert("###.###.###-##", cpf));
//
//EditText cnpj = (EditText) findViewById(R.id.txtCNPJ);
//cnpj.addTextChangedListener(Mask.insert("##.###.###/####-##", cnpj));
//
//EditText telefone = (EditText) findViewById(R.id.txtTelefone);
//telefone.addTextChangedListener(Mask.insert("(##)####-####", telefone));

public abstract class Mask {
	 
    public static String unmask(String s) {
	return s.replaceAll("[.]", "").replaceAll("[-]", "")
			.replaceAll("[/]", "").replaceAll("[(]", "")
			.replaceAll("[)]", "");
}

public static TextWatcher insert(final String mask, final EditText ediTxt) {
	return new TextWatcher() {
		boolean isUpdating;
		String old = "";

		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			String str = Mask.unmask(s.toString());
			String mascara = "";
			if (isUpdating) {
				old = str;
				isUpdating = false;
				return;
			}
			int i = 0;
			for (char m : mask.toCharArray()) {
				if (m != '#' && str.length() > old.length()) {
					mascara += m;
					continue;
				}
				try {
					mascara += str.charAt(i);
				} catch (Exception e) {
					break;
				}
				i++;
			}
			isUpdating = true;
			ediTxt.setText(mascara);
			ediTxt.setSelection(mascara.length());
		}

		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
		}

		public void afterTextChanged(Editable s) {
		}
	};
}

}