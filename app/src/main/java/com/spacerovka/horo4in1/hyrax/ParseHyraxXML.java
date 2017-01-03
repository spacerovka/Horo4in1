package com.spacerovka.horo4in1.hyrax;

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

    private String text_param;
    private TextView textView;
    String myXmlData;
    String astro;
    private boolean inAstro;

    public ParseHyraxXML(TextView textView) {
        this.textView = textView;
    }

    @Override
    protected String doInBackground(String... strings) {
        StringBuffer buffer = new StringBuffer();
        text_param = strings[1];
        astro = strings[2];
        Log.i("text_param",text_param);
        Log.i("astro", astro);
        try {
            myXmlData = downloadXML(strings[0]);
            Log.i("XML",myXmlData);
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
        if (textView != null) {
            textView.setText(s);
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

            InputStreamReader isr = new InputStreamReader(is,"windows-1251");
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
            String tagName = "";
            while ((eventType != XmlPullParser.END_DOCUMENT)){

                if(eventType == XmlPullParser.START_TAG){
                    tagName = xpp.getName();
                    if(tagName.equalsIgnoreCase("item")){
                        inEntry = true;
                    }

                }
                //else if(eventType == XmlPullParser.TEXT){
                else if(eventType == XmlPullParser.TEXT){
                    if(text_param.equals("today")){
                        if(tagName.equalsIgnoreCase("description") && inEntry) {
                            textValue = xpp.getText();
                            inEntry = false;
                        }
                    }else if(text_param.equals("week")){

                        if(tagName.equalsIgnoreCase("title") && inEntry){
                            String titleText= xpp.getText();
                            int index = titleText.indexOf("(")-1;

                            if(index>1 && titleText.substring(0,index).equalsIgnoreCase(astro)){
                                inAstro = true;
                            }
                        }
                        if(tagName.equalsIgnoreCase("description") && inAstro){
                                textValue = xpp.getText();
                                break;
                        }
                    }else if(text_param.equals("month")){

                        if(tagName.equalsIgnoreCase("title") && inEntry){
                            String titleText= xpp.getText();
                            int index = titleText.indexOf("(")-1;
                            if(index>1 && titleText.substring(0,index).equalsIgnoreCase(astro)){
                                inAstro = true;
                            }
                        }
                        if(tagName.equalsIgnoreCase("description") && inAstro){
                            textValue= xpp.getText();
                            break;
                        }
                    }
                }

                eventType = xpp.next();
            }


        }catch (Exception e){
            e.printStackTrace();
        }
        Log.i("Parsed",textValue);
        return textValue;
    }


}
