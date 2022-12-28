package com.dsi.myapplication;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    public static final Integer RecordAudioRequestCode = 1;
    private SpeechRecognizer speechRecognizer;
    private EditText editText;
    private ImageView micButton , imgEtat;
    private  TextView textView ;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
            checkPermission();
        }

        micButton = findViewById(R.id.imageView4);
        imgEtat = findViewById(R.id.imageView5);

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
textView = findViewById(R.id.textView3);
        final Intent speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,"ar-IL");
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
            public void onResults(Bundle bundle) {
                ArrayList<String> data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                String[] words = data.get(0).split(" ");
                System.out.println(words[0]);
                ArrayList<String> tab = new ArrayList<>();
                for (String s : words){
                    tab.add(s);
                }
                if(words[0].equals("سوره")) {
                    mediaPlayer = new MediaPlayer();
                    if (mediaPlayer.isPlaying()) {
                        // pausing the media player if media player
                        // is playing we are calling below line to
                        // stop our media player.
                        mediaPlayer.stop();
                        mediaPlayer.release(); }
    Quran q = new Quran(words[1]);
    System.out.println(q.nombreOfChap());
    String audioUrl = "https://server7.mp3quran.net/basit/Almusshaf-Al-Mojawwad/"+q.nombreOfChap()+".mp3";
                    System.out.println(audioUrl);
                    imgEtat.setImageDrawable(getResources().getDrawable(R.drawable.quran));
                    textView.setText(data.get(0));



    // below line is use to set the audio
    // stream type for our media player.
    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

    // below line is use to set our
    // url to our media player.

    try {
        mediaPlayer.setDataSource(audioUrl);
        // initializing media player

        // below line is use to prepare
        // and start our media player.
        mediaPlayer.prepare();
        mediaPlayer.start();

    } catch (IOException e) {
        e.printStackTrace();
    }
}
                else if (tab.contains("شوفلي")||(tab.contains("شوف")&&tab.contains("لي"))){
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    String s = "";
                    String operateur = "اريد";
                    System.out.println(words.length);
if(words.length==3) {
    operateur = words[2];
    System.out.println(operateur);

}
else if (words.length==4)  {
    operateur = words[3];
    System.out.println(operateur);

}
    switch(operateur){
        case "اوريدو" :
            s = "*100#";
            break;
        case "اريد" :
            s = "*100#";
            break;

        case "تيليكوم" :
            s = "*124#";
            break;
        case "اورنج" :
            s = "*111#";
            break;
    }

                    callIntent.setData(Uri.parse(String.valueOf(Uri.parse("tel:" + Uri.encode(s)))));
                    //change the number
                    startActivity(callIntent);
                }
                else if (tab.contains("نكته")){
                    ArrayList<String> nokta = new ArrayList<>();
                    nokta.add("التوانسة من كثر البخل يسلموا على بعضهم بالحواجب .. إي هكاكة كيما عملت إنت توّه بالضبط");
                    nokta.add("في تونس الي ماماتش بالحوت الازرق ممكن يموت من الهمّ الأزرق .");
                    nokta.add("من كثرة ما قابلت منافقين في حياتي مازالي ستاج ونقابل المسيح الدجال ");
                    nokta.add("فما واحد حب يشق الطريق شوية جرح صبعوا.");
                    nokta.add("واحد يمشي يمشي يمشي ياخي تعب ولى يجري.");
                    nokta.add("رد يتبع في طفلة معجب بيها علاش ؟ اسمها موزة.");
                    nokta.add("واحد غبي اتهموه بالذكاء طلع براءة.");
                    nokta.add("خبير في الأنترنيت جابت مرته طفل سمّاه دوت كوم.");
                    int nombreAleatoire = 0 + (int)(Math.random() * (((nokta.size()-1) - 0) + 1));
                    imgEtat.setImageDrawable(getResources().getDrawable(R.drawable.rire));
                    textView.setText(nokta.get(nombreAleatoire));
                }
                else if (tab.contains("كلملي")||tab.contains("اطلبلي")||tab.contains("اطلب")||tab.contains("كلم")){
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:"+words[1]));//change the number
                    startActivity(callIntent);  
                }
                else if ((tab.contains("الوقت")||tab.contains("التوقيت")||tab.contains("وقت"))&&(tab.contains("قديه")||tab.contains("قداه"))){
                    imgEtat.setImageDrawable(getResources().getDrawable(R.drawable.temps));
                    SimpleDateFormat formatter= new SimpleDateFormat("HH:mm:ss");
                    Date date = new Date(System.currentTimeMillis());
                    System.out.println(formatter.format(date));
                    textView.setText(formatter.format(date)+"\n"+"الوقت تو :");
                }

                else if (tab.contains("نحبك")||tab.contains("نعشق")||tab.contains("حبي")||tab.contains("نحبك")){
                    imgEtat.setImageDrawable(getResources().getDrawable(R.drawable.love));
                    textView.setText("عيشك حتى أنا نحبك ...");
                }
                else if (data.get(0).equals("صباح الخير")|| data.get(0).equals("عسلامة")||data.get(0).equals("مرحبا")||data.get(0).equals("اهلا")||tab.contains("اهلا")){
textView.setText("مرحبا بيك كيفاه نجم نعاونك");
                }
                else if (data.get(0).equals("شكون انت")){
                    textView.setText("أنا محمد علي العدواني");
                }
                else if(data.get(0).equals("اوقات الكار في ولايه صفاقس")){
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.soretras.com.tn/tarif_reg3"));
                    startActivity(browserIntent);
                }
                else{
                    imgEtat.setImageDrawable(getResources().getDrawable(R.drawable.change));
                    textView.setText("ما فهمتكش تنجم تفسرلي اكثر ؟");

                }

        // below line is use to display a toast message.

             /**   Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com/"+data.get(0)));
                startActivity(browserIntent); */
            }

            @Override
            public void onPartialResults(Bundle bundle) {

            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });

        micButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    speechRecognizer.stopListening();
                }
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){

                    speechRecognizer.startListening(speechRecognizerIntent);
                }
                return false;
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        speechRecognizer.destroy();
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECORD_AUDIO},RecordAudioRequestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RecordAudioRequestCode && grantResults.length > 0 ){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show();
        }
    }
}