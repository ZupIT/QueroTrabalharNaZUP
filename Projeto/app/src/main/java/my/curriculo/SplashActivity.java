package my.curriculo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;


public class SplashActivity extends Activity {

    private Handler handler = new Handler();
    private Context ctx;
    private static int TIME_MENSAGEM = 1000;
    private static SharedPreferences prefs;
    private static final String NOME_PREFS = "PREFS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        prefs = getSharedPreferences(NOME_PREFS, 0);

        //Declaração de variáveis
        TextView txt_msg = (TextView) findViewById(R.id.splash_txt_mensagem);
        LinearLayout linear_conceitos = (LinearLayout) findViewById(R.id.splash_linear_conceitos);
        ctx = this;

        //Enviar as mensagens de abertura
        if(prefs.getBoolean("primeira_vez", true)) {
            enviar_mensagem(txt_msg, linear_conceitos, ctx.getResources().getString(R.string.hello_world), 0);
        } else {
            Intent it = new Intent(ctx, MainActivity.class);
            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(it);
        }
    }

    private void enviar_mensagem(final TextView v, final LinearLayout l, final String msg, final int position) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                v.setText(msg);

                //Mensagens para o usuário
                if (position < 11) {
                    enviar_mensagem(v, l, escolhe_mensagem(position + 1), position + 1);
                    enfeita_mensagem(v, position);
                }

                if (position > 4 && position < 11) {
                    addConceito(l, position);
                }

                if(position == 11) {
                    l.removeAllViews();
                    enviar_mensagem(v, l, escolhe_mensagem(position), position + 1);
                }

                if(position == 12) {
                    Intent it = new Intent(ctx, MainActivity.class);
                    it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(it);
                }
            }
        }, TIME_MENSAGEM);
    }

    private String escolhe_mensagem(int p) {
        switch (p) {
            case 1:
                TIME_MENSAGEM = 2000;
                return ctx.getResources().getString(R.string.msg_1);
            case 2:
                TIME_MENSAGEM = 4000;
                return ctx.getResources().getString(R.string.msg_2);
            case 3:
            case 4:
                TIME_MENSAGEM = 3000;
                return ctx.getResources().getString(R.string.msg_3);
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                TIME_MENSAGEM = 2000;
                return ctx.getResources().getString(R.string.msg_4);
            case 11:
                TIME_MENSAGEM = 2000;
                return ctx.getResources().getString(R.string.msg_5);
            default:
                return ctx.getResources().getString(R.string.msg_ops);
        }
    }

    private void enfeita_mensagem(TextView v, int p) {
        switch (p) {
            case 3:
                v.setBackgroundResource(R.drawable.shape_square_light_grey);
                v.setPadding(15, 10, 15, 10);
                Animation pulse = AnimationUtils.loadAnimation(ctx, R.anim.pulse);
                v.startAnimation(pulse);
                break;
            case 4:
                v.clearAnimation();
                v.setBackgroundResource(Color.TRANSPARENT);
                v.setPadding(0, 0, 0, 0);
                break;
            default:
                v.setBackgroundResource(Color.TRANSPARENT);
                v.setPadding(0, 0, 0, 0);
        }
    }

    private void addConceito(LinearLayout l, int p) {
        TextView text = new TextView(ctx);
        text.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        text.setTextSize(TypedValue.COMPLEX_UNIT_PX, ctx.getResources().getDimension(R.dimen.font_default_size));

        switch (p) {
            case 5:
                text.setText(ctx.getResources().getString(R.string.conceito_1));
                l.addView(text);
                break;
            case 6:
                text.setText(ctx.getResources().getString(R.string.conceito_3));
                l.addView(text);
                break;
            case 7:
                text.setText(ctx.getResources().getString(R.string.conceito_4));
                l.addView(text);
                break;
            case 8:
                text.setText(ctx.getResources().getString(R.string.conceito_5));
                l.addView(text);
                break;
            case 9:
                text.setText(ctx.getResources().getString(R.string.conceito_6));
                l.addView(text);
                break;
            case 10:
                text.setText(ctx.getResources().getString(R.string.conceito_7));
                l.addView(text);
                break;
        }
    }
}
