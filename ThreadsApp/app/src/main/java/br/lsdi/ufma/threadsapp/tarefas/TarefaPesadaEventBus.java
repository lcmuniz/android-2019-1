package br.lsdi.ufma.threadsapp.tarefas;

import android.app.Activity;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import br.lsdi.ufma.threadsapp.R;

public class TarefaPesadaEventBus implements Runnable {

    public void run() {

        EventBus.getDefault().post(new IniciarTarefaEvent("Tarefa iniciada"));

        for (int i = 1; i <= 10; i++) {
            sleep();
            EventBus.getDefault().post(new AtualizarTarefaEvent(i * 10));
        }

        EventBus.getDefault().post(new FinalizarTarefaEvent("Tarefa concluida"));

    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public class IniciarTarefaEvent {
        private String mensagem;
        public IniciarTarefaEvent(String mensagem) {
            this.mensagem = mensagem;
        }
        public String getMensagem() {
            return mensagem;
        }
    }
    public class AtualizarTarefaEvent {
        private int progresso;
        public AtualizarTarefaEvent (int progresso) {
            this.progresso = progresso;
        }
        public int getProgresso() {
            return progresso;
        }
    }
    public class FinalizarTarefaEvent {
        private String mensagem;

        public FinalizarTarefaEvent(String mensagem) {
            this.mensagem = mensagem;
        }
        public String getMensagem() {
            return mensagem;
        }
    }
}
