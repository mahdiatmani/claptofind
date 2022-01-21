package ma.clapstofinds.claptofinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ringing extends AppCompatActivity {
    public MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ringing);
        ImageView imageView1= (ImageView) findViewById(R.id.stopimage);
        mp = MediaPlayer.create(getApplicationContext(),R.raw.song_ring);
        mp.start();

    }


    public void stopAndBack(View view) {
        mp.stop();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}