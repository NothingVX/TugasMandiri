package com.example.buangan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final int DB_VERSION=1;
    private static final String DB_NAME="UserInfo";
    private static final String TABLE_NAME="tbl_item";
    private static final String KEY_NAME="judul";
    private static final String KEY_DESC="desk";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String createItemTable="Create Table "+TABLE_NAME+" ( "+KEY_NAME+" TEXT PRIMARY KEY, "+KEY_DESC+" TEXT )";
        db.execSQL(createItemTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql=("drop table if exists " +TABLE_NAME);
        db.execSQL(sql);
        onCreate(db);
    }

    public void  insert (ItemBean itemBean){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,itemBean.getJudul());
        values.put(KEY_DESC,itemBean.getDesc());

        db.insert(TABLE_NAME,null,values);
    }

    public List<ItemBean> selectUserData(){
        ArrayList<ItemBean> userList=new ArrayList<ItemBean>();

        SQLiteDatabase db = getReadableDatabase();
        String[] columns={KEY_NAME,KEY_DESC};

        Cursor c = db.query(TABLE_NAME,columns,null,null,null,null,null);

        while (c.moveToNext()){
            String judul = c.getString(0);
            String desc = c.getString(1);

            ItemBean itemBean = new ItemBean();
            itemBean.setJudul(judul);
            itemBean.setDesc(desc);
            userList.add(itemBean);
        }

        return userList;
    }

    public void delete(String name){
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = KEY_NAME+"='"+name+"'";
        db.delete(TABLE_NAME,whereClause,null);
    }

    public void update(ItemBean itemBean){
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_DESC,itemBean.getDesc());
        String whereClause = KEY_NAME+"='"+itemBean.getJudul()+"'";
        db.update(TABLE_NAME,values,whereClause,null);
    }

}
