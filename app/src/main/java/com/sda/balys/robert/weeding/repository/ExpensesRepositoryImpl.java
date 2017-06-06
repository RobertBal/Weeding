package com.sda.balys.robert.weeding.repository;


import com.sda.balys.robert.weeding.AndroidApplication;
import com.sda.balys.robert.weeding.presistetence.WeddingSharedPreferences;

/**
 * Created by RENT on 2017-05-17.
 */

public class ExpensesRepositoryImpl implements ExpensesRepository{


    private static ExpensesRepositoryImpl mInstance = new ExpensesRepositoryImpl();
    private final WeddingSharedPreferences mSharedPreferences;
    private ExpensesRepositoryImpl(){
       mSharedPreferences = AndroidApplication.getmSharedPreferences();
    }

    public static ExpensesRepositoryImpl getmInstance(){
        return mInstance;
    }

    @Override

    public void saveCounter(int counter) {
       mSharedPreferences.saveCounter(counter);
    }

    @Override
    public int getCounter() {

        return mSharedPreferences.readCounter();
    }
}
