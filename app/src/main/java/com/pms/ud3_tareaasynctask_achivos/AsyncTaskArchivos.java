package com.pms.ud3_tareaasynctask_achivos;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;

public class AsyncTaskArchivos extends Activity {

    DoBackgroundTask descargaTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_archivos);

    }


    // Se ejeucta en un hilo de ejecución en segundo plano y es
    // donde se sitúa el código de larga duración.

    private class DoBackgroundTask extends AsyncTask<URL, Integer, Long> {
        protected Long doInBackground(URL... urls) {
            int count = urls.length;
            long totalBytesDownloaded = 0;
            /*********************************************************************/
          /*** Poner código que falta sobre isCancelled() en el lugar apropiado***/

            for (int i = 0; i < count; i++) {
                totalBytesDownloaded += DownloadFile(urls[i]);
                // ---calcula el porcentaje descargado  reportando el progreso---
                publishProgress((int) (((i + 1) / (float) count) * 100));
            }
            return totalBytesDownloaded;
        }

        // se invoca en el hilo de ejecución de la interfaz de usuario y se
        // llama cuando llama al método publishProgress()
        protected void onProgressUpdate(Integer... progress) {
            Log.d("Descargando Archivos", String.valueOf(progress[0])
                    + "% descargado");
            Toast.makeText(getBaseContext(),
                    String.valueOf(progress[0]) + "% descargado",
                    Toast.LENGTH_LONG).show();
        }

        // se invoca en el hilo de ejecución de la interfaz de usUario y se llama
        //cuando el método doInBackground() ha terminado de ejecutarse

        protected void onPostExecute(Long result) {
            //***********si descarga completada,
            if (result == 400) //mejor poner ese valor 400 como una constante declarada
                //entonces la Tarea se da por no iniciada
                // para que startDescarga pueda iniciarla de nuevo
                descargaTask = null;

            Toast.makeText(getBaseContext(),
                    "Descargados " + result + " bytes", Toast.LENGTH_LONG)
                    .show();
        }
    }


    // Método que simula la descarga de un archivo -- tarea de larga duración
    private int DownloadFile(URL url) {

        try {
            // ---simula el tiempo necesario para descargar el archivo---
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // devuelve el valor simulado de la descarga--
        return 100;
    }


    //simula la descarga de 4 archivos al pulsar el botón de descarga
    public void startDescarga(View view) {

          /**********************************************************/
         //Si la descarga no se ha iniciado nunca o ha sido cancelada
        /********** PONER CÓDIGO QUE FALTA *********/

        try {
            new DoBackgroundTask().execute(new URL(
                    "http://www.amazon.com/somefiles.pdf"), new URL(
                    "http://www.wrox.com/somefiles.pdf"), new URL(
                    "http://www.google.com/somefiles.pdf"), new URL(
                    "http://www.learn2develop.net/somefiles.pdf"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    //para o cancela la descarga
    //simula la descarga de 4 archivos al pulsar el botón de stopDescarga
    public void stopDescarga(View view) {
         /*********************************************/
        /***** poner código que falta ******/
    }
}




