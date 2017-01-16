package rtc.thanyalak.jaruwan.gamebetting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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
            choice3TextView, choice4TextView, moneyTextView;
    private int moneyAnInt = 100000;
    private EditText chice1EditText, chice2EditText, chice3EditText, chice4EditText;
    private MyAlert myAlert;
    private boolean[] booleen = new boolean[]{true, true, true, true, true, true};
    private int choice1AnInt, choice2AnInt, choice3AnInt, choice4AnInt;
    private int[] answerInts;
    private int sumChoice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        myAlert = new MyAlert(Question.this);

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
        moneyTextView = (TextView) findViewById(R.id.textView3);
        chice1EditText = (EditText) findViewById(R.id.editText);
        chice2EditText = (EditText) findViewById(R.id.editText2);
        chice3EditText = (EditText) findViewById(R.id.editText3);
        chice4EditText = (EditText) findViewById(R.id.editText4);

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
            answerInts = new int[jsonArray.length()];

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                questionStrings[i] = jsonObject.getString("Question");
                choice1Strings[i] = jsonObject.getString("Choice1");
                choice2Strings[i] = jsonObject.getString("Choice2");
                choice3Strings[i] = jsonObject.getString("Choice3");
                choice4Strings[i] = jsonObject.getString("Choice4");
                answerStrings[i] = jsonObject.getString("Answer"); // 1,2,3,4
                answerInts[i] = Integer.parseInt(answerStrings[i]);

            }   //for

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void imageController() {

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (indexAnInt == 14 && booleen[0]) {
                    myAlert.myDialog("สรุปคะแนน", "คุณมีเงินเหลือ = " + Integer.toString(moneyAnInt));
                    booleen[0] = false;
                } else if (indexAnInt == 29 && booleen[1]) {
                    myAlert.myDialog("สรุปคะแนน", "คุณมีเงินเหลือ = " + Integer.toString(moneyAnInt));
                    booleen[1] = false;
                } else if (indexAnInt == 44 && booleen[2]) {
                    myAlert.myDialog("สรุปคะแนน", "คุณมีเงินเหลือ = " + Integer.toString(moneyAnInt));
                    booleen[2] = false;
                } else if (indexAnInt == 59 && booleen[3]) {
                    myAlert.myDialog("สรุปคะแนน", "คุณมีเงินเหลือ = " + Integer.toString(moneyAnInt));
                    booleen[3] = false;
                } else if (indexAnInt == 74 && booleen[4]) {
                    myAlert.myDialog("สรุปคะแนน", "คุณมีเงินเหลือ = " + Integer.toString(moneyAnInt));
                    booleen[4] = false;
                } else if (indexAnInt == 89 && booleen[5]) {
                    myAlert.myDialog("สรุปคะแนน", "คุณมีเงินเหลือ = " + Integer.toString(moneyAnInt));
                    booleen[5] = false;
                } else if (checkSumMoney()) {
                    // กรอกเงินไม่ถูกต้อง
                    myAlert.myDialog("กรอกเงินไม่ถุกต้อง", "กรุณากรอกเงินใหม่ กรอกเงินไม่ถูกต้อง");
                } else {

                    checkScore(indexAnInt);

                    //สิ่งที่ต้องทำ
                    indexAnInt += 1;
                    changeView(indexAnInt);
                }


                //startActivity(new Intent(Question.this,Answer.class));
            }   // onClick
        });

    }

    private void checkScore(int indexAnInt) {

        int trueAnswer = 0;

        switch (answerInts[indexAnInt]) {
            case 1:
                trueAnswer = choice1AnInt;
                break;
            case 2:
                trueAnswer = choice2AnInt;
                break;
            case 3:
                trueAnswer = choice3AnInt;
                break;
            case 4:
                trueAnswer = choice4AnInt;
                break;

        }


        moneyAnInt = moneyAnInt - (sumChoice - trueAnswer);
        Log.d("16janV2", "current money ==> " + moneyAnInt);

    }   // checkScore

    private boolean checkSumMoney() {

        boolean result = true;

        choice1AnInt = myGetFromChoice(chice1EditText.getText().toString().trim());
        choice2AnInt = myGetFromChoice(chice2EditText.getText().toString().trim());
        choice3AnInt = myGetFromChoice(chice3EditText.getText().toString().trim());
        choice4AnInt = myGetFromChoice(chice4EditText.getText().toString().trim());

        Log.d("16janV2", "Choice1 ==> " + choice1AnInt);
        Log.d("16janV2", "Choice2 ==> " + choice2AnInt);
        Log.d("16janV2", "Choice3 ==> " + choice3AnInt);
        Log.d("16janV2", "Choice4 ==> " + choice4AnInt);

        sumChoice = choice1AnInt + choice2AnInt + choice3AnInt + choice4AnInt;

        if (sumChoice == moneyAnInt) {
            result = false;
        }


        return result;
    }

    private int myGetFromChoice(String strChoice) {

        int intResult = 0;

        try {
            intResult = Integer.parseInt(strChoice);
        } catch (Exception e) {
            intResult = 0;
        }

        return intResult;
    }

    private void changeView(int indexAnInt) {

        moneyTextView.setText(Integer.toString(moneyAnInt));
        questionTextView.setText(Integer.toString(indexAnInt + 1) + ". " + questionStrings[indexAnInt]);
        choice1TextView.setText("ก. " + choice1Strings[indexAnInt]);
        choice2TextView.setText("ข. " + choice2Strings[indexAnInt]);
        choice3TextView.setText("ค. " + choice3Strings[indexAnInt]);
        choice4TextView.setText("ง. " + choice4Strings[indexAnInt]);

        //Clear Edti
        chice1EditText.setText("");
        chice2EditText.setText("");
        chice3EditText.setText("");
        chice4EditText.setText("");


    }   // changeView

}   // Main Class
