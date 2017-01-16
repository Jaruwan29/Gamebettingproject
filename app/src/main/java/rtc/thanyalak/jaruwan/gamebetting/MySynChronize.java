package rtc.thanyalak.jaruwan.gamebetting;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

/**
 * Created by masterUNG on 1/16/2017 AD.
 */

public class MySynChronize extends AsyncTask<Void, Void, String>{

    //Explicit
    private Context context;
    private static final String urlJSON = "http://swiftcodingthai.com/pay/get_question.php";
    public ProgressDialog progressDialog;

    public MySynChronize(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog = ProgressDialog.show(context, "โปรรอสักครู่", "กำลังโหลด คำถามจาก Server");

    }

    @Override
    protected String doInBackground(Void... voids) {

        try {

            OkHttpClient okHttpClient = new OkHttpClient();
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(urlJSON).build();
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }
}   // Main Class
