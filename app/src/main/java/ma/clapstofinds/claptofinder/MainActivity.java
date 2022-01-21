package ma.clapstofinds.claptofinder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Parcelable;
import android.speech.RecognitionListener;
import android.speech.RecognitionService;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button bt,speak;
    TextView googleSpeech;
    SpeechRecognizer speechRecognizer;
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Switch switch12 = findViewById(R.id.switch1);
        speak = findViewById(R.id.Speak);
        googleSpeech =findViewById(R.id.googleSpeech);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECORD_AUDIO},1);
        }
        speechRecognizer=SpeechRecognizer.createSpeechRecognizer(this);
        Intent speechedIntent =new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            speak.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                 if(count==0){
                     speak.setText("MIC ON");
                     count=1;
                     speechRecognizer.startListening(speechedIntent);
                 }else {
                     speak.setText("MIC OFF");
                    count=0;
                    speechRecognizer.stopListening();

                 }
                }
            });
            speechRecognizer.setRecognitionListener(new RecognitionListener() {
                @Override
                public void onReadyForSpeech(Bundle bundle) {

                }

                @Override
                public void onBeginningOfSpeech() {

                }

                @Override
                public void onRmsChanged(float v) {

                }

                @Override
                public void onBufferReceived(byte[] bytes) {

                }

                @Override
                public void onEndOfSpeech() {

                }

                @Override
                public void onError(int i) {

                }

                @Override
                public void onResults(Bundle results) {
                        if (switch12.isChecked()){
                        ArrayList<String> data = results.getStringArrayList(speechRecognizer.RESULTS_RECOGNITION);
                        if(data.get(0).equals("1")){
                            Intent intent1 = new Intent(getApplicationContext(), ringing.class);
                            startActivity(intent1);
                        }
                        }
                }

                @Override
                public void onPartialResults(Bundle bundle) {

                }

                @Override
                public void onEvent(int i, Bundle bundle) {

                }
            });


        bt =(Button) findViewById(R.id.button);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switch12.isChecked()){


                    Intent intent = new Intent(getApplicationContext(), ringing.class);
                    startActivity(intent);

                }else if (switch12.getText().equals("OFF")){
                    Toast.makeText(getApplicationContext(),"TURN ON THE APP SWITCH.",Toast.LENGTH_SHORT).show();
                }
            }
        });
        if (switch12 != null) {
            switch12.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        Toast.makeText(getApplicationContext(),"CLAP FINDER ON.",Toast.LENGTH_SHORT).show();
                        switch12.setText("ON");

                    } else {
                        Toast.makeText(getApplicationContext(),"CLAP FINDER OFF",Toast.LENGTH_SHORT).show();
                        switch12.setText("OFF");

                    }
                }
            });


        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull  String[] permissions, @NonNull  int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if (requestCode==1){
        if (grantResults[0]==PackageManager.PERMISSION_GRANTED) {
        Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT);

        }else{
            Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT);
        }
    }
    }

}