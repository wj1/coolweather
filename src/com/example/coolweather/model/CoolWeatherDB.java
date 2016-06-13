package com.example.coolweather.model;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.coolweather.db.CoolWeatherOpenHelper;

public class CoolWeatherDB {
	
	//���ݿ�����
	public static final String DB_NAME="coolweather" ;
	
	//
	
	//���ݿ�汾
	public static final int VERSION=1;
	
	private static CoolWeatherDB coolweatherdb;
	private SQLiteDatabase db;
	
	//�����췽��˽�л�
	
	private CoolWeatherDB(Context context){
		CoolWeatherOpenHelper dbhelper=new CoolWeatherOpenHelper(context,DB_NAME,null,VERSION);
		db=dbhelper.getWritableDatabase();
	}

	//���COOLWEATHERDBʵ��
	
	public synchronized static CoolWeatherDB getInstance(Context context){//��������ֻ����һ���ֳ����з���
		if(coolweatherdb==null){
			coolweatherdb=new CoolWeatherDB(context);
		}
		return coolweatherdb;
	}
	
	//��provinceʵ���洢�����ݿ���
	public void saveprovince(Province province){
		ContentValues values=new ContentValues();
		values.put("province_name", province.getProvincename());
		values.put("province_code", province.getProvincecode());
		db.insert("Province", null, values);
		
	}
	
	//�����ݿ��ȡȫ����ʡ��Ϣ
	public List<Province> loadProvince(){
		List<Province> list=new ArrayList<Province>();
		Cursor cursor=db.query("Province", null, null, null, null, null, null);
		if(cursor.moveToFirst()){
			do{
				Province province=new Province();
				province.setId(cursor.getInt(cursor.getColumnIndex("id")));
				province.setProvincename(cursor.getString(cursor.getColumnIndex("province_name")));
				province.setProvincecode(cursor.getString(cursor.getColumnIndex("province_code")));
				list.add(province);
				
				
			}while(cursor.moveToNext());
		}
		return list;
		
	}
	
	//��city���ݴ浽���ݿ�
	
	public void savecity(City city){
		if(city!=null){
			ContentValues values=new ContentValues();
			values.put("city_name", city.getCityname());
			values.put("city_code", city.getCitycode());
			values.put("province_id", city.getProvinceid());
			db.insert("City", null, values);
		}
	}
	
	//�����ݿ��ȡ������Ϣ
	public List<City> loadCities(int provinceId){
		List<City> list=new ArrayList<City>();
		Cursor cursor=db.query("City", null, "province_id=?", new String[]{String.valueOf(provinceId)}, null, null, null);
		if(cursor.moveToFirst()){
			do{
				City city=new City();
				city.setId(cursor.getInt(cursor.getColumnIndex("id")));
				city.setCityname(cursor.getString(cursor.getColumnIndex("city_name")));
				city.setCitycode(cursor.getString(cursor.getColumnIndex("city_code")));
				city.setProvinceid(provinceId);
				list.add(city);
			}while(cursor.moveToNext());
		}
		return list;
		
	}
	
	
	//��county�洢�����ݿ�
	public void savecounty(County county){
		if(county!=null){
			ContentValues values=new ContentValues();
			values.put("county_name", county.getCountyname());
			values.put("county_code", county.getCountycode());
			values.put("county_id", county.getId());
			db.insert("County", null, values);
		}
		
	}
	
	//�����ݿ��ȡcounty��Ϣ
	public List<County> loadCounties(int cityId){
		List<County> list=new ArrayList<County>();
		Cursor cursor=db.query("County", null, "city_id=?", new String[]{String.valueOf(cityId)}, null, null, null);
		if(cursor.moveToFirst()){
			do{
				County county=new County();
				county.setId(cursor.getInt(cursor.getColumnIndex("id")));
				county.setCountyname(cursor.getString(cursor.getColumnIndex("county_name")));
				county.setCountycode(cursor.getString(cursor.getColumnIndex("county_code")));
				county.setCityid(cityId);
				list.add(county);
			}while(cursor.moveToNext());
			
		}
		return list;
		
	}
	
	
}
