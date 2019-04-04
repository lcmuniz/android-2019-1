package br.lsdi.ufma.threadsapp.tarefas;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import br.lsdi.ufma.threadsapp.R;

public class TarefaPesadaAsyncTask extends AsyncTask<Integer, Integer, String> {

    private final TextView tarefaTextView;
    private final ProgressBar progressoProgressBar;
    private final Button executarTarefaPesadaButton;

    public TarefaPesadaAsyncTask(Activity activity) {
        tarefaTextView = activity.findViewById(R.id.tarefaTextView);
        progressoProgressBar = activity.findViewById(R.id.progressoProgressBar);
        executarTarefaPesadaButton = activity.findViewById(R.id.executarTarefaPesadaButton);
    }

    @Override
    protected void onPreExecute() {
        executarTarefaPesadaButton.setEnabled(false);
        tarefaTextView.setText("Tarefa iniciada");
        progressoProgressBar.setProgress(0);
        progressoProgressBar.setVisibility(ProgressBar.VISIBLE);
    }

    @Override
    protected void onPostExecute(String s) {
        tarefaTextView.setText(s);
        progressoProgressBar.setVisibility(ProgressBar.INVISIBLE);
        executarTarefaPesadaButton.setEnabled(true);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        progressoProgressBar.setProgress(values[0]);
    }

    @Override
    protected String doInBackground(Integer... integers) {
        for (int i = 1; i <= 10; i++) {
            sleep();
            publishProgress(i * 10);
        }
        return "Tarefa concluida";
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
