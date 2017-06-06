package com.sda.balys.robert.weeding.database;

import com.sda.balys.robert.weeding.model.Visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by RENT on 2017-05-20.
 */

public class DataBaseCashe implements DataBase {

    private final Map<Integer,Visitor> mVisitors = new HashMap<>();
    public DataBaseCashe(){
//        Visitor visitor1 = new Visitor(1,"Jan","Nowak",2,Visitor.VisitorStatus.NO_RESPONSE);
//        Visitor visitor2 = new Visitor(2,"Bar","Kowal",1,Visitor.VisitorStatus.NO_RESPONSE);
//        Visitor visitor3 = new Visitor(3,"Bara","Dupa",2,Visitor.VisitorStatus.RESPONSE_OK);
//        Visitor visitor4 = new Visitor(4,"Mar","Ass",2,Visitor.VisitorStatus.RESPONSE_OK);
//        Visitor visitor5 = new Visitor(5,"Ta","Butt",1,Visitor.VisitorStatus.NO_RESPONSE);
//        Visitor visitor6 = new Visitor(6,"Kolo","P&P",2,Visitor.VisitorStatus.RESPONSE_NO);
//        Visitor visitor7 = new Visitor(7,"Bolo","Cloud",2,Visitor.VisitorStatus.RESPONSE_NO);
//
//        mVisitors.put(1,visitor1);
//        mVisitors.put(2,visitor2);
//        mVisitors.put(3,visitor3);
//        mVisitors.put(4,visitor4);
//        mVisitors.put(5,visitor5);
//        mVisitors.put(6,visitor6);
//        mVisitors.put(7,visitor7);
    }

    @Override
    public List<Visitor> getAllVisitors() {
        return new ArrayList<>(mVisitors.values());
    }

    @Override
    public void changeStatus(int vistorId, Visitor.VisitorStatus visitorStatus) {
        mVisitors.get(vistorId).setmVisitorStatus(visitorStatus);
    }

    @Override
    public void saveVisitor(Visitor visitor) {
        mVisitors.put(visitor.getId(),visitor);
    }

    @Override
    public Visitor getVisitor(int visitorID) {

        return mVisitors.get(visitorID);
    }

    @Override
    public void updateVisitor(Visitor visitor) {

    }


}
