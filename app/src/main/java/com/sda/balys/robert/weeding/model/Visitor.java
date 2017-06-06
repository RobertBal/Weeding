package com.sda.balys.robert.weeding.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.sda.balys.robert.weeding.R;

/**
 * Created by RENT on 2017-05-20.
 */
@DatabaseTable(tableName = "visitors")
public class Visitor {

    public enum VisitorStatus{
        NO_RESPONSE(R.color.white_light),
        RESPONSE_OK(R.color.green_light),
        RESPONSE_NO(R.color.red_light);
        private int mColor;
        VisitorStatus(int color){
            mColor=color;
        }
        public int getmColor(){
            return mColor;
        }

    }
     @DatabaseField(columnName = "id",generatedId = true)
    private int mId;

    @DatabaseField(columnName = "name",canBeNull = false)
    private String mName;
    @DatabaseField(columnName = "surname",canBeNull = false)
    private String mSurname;

    @DatabaseField(columnName = "additional_person",canBeNull = false)
    private int mAdditionalPerson;
    @DatabaseField(columnName = "status")
    private VisitorStatus mVisitorStatus;

    public Visitor(){}
    public Visitor(int id, String mName, String mSurname, int mAdditionalPerson, VisitorStatus mVisitorStatus) {

        this.mId=id;
        this.mName = mName;
        this.mSurname = mSurname;
        this.mAdditionalPerson = mAdditionalPerson;
        this.mVisitorStatus = mVisitorStatus;
    }
    public int getId(){
        return mId;
    }

    public String getmName() {
        return mName;
    }


    public String getmSurname() {
        return mSurname;
    }

    public int getmAdditionalPerson() {
        return mAdditionalPerson;
    }

    public VisitorStatus getmVisitorStatus() {
        return mVisitorStatus;
    }
    public void setmVisitorStatus(VisitorStatus status){
        mVisitorStatus = status;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmSurname(String mSurname) {
        this.mSurname = mSurname;
    }

    public void setmAdditionalPerson(int mAdditionalPerson) {
        this.mAdditionalPerson = mAdditionalPerson;
    }
}
