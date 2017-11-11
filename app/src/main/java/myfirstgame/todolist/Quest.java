package myfirstgame.todolist;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;

import static myfirstgame.todolist.DBHelper.QUEST_COLUMN_CATEGORY;
import static myfirstgame.todolist.DBHelper.QUEST_COLUMN_COMPLETED;
import static myfirstgame.todolist.DBHelper.QUEST_COLUMN_DATE;
import static myfirstgame.todolist.DBHelper.QUEST_COLUMN_DESC;
import static myfirstgame.todolist.DBHelper.QUEST_COLUMN_EXP;
import static myfirstgame.todolist.DBHelper.QUEST_COLUMN_ID;
import static myfirstgame.todolist.DBHelper.QUEST_COLUMN_NAME;
import static myfirstgame.todolist.DBHelper.QUEST_TABLE_NAME;

/**
 * Created by James on 09/11/2017.
 */

public class Quest {

    private String name;
    private String description;
    private Integer expValue;
    private Integer category;
    private int isCompleted;
    private String date;
    private int id;

    public Quest(String name, String description, Integer expValue, Integer category, String date) {
        this.name = name;
        this.description = description;
        this.expValue = expValue;
        this.category = category;
        this.isCompleted = 0;
        this.date = date;
    }

    public Quest(int id, String name, String description, Integer expValue, Integer category, String date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.expValue = expValue;
        this.category = category;
        this.isCompleted = 0;
        this.date = date;
    }

    //    Getters

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getExpValue() {
        return expValue;
    }

    public Integer getCategory() {
        return category;
    }

    public int isCompleted() {
        return isCompleted;
    }

    public String getDate() {
        return date;
    }

    //    Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setExpValue(Integer expValue) {
        this.expValue = expValue;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public void setCompleted(int completed) {
        isCompleted = completed;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean save(DBHelper dbHelper) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(QUEST_COLUMN_NAME, name);
        contentValues.put(QUEST_COLUMN_DESC, description);
        contentValues.put(QUEST_COLUMN_EXP, expValue);
        contentValues.put(QUEST_COLUMN_CATEGORY, category);
        contentValues.put(QUEST_COLUMN_DATE, date);

        db.insert(QUEST_TABLE_NAME, null, contentValues);
        return true;
    }

    public static ArrayList<Quest> all(DBHelper dbHelper){
        ArrayList<Quest> quests = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + QUEST_TABLE_NAME, null);
        while (cursor.moveToNext()) {
            Integer id = cursor.getInt(cursor.getColumnIndex(QUEST_COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(QUEST_COLUMN_NAME));
            String description = cursor.getString(cursor.getColumnIndex(QUEST_COLUMN_DESC));
            Integer expValue = cursor.getInt(cursor.getColumnIndex(QUEST_COLUMN_EXP));
            Integer category = cursor.getInt(cursor.getColumnIndex(QUEST_COLUMN_CATEGORY));
            String date = cursor.getString(cursor.getColumnIndex(QUEST_COLUMN_DATE));


            Quest quest = new Quest(id, name, description, expValue, category, date);
            quests.add(quest);
        }
        cursor.close();
        return quests;
    }

    public static ArrayList<Quest> allComplete(DBHelper dbHelper){
        ArrayList<Quest> quests = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = " WHERE completed = ?";
        Cursor cursor = db.rawQuery("SELECT * FROM " + QUEST_TABLE_NAME + query , new String[] {"1"});
        while (cursor.moveToNext()) {
            Integer id = cursor.getInt(cursor.getColumnIndex(QUEST_COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(QUEST_COLUMN_NAME));
            String description = cursor.getString(cursor.getColumnIndex(QUEST_COLUMN_DESC));
            Integer expValue = cursor.getInt(cursor.getColumnIndex(QUEST_COLUMN_EXP));
            Integer category = cursor.getInt(cursor.getColumnIndex(QUEST_COLUMN_CATEGORY));
            String date = cursor.getString(cursor.getColumnIndex(QUEST_COLUMN_DATE));


            Quest quest = new Quest(id, name, description, expValue, category, date);
            quests.add(quest);
        }
        cursor.close();
        return quests;
    }

    public static ArrayList<Quest> allIncomplete(DBHelper dbHelper){
        ArrayList<Quest> quests = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = " WHERE completed = ?";
        Cursor cursor = db.rawQuery("SELECT * FROM " + QUEST_TABLE_NAME + query , new String[] {"0"});
        while (cursor.moveToNext()) {
            Integer id = cursor.getInt(cursor.getColumnIndex(QUEST_COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(QUEST_COLUMN_NAME));
            String description = cursor.getString(cursor.getColumnIndex(QUEST_COLUMN_DESC));
            Integer expValue = cursor.getInt(cursor.getColumnIndex(QUEST_COLUMN_EXP));
            Integer category = cursor.getInt(cursor.getColumnIndex(QUEST_COLUMN_CATEGORY));
            String date = cursor.getString(cursor.getColumnIndex(QUEST_COLUMN_DATE));


            Quest quest = new Quest(id, name, description, expValue, category, date);
            quests.add(quest);
        }
        cursor.close();
        return quests;
    }

//    rawQuery("SELECT id, name FROM people WHERE name = ? AND id = ?", new String[] {"David", "2"});


    public static boolean deleteAll(DBHelper dbHelper){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM " + QUEST_TABLE_NAME);
        return true;
    }

    public static boolean delete(DBHelper dbHelper, Integer id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = " id = ?";
        String[] values = {id.toString()};
        db.delete(QUEST_TABLE_NAME, selection, values);
        return true;
    }

    public void update(DBHelper dbHelper) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db == null) {
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(QUEST_COLUMN_NAME, name);
        contentValues.put(QUEST_COLUMN_DESC, description);
        contentValues.put(QUEST_COLUMN_EXP, expValue);
        contentValues.put(QUEST_COLUMN_CATEGORY, category);
        contentValues.put(QUEST_COLUMN_COMPLETED, isCompleted);
        contentValues.put(QUEST_COLUMN_DATE, date);

        db.update(QUEST_TABLE_NAME, contentValues, QUEST_COLUMN_ID+"="+id, null);
        db.close();
    }
}
