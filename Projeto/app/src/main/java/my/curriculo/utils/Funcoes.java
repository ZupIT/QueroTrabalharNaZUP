package my.curriculo.utils;

import java.util.Date;

/**
 * Created by Heitor on 22/02/2015.
 */
public abstract class Funcoes {

    public static int idade(Date nascimento) {
        Date atual = new Date();

        long diff = atual.getTime() - nascimento.getTime();
        long diffDays = diff / (24 * 60 * 60 * 1000);

        int idade = (int) (diffDays / 365);

        return idade;
    }

    public static String mask_cpf(String s) {
        String t = Mask.unmask(s);

        if (t.length() == 11) {
            t = mask_format(s, "###.###.###-##");
        }

        return t;
    }

    public static String mask_telefone(String s) {
        String t = Mask.unmask(s);

        if (t.length() == 10) {
            t = mask_format(s, "(##) ####-####");
        } else if(t.length() == 11) {
            t = mask_format(s, "(##) #####-####");
        }

        return t;
    }

    public static String mask_format(String texto, String maskPadrao) {
        String old = "";
        String str = Mask.unmask(texto);
        String mascara = "";
        int i = 0;
        for (char m : maskPadrao.toCharArray()) {
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

        return mascara;
    }

}


