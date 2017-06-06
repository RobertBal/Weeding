package com.sda.balys.robert.weeding.repository;

import com.sda.balys.robert.weeding.model.Visitor;

import java.util.List;

/**
 * Created by RENT on 2017-05-20.
 */

public interface PeopleRepository {
    List<Visitor> getAllVisitors();
    void changeStatus(int visitorId, Visitor.VisitorStatus status);
    void saveVisitor(Visitor visitor);
    Visitor getVisitor(int visitorID);
    void updateVisitor(Visitor visitor);
}
