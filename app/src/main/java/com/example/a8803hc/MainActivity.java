package com.example.a8803hc;

//public class TCPclient {
//}
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    TextView output;
    Button mkconn;
    //    EditText hostname, port;
    int port;
    InetAddress hostname;
    Thread myNet;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        output = (TextView) findViewById(R.id.output);
        output.append("\n");
//        Log.e("here","1");

//        hostname = (EditText) findViewById(R.id.EThostname);
//        new RetrieveFeedTask().execute(urlToRssFeed);


//        class RetrieveFeedTask extends AsyncTask<String, Void, RSSFeed> {
//
//            private Exception exception;
//
//            protected RSSFeed doInBackground(String... urls) {
//                try {
//                    hostname = InetAddress.getLocalHost();
//                    InetAddress hn = hostname;
//                } catch (UnknownHostException e) {
//                    e.printStackTrace();
//                }
//            }
//        }


//        hostname.setText("10.0.2.2"); //This address is the localhost for the computer the emulator is running on.
//        port = (EditText) findViewById(R.id.ETport);
        port = 9610;
        mkconn = (Button) findViewById(R.id.makeconn);
//        mkconn.setOnClickListener(this);
//        Log.e("here","2");
        mkconn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.e("here","3");
                doNetwork stuff = new doNetwork();
                myNet = new Thread(stuff);
                myNet.start();
//                Log.e("here","4");
                //((doNetwork) myNet).out.println("hi");
//                stuff.out.println("");
            }
        });


//    @Override
//    public void onClick(View v) {
//        doNetwork stuff = new doNetwork();
//        myNet = new Thread(stuff);
//        myNet.start();
//        //((doNetwork) myNet).out.println("hi");
//        stuff.out.println("");
//
//    }
//        Handler handler = new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                output.append(msg.getData().getString("msg"));
//            }
//
//        };
//    public void mkmsg(String str) {
//        //handler junk, because thread can't update screen!
//        Message msg = new Message();
//        Bundle b = new Bundle();
//        b.putString("msg", str);
//        msg.setData(b);
//        handler.sendMessage(msg);
//    }

    }
}