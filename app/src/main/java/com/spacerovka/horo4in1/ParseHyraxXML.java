package com.spacerovka.horo4in1;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Spacerovka on 24.07.2016.
 */
public class ParseHyraxXML extends AsyncTask<String, Integer, String> {

    private TextView text_1;
    private TextView text_2;
    private String text_param;
    String myXmlData;

    public ParseHyraxXML(Activity myContext) {
        text_1 = (TextView) myContext.findViewById(R.id.text_1);
        text_2 = (TextView) myContext.findViewById(R.id.text_2);
    }

    @Override
    protected String doInBackground(String... strings) {
        StringBuffer buffer = new StringBuffer();
        text_param = strings[1];
        try {
            myXmlData = downloadXML(strings[0]);

            buffer.append(parseData(myXmlData));
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

    private String downloadXML(String theUrl) throws IOException {
        int BUFFER_SIZE = 2000;
        InputStream is = null;

        String xmlContents = "";

        try {

            URL url = new URL(theUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            int response = conn.getResponseCode();
            Log.d("Download XML", "the response is " + response);
            is = conn.getInputStream();

            InputStreamReader isr = new InputStreamReader(is);
            int charRead;

            char[] inputBuffer = new char[BUFFER_SIZE];
            try {
                while ((charRead = isr.read(inputBuffer)) > 0) {
                    String readString = String.copyValueOf(inputBuffer, 0, charRead);
                    xmlContents += readString;
                    inputBuffer = new char[BUFFER_SIZE];
                }
                return xmlContents;

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

        } finally {

            if (is != null)
                is.close();
        }
    }

    private String parseData(String dataToParse){

        boolean inEntry = false;
        String textValue = "";

        try {
//setup basic xml parser
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            //setup its input to parse
            xpp.setInput(new StringReader(dataToParse));
            int eventType = xpp.getEventType();
            while ((eventType != XmlPullParser.END_DOCUMENT)){
                String tagName = "";
                if(eventType == XmlPullParser.START_TAG){
                    tagName = xpp.getName();

                    if(tagName.equalsIgnoreCase("item")){
                        inEntry = true;
                    }

                }
                else if(eventType == XmlPullParser.TEXT){

                    if(tagName.equalsIgnoreCase("description") && inEntry){
                        textValue= xpp.getText();
                    }
                }
                else if(eventType == XmlPullParser.END_TAG){

                    if(inEntry == true) {
                        inEntry = false;
                    }

                }
                eventType = xpp.next();
            }


        }catch (Exception e){
            e.printStackTrace();
        }
        return textValue;
    }


}
