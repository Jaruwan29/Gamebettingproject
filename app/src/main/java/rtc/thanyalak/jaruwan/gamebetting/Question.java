package rtc.thanyalak.jaruwan.gamebetting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class Question extends AppCompatActivity {

    private ImageView imageView;
    private String tag = "16janV1";
    private String[] questionStrings, choice1Strings, choice2Strings,
            choice3Strings, choice4Strings, answerStrings;
    private int indexAnInt = 0;
    private TextView questionTextView, choice1TextView, choice2TextView,
            choice3TextView, choice4TextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        //Bind Widget
        bindWidget();

        //Syn Question
        sysQuestion();

        //Setup View
        changeView(indexAnInt);

        //Image Controller
        imageController();


    }   // Main Method

    private void bindWidget() {

        imageView = (ImageView) findViewById(R.id.imageView3);
        questionTextView = (TextView) findViewById(R.id.textView5);
        choice1TextView = (TextView) findViewById(R.id.textView6);
        choice2TextView = (TextView) findViewById(R.id.textView7);
        choice3TextView = (TextView) findViewById(R.id.textView8);
        choice4TextView = (TextView) findViewById(R.id.textView9);

    }

    private void sysQuestion() {

        try {

            MySynChronize mySynChronize = new MySynChronize(Question.this);
            mySynChronize.execute();
            String strJSON = mySynChronize.get();
            Log.d(tag, "JSON ==> " + strJSON);

            if (strJSON.length() != 0) {
                mySynChronize.progressDialog.dismiss();
            }

            JSONArray jsonArray = new JSONArray(strJSON);
            questionStrings = new String[jsonArray.length()];
            choice1Strings = new String[jsonArray.length()];
            choice2Strings = new String[jsonArray.length()];
            choice3Strings = new String[jsonArray.length()];
            choice4Strings = new String[jsonArray.length()];
            answerStrings = new String[jsonArray.length()];

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                questionStrings[i] = jsonObject.getString("Question");
                choice1Strings[i] = jsonObject.getString("Choice1");
                choice2Strings[i] = jsonObject.getString("Choice2");
                choice3Strings[i] = jsonObject.getString("Choice3");
                choice4Strings[i] = jsonObject.getString("Choice4");
                answerStrings[i] = jsonObject.getString("Answer");

            }   //for

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void imageController() {

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                indexAnInt += 1;
                changeView(indexAnInt);


                //startActivity(new Intent(Question.this,Answer.class));
            }   // onClick
        });

    }

    private void changeView(int indexAnInt) {

        questionTextView.setText(Integer.toString(indexAnInt + 1) + ". " + questionStrings[indexAnInt]);
        choice1TextView.setText("ก. " + choice1Strings[indexAnInt]);
        choice2TextView.setText("ข. " + choice2Strings[indexAnInt]);
        choice3TextView.setText("ค. " + choice3Strings[indexAnInt]);
        choice4TextView.setText("ง. " + choice4Strings[indexAnInt]);


    }   // changeView

}   // Main Class
