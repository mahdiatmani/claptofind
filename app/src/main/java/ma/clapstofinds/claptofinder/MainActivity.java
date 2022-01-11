package ma.clapstofinds.claptofinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button bt;
   public MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Switch switch12 = findViewById(R.id.switch1);
        bt =(Button) findViewById(R.id.button);
        mp = MediaPlayer.create(getApplicationContext(),R.raw.song_ring);

        if (switch12 != null) {
            switch12.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        Toast.makeText(getApplicationContext(),"CLAP FINDER ON",Toast.LENGTH_SHORT).show();
                        switch12.setText("ON");
                        bt.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getApplicationContext(), ringing.class);
                                startActivity(intent);
                                mp.start();
                            }
                        });
                    } else {
                        Toast.makeText(getApplicationContext(),"CLAP FINDER OFF",Toast.LENGTH_SHORT).show();
                        switch12.setText("OFF");
                        mp.stop();

                    }
                }
            });


        }

    }


}