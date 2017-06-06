package com.sda.balys.robert.weeding.view;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.sda.balys.robert.weeding.R;
import com.sda.balys.robert.weeding.model.Visitor;
import com.sda.balys.robert.weeding.repository.PeopleRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by RENT on 2017-05-18.
 */

public class VisitorsActivity extends AppCompatActivity implements AddVisitorsDialogFragment.AddVisitorDialogInterface {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.add_new_visitor)
    FloatingActionButton floatingActionButton;

    VisitorAdapter mVisitorAdapter;
    List<Visitor> visitors2;
    PeopleRepositoryImpl mDataBase = PeopleRepositoryImpl.getmInstance();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitors);
        ButterKnife.bind(this);
        setupRecyclerView();
        initToolbar();

    }

    //ustawienie recycler view z kodu , menagera mozemy ustawic w xml.
    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecorartion = new DividerItemDecoration(this,linearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecorartion);
        mVisitorAdapter = new VisitorAdapter(this);
        mRecyclerView.setAdapter(mVisitorAdapter);
        List<Visitor> visitors = mDataBase.getAllVisitors();
//        List<Visitor> visitor = new ArrayList<Visitor>();
//        Visitor visitor1 = new Visitor("Jan","Nowak",2,Visitor.VisitorStatus.NO_RESPONSE);
//        Visitor visitor2 = new Visitor("Bar","Kowal",2,Visitor.VisitorStatus.NO_RESPONSE);
//        Visitor visitor3 = new Visitor("Bara","Dupa",2,Visitor.VisitorStatus.RESPONSE_OK);
//        Visitor visitor4 = new Visitor("Mar","Ass",2,Visitor.VisitorStatus.RESPONSE_OK);
//        Visitor visitor5 = new Visitor("Ta","Butt",2,Visitor.VisitorStatus.NO_RESPONSE);
//        Visitor visitor6 = new Visitor("Kolo","P&P",2,Visitor.VisitorStatus.RESPONSE_NO);
//        Visitor visitor7 = new Visitor("Bolo","Cloud",2,Visitor.VisitorStatus.RESPONSE_NO);
//
//        visitor.add(visitor1);
//        visitor.add(visitor2);
//        visitor.add(visitor3);
//        visitor.add(visitor4);
//        visitor.add(visitor5);
//        visitor.add(visitor6);
        mVisitorAdapter.setData(visitors);
        mVisitorAdapter.setmOnLongClickListener(mOnLongClickListener);
        mVisitorAdapter.setmOnClickListener(mOnClickLintener);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            onBackPressed();
        } else if (item.getItemId()==R.id.import_contacts){
            Log.d("test","contacts");
            Intent intent = new Intent(this,ContactActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.add_new_visitor)
    void clickAddNewVisitor(View view){
        DialogFragment dialog=AddVisitorsDialogFragment.newInstance("Hello Java");
        dialog.show(getFragmentManager(),"Dialog");

    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_visitor_activity,menu);
        return true;
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);//informujemy  aplikacje ze naszym systmeowym toolbarem jest mToolbar
        getSupportActionBar().setDisplayShowTitleEnabled(true);          //pobieramy
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_cash);

    }

    private void showAskDialog(final Visitor visitor){
        new AlertDialog.Builder(this).setTitle("Potwierdzenie").setMessage(getString(R.string.confirm_visitor,visitor.getmName(),visitor.getmSurname()))
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        changeVisitorStatus(visitor.getId(),Visitor.VisitorStatus.RESPONSE_OK);

                    }
                })
                .setNegativeButton("Nie", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        changeVisitorStatus(visitor.getId(),Visitor.VisitorStatus.RESPONSE_NO);
                    }
                })
                .setNeutralButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        changeVisitorStatus(visitor.getId(), Visitor.VisitorStatus.NO_RESPONSE);

                    }
                }).show();

    }

    private void changeVisitorStatus(int visitorId, Visitor.VisitorStatus visitorStatus){
        mDataBase.changeStatus(visitorId,visitorStatus);
        displayData();
    }

    private void displayData(){
        mVisitorAdapter.setData(mDataBase.getAllVisitors());
    }

    private final View.OnClickListener mOnClickLintener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          Visitor visitor = (Visitor) v.getTag();
            DialogFragment dialog=AddVisitorsDialogFragment.newInstance(visitor.getId());
            dialog.show(getFragmentManager(),"Dialog");

        }
    };


    private final View.OnLongClickListener mOnLongClickListener = new View.OnLongClickListener() {

        @Override
        public boolean onLongClick(View v) {
            Visitor visitor = (Visitor) v.getTag();
            showAskDialog(visitor);
            Log.d("sadas",visitor.getmName());
            return true;
        }
    };

    @Override
    public void onDismisDialog() {
        displayData();
    }
}
