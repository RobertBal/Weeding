package com.sda.balys.robert.weeding.model;

import android.database.Cursor;
import android.provider.ContactsContract;

/**
 * Created by RENT on 2017-05-23.
 */

public class Contact {
    private int mID;
    private String mName;
    public Contact(Cursor cursor){
        mID = cursor.getInt(cursor.getColumnIndex("_ID"));
        mName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY));
    }

    public int getmID() {
        return mID;
    }

    public String getmName() {
        return mName;
    }
}
