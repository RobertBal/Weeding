package com.sda.balys.robert.weeding.database;

import com.sda.balys.robert.weeding.model.Visitor;

import java.util.List;

/**
 * Created by RENT on 2017-05-20.
 */

public interface DataBase {

    List<Visitor> getAllVisitors();
     void changeStatus(int vistorId, Visitor.VisitorStatus visitorStatus);


    void saveVisitor(Visitor visitor);
    Visitor getVisitor(int vistitorID);
    void updateVisitor(Visitor visitor);
}
