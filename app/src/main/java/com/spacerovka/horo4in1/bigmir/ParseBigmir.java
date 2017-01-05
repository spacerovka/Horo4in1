package com.spacerovka.horo4in1.bigmir;

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
public class ParseBigmir extends AsyncTask<String, Integer, String> {

    private TextView textView;
    private String text_param;
    String result;

    public ParseBigmir(TextView textView) {

        this.textView = textView;
    }

    @Override
    protected String doInBackground(String... strings) {
        StringBuffer buffer = new StringBuffer();
        text_param = strings[1];
        try {
            Log.d("JSwa", "Connecting to ["+strings[0]+"]");
            Document doc  = Jsoup.connect(strings[0]).get();
            Log.d("JSwa", "Connected to ["+strings[0]+"]");
            // Get document (HTML page) title


            Elements classes = doc.getElementsByClass("ml");
            for(Element part : classes){
                Elements paragraphs = part.getElementsByTag("p");
                for(Element p: paragraphs){
                    buffer.append(p.text());

                    Log.i("parsed", p.text());
                }

            }
            doc = null;
            classes = null;


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
            if (textView != null) {
                textView.setText(s);
            }

    }


}
