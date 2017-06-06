package com.sda.balys.robert.weeding.presistetence;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by RENT on 2017-05-17.
 */

public class WeddingSharedPreferences {
    private static final String SHARED_NAME="wedingapl";
    private static final String COUNTER_KEY="counter";
    protected final SharedPreferences mSharedPrefernces;

    public WeddingSharedPreferences(Context context) {
        mSharedPrefernces = context.getSharedPreferences(SHARED_NAME,Context.MODE_PRIVATE);
    }
    public void saveCounter(int counter){
        final SharedPreferences.Editor editor = mSharedPrefernces.edit();
        editor.putInt(COUNTER_KEY,counter);
        editor.apply();
        //editor.commit();
    }
    public int readCounter(){
        final int counter = mSharedPrefernces.getInt(COUNTER_KEY,0);
      return  counter;

    }


}
