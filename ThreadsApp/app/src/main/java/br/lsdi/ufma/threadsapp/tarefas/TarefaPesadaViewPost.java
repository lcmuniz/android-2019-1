package br.lsdi.ufma.threadsapp.tarefas;

import android.app.Activity;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import br.lsdi.ufma.threadsapp.R;

public class TarefaPesadaViewPost implements Runnable {


    private final Activity activity;

    public TarefaPesadaViewPost(Activity activity) {
        this.activity = activity;
    }

    public void run() {

        final TextView tarefaTextView = activity.findViewById(R.id.tarefaTextView);
        final ProgressBar progressoProgressBar = activity.findViewById(R.id.progressoProgressBar);
        final Button executarTarefaPesadaButton = activity.findViewById(R.id.executarTarefaPesadaButton);

        tarefaTextView.post(new Runnable() {
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
            tarefaTextView.post(new Runnable() {
                @Override
                public void run() {
                    progressoProgressBar.setProgress(passo * 10);
                }
            });

        }
        tarefaTextView.post(new Runnable() {
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
