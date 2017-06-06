package com.sda.balys.robert.weeding.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.sda.balys.robert.weeding.R;
import com.sda.balys.robert.weeding.model.Contact;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by RENT on 2017-05-23.
 */

public class ContactActivity extends AppCompatActivity {
    @BindView(R.id.recyler_contact)
    RecyclerView mRecyclerView;

    ContactAdapter mContactAdapter;
    private static final int CONTACT_LOADER =1;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        ButterKnife.bind(this);


        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_CONTACTS)){
                Toast.makeText(this,"Kliknij tu",Toast.LENGTH_SHORT).show();
            }
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.READ_CONTACTS},12);
        } else {
            loadContacts();}


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 12){
            if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                loadContacts();
            }else{
                Toast.makeText(this,"Brak zgody",Toast.LENGTH_SHORT).show();
            }
        }

        setupRecycleView();


    }

    private void setupRecycleView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mContactAdapter = new ContactAdapter(this);
        mRecyclerView.setAdapter(mContactAdapter);
      ;
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
    }

    private void loadContacts() {
        getSupportLoaderManager().initLoader(CONTACT_LOADER,null,mCallbackContacts);
    }
    private LoaderManager.LoaderCallbacks<Cursor> mCallbackContacts = new LoaderManager.LoaderCallbacks<Cursor>() {
        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            return new CursorLoader(ContactActivity.this, ContactsContract.Contacts.CONTENT_URI,null,null,null,null);
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            displayContacts(data);
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {

        }
    };

    private void displayContacts(Cursor cursor) {
        if(cursor==null){
            return;
        }

        cursor.moveToFirst();
        List<String> kontakty = new ArrayList<>();
        List<Contact> contactList = new ArrayList<>();
        do{


            Contact contact = new Contact(cursor);
            contactList.add(contact);

            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            int idcount = cursor.getColumnCount();
            String idse = cursor.getColumnName(2);
            Log.d("contacts",id+" ");
            Log.d("contacts",idse);
            Log.d("count",idcount+"");

        }while(cursor.moveToNext());

        mContactAdapter.setData(contactList);



    }
}
