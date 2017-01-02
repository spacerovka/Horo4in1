package com.spacerovka.horo4in1;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Spacerovka on 24.07.2016.
 */
public class ParseMailRuHTML extends AsyncTask<String, Integer, String> {

    private TextView text_1;
    private TextView text_2;
    private String text_param;

    public ParseMailRuHTML(Activity myContext) {
        text_1 = (TextView) myContext.findViewById(R.id.text_1);
        text_2 = (TextView) myContext.findViewById(R.id.text_2);
    }

    @Override
    protected String doInBackground(String... strings) {
        StringBuffer buffer = new StringBuffer();
        text_param = strings[1];
        try {
            Log.i("JSwa", "Connecting to ["+strings[0]+"]");
            Document doc  = Jsoup.connect(strings[0]).maxBodySize(0).timeout(600000).get();
//            Log.i("JSwa", doc.html());
            String substring = doc.text().substring(
                    doc.text().indexOf("Прогноз на")+10,//for today and tomorrow
                    doc.text().indexOf("Подробнее о знаке"));
            Log.i("DOC", substring);
            buffer.append(substring);
        }
        catch(Throwable t) {
            t.printStackTrace();
        }

        return buffer.toString();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(text_param=="text_1") {
            if (text_1 != null) {
                text_1.setText(s);
            }
        }

        if(text_param=="text_2") {
            if (text_2 != null) {
                text_2.setText(s);
            }
        }

    }

}
