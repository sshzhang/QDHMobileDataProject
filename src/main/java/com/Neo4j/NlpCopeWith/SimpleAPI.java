package com.Neo4j.NlpCopeWith;

import edu.hit.ir.ltp4j.Segmentor;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.*;

public class SimpleAPI {//分词工具类

    //停用词
    public static List<String> shopName;

    public static List<String> shopPlace;

    public static  Segmentor segmentor;
    static {
        segmentor = new Segmentor();
        if (segmentor.create("A:/resources/videoInformation/3.4.0/ltp_data_v3.4.0/ltp_data_v3.4.0/cws.model") < 0) {
            throw new RuntimeException("fail to load cws model");
        }
        shopName = Arrays.asList("千岛湖", "杭州", "杭州市");
        shopPlace = Arrays.asList("淳安", "淳安县");
    }

//线下数据
    public static  List<String>  jniLtpfenchi(String str) {
        List<String> words = new ArrayList<>();
        segmentor.segment(str, words);
        System.out.println(words);
        return words;
    }

    public static void main(String[] args) throws IOException {

        //千岛湖绿城度假酒店:最相似的携程酒店是:千岛湖温馨岛蝶来度假酒店(原浙旅度假酒店)  且相似度为1.25
        List<String> text1 = jniLtpfenchi("【 千岛湖镇】千岛湖镇温馨岛，近千岛湖县政府，中心湖区，骑行绿道，水下古城文化科技主题乐园，进贤湾水上乐园。 地图\"");
        List<String> text2 = jniLtpfenchi("位于千岛湖风景区，淳安梦姑路298号，在猴岛附近\uE636查看地图");
        double similitytext = Similitytext(text1, text2,"shopName");
        System.out.println(similitytext);
        //C:/Users/xiujiezhang/DailyProject/QDHMobileDataProject
        writeTheDataResultToFile("./src/main/resources/result.txt", similitytext+"");
    }

    //线上云数据
    public static  String  textConnection( String text ) throws IOException {
        String api_key = "91I9I0G5r2TUjnJdPlQEwZmOVSRpGkxCdCKGYVKT";
        String pattern = "ws";
        String format  = "plain";
//        String text    = "千岛湖润和建国度假酒店";
        text = text.replace(" ", "");
        text = URLEncoder.encode(text, "utf-8");

        URL url     = new URL("https://api.ltp-cloud.com/analysis/?"
                + "api_key=" + api_key + "&"
                + "text="    + text    + "&"
                + "format="  + format  + "&"
                + "pattern=" + pattern);
        URLConnection conn = url.openConnection();
        conn.connect();

        BufferedReader innet = new BufferedReader(new InputStreamReader(
                conn.getInputStream(),
                "utf-8"));
        String line=null;
        while ((line = innet.readLine())!= null) {
            System.out.println(line);
            break;
        }

        innet.close();
        return line;
    }

    //text1  [千岛湖, 36, 都, 斯维登, 度假, 酒店]
    public static double Similitytext(List<String> text1, List<String> text2,String type) {
         //构建向量
        HashSet<String> sets = new HashSet<>();
        sets.addAll(text1);
        sets.addAll(text2);
        if (type.equals("shopName")) {
            sets.removeAll(shopName);
        }else{
            sets.removeAll(shopPlace);
        }
        int[] vect1 = new int[sets.size()];
        int[] vect2 = new int[sets.size()];
        Iterator<String> iterator = sets.iterator();
        int i = 0;
         while (iterator.hasNext()) {
             String word = iterator.next();
             vect1[i] = OccurenceTimes(text1, word);
             vect2[i++] = OccurenceTimes(text2, word);
        }
        int dotsum = 0;
        int vect1sum = 0;
        int vect2sum = 0;
        //计算余弦相似度
        for(int j=0;j<vect1.length;j++) {
            dotsum += vect1[j] * vect2[j];
            vect1sum += vect1[j] * vect1[j];
            vect2sum += vect2[j] * vect2[j];
        }
        return dotsum / (Math.sqrt(vect1sum) * Math.sqrt(vect2sum));
    }

    public static int OccurenceTimes(List<String> text1,String word) {
        int currtimes = 0;//出现次数
        for (String text : text1) {
            if (word.equals(text)) {
                currtimes++;
            }
        }
        return  currtimes;
    }


    public static boolean writeTheDataResultToFile(String filepath,String fileContent) {//写入数据到文件

        FileWriter wi = null;
        try {
            wi = new FileWriter(new File(filepath), true);
            wi.write(fileContent + "\n");
            wi.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }finally {
            if(wi!=null)
                try {
                    wi.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return true;

    }

}