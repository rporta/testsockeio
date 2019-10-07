
package com.example.testsocketio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.testsocketio.socketImplement.socketConection;
import com.github.nkzawa.emitter.Emitter;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    public static String TAG = "MainActivity";
    socketConection socket;
    boolean runSocket = false;
    boolean runLive = false;
    JSONObject live;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        final MainActivity self = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, nameofCurrMethod);

        self.runSocket();
    }

    public socketConection getSocket() {
        final String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        final MainActivity self = this;

        Log.d(TAG, nameofCurrMethod);

        return self.socket;
    }

    public void setSocket(socketConection socket) {
        final String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        final MainActivity self = this;

        Log.d(TAG, nameofCurrMethod);

        self.socket = socket;
    }

    public void runSocket(){
        final String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        final MainActivity self = this;

        Log.d(TAG, nameofCurrMethod);

        self.runSocket = true;
        self.setSocket(new socketConection("http", "rta-panel.opratel.com", 10005));
        self.getSocket().init();
        self.runSocketOn();
    }

    public void runSocketOn() {

        final String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        final MainActivity self = this;

        Log.d(TAG, nameofCurrMethod);

        // socketServer -> App(java) : disconnect
        self.getSocket().getSocket().on(self.getSocket().getSocket().EVENT_DISCONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                self.runSocket = false;

                // self.getSocket().getSocket().disconnect();

                Log.d(TAG, nameofCurrMethod +
                        ", socketServer -> App(java) : disconnect"
                );
            }
        });
        // socketServer -> App(java) : connect
        self.getSocket().getSocket().on(self.getSocket().getSocket().EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                self.runSocket = true;

                Log.d(TAG, nameofCurrMethod +
                        ", App(java) socket id : " + self.getSocket().getSocket().id()
                );

                self.generateLive();
                self.sendLive();
                // self.xbug("test - xbug");
                Log.d(TAG, nameofCurrMethod +
                        ", socketServer -> App(java) : connect"
                );
            }
        });
    }

    public void xbug(String dbug){
        final String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        final MainActivity self = this;

        Log.d(TAG, nameofCurrMethod);

        if(self.runSocket){
            JSONObject xbug = new JSONObject();

            try {
                xbug.put("id", self.getSocket().getSocket().id());
                xbug.put("e", "xbug");
                xbug.put("data", dbug);
                self.socket.sendEvent("data", xbug);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void generateLive(){
        final String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        final MainActivity self = this;

        Log.d(TAG, nameofCurrMethod);

        // Create date live mokup ...

        self.live = new JSONObject();

        try {
            JSONObject perfil = new JSONObject();

            JSONObject device = new JSONObject();

            device.put("android", true);

            perfil.put("avatar", "data:image/png;base64,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");
            perfil.put("msisdn", "1111(test)");
            perfil.put("name", "ramiro");
            perfil.put("uuid", "00000000-0000-0000-afd0-5e5c46aaad4c");

            self.live.put("e", "live");
            self.live.put("id", self.getSocket().getSocket().id());
            self.live.put("perfil", perfil);
            self.live.put("device", device);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void sendLive()
    {
        final String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        final MainActivity self = this;

        Log.d(TAG, nameofCurrMethod);

        if(self.runSocket && self.runLive == false){
            TimerTask sendLiveTask = new TimerTask() {
                public void run() {
                    try {
                        if(self.runSocket && self.runLive == false){
                            self.runLive = true;
                        }
                         Log.d(TAG, nameofCurrMethod + ", sendLive ..." + self.live);

                        self.live.put("id", self.getSocket().getSocket().id());
                        self.socket.sendEvent("data", self.live);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };

            Timer sendLiveTimer = new Timer("sendLive");

            long sendLiveDelay  = 1000L;
            long sendLivePeriod = 5000L;
            sendLiveTimer.scheduleAtFixedRate(sendLiveTask, sendLiveDelay, sendLivePeriod);
        }
    }
}
