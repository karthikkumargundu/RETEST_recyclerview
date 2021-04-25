package com.karthik.karthik

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.util.*

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION)
{
    private val table = ("CREATE TABLE " + TABLE_USER + "(" + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT," + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_PASSWORD + " TEXT" + ")")
    private val DROP_USER_TABLE = "DROP TABLE IF EXISTS $TABLE_USER"
    override fun onCreate(dataB: SQLiteDatabase)
    {
        dataB.execSQL(table)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int)
    {
        db.execSQL(DROP_USER_TABLE)
        onCreate(db)
    }
    fun getAllUser(): ArrayList<User>
    {
        val column = arrayOf(COLUMN_USER_ID, COLUMN_USER_EMAIL, COLUMN_USER_NAME, COLUMN_USER_PASSWORD)
        val sortOrder = "$COLUMN_USER_NAME"
        val list =ArrayList<User>()
        val dataB = this.readableDatabase
        val cursor = dataB.query(TABLE_USER, column, null, null, null, null, sortOrder)
        if (cursor.moveToFirst())
        { do {
                val customer = User(name = cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)), email = cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)), password = cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)))
                list.add(customer)
                Log.i("COLUMN_USER_NAME",cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)))
                Log.i("COLUMN_USER_PASSWORD",cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)))
                Log.i("COLUMN_USER_EMAIL",cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)))
            } while (cursor.moveToNext()) }
        cursor.close()
        dataB.close()
        return list
    }
    fun addUser(user: User)
    {
        Log.i("", user.name +"//"+ user.email +"//"+ user.password)
        val dataB = this.writableDatabase
        val Cvalues = ContentValues()
        Cvalues.put(COLUMN_USER_NAME, user.name)
        Cvalues.put(COLUMN_USER_EMAIL, user.email)
        Cvalues.put(COLUMN_USER_PASSWORD, user.password)
        dataB.insert(TABLE_USER, null, Cvalues)
        dataB.close()
    }
    fun isUserExists(email: String): Boolean
    {
        val column = arrayOf(COLUMN_USER_ID)
        val dataB = this.readableDatabase
        val select = "$COLUMN_USER_EMAIL = ?"
        val args = arrayOf(email)
        val cursor = dataB.query(TABLE_USER, column, select, args, null, null, null)
        val cursorCount = cursor.count
        cursor.close()
        dataB.close()
        if (cursorCount > 0) {
            return true
        }
        return false
    }

    fun isUserExists(email: String, password: String): Boolean {
        val column = arrayOf(COLUMN_USER_ID)
        val dataB = this.readableDatabase
        val select = "$COLUMN_USER_EMAIL = ? AND $COLUMN_USER_PASSWORD = ?"
        val args = arrayOf(email, password)
        val cursor = dataB.query(TABLE_USER, column, select, args, null, null, null)
        val cursorCount = cursor.count
        cursor.close()
        dataB.close()
        if (cursorCount > 0) {
            return true
        }
        return false
    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "db"
        private const val TABLE_USER = "user"
        private const val COLUMN_USER_ID = "id"
        private const val COLUMN_USER_NAME = "name"
        private const val COLUMN_USER_EMAIL = "email"
        private const val COLUMN_USER_PASSWORD = "password"
    }
}