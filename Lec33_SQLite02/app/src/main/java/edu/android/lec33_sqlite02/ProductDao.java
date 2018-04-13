package edu.android.lec33_sqlite02;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

import static edu.android.lec33_sqlite02.Product.ProductEnity.*;

/**
 * Created by user on 2018-03-27.
 * SQLiteDatabase 클래스의 insert, update, delete, quary를 사용해서 기능들을 작성
 */

public class ProductDao {
    //TODO
    private static SQLiteDatabase database;//데이터 베이스를 멤버 변수로
    private static ProductDao instance = null;

    private ProductDao(Context context){
        ProductDbhelper helper = new ProductDbhelper(context);
        database = helper.getWritableDatabase();

    }
    public static ProductDao getInstance(Context context){
        if(instance == null){
            instance = new ProductDao(context);
        }
        return instance;
    }

    public long insert(Product product){
        //insert하기 위해서는 상품 이름, 가격, 설명이 필요
        //SQL 문장 형식 >>insert into products (pname, price, description) values (?,?,?)
        //insert메소드는 DB가 가지고 있다

        long result = 0;

        ContentValues values = new ContentValues();
        values.put(COL_NAME, product.getProductname());
        values.put(COL_PRICE, product.getPrice());
        values.put(COL_DESC, product.getDescription());

        result = database.insert(TABLE_NAME,null, values);

        return result;
    }

    //product 테이블 전체 레코드 검색
    public List<Product> select(){
        List<Product> list = new ArrayList<>();

        //select * from Products order by _id(id의 오름차순으로 )
        Cursor cursor = database.query(TABLE_NAME,   //검색할 테이블 이름
                                        null, //검색할 컬럼 이름, null = 전체 검색하겠다
                                        null,    //where구문, 만약 조건이 없다면 null로 써도 OK
                                        null, //where구문을  완성시기틑 값들, where구문이 null이면 여기도 null
                                        null,  //groupBy구문
                                        null,    // having 구문
                                        _ID );  // order by 구문에서 사용하는 컬럼이름
        while (cursor.moveToNext()){
            //1. ID꺼내기
            int id = cursor.getInt(0);
            String pname =cursor.getString(1);
            int price = cursor.getInt(2);
            String desc = cursor.getString(3);
            Product product = new Product(id,pname, price,desc);
            list.add(product);
        }
        cursor.close();



        return list;

    }

    //ID로 검색하는 - ID는 primary key이기 때문에 데이터가 없거나 있어야 한다
    //있을 경우 product가 나올 것


    public Product select(int id){
        Product product = null;

        //select * from Products where _id = ??

        String selection = _ID + " =?";
        String[] selectionArgs = { String.valueOf(id)};//원소 하나짜리 배열

        Cursor cursor = database.query( TABLE_NAME,
                                        null,
                                        selection,
                                        selectionArgs,
                                        null,
                                        null,
                                         _ID);

        if(cursor.moveToNext()){
            String pname = cursor.getString(1);
            int price = cursor.getInt(2);
            String desc = cursor.getString(3);
            product = new Product(id, pname, price,desc);
        }
        cursor.close();

        return product;
    }

    //살품 이름으로 검색 >> 비슷한 이름 가지고 있는건 다 검색, 결과가 여러건이 나올 수 있다
//    public List<Product> select(String pname){
    public List<Product> select(String keyword){

        List<Product> list = new ArrayList<>();

        //select * from products where pname like ? order by _id
        //    필요한 것 >> 테이블 이름, 컬럼이름, selection, selectionArgs, order by

        //select * from products where pname like '%key%' or description like'%key%' order by _id
//        String selection = COL_NAME + " like ?";
//        String[] selecrionArgs = { "%" + pname + "%" };//원소 1개짜리 배열 - ?가 하나이므로 원소도 1개가 필요
        String selection = COL_NAME + "like ? or " + COL_DESC + ";ike ?";
        String [] selecrionArgs = { "%" + keyword +"%", "%" + keyword +"%" };


        Cursor cursor = database.query(TABLE_NAME,
                                       null,
                                       selection,
                                       selecrionArgs,
                                       null,
                                       null,
                                       _ID);

        while (cursor.moveToNext()){

            int productId = cursor.getInt(0);
            String productname = cursor.getString(1);
            int price = cursor.getInt(2);
            String desc = cursor.getString(4);
            Product product = new Product(productId, productname, price, desc);
            list.add(product);

        }
        cursor.close();
        return list;
    }


}





















