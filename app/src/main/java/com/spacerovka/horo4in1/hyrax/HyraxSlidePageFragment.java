/*
 * Copyright 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.spacerovka.horo4in1.hyrax;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.spacerovka.horo4in1.R;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * A fragment representing a single step in a wizard. The fragment shows a dummy title indicating
 * the page number, along with some dummy text.
 *
 * <p>This class is used by the {@link
 * HyraxScreenSlideActivity} samples.</p>
 */
public class HyraxSlidePageFragment extends Fragment {
    /**
     * The argument key for the page number this fragment represents.
     */
    public static final String ARG_PAGE = "page";

    /**
     * The fragment's page number, which is set to the argument value for {@link #ARG_PAGE}.
     */
    private int mPageNumber;

    /**
     * Factory method for this fragment class. Constructs a new fragment for the given page number.
     */
    public static HyraxSlidePageFragment create(int pageNumber) {
        HyraxSlidePageFragment fragment = new HyraxSlidePageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public HyraxSlidePageFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout containing a title and body text.
        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_screen_slide_page, container, false);

        // Set the title view to show the page number.
        /*((TextView) rootView.findViewById(android.R.id.text1)).setText(
                getString(R.string.title_template_step, mPageNumber + 1));*/

        Log.i("page num is ",mPageNumber+"");
        String astro = "libra";
        String hyraxAstro = getHyraxAstro(astro);
        Log.i("Astro ru is", hyraxAstro);
        if(mPageNumber==0){
            //TODO find preference
            ((TextView) rootView.findViewById(android.R.id.text1)).setText(
                    getString(R.string.today));
            String siteUrl = "http://hyrax.ru/rss_daily_common_" + "libra" + ".xml";
            (new ParseHyraxXML((TextView) rootView.findViewById(R.id.result))).execute(new String[]{siteUrl, "today",hyraxAstro});
        }else if(mPageNumber==1){
            ((TextView) rootView.findViewById(android.R.id.text1)).setText(
                    getString(R.string.week));
            String siteUrl = "http://hyrax.ru/week/week.rss";
            //http://hyrax.ru/week/week.rss
            (new ParseHyraxXML((TextView) rootView.findViewById(R.id.result))).execute(new String[]{siteUrl, "week",hyraxAstro});
        }else if(mPageNumber==2){
            ((TextView) rootView.findViewById(android.R.id.text1)).setText(
                    getString(R.string.month));
            String siteUrl = "http://hyrax.ru/cgi-bin/bn_mon_xml.cgi";
            (new ParseHyraxXML((TextView) rootView.findViewById(R.id.result))).execute(new String[]{siteUrl, "month",hyraxAstro});
        }
        ((TextView) rootView.findViewById(android.R.id.text1)).setTextColor(getResources().getColor(R.color.md_amber_50));
        ((TextView) rootView.findViewById(R.id.result)).setTextColor(getResources().getColor(R.color.md_amber_50));

        return rootView;
    }

    /**
     * Returns the page number represented by this fragment object.
     */
    public int getPageNumber() {
        return mPageNumber;
    }

    private static String getTomorrowDate(){
        GregorianCalendar gc = new GregorianCalendar();
        gc.add(Calendar.DATE, 1);
        return Integer.toString(gc.get(Calendar.DAY_OF_MONTH))+"-"+ Integer.toString(gc.get(Calendar.MONTH) + 1)+"-"+ Integer.toString(gc.get(Calendar.YEAR));
    }

    private String getHyraxAstro(String astro){
        switch (astro){
            case "libra":
                return "Весы";
            default:
                return null;
        }
    }
}
