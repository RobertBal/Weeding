package com.sda.balys.robert.weeding.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.ContextCompat;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.sda.balys.robert.weeding.model.Contact;
import com.sda.balys.robert.weeding.model.Visitor;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by RENT on 2017-05-24.
 */

public class DataBaseOrmImpl extends OrmLiteSqliteOpenHelper implements DataBase{
    private static final String DATABASE_NAME = "weedingAppDatabase";
    private static final int DATABASE_VERSION = 1;

    private RuntimeExceptionDao<Visitor,Integer> visitors;

    public DataBaseOrmImpl(final Context contex) throws SQLException {
        super(contex,DATABASE_NAME,null,DATABASE_VERSION);
        //visitors = RuntimeExceptionDao.createDao(connectionSource,Visitor.class);
        visitors = getRuntimeExceptionDao(Visitor.class);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

        try {
            TableUtils.createTableIfNotExists(connectionSource,Visitor.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Visitor visitor1 = new Visitor(1,"Jan","Nowak",2,Visitor.VisitorStatus.NO_RESPONSE);
        Visitor visitor2 = new Visitor(2,"Bar","Kowal",1,Visitor.VisitorStatus.NO_RESPONSE);
        Visitor visitor3 = new Visitor(3,"Bara","Dupa",2,Visitor.VisitorStatus.RESPONSE_OK);
        Visitor visitor4 = new Visitor(4,"Mar","Ass",2,Visitor.VisitorStatus.RESPONSE_OK);
        Visitor visitor5 = new Visitor(5,"Ta","Butt",1,Visitor.VisitorStatus.NO_RESPONSE);
        Visitor visitor6 = new Visitor(6,"Kolo","P&P",2,Visitor.VisitorStatus.RESPONSE_NO);
        Visitor visitor7 = new Visitor(7,"Bolo","Cloud",2,Visitor.VisitorStatus.RESPONSE_NO);

        visitors.create(visitor1);
        visitors.create(visitor2);
        visitors.create(visitor3);
        visitors.create(visitor4);
        visitors.create(visitor5);
        visitors.create(visitor6);
        visitors.create(visitor7);

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {



    }

    @Override
    public List<Visitor> getAllVisitors() {

        return visitors.queryForAll();
    }

    @Override
    public void changeStatus(int vistorId, Visitor.VisitorStatus visitorStatus) {


        try {
            UpdateBuilder<Visitor,Integer> query = visitors.updateBuilder();
            Where where = query.where();
            where.eq("id",vistorId);
            query.updateColumnValue("status",visitorStatus);
            query.update();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void saveVisitor(Visitor visitor) {
        visitors.create(visitor);

    }

    @Override
    public Visitor getVisitor(int vistitorID) {
        QueryBuilder<Visitor,Integer> queryBuilder = visitors.queryBuilder();
        Where where = queryBuilder.where();
        try {
            where.eq("id",vistitorID);
            return queryBuilder.queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    public void updateVisitor(Visitor visitor) {

        UpdateBuilder<Visitor,Integer> updateVisitor = visitors.updateBuilder();
        Where where = updateVisitor.where();
        try {
            where.eq("id",visitor.getId());
            updateVisitor.updateColumnValue("status",visitor.getmVisitorStatus());
            updateVisitor.updateColumnValue("name",visitor.getmName());
            updateVisitor.updateColumnValue("surname",visitor.getmSurname());
            updateVisitor.updateColumnValue("additional_person",visitor.getmAdditionalPerson());
            updateVisitor.update();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
