package com.example.demo.Tables;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

@Data
public class Company implements Serializable {
        String a;
        String b;
        String c;
        String d;
        String e;
        String f;
        Company(ArrayList<String>arr)
        {
            a=arr.get(0);
            b=arr.get(1);
            c=arr.get(2);
            d=arr.get(3);
            e=arr.get(4);
            f=arr.get(5);
        }
    @Override
    public Object clone() {
        Company company = null;
        try{
            company = (Company) super.clone();
        }catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return company;
    }
}
