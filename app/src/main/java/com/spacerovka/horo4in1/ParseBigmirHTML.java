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
public class ParseBigmirHTML extends AsyncTask<String, Integer, String> {

    private TextView text_1;
    private TextView text_2;
    private String text_param;

    public ParseBigmirHTML(Activity myContext) {
        text_1 = (TextView) myContext.findViewById(R.id.text_1);
        text_2 = (TextView) myContext.findViewById(R.id.text_2);
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

//            String title = doc.title();
//            Log.d("JSwA", "Title ["+title+"]");
            /**buffer.append("Title: " + title + "\r\n");
**/
            // Get meta info
            /**
             * Elements metaElems = doc.select("meta");
            buffer.append("META DATA\r\n");
            for (Element metaElem : metaElems) {
                String name = metaElem.attr("name");
                String content = metaElem.attr("content");
                buffer.append("name ["+name+"] - content ["+content+"] \r\n");
            }

            Elements topicList = doc.select("h2.topic");
            buffer.append("Topic list\r\n");
            for (Element topic : topicList) {
                String data = topic.text();

                buffer.append("Data ["+data+"] \r\n");
            }
             **/
//        buffer.append("Text:");
            Elements classes = doc.getElementsByClass("ml");
            for(Element part : classes){
                Elements paragraphs = part.getElementsByTag("p");
                for(Element p: paragraphs){
                    buffer.append(p.text());
                }

            }



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
