package br.lsdi.ufma.threadsapp.tarefas;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import br.lsdi.ufma.threadsapp.R;

public class TarefaPesadaHandlerPost implements  Runnable {

    private final Activity activity;
    private final Handler handler;

    public TarefaPesadaHandlerPost(Activity activity, Handler handler) {
        this.activity = activity;
        this.handler = handler;
    }

    @Override
    public void run() {

        final TextView tarefaTextView = activity.findViewById(R.id.tarefaTextView);
        final ProgressBar progressoProgressBar = activity.findViewById(R.id.progressoProgressBar);
        final Button executarTarefaPesadaButton = activity.findViewById(R.id.executarTarefaPesadaButton);

        handler.post(new Runnable() {
            @Override
            public void run() {
                executarTarefaPesadaButton.setEnabled(false);
                tarefaTextView.setText("Tarefa iniciada");
                progressoProgressBar.setProgress(0);
                progressoProgressBar.setVisibility(ProgressBar.VISIBLE);
            }
        });
        for (int i = 1; i <= 10; i++) {
            sleep();
            final int passo = i;
            handler.post(new Runnable() {
                @Override
                public void run() {
                    progressoProgressBar.setProgress(passo * 10);
                }
            });

        }
        handler.post(new Runnable() {
            @Override
            public void run() {
                tarefaTextView.setText("Tarefa concluida");
                progressoProgressBar.setVisibility(ProgressBar.INVISIBLE);
                executarTarefaPesadaButton.setEnabled(true);
            }
        });
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
