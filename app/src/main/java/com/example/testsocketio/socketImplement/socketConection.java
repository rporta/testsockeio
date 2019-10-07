package com.example.testsocketio.socketImplement;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.URISyntaxException;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.emitter.Emitter;

public class socketConection {

    public JSONObject data;

    public static String TAG = "socketConection";
    private String protocol;
    private String host;
    private Integer port;
    private com.github.nkzawa.socketio.client.Socket socket;
    private Emitter.Listener onNewMessage;

    public socketConection(){
        this(null,null, 0);
    }

    public socketConection(String protocol, String host){
        this(protocol,host, 0);
    }

    public socketConection (String protocol, String host, int port){
        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        this.setProtocol(protocol);
        this.sethost(host);
        this.setPort(port);
    }

    public String getHost() {
        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        return this.host;
    }

    public int getPort() {
        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        return this.port;
    }

    public String getProtocol(){
        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        return this.protocol;
    }

    public void sethost(String host) {
        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        this.host = host;
    }

    public void setPort(int port) {
        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        this.port = port;
    }

    public void setProtocol(String protocol) {
        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        this.protocol = protocol;
    }

    public com.github.nkzawa.socketio.client.Socket getSocket() {
        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        return this.socket;
    }

    public void setSocket(com.github.nkzawa.socketio.client.Socket socket) {
        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        this.socket = socket;
    }

    public void init(){
        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        Log.d(TAG, nameofCurrMethod);
        try {
            if(port == 0){
                this.setSocket(IO.socket(this.getProtocol() + "://" + this.getHost()));
                this.connect();
                Log.d(TAG, nameofCurrMethod);
            }else{
                this.setSocket(IO.socket(this.getProtocol() + "://" + this.getHost() + ":" + this.getPort()));
                this.connect();
                Log.d(TAG, nameofCurrMethod);
            }
        } catch (URISyntaxException e) {
            Log.d(TAG, nameofCurrMethod +
                    ", catch " + e
            );
        }
    }
    public void sendEvent(String event, Object... data){
        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        Log.d(TAG, nameofCurrMethod);

        this.getSocket().emit(event, data);
    }

    public void connect(){
        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        Log.d(TAG, nameofCurrMethod);

        this.getSocket().connect();
    }

    public void disconnect(){
        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        Log.d(TAG, nameofCurrMethod);

        this.getSocket().disconnect();
    }

}

