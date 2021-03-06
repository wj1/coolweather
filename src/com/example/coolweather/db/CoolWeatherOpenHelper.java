package com.example.coolweather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CoolWeatherOpenHelper extends SQLiteOpenHelper{
	
	//省建表语句
	public static final String CREATE_PROVINCE="create table Province"+
			"id integer primary key autoincrement"+//序列号
			"province_name text"+//省名称
			"province_code test";//省级代号
	
	public static final String CREATE_CITY="create table City"+
			"id integer primary key autoincrement"+
			"city_name text"+
			"city_code text"+
			"province_id integer";//市与省关联的外键
	
	public static final String CREATE_COUNTY="create table County"+
			"id integer primary key autoincrement"+
			"county_name text"+
			"county_code text"+
			"city_id integer";
	
			

	public CoolWeatherOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_CITY);
		db.execSQL(CREATE_COUNTY);
		db.execSQL(CREATE_PROVINCE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
