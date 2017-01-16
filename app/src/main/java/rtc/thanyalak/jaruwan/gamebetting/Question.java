package rtc.thanyalak.jaruwan.gamebetting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class Question extends AppCompatActivity {

    private ImageView imageView;
    private String tag = "16janV1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        //Bind Widget
        imageView = (ImageView) findViewById(R.id.imageView3);

        //Syn Question
        sysQuestion();

        //Image Controller
        imageController();


    }   // Main Method

    private void sysQuestion() {

        try {

            MySynChronize mySynChronize = new MySynChronize(Question.this);
            mySynChronize.execute();
            String strJSON = mySynChronize.get();
            Log.d(tag, "JSON ==> " + strJSON);

            if (strJSON.length() != 0) {
                mySynChronize.progressDialog.dismiss();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void imageController() {

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //startActivity(new Intent(Question.this,Answer.class));
            }   // onClick
        });

    }

}   // Main Class
