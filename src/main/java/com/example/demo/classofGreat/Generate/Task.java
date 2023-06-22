package com.example.demo.classofGreat.Generate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.demo.classofGreat.Store;
import com.example.demo.classofGreat.StoreOperator;
import com.example.demo.classofGreat.Stuff;
import com.example.demo.classofGreat.StuffOperator;
import com.example.demo.mapping.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
public class Task implements Runnable {
    public static ArrayList<ArrayList<Integer>> tableof_class=new ArrayList<>();
    private String jsonString;
    public String name;
    private int Store_id;
    public Task(String s, int t) {
        name = s;
        Store_id = t;
    }

    public void run() {

        /**
         * GA算法测试
         */
        System.out.println("智能排班系统欢迎你！");
        Scanner sc = new Scanner(System.in);
        //stuff_arr员工集合

        /**
         * 偏好输入规则：
         *
         * 工作日偏好 1、3、4、5、7
         * 工作时间偏好 9:30-21:30 9:00-21:00
         * 日工作时间偏好 6小时
         * 无偏好，正常工作
         */
        HashMap<String, Stuff> stuff_arr = StuffOperator.stuff_arrInit();
        //Stuff a = new Stuff("张三", "店长", "173028", "qq.com", "1", "工作日偏好 1、3、4、5、6");
        //Stuff a = new Stuff("张三", "店长", "173028", "qq.com", "1", "工作时间偏好 12:00-18:00");
        Stuff a = new Stuff("张三", "店长", "173028", "qq.com", "1", new ArrayList<>(List.of(new String[]{"日工作时间偏好 7小时","工作日偏好 1、3、4、5、6"})));
        Stuff b = new Stuff("李四", "王桑", "173029", "weixin.com", "1", new ArrayList<>(List.of(new String[]{"工作日偏好 1、3、4、5、6"})));
        Stuff c = new Stuff("王五", "经理", "173029", "weixin.com", "1", new ArrayList<>(List.of(new String[]{"无偏好，正常工作"})));
        Stuff d = new Stuff("老六", "经理", "173029", "weixin.com", "1", new ArrayList<>(List.of(new String[]{"无偏好，正常工作"})));
        Stuff e = new Stuff("田七", "经理", "173029", "weixin.com", "1", new ArrayList<>(List.of(new String[]{"无偏好，正常工作"})));
        Stuff f = new Stuff("丈八", "经理", "173029", "weixin.com", "1", new ArrayList<>(List.of(new String[]{"工作日偏好 1、3、4、5、6"})));
        Stuff g = new Stuff("小松", "经理", "173029", "weixin.com", "1", new ArrayList<>(List.of(new String[]{"工作日偏好 1、2、5、6"})));
        Stuff h = new Stuff("小美", "经理", "173029", "weixin.com", "1", new ArrayList<>(List.of(new String[]{"无偏好，正常工作"})));
        Stuff i = new Stuff("金发妹", "经理", "173029", "weixin.com", "1", new ArrayList<>(List.of(new String[]{"无偏好，正常工作"})));
        Stuff j = new Stuff("哈哈", "经理", "173029", "weixin.com", "1", new ArrayList<>(List.of(new String[]{"无偏好，正常工作"})));
        Stuff k = new Stuff("12", "经理", "173029", "weixin.com", "1", new ArrayList<>(List.of(new String[]{"无偏好，正常工作"})));
        Stuff l = new Stuff("23", "经理", "173029", "weixin.com", "1", new ArrayList<>(List.of(new String[]{"无偏好，正常工作"})));
        Stuff m = new Stuff("34", "经理", "173029", "weixin.com", "1", new ArrayList<>(List.of(new String[]{"工作时间偏好 10:30-21:00"})));
        Stuff n = new Stuff("45", "经理", "173029", "weixin.com", "1", new ArrayList<>(List.of(new String[]{"工作时间偏好 12:00-19:30"})));
        Stuff o = new Stuff("56", "经理", "173029", "weixin.com", "1", new ArrayList<>(List.of(new String[]{"无偏好，正常工作"})));

        stuff_arr.put(a.getName(),a);
        stuff_arr.put(b.getName(),b);
        stuff_arr.put(c.getName(),c);
        stuff_arr.put(d.getName(),d);
        stuff_arr.put(e.getName(),e);
        stuff_arr.put(f.getName(),f);
        stuff_arr.put(g.getName(),g);
        stuff_arr.put(h.getName(),h);
        stuff_arr.put(i.getName(),i);
        stuff_arr.put(j.getName(),j);
        stuff_arr.put(k.getName(),k);
        stuff_arr.put(l.getName(),l);
        stuff_arr.put(m.getName(),m);
        stuff_arr.put(n.getName(),n);
        stuff_arr.put(o.getName(),o);
        //店集合
        Store store1 = new Store("1","张家界",100.0,stuff_arr);
        StuffOperator.allStuffInformation(stuff_arr);//显示员工

        HashMap<String,Store> store_arr = StoreOperator.store_arrInit();
        store_arr.put(store1.getName(),store1);
        StoreOperator.allStoreInformation(store_arr);//显示店集
        WeakHashMap<Integer,Object> map = new WeakHashMap<>();
        WeakHashMap<Integer,Object> map2 = new WeakHashMap<>();
        WeakHashMap<String,Object> map212 = new WeakHashMap<>();
        //已存在店、员工
        //员工染色体已自动初始化

        /**
         * 需要排班的天数，这里只是为了获取是星期几
         */
        int DAY1=30;//

        ArrayList<Chromo>answer;
        for(int day=1;day<=DAY1;day++)
        {
            map2 =new WeakHashMap<Integer,Object>();
            answer=store1.use_GA(day);//哪天
            for(int len=0;len<answer.size();len++)
            {
                Chromo is=answer.get(len);
                map2.put(len,new Chromo(is));
            }

            map.put(day,new WeakHashMap<Integer,Object>(map2));
            tableof_class.clear();
            map2.clear();
        }
        map212.put("List",map);
        jsonString = JSON.toJSONString(map212,SerializerFeature.DisableCircularReferenceDetect);
        System.out.println(jsonString);
        //用的是一天来排的班
        //StoreOperator.store_add(store_arr);//add出问题
        /**
         * 以下为增添改查代码测试
         */
        //HashMap<String, Stuff> stuff_arr = StuffOperator.stuff_arrInit();



        /*Stuff a = new Stuff("张三", "店长", "173028", "qq.com", 1, 1);
        Stuff b = new Stuff("李四", "经理", "173029", "weixin.com", 1, 2);
        stuff_arr.put(a.getName(),a);
        stuff_arr.put(b.getName(),b);
        Store store1 = new Store("Z店","张家界",13.14,stuff_arr);
        StuffOperator.allStuffInformation(stuff_arr);
        */



        /**
         * Stuff功能测试
         */
        //StuffOperator.stuff_add(stuff_arr);//静态方法
        //StuffOperator.stuff_serchByName(stuff_arr);
        //StuffOperator.stuff_modifyByName(stuff_arr);
        //StuffOperator.stuff_delByName(stuff_arr);




        /**
         * Store功能测试
         */
        /*HashMap<String,Store> store_arr = StoreOperator.store_arrInit();
        store_arr.put(store1.getName(),store1);
        StoreOperator.allStoreInformation(store_arr);

        Store store2 = new Store("Y店","陈家界",5.20,stuff_arr);
        store_arr.put(store2.getName(),store2);
        StoreOperator.allStoreInformation(store_arr);

        Store store3 = new Store("X店","朱家界",1314.520,stuff_arr);
        store_arr.put(store3.getName(),store3);
        StoreOperator.allStoreInformation(store_arr);


        StoreOperator.store_add(store_arr);
        StoreOperator.store_serchByName(store_arr);
        StoreOperator.store_modifyByName(store_arr);
        StoreOperator.stuff_delByName(store_arr);
        */


    }
    public String getReturnValue(){
        return jsonString;
    }
}