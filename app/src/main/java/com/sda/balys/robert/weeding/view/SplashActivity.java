package com.sda.balys.robert.weeding.view;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.sda.balys.robert.weeding.R;
import com.sda.balys.robert.weeding.repository.ExpensesRepositoryImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {
    @BindView(R.id.textCounter)
    TextView mCounterText;
    ExpensesRepositoryImpl mExpenseRepository = ExpensesRepositoryImpl.getmInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        displayCounter();
        runMainScreen();
    }



    private void displayCounter() {
        int counter = mExpenseRepository.getCounter();
        //String counterText = getString(R.string.,counter);
        String counterText2 = getResources().getQuantityString(R.plurals.open_app_counter,counter,counter);

        mCounterText.setText(counterText2);
        incrmentCounter(counter);
    }

    private void incrmentCounter(int counter) {
        counter++;
        mExpenseRepository.saveCounter(counter);
    }

    private void runMainScreen(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                navigateToMenuScreen();
            }
        },3000);
    }

    private void navigateToMenuScreen() {
        Intent intent = new Intent(this,MenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);

    }
}
