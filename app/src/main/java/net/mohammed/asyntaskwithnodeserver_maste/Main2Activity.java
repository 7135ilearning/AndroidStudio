package net.mohammed.asyntaskwithnodeserver_maste;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpGet;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main2Activity extends AppCompatActivity {
    TextView txtv;
Button bt ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        txtv=(TextView)findViewById(R.id.textView3);
        bt = (Button)  findViewById(R.id.button2);
bt.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String myurl = "http://selling.alruabye.net/UsersWS.asmx/Login?UserName=a&Password=حسين"  ;
        new  MyAsyncTaskgetNews().execute(myurl);
    }
});

    }
    public class MyAsyncTaskgetNews extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {


        }
        @Override
        protected String  doInBackground(String... params) {
            String NewsData="";
            try {
                InputStream  inputStream;
                HttpClient httpClient =new DefaultHttpClient();
                HttpResponse httpResponse =httpClient.execute(new HttpGet(params[0]));
                inputStream = httpResponse.getEntity().getContent();
                NewsData=Stream2String(inputStream);
                publishProgress(NewsData );

            } catch (Exception e) {
                // TODO Auto-generated catch block

            }
            return null;
        }
        protected void onProgressUpdate(String... progress) {

            try {

                txtv.setText(progress[0]);


            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
        protected void onPostExecute(String  result2){

        }




    }
    public String Stream2String(InputStream inputStream) {
        BufferedReader bureader=new BufferedReader( new InputStreamReader(inputStream));
        String line ;
        String Text="";
        try{
            while((line=bureader.readLine())!=null) {
                Text+=line;
            }
            inputStream.close();
        }catch (Exception ex){}
        return Text;
    }


}
