package com.automationtestingrestassured.utils;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import javax.annotation.meta.When;
import java.io.File;

public class FillowUtil {
    static String FILEPATH="src/main/java/com/automationtestingrestassured/resource/FillowExcel.xlsx";

    public static String fetchDatafromExcel(String sheetName,String id,String field){
        String value=null;
        Fillo f=new Fillo();
        try {
            Connection c=f.getConnection(FILEPATH);
            String query="Select *from "+ sheetName + " " +" where ID='"+ id + "'";
            Recordset recordset=c.executeQuery(query);
            while (recordset.next()){
                value=recordset.getField(field);
            }
            System.out.println(value);
            return value;
        } catch (FilloException e) {
            throw new RuntimeException(e);
        }

    }
}
