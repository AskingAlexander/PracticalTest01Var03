package ro.pub.cs.systems.eim.practicaltest01var03;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class PracticalTest01Var03Service extends Service {

    private ProcessingThread processingThread = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String _firstText = intent.getStringExtra("topEditText");
        String _secondText = intent.getStringExtra("bottomEditText");

        processingThread = new ProcessingThread(this, _firstText, _secondText);
        processingThread.start();
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        processingThread.stopThread();
    }
}

