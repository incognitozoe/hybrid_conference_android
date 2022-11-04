package com.example.a8803hc;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

class doNetwork  implements Runnable {

    public void run() {
//            int p = Integer.parseInt(port.getText().toString());
        int p=9610;
//        MediaPlayer mp1 = MediaPlayer.create(MainActivity, R.raw.test1);
        Log.e("p: ", String.valueOf(p));
        InetAddress hostname = null;
        try {
//            hostname = InetAddress.getLocalHost();
//            hostname=InetAddress.getByName("localhost");
            hostname=InetAddress.getByName("192.168.0.100");
            Log.e("hn",String.valueOf(hostname));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        try {
//            Socket socket = new Socket(hostname, p);
            Socket socket=new Socket();
            SocketAddress socketAddress=new InetSocketAddress(hostname, p);
            socket.connect(socketAddress);
            InputStream input = socket.getInputStream();
            InputStreamReader reader = new InputStreamReader(input);

            int character;
            StringBuilder data = new StringBuilder();

            while ((character = reader.read()) != -1) {
                data.append((char) character);
            }

            Log.e("data", String.valueOf(data));
            //now send a message to the server and then read back the response.
            try {

//                play();
            } catch(Exception e) {
//                    mkmsg("Error happened sending/receiving\n");

            } finally {
//                in.close();
//                out.close();
                socket.close();
            }

        } catch (Exception e) {
//                mkmsg("Unable to connect...\n");
        }
    }



    public void play() {
// Get the file we want to playback.
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/reverseme.pcm");

        // Get the length of the audio stored in the file (16 bit so 2 bytes per short)
// and create a short array to store the recorded audio.
        int musicLength = (int)(file.length()/2);
        short[] music = new short[musicLength];


        try {
// Create a DataInputStream to read the audio data back from the saved file.
            InputStream is = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);
            DataInputStream dis = new DataInputStream(bis);

// Read the file into the music array.
            int i = 0;
            while (dis.available() > 0) {
                music[musicLength-1-i] = dis.readShort();
                i++;
            }


// Close the input streams.
            dis.close();


// Create a new AudioTrack object using the same parameters as the AudioRecord
// object used to create the file.
            AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
                    11025,
                    AudioFormat.CHANNEL_CONFIGURATION_MONO,
                    AudioFormat.ENCODING_PCM_16BIT,
                    musicLength,
                    AudioTrack.MODE_STREAM);
// Start playback
            audioTrack.play();

// Write the music buffer to the AudioTrack object
            audioTrack.write(music, 0, musicLength);


        } catch (Throwable t) {
            Log.e("AudioTrack","Playback Failed");
        }
    }
}