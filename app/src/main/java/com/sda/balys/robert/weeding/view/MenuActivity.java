package com.sda.balys.robert.weeding.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.sda.balys.robert.weeding.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by RENT on 2017-05-17.
 */

public class MenuActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.expense)
    public void expenseOnClick(){
        Intent intent = new Intent(this,ExpanseActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }
    @OnClick(R.id.visitors)
    public void visitorsOnClick(){
        Intent intent = new Intent(this,VisitorsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);

    }
}

