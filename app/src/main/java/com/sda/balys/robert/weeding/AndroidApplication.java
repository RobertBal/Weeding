package com.sda.balys.robert.weeding;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.sda.balys.robert.weeding.database.DataBase;
import com.sda.balys.robert.weeding.database.DataBaseCashe;
import com.sda.balys.robert.weeding.database.DataBaseOrmImpl;
import com.sda.balys.robert.weeding.presistetence.WeddingSharedPreferences;


/**
 * Created by RENT on 2017-05-17.
 */

public class AndroidApplication extends Application{
    //private static DataBase mDataBase;
   private static WeddingSharedPreferences mSharedPreferences;
    private static DataBase mDataBase;


    @Override
    public void onCreate() {
        super.onCreate();
        mSharedPreferences = new WeddingSharedPreferences(this);
        //mDataBase = new DataBaseCashe();
        mDataBase = OpenHelperManager.getHelper(this, DataBaseOrmImpl.class);

    }
    public static WeddingSharedPreferences getmSharedPreferences(){
        return mSharedPreferences;
    }

    private void    initializeVectorResources(){
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
    public static DataBase getmDataBase(){
        return mDataBase;
    }

}
