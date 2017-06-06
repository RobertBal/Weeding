package com.sda.balys.robert.weeding.repository;

import com.sda.balys.robert.weeding.AndroidApplication;
import com.sda.balys.robert.weeding.database.DataBase;
import com.sda.balys.robert.weeding.database.DataBaseCashe;
import com.sda.balys.robert.weeding.model.Visitor;

import java.util.List;

/**
 * Created by RENT on 2017-05-20.
 */

public class PeopleRepositoryImpl implements PeopleRepository{

    private static PeopleRepositoryImpl mInstance = new PeopleRepositoryImpl();
    private final DataBase mDataBase;
    private PeopleRepositoryImpl(){
        mDataBase = AndroidApplication.getmDataBase();
    }
    public static PeopleRepositoryImpl getmInstance(){return mInstance;}


    @Override
    public List<Visitor> getAllVisitors() {
        return mDataBase.getAllVisitors();
    }



    public void changeStatus(final int visitorID, final Visitor.VisitorStatus visitorStatus){
        mDataBase.changeStatus(visitorID, visitorStatus);

    }

    @Override
    public void saveVisitor(Visitor visitor) {
        mDataBase.saveVisitor(visitor);

    }

    @Override
    public Visitor getVisitor(int visitorID) {
        return  mDataBase.getVisitor(visitorID);
    }

    @Override
    public void updateVisitor(Visitor visitor) {
        mDataBase.updateVisitor(visitor);
    }
}
