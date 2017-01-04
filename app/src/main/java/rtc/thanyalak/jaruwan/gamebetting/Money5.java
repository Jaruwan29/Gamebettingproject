package rtc.thanyalak.jaruwan.gamebetting;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Money5 extends AppCompatActivity {

    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money5);

        imageView = (ImageView) findViewById(R.id.imageView7);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MediaPlayer mediaPlayer = MediaPlayer.create(getBaseContext(),R.raw.sound);

                mediaPlayer.start();

                startActivity(new Intent(Money5.this,Question.class));
            }
        });

    }
}
