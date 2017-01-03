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

package com.spacerovka.horo4in1.ignio;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.spacerovka.horo4in1.Astros;
import com.spacerovka.horo4in1.R;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class IgnioSlidePageFragment extends Fragment {
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
    public static IgnioSlidePageFragment create(int pageNumber) {
        IgnioSlidePageFragment fragment = new IgnioSlidePageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public IgnioSlidePageFragment() {
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

        SharedPreferences sPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String astro = sPref.getString("astro", null);

        if(mPageNumber==0){
            ((TextView) rootView.findViewById(android.R.id.text1)).setText(Astros.getHyraxAstro(astro)+". "+
                    getString(R.string.today));
            String siteUrl = "http://img.ignio.com/r/export/utf/xml/daily/com.xml";
            (new ParseIgnioXML((TextView) rootView.findViewById(R.id.result))).execute(new String[]{siteUrl, "today",astro});
        }else if(mPageNumber==1){
            ((TextView) rootView.findViewById(android.R.id.text1)).setText(Astros.getHyraxAstro(astro)+". "+
                    getString(R.string.tomorrow));
            String siteUrl = "http://img.ignio.com/r/export/utf/xml/daily/com.xml";
            (new ParseIgnioXML((TextView) rootView.findViewById(R.id.result))).execute(new String[]{siteUrl, "tomorrow",astro});
        }else if(mPageNumber==2){
            ((TextView) rootView.findViewById(android.R.id.text1)).setText(Astros.getHyraxAstro(astro)+". "+
                    getString(R.string.week));
            String siteUrl = "http://img.ignio.com/r/export/utf/xml/weekly/cur.xml";
            (new ParseIgnioXML((TextView) rootView.findViewById(R.id.result))).execute(new String[]{siteUrl, "week",astro});
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
}
