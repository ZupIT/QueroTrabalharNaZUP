package my.curriculo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import my.curriculo.model.PessoaModel;
import my.curriculo.utils.Funcoes;


public class MainActivity extends Activity {

    private Context ctx;
    private static SharedPreferences prefs;
    private static final String NOME_PREFS = "PREFS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ctx = this;
        prefs = getSharedPreferences(NOME_PREFS, 0);

        SharedPreferences.Editor edit = prefs.edit();
        edit.putBoolean("primeira_vez", false);
        edit.apply();

        //Declaração de variáveis
        ImageView img_profile = (ImageView) findViewById(R.id.main_img_profile);
        TextView txt_nome = (TextView) findViewById(R.id.main_txt_nome);
        TextView txt_idade = (TextView) findViewById(R.id.main_txt_idade);
        TextView txt_cpf = (TextView) findViewById(R.id.main_txt_cpf);
        TextView txt_telefone = (TextView) findViewById(R.id.main_txt_telefone);
        TextView txt_email = (TextView) findViewById(R.id.main_txt_email);
        TextView txt_idioma = (TextView) findViewById(R.id.main_txt_idioma);
        TextView txt_resumo = (TextView) findViewById(R.id.main_txt_resumo);
        TextView txt_experiencia = (TextView) findViewById(R.id.main_txt_experiencia);
        TextView txt_competencia = (TextView) findViewById(R.id.main_txt_competencia);

        PessoaModel heitor = preenche_dados_heitor();

        //Coloca os dados do heitor nas views
        img_profile.setImageDrawable(ctx.getResources().getDrawable(heitor.getImgSRC()));
        txt_nome.setText(heitor.getNome());
        txt_idade.setText(String.valueOf(Funcoes.idade(heitor.getData_nascimento())));
        txt_cpf.setText(Funcoes.mask_cpf(heitor.getCpf()));
        txt_email.setText(heitor.getEmail());
        txt_resumo.setText(heitor.getResumo());
        txt_experiencia.setText(heitor.getExperiencia());

        String telefone = "";
        for(String t : heitor.getTelefones()) {
            telefone += Funcoes.mask_telefone(t) + " | ";
        }
        telefone = telefone.substring(0, telefone.length() - 2);
        txt_telefone.setText(telefone);

        String idioma = "";
        for(String t : heitor.getIdiomas()) {
            idioma += t + " | ";
        }
        idioma = idioma.substring(0, idioma.length() - 2);
        txt_idioma.setText(idioma);

        String competencias = "";
        for(String t : heitor.getCompetencias()) {
            competencias += t + " | ";
        }
        competencias = competencias.substring(0, competencias.length() - 2);
        txt_competencia.setText(competencias);
    }

    private PessoaModel preenche_dados_heitor() {
        PessoaModel p = new PessoaModel();

        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

            p.setImgSRC(R.drawable.img_heitor);
            p.setNome("Heitor Polizeli Rodrigues");
            p.setCpf("39793812850");
            p.setData_nascimento(format.parse("17/02/1992"));
            p.setEmail("heitorprodrigues@gmail.com");
            p.setResumo("Atualmente trabalhando com desenvolvimento de aplicativos mobile para empresas.\n\n" +
                    "Estudei no curso de Bacharelado em Ciência da Computação com ênfase em Sistemas de Informação na " +
                    "Universidade Estadual Paulista Júlio de Mesquita Filho. Destacando os conhecimentos para gerenciamento " +
                    "de banco de dados e desenvolvimento de aplicações comerciais com foco na internet.");

            p.setExperiencia("Analista Programador Júnior 2 NewM\n abril de 2014 – até o momento\n\n" +
                    "Desenvolvedor Grupo de Banco de Dados - GBD\n" +
                    "março de 2011 – março de 2013");

            List<String> competencias = new ArrayList();

            competencias.add("Android");
            competencias.add("Java");
            competencias.add("Javascript");
            competencias.add("SQL");
            competencias.add("JQuery");
            competencias.add("PHP");
            competencias.add("HTML/CSS");

            p.setCompetencias(competencias);

            List<String> telefones = new ArrayList();
            telefones.add("17981109351");
            telefones.add("1734721481");

            p.setTelefones(telefones);

            List<String> idiomas = new ArrayList();
            idiomas.add("Português");
            idiomas.add("Inglês");

            p.setIdiomas(idiomas);


        } catch (Exception ex) {
            Log.e("Erro preencher_pessoa", ex.getMessage());
        }

        return p;
    }
}
