package br.lsdi.ufma.threadsapp.tarefas;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import br.lsdi.ufma.threadsapp.R;

public class TarefaPesadaHandlerMessage implements Runnable {

    private final Activity activity;
    private final Handler handler;

    public TarefaPesadaHandlerMessage(Activity activity, Handler handler) {
        this.activity = activity;
        this.handler = handler;
    }

    public void run() {

        Message message;

        message = handler.obtainMessage(Handler.INICIAR_TAREFA, "Tarefa iniciada");
        handler.sendMessage(message);

        for (int i = 1; i <= 10; i++) {
            sleep();
            final int passo = i;

            message = handler.obtainMessage(Handler.ATUALIZAR_PROGRESSO, passo * 10);
            handler.sendMessage(message);

        }

        message = handler.obtainMessage(Handler.FINALIZAR_TAREFA, "Tarefa concluida");
        handler.sendMessage(message);
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class Handler extends android.os.Handler  {

        private static final int INICIAR_TAREFA = 0;
        private static final int ATUALIZAR_PROGRESSO = 1;
        private static final int FINALIZAR_TAREFA = 2;

        private final Activity activity;

        public Handler(Activity activity) {
            this.activity = activity;
        }

        @Override
        public void handleMessage(Message msg) {

            final TextView tarefaTextView = activity.findViewById(R.id.tarefaTextView);
            final ProgressBar progressoProgressBar = activity.findViewById(R.id.progressoProgressBar);
            final Button executarTarefaPesadaButton = activity.findViewById(R.id.executarTarefaPesadaButton);

            switch (msg.what) {
                case INICIAR_TAREFA:
                    executarTarefaPesadaButton.setEnabled(false);
                    tarefaTextView.setText((String) msg.obj);
                    progressoProgressBar.setProgress(0);
                    progressoProgressBar.setVisibility(ProgressBar.VISIBLE);
                    break;
                case ATUALIZAR_PROGRESSO:
                    progressoProgressBar.setProgress((Integer) msg.obj);
                    break;
                case FINALIZAR_TAREFA:
                    tarefaTextView.setText((String) msg.obj);
                    progressoProgressBar.setVisibility(ProgressBar.INVISIBLE);
                    executarTarefaPesadaButton.setEnabled(true);
                    break;
            }
        }
    };

}
