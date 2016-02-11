package com.example.konstantin.gateopener;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.net.MalformedURLException;
import java.util.concurrent.ExecutionException;

import de.timroes.axmlrpc.XMLRPCException;

public class MainActivity extends AppCompatActivity {
    ImageButton up;
    ImageButton down;
    ImageButton stop;
    ImageButton move_up;
    ImageButton move_down;
    XmlRpcUtil xml = new XmlRpcUtil();
    MoveIt mi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        up = (ImageButton) findViewById(R.id.open_full);
        down = (ImageButton) findViewById(R.id.close_full);
        stop = (ImageButton) findViewById(R.id.STOP);
        move_up = (ImageButton) findViewById(R.id.move_up);
        move_down = (ImageButton) findViewById(R.id.move_down);

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setEnabled(false);
                mi = new MoveIt();
                mi.execute("up");
                view.setEnabled(true);
            }
        });

        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setEnabled(false);
                mi = new MoveIt();
                mi.execute("down");
                view.setEnabled(true);
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mi.cancel(false);
                up.setEnabled(true);
                down.setEnabled(true);
            }
        });

    }


    private class MoveIt extends AsyncTask<String, Void, Void>{
        @Override
        protected Void doInBackground(String... strings) {
            try {
                for (int i = 0; i < 20; i++) {
                    xml.moveIt(strings[0]);
                    if(isCancelled()) {
                        xml.stopAllMoves();
                        return null;
                    }
                }
                xml.stopAllMoves();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (XMLRPCException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
