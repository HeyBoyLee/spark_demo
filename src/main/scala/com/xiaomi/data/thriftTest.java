package com.xiaomi.data;

import org.apache.thrift.TDeserializer;
import org.apache.thrift.TException;
import org.apache.thrift.TSerializer;
import org.apache.thrift.protocol.TBinaryProtocol;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by guohao on 10/20/16.
 */

public class thriftTest {
    private static final Random sRandom = new Random();

    public static void main(String[] args) throws IOException, TException {

        //构造school对象
        String[] nameArr = {"张三", "李四", "王五", "赵6", "王祖贤", "赵敏", "漩涡鸣人", "诺维茨基", "邓肯", "克莱尔丹尼斯", "长门", "弥彦", "威少"};
        int nameArrLength = nameArr.length;
        school sc = new school();
        sc.setSchoolName("哈哈哈哈哈哈");
        sc.setAge(12);
        List<String> l = new ArrayList<String>();
        for (int i = 0; i < 100; i++) {
            l.add("专业" + i);
        }
        sc.setZhuanye(l);


        List<banji> allBanji = new ArrayList<banji>();
        for (int i = 0; i < 1000; i++) {
            banji bj = new banji();
            bj.setBanjiName("班级" + i);
            List allStuents = new ArrayList<student>();
            for (int j = 0; j < 1000; j++) {
                allStuents.add(
                        new student(
                                nameArr[sRandom.nextInt(nameArrLength)],
                                ((sRandom.nextInt(2) == 0) ? "男" : "女"),
                                (sRandom.nextInt(10) + 18)
                        )
                );
            }
            bj.setAllStudents(allStuents);
            allBanji.add(bj);
        }
        sc.setAllBanji(allBanji);

//        System.out.println(sc);


        //①序列化为thrift binary protocol
        final long startTime = System.currentTimeMillis();
        TSerializer serializer = new TSerializer(new TBinaryProtocol.Factory());

        /*
        for (int i = 0; i < 200; i++) {
            byte[] bytes = serializer.serialize(sc);
            //serializer.toString(sc);
        }
        final long endTime = System.currentTimeMillis();
        System.out.println("thrift序列化200次时间为" + (endTime - startTime) + "ms");
        */



/*
        //②序列化为json
        final long endTime7 = System.currentTimeMillis();
        for (int i = 0; i < 200; i++) {
            JSON.toJSONString(sc);
        }
        final long endTime2 = System.currentTimeMillis();
        System.out.println("json序列化200次时间为" + (endTime2 - endTime7) + "ms");
*/


        //准备待序列化的数据
        byte[] bytes = serializer.serialize(sc);

        System.out.println("****************************");
        System.out.println(bytes);
//        String jsonStr = JSON.toJSONString(sc);


        //③反序列thrift binary data
        final long endTime3 = System.currentTimeMillis();
        TDeserializer tDeserializer = new TDeserializer();
//        for (int i = 0; i < 200; i++) {
//            school sc1 = new school();
//            tDeserializer.deserialize(sc1, bytes);
////            System.out.println(sc1.toString());
//        }
//        final long endTime4 = System.currentTimeMillis();
//        System.out.println("thrift反序列化200次时间为" + (endTime4 - endTime3) + "ms");
        school sc1 = new school();
        tDeserializer.deserialize(sc1, bytes);
        System.out.println("=====================");
        System.out.println(sc1.toString());



/*
        //④反序列化json
        final long endTime5 = System.currentTimeMillis();
        for (int i = 0; i < 200; i++) {
            JSON.parseObject(jsonStr, sc.getClass());
//            System.out.println(JSON.parseObject(jsonStr, sc.getClass()).toString());
        }
        final long endTime6 = System.currentTimeMillis();
        System.out.println("json反序列化200次时间为" + (endTime6 - endTime5) + "ms");
*/


    }
}
