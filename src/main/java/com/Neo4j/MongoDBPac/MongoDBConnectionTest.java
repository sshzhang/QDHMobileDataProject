package com.Neo4j.MongoDBPac;

import com.Neo4j.MongoDBPac.qunaerJavaBean.*;
import com.Neo4j.MongoDBPac.xiechengJavaBean.*;
import com.Neo4j.Neo4jtest;
import com.Neo4j.NlpCopeWith.SimpleAPI;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.logging.Filter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MongoDBConnectionTest {

    //台式机上的服务链接http://10.1.17.1
    public static MongoClient localServiceClient = remoteConnection("192.168.199.202");
    //远程服务器链接
//    public static MongoClient remoteServideClient = remoteConnection("10.1.17.15");
//    public static Neo4jtest neo4jtest = new Neo4jtest("bolt://10.1.17.1:7687", "neo4j", "09120912");

    public static MongoClient remoteConnection(String host) {
        try {
            ServerAddress serverAddress = new ServerAddress(host, 27017);
            List<ServerAddress> serverAddresses = new ArrayList<ServerAddress>();
            serverAddresses.add(serverAddress);
            //三个参数 用户名 数据库名称 密码
            MongoClient mongoClient = new MongoClient(serverAddresses);
            return mongoClient;
        } catch (Exception e) {
            System.out.println(e.getStackTrace() + ":" + e.getMessage());
            return null;
        }
    }

    //把document转换为JavaBean
    public static Object DocumentConvextToModel(String className, Document document) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        Set<String> strings = document.keySet();
        Class<?> aClass = Class.forName(className);
        Object dest = aClass.newInstance();
        for (String strKey : strings) {
            Field field = aClass.getField(strKey);
            FiledMethodAnnotation annotation = field.getAnnotation(FiledMethodAnnotation.class);
            String methodName = annotation.MethodName();
            Class<?> classType = annotation.ParameterType();
            Method method = aClass.getMethod(methodName, classType);
            if (String.class.isAssignableFrom(classType)) {
                method.invoke(dest, document.get(strKey).toString());
            } else if (int.class.isAssignableFrom(classType)) {
                method.invoke(dest, Integer.parseInt(document.get(strKey).toString()));
            } else if (float.class.isAssignableFrom(classType)) {
                method.invoke(dest, Float.parseFloat(document.get(strKey).toString()));
            }
        }

        return dest;
    }


    public static Object CovertTheStrToJavaBean(String className, String str) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> aClass = Class.forName(className);
        String pattern = "";
        FiledNameReplace annotation = aClass.getAnnotation(FiledNameReplace.class);
        String name = annotation.name();//类的名称
        Field[] fields = aClass.getFields();
        //构造JavaBean对象数据
        Object dest = aClass.newInstance();
        for (Field field : fields) {
            String fieldStringName = field.getName();
            FiledNameReplace annotation1 = field.getAnnotation(FiledNameReplace.class);
            String attributeKey = annotation1.name();
            //"(?<transportService>(?<=\"交通服务\":\\s\\[).*?(?=\"\\],\\s.*:))"
            if ("other".equals(fieldStringName)) {
                pattern = "(?<" + fieldStringName + ">(?<=\"" + attributeKey + "\":\\s).*?(?=,\\s\"))";
            } else {
                pattern = "(?<" + fieldStringName + ">(?<=\"" + attributeKey + "\":\\s\\[).*?(?=\\]))";
            }
            Pattern compile = Pattern.compile(pattern);
            Matcher matcher = compile.matcher(str);
            if (!matcher.find()) {
                continue;
            }
            String value = matcher.group(fieldStringName);
            System.out.println(annotation1.name() + "   " + value);
            String methodName = annotation1.MethodName();
            Method method = aClass.getMethod(methodName, String.class);
            method.invoke(dest, value);
        }
        return dest;
    }

    //转化 房屋类型信息为 JavaBean模式
    public static List<hotelHomeList> getHomeInfos(String shop_room_recommend_all) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        // String str = "{\"recommend_room\": {\"room_info_list\": [{\"床型\": \"双床\", \"宽带\": \"免费\", \"房价\": \"¥1058\", \"政策\": \"不可取消立即确认\", \"入住人数\": \"每间最多入住2人\", \"早餐\": \"\", \"满意度\": \"[无早]预订满意度 99%\"}], \"room_detail\": {\"浴室\": \"拖鞋、浴室化妆放大镜、24小时热水、免费洗漱用品(6样以上)、浴衣、浴缸、吹风机、浴室、洗浴间电话、淋浴\", \"房型\": \"豪华湖景双床房\", \"该房可无烟处理\": \"该房可无烟处理\", \"建筑面积\": \"42-46平方米\", \"楼层\": \"1-6层\", \"便利设施\": \"雨伞、书桌、熨衣设备、多种规格电源插座、房内保险箱、空调、闹钟、针线包、220V电压插座、遮光窗帘、手动窗帘、备用床具、床具\", \"床型\": \"2张1.35米单人床\", \"可加床\": \"RMB 200/床/间夜\"}}, \"all_room\": [{\"room_info_list\": [{\"床型\": \"多床\", \"宽带\": \"免费\", \"房价\": \"¥6888\", \"政策\": \"不可取消\", \"入住人数\": \"每间最多入住4人\", \"早餐\": \"每天四早\", \"满意度\": \"标准价预订满意度 99%\"}], \"room_detail\": {\"浴室\": \"拖鞋、浴室化妆放大镜、24小时热水、免费洗漱用品(6样以上)、浴衣、浴缸、吹风机、浴室、洗浴间电话、淋浴\", \"房型\": \"总统套房\", \"该房可无烟处理\": \"该房可无烟处理\", \"建筑面积\": \"260平方米\", \"楼层\": \"3层\", \"便利设施\": \"雨伞、书桌、熨衣设备、多种规格电源插座、房内保险箱、空调、闹钟、针线包、220V电压插座、遮光窗帘、手动窗帘、备用床具、床具\", \"床型\": \"2张2米双人床\", \"可加床\": \"RMB 200/床/间夜\"}}, {\"room_info_list\": [{\"床型\": \"大床\", \"宽带\": \"免费\", \"房价\": \"¥2188\", \"政策\": \"不可取消\", \"入住人数\": \"每间最多入住2人\", \"早餐\": \"每天双早\", \"满意度\": \"标准价预订满意度 99%\"}], \"room_detail\": {\"浴室\": \"拖鞋、浴室化妆放大镜、24小时热水、免费洗漱用品(6样以上)、浴衣、浴缸、吹风机、浴室、洗浴间电话、淋浴\", \"房型\": \"豪华湖景套房\", \"建筑面积\": \"110平方米\", \"楼层\": \"2层\", \"便利设施\": \"雨伞、书桌、熨衣设备、多种规格电源插座、房内保险箱、空调、闹钟、针线包、220V电压插座、遮光窗帘、手动窗帘、备用床具、床具\", \"床型\": \"1张2米双人床\", \"可加床\": \"RMB 200/床/间夜\", \"该房型可安排无烟楼层\": \"该房型可安排无烟楼层\"}}, {\"room_info_list\": [{\"床型\": \"大床\", \"宽带\": \"免费\", \"房价\": \"¥1988\", \"政策\": \"不可取消\", \"入住人数\": \"每间最多入住2人\", \"早餐\": \"每天双早\", \"满意度\": \"标准价预订满意度 99%\"}], \"room_detail\": {\"浴室\": \"拖鞋、浴室化妆放大镜、24小时热水、免费洗漱用品(6样以上)、浴衣、浴缸、吹风机、浴室、洗浴间电话、淋浴\", \"房型\": \"豪华景观套房\", \"建筑面积\": \"90平方米\", \"楼层\": \"2层\", \"便利设施\": \"雨伞、书桌、熨衣设备、多种规格电源插座、房内保险箱、空调、闹钟、针线包、220V电压插座、遮光窗帘、手动窗帘、备用床具、床具\", \"床型\": \"1张2米双人床\", \"可加床\": \"RMB 200/床/间夜\", \"该房型可安排无烟楼层\": \"该房型可安排无烟楼层\"}}, {\"room_info_list\": [{\"床型\": \"大床\", \"宽带\": \"免费\", \"房价\": \"¥1188\", \"政策\": \"不可取消\", \"入住人数\": \"每间最多入住2人\", \"早餐\": \"每天双早\", \"满意度\": \"[含早](超值抢)预订满意度 99%\"}], \"room_detail\": {\"浴室\": \"拖鞋、浴室化妆放大镜、24小时热水、免费洗漱用品(6样以上)、浴衣、浴缸、吹风机、浴室、洗浴间电话、淋浴\", \"房型\": \"建国泳池大床房\", \"该房可无烟处理\": \"该房可无烟处理\", \"建筑面积\": \"42-46平方米\", \"楼层\": \"1层\", \"便利设施\": \"雨伞、书桌、熨衣设备、多种规格电源插座、房内保险箱、空调、闹钟、针线包、220V电压插座、遮光窗帘、手动窗帘、备用床具、床具\", \"床型\": \"1张2米双人床\", \"可加床\": \"RMB 200/床/间夜\"}}, {\"room_info_list\": [{\"床型\": \"双床\", \"宽带\": \"免费\", \"房价\": \"¥968\", \"政策\": \"不可取消\", \"入住人数\": \"每间最多入住2人\", \"早餐\": \"每天双早\", \"满意度\": \"[含早](超值抢)预订满意度 99%\"}], \"room_detail\": {\"浴室\": \"拖鞋、浴室化妆放大镜、24小时热水、免费洗漱用品(6样以上)、浴衣、浴缸、吹风机、浴室、洗浴间电话、淋浴\", \"房型\": \"豪华园景双床房\", \"该房可无烟处理\": \"该房可无烟处理\", \"建筑面积\": \"42-46平方米\", \"楼层\": \"1-5层\", \"便利设施\": \"雨伞、书桌、熨衣设备、多种规格电源插座、房内保险箱、空调、闹钟、针线包、220V电压插座、遮光窗帘、手动窗帘、备用床具、床具\", \"床型\": \"2张1.35米单人床\", \"可加床\": \"RMB 200/床/间夜\"}}, {\"room_info_list\": [{\"床型\": \"大床\", \"宽带\": \"免费\", \"房价\": \"¥1058\", \"政策\": \"不可取消\", \"入住人数\": \"每间最多入住2人\", \"早餐\": \"每天双早\", \"满意度\": \"[含早](超值抢)预订满意度 99%\"}], \"room_detail\": {\"浴室\": \"拖鞋、浴室化妆放大镜、24小时热水、免费洗漱用品(6样以上)、浴衣、浴缸、吹风机、浴室、洗浴间电话、淋浴\", \"房型\": \"豪华景观大床房\", \"该房可无烟处理\": \"该房可无烟处理\", \"建筑面积\": \"42-46平方米\", \"楼层\": \"1-5层\", \"便利设施\": \"雨伞、书桌、熨衣设备、多种规格电源插座、房内保险箱、空调、闹钟、针线包、220V电压插座、遮光窗帘、手动窗帘、备用床具、床具\", \"床型\": \"1张2米双人床\", \"可加床\": \"RMB 200/床/间夜\"}}, {\"room_info_list\": [{\"床型\": \"双床\", \"宽带\": \"免费\", \"房价\": \"¥908\", \"政策\": \"不可取消\", \"入住人数\": \"每间最多入住2人\", \"早餐\": \"\", \"满意度\": \"[无早]预订满意度 99%\"}], \"room_detail\": {\"浴室\": \"拖鞋、浴室化妆放大镜、24小时热水、免费洗漱用品(6样以上)、浴衣、浴缸、吹风机、浴室、洗浴间电话、淋浴\", \"房型\": \"豪华景观双床房\", \"该房可无烟处理\": \"该房可无烟处理\", \"建筑面积\": \"42-46平方米\", \"楼层\": \"1-5层\", \"便利设施\": \"雨伞、书桌、熨衣设备、多种规格电源插座、房内保险箱、空调、闹钟、针线包、220V电压插座、遮光窗帘、手动窗帘、备用床具、床具\", \"床型\": \"2张1.35米单人床\", \"可加床\": \"RMB 200/床/间夜\"}}, {\"room_info_list\": [{\"床型\": \"大床\", \"宽带\": \"免费\", \"房价\": \"¥1058\", \"政策\": \"不可取消\", \"入住人数\": \"每间最多入住2人\", \"早餐\": \"\", \"满意度\": \"[无早]预订满意度 99%\"}], \"room_detail\": {\"浴室\": \"拖鞋、浴室化妆放大镜、24小时热水、免费洗漱用品(6样以上)、浴衣、浴缸、吹风机、浴室、洗浴间电话、淋浴\", \"房型\": \"豪华湖景大床房\", \"该房可无烟处理\": \"该房可无烟处理\", \"建筑面积\": \"42-46平方米\", \"楼层\": \"1-6层\", \"便利设施\": \"雨伞、书桌、熨衣设备、多种规格电源插座、房内保险箱、空调、闹钟、针线包、220V电压插座、遮光窗帘、手动窗帘、备用床具、床具\", \"床型\": \"1张2米双人床\", \"可加床\": \"RMB 200/床/间夜\"}}, {\"room_info_list\": [{\"床型\": \"大床\", \"宽带\": \"免费\", \"房价\": \"¥1558\", \"政策\": \"不可取消\", \"入住人数\": \"每间最多入住2人\", \"早餐\": \"每天双早\", \"满意度\": \"[含早](超值抢)预订满意度 99%\"}], \"room_detail\": {\"浴室\": \"拖鞋、浴室化妆放大镜、24小时热水、免费洗漱用品(6样以上)、浴衣、浴缸、吹风机、浴室、洗浴间电话、淋浴\", \"房型\": \"行政湖景大床房\", \"该房可无烟处理\": \"该房可无烟处理\", \"建筑面积\": \"70-79平方米\", \"楼层\": \"1-6层\", \"便利设施\": \"雨伞、书桌、熨衣设备、多种规格电源插座、房内保险箱、空调、闹钟、针线包、220V电压插座、遮光窗帘、手动窗帘、备用床具、床具\", \"床型\": \"1张2米双人床\", \"可加床\": \"RMB 200/床/间夜\"}}, {\"room_info_list\": [{\"床型\": \"大床\", \"宽带\": \"免费\", \"房价\": \"¥968\", \"政策\": \"不可取消\", \"入住人数\": \"每间最多入住2人\", \"早餐\": \"每天双早\", \"满意度\": \"[含早](超值抢)预订满意度 99%\"}], \"room_detail\": {\"浴室\": \"拖鞋、浴室化妆放大镜、24小时热水、免费洗漱用品(6样以上)、浴衣、浴缸、吹风机、浴室、洗浴间电话、淋浴\", \"房型\": \"豪华园景大床房\", \"该房可无烟处理\": \"该房可无烟处理\", \"建筑面积\": \"42-46平方米\", \"楼层\": \"1-3层\", \"便利设施\": \"雨伞、书桌、熨衣设备、多种规格电源插座、房内保险箱、空调、闹钟、针线包、220V电压插座、遮光窗帘、手动窗帘、备用床具、床具\", \"床型\": \"1张2米双人床\", \"可加床\": \"RMB 200/床/间夜\"}}, {\"room_info_list\": [{\"床型\": \"多床\", \"宽带\": \"免费\", \"房价\": \"¥2988\", \"政策\": \"不可取消立即确认\", \"入住人数\": \"每间最多入住4人\", \"早餐\": \"每天四早\", \"满意度\": \"[含早](标准价)预订满意度 99%\"}], \"room_detail\": {\"浴室\": \"拖鞋、浴室化妆放大镜、24小时热水、免费洗漱用品(6样以上)、浴衣、浴缸、吹风机、浴室、洗浴间电话、淋浴\", \"房型\": \"润和套房\", \"该房可无烟处理\": \"该房可无烟处理\", \"建筑面积\": \"146平方米\", \"楼层\": \"1-3层\", \"便利设施\": \"雨伞、书桌、熨衣设备、多种规格电源插座、房内保险箱、空调、闹钟、针线包、220V电压插座、遮光窗帘、手动窗帘、备用床具、床具\", \"床型\": \"2张2米双人床\", \"可加床\": \"RMB 200/床/间夜\"}}, {\"room_info_list\": [{\"床型\": \"多床\", \"宽带\": \"免费\", \"房价\": \"¥1228\", \"政策\": \"不可取消\", \"入住人数\": \"每间最多入住3人\", \"早餐\": \"每天三早\", \"满意度\": \"(亲子房)预订满意度 99%\"}], \"room_detail\": {\"浴室\": \"拖鞋、浴室化妆放大镜、24小时热水、免费洗漱用品(6样以上)、浴衣、浴缸、吹风机、浴室、洗浴间电话、淋浴\", \"房型\": \"豪华亲子房 闪住\", \"该房可无烟处理\": \"该房可无烟处理\", \"建筑面积\": \"45-75平方米\", \"楼层\": \"1-4层\", \"便利设施\": \"雨伞、书桌、熨衣设备、多种规格电源插座、房内保险箱、空调、闹钟、针线包、220V电压插座、遮光窗帘、手动窗帘、备用床具、床具\", \"床型\": \"1张1.8米双人床 和 1张1.1米单人床\", \"可加床\": \"RMB 200/床/间夜\"}}, {\"room_info_list\": [{\"床型\": \"双床\", \"宽带\": \"免费\", \"房价\": \"¥1200\", \"政策\": \"不可取消立即确认\", \"入住人数\": \"每间最多入住2人\", \"早餐\": \"每天双早\", \"满意度\": \"标准价闪住预订满意度 99%\"}], \"room_detail\": {\"浴室\": \"拖鞋、浴室化妆放大镜、24小时热水、免费洗漱用品(6样以上)、浴衣、浴缸、吹风机、浴室、洗浴间电话、淋浴\", \"房型\": \"建国泳池双床房 闪住\", \"该房可无烟处理\": \"该房可无烟处理\", \"建筑面积\": \"42-46平方米\", \"楼层\": \"1层\", \"便利设施\": \"雨伞、书桌、熨衣设备、多种规格电源插座、房内保险箱、空调、闹钟、针线包、220V电压插座、遮光窗帘、手动窗帘、备用床具、床具\", \"床型\": \"2张1.35米单人床\", \"可加床\": \"RMB 200/床/间夜\"}}, {\"room_info_list\": [{\"床型\": \"双床\", \"宽带\": \"免费\", \"房价\": \"¥1200\", \"政策\": \"不可取消立即确认\", \"入住人数\": \"每间最多入住2人\", \"早餐\": \"每天双早\", \"满意度\": \"标准价闪住预订满意度 99%\"}, {\"床型\": \"双床\", \"宽带\": \"免费\", \"房价\": \"¥1188\", \"政策\": \"不可取消立即确认\", \"入住人数\": \"每间最多入住2人\", \"早餐\": \"每天双早\", \"满意度\": \"[含早](超值抢)预订满意度 99%\"}], \"room_detail\": {\"浴室\": \"拖鞋、浴室化妆放大镜、24小时热水、免费洗漱用品(6样以上)、浴衣、浴缸、吹风机、浴室、洗浴间电话、淋浴\", \"房型\": \"豪华湖景双床房 闪住\", \"该房可无烟处理\": \"该房可无烟处理\", \"建筑面积\": \"42-46平方米\", \"楼层\": \"1-6层\", \"便利设施\": \"雨伞、书桌、熨衣设备、多种规格电源插座、房内保险箱、空调、闹钟、针线包、220V电压插座、遮光窗帘、手动窗帘、备用床具、床具\", \"床型\": \"2张1.35米单人床\", \"可加床\": \"RMB 200/床/间夜\"}}]}";
        String str = shop_room_recommend_all;
        Pattern compiledetail = Pattern.compile("(?<=\"room_detail\":\\s\\{).*?(?=\\})");
        Pattern compileinfo = Pattern.compile("(?<=\"room_info_list\":\\s\\[).*?(?=\\]\\})");
        Matcher matcherdetail = compiledetail.matcher(str);
        Matcher matcherinfo = compileinfo.matcher(str);
        List<hotelHomeList> hotelHomeLists = new ArrayList<>();
        //生成RoomDetail对象
        Class<?> aClassDetail = Class.forName("com.Neo4j.MongoDBPac.xiechengJavaBean.RoomDetaill");
        Class<?> aClassInfo = Class.forName("com.Neo4j.MongoDBPac.xiechengJavaBean.RoomInfoList");
        while (matcherdetail.find() && matcherinfo.find()) {
            hotelHomeList hotelHomeList = new hotelHomeList();

            System.out.println("matchdetail对应的matchinfolist");
            System.out.println(matcherinfo.group());
            System.out.println(matcherdetail.group());
            System.out.println("end----------");
            Pattern compile = Pattern.compile("(\"(.*?)\")+?:\\s(\"(.*?)\")+?");
            Matcher matcherDetailin = compile.matcher(matcherdetail.group());
            Map<String, String> strMapDetail = new HashMap<>();
            while (matcherDetailin.find()) {
                String key=matcherDetailin.group(2);
                String value= matcherDetailin.group(4);
                strMapDetail.put(key, value);
            }
            Field[] fieldDetails = aClassDetail.getFields();
            Object destDetailsdivid = aClassDetail.newInstance();
            for (Field fielddetail : fieldDetails) {
                String name = fielddetail.getName();
                FiledNameReplace annotationInfo = fielddetail.getAnnotation(FiledNameReplace.class);
                String reallName = annotationInfo.name();
                String methodName = annotationInfo.MethodName();

                if (reallName.indexOf("*") != -1) {
                    String substring = reallName.substring(1, reallName.length() - 1);
                    Set<String> strings = strMapDetail.keySet();
                    for (String sm : strings) {
                        if (sm.contains(substring)) {
                            String value = strMapDetail.get(sm);
                            Method method = aClassDetail.getMethod(methodName, String.class);
                            method.invoke(destDetailsdivid, value);
                            break;
                        }
                    }
                } else {
                    //看看会不会报错
                    String value = strMapDetail.get(reallName);
                    Method method = aClassDetail.getMethod(methodName, String.class);
                    method.invoke(destDetailsdivid, value);
                }
            }
            hotelHomeList.setRoom_detail((RoomDetaill) destDetailsdivid);

            String Allinfos = matcherinfo.group();//所有的info列表
            Pattern compile1 = Pattern.compile("\\{(.*?)\\}");//\\{(".*?")\\}
            Matcher matcherAllInfos = compile1.matcher(Allinfos);
            List<RoomInfoList> roomInfoLists = new ArrayList<>();
            while (matcherAllInfos.find()) {
                HashMap<String, String> strMapInfo = new HashMap<>();
                Matcher matcherdicidone = compile.matcher(matcherAllInfos.group(1));
                while (matcherdicidone.find()) {
                    String key=matcherdicidone.group(2);
                    String value= matcherdicidone.group(4);
                    if (key.equals("满意度")&&value.endsWith("(\\")) {//重新匹配数据
                        Pattern compileupdate = Pattern.compile("(?<=\""+key+"\":)\\s\"(.*?%)\"");
                        Matcher msn = compileupdate.matcher(matcherAllInfos.group(1));
                        msn.find();
                        value = msn.group(1);
                    }
                    if(key.equals("满意度")){
                        System.out.println(matcherAllInfos.group(1));
                        System.out.println(value);
                    }
                    strMapInfo.put(key, value);
                }
                Field[] fieldInfos = aClassInfo.getFields();
                Object destInfodivid = aClassInfo.newInstance();
                for (Field fieldinfo : fieldInfos) {
                    String name = fieldinfo.getName();
                    FiledNameReplace annotationInfo = fieldinfo.getAnnotation(FiledNameReplace.class);
                    String reallName = annotationInfo.name();
                    String methodName = annotationInfo.MethodName();
                    //看看会不会报错
                    String value = strMapInfo.get(reallName);
                    Method method = aClassInfo.getMethod(methodName, String.class);
                    method.invoke(destInfodivid, value);
                }
                roomInfoLists.add((RoomInfoList) destInfodivid);
            }
            hotelHomeList.setRoom_info_list(roomInfoLists);
            hotelHomeLists.add(hotelHomeList);
        }
        return hotelHomeLists;
    }

    public static ImpressionAndDianping ConvertDianpingStrToMap(String shop_statistics, String labels) {
        Pattern dianping = Pattern.compile("(?<=\"" + labels + "\":\\s\\[).*?(?=\\])");
        Matcher yxmatcher = dianping.matcher(shop_statistics);
        yxmatcher.find();
        String str = yxmatcher.group();
        System.out.println(str);
        ImpressionAndDianping impressionAndDianping = new ImpressionAndDianping();
        impressionAndDianping.setInfo(labels);
        Map<String, String> params = new HashMap<>();
//        String trim = str.trim();//去掉首尾空格
        String trim = str.replace(" ", "");
        String[] split = trim.split(",");
        for (String strm : split) {
            if (strm.contains(":")) {
                String substring = strm.substring(1, strm.length() - 1);
                String[] split1 = substring.split(":");
                params.put(split1[0], split1[1]);
            } else {
                params.put(strm, strm);
            }
        }
        impressionAndDianping.setParams(params);
        return impressionAndDianping;
    }

    public static void main(String... args) throws Exception {

//        MongoCollection<Document> collection = remoteServideClient.getDatabase("dspider2").getCollection("shops");
//        FindIterable<Document> documents1 = collection.find(Filters.and(Filters.eq("data_source", "酒店"), Filters.eq("data_website", "去哪儿")));
//
//        MongoCollection<Document> collection2 = localServiceClient.getDatabase("hotel").getCollection("qunaerhotel");
//
//        MongoCursor<Document> iterator1 = documents1.iterator();
//        while (iterator1.hasNext()) {
//
//            Document next = iterator1.next();
//            collection2.insertOne(next);
//        }
//TODO


        //TODO
        //获携程url   酒店名称  酒店地址
//        Neo4jtest greeter = new Neo4jtest("bolt://192.168.199.202:7687", "neo4j", "09120912");
//        List<NameAndPlaceHotel> xiechengNamePlaceList= greeter.FindAllXieChengHotalInfos();
//        greeter.close();
//        List<qunaerCompleteHotel> qCHotels = qunaerFinallyDatas();
//
//
//        for (int i=0;i<qCHotels.size();i++) {
//            qunaerCompleteHotel qCompleteHotel = qCHotels.get(i);
//            qunaerHotel qhotel = qCompleteHotel.qhotel;
//            System.out.println("酒店名称:"+qhotel.shop_name);
//            List<String> text1 = SimpleAPI.jniLtpfenchi(qhotel.shop_name);
//            double max = -1;//最大的相似度
//            String maxName = "";//最相似对应的名字
//            String url = "";
//            //遍历每一个携程酒店数据
//            for (NameAndPlaceHotel xieName : xiechengNamePlaceList) {
//                String name = xieName.getName();
//                List<String> text2 = SimpleAPI.jniLtpfenchi(name);
//                double similitytext = SimpleAPI.Similitytext(text1, text2,"shopName");
//                System.out.println(similitytext);
//                if(similitytext>max) {max=similitytext;maxName = name;url=xieName.getUrl();}
//            }
//           if(max<0.5){
//               SimpleAPI.writeTheDataResultToFile("./src/main/resources/result.txt",qhotel.shop_name + ":最相似的携程酒店是:" + maxName + "  且相似度为" + max);
//               SimpleAPI.writeTheDataResultToFile("./src/main/resources/result.txt", qhotel.shop_curr_url + "  对应携程酒店url" + url + "\n");
//           }
//            System.out.println(qhotel.shop_name + ":最相似的携程酒店是:" + maxName + "  且相似度为" + max);
//            System.out.println(qhotel.shop_curr_url+"  对应携程酒店url"+url);
//            System.out.println();
//        }

        ParsexiechengData();
    }
    public static void WriteTheDataToNoe4j(XieChengHotel xiehotel,hotelfacility hotelfacilitys, hotelPolicy hotelpolicy, hotelIntroduction introduction, hotelAroundFacility aroundFacility, ImpressionAndDianping statics_impression, ImpressionAndDianping statics_dianpin, List<hotelHomeList> homeInfos) throws Exception {
        try (Neo4jtest greeter = new Neo4jtest("bolt://192.168.199.202:7687", "neo4j", "09120912")) {
            greeter.CreateTheHotelNeo4jNode(xiehotel, hotelfacilitys, hotelpolicy, introduction, aroundFacility, statics_impression, statics_dianpin, homeInfos);
        }
    }


    //解析携程数据
    public static void ParsexiechengData() throws Exception {
                MongoCollection<Document> collection1 = localServiceClient.getDatabase("hotel").getCollection("xiechenghotel");
        FindIterable<Document> documents = collection1.find();
        MongoCursor<Document> iterator = documents.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Document document = iterator.next();
            XieChengHotel xiehotel = (XieChengHotel) DocumentConvextToModel("com.Neo4j.MongoDBPac.xiechengJavaBean.XieChengHotel", document);
            System.out.println(xiehotel.shop_url + " " + xiehotel.shop_name);
            System.out.println(xiehotel.shop_around_facilities);
            System.out.println("酒店设施");
            hotelfacility hotelfacilitys = (hotelfacility) CovertTheStrToJavaBean("com.Neo4j.MongoDBPac.xiechengJavaBean.hotelfacility", xiehotel.shop_intro);
            System.out.println("酒店政策");
            hotelPolicy hotelpolicy = (hotelPolicy) CovertTheStrToJavaBean("com.Neo4j.MongoDBPac.xiechengJavaBean.hotelPolicy", xiehotel.shop_intro);
            System.out.println("酒店介绍");
            hotelIntroduction introduction = (hotelIntroduction) CovertTheStrToJavaBean("com.Neo4j.MongoDBPac.xiechengJavaBean.hotelIntroduction", xiehotel.shop_intro);
            System.out.println("酒店周边设施");
            hotelAroundFacility aroundFacility = (com.Neo4j.MongoDBPac.xiechengJavaBean.hotelAroundFacility) CovertTheStrToJavaBean("com.Neo4j.MongoDBPac.xiechengJavaBean.hotelAroundFacility", xiehotel.shop_around_facilities);
            ImpressionAndDianping statics_impression = ConvertDianpingStrToMap(xiehotel.shop_statistics, "印象");
            ImpressionAndDianping statics_dianpin = ConvertDianpingStrToMap(xiehotel.shop_statistics, "点评");
            System.out.println(statics_dianpin);
            System.out.println(statics_impression);
            System.out.println("所有的房型列表");
            System.out.println(xiehotel.shop_room_recommend_all);
            List<hotelHomeList> homeInfos = getHomeInfos(xiehotel.shop_room_recommend_all);
            for (hotelHomeList info : homeInfos) {
                System.out.println(info.room_detail.getHomeType());
                System.out.println(info.getRoom_info_list().get(0).toString());
            }
            System.out.println(homeInfos);
            System.out.println(hotelpolicy.acceptPayWay);
            WriteTheDataToNoe4j(xiehotel,hotelfacilitys, hotelpolicy, introduction, aroundFacility, statics_impression, statics_dianpin, homeInfos);
            System.out.println(++i + "________________________________");
        }
    }


    //获得解析之后的去哪儿数据
    public static  List<qunaerCompleteHotel> qunaerFinallyDatas() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException, NoSuchMethodException, ClassNotFoundException {
        MongoCollection<Document> collection = localServiceClient.getDatabase("hotel").getCollection("qunaerhotel");
        FindIterable<Document> documents = collection.find();
        MongoCursor<Document> iterator = documents.iterator();
        List<qunaerCompleteHotel> CompleteHotelLists = new ArrayList<>();
        int i = 1;
        while (iterator.hasNext()) {
            //预处理之后的去哪儿数据
            Document document = iterator.next();
            qunaerHotel qHotel = (qunaerHotel) DocumentConvextToModel("com.Neo4j.MongoDBPac.qunaerJavaBean.qunaerHotel", document);
            qunaerCompleteHotel CompleteHotel = new qunaerCompleteHotel();
            qAroundFacility aroundFacility =
                    qunaerUtils.CopeWithQAroundFacility(qHotel.shop_traffic);
            Map<String, String> params = qunaerUtils.CopeWithQBasicFacility(qHotel.shop_facilities);
            qevaluate qvaluate = qunaerUtils.CopeWithEvaluation(qHotel.shop_statistics);
            List<qHomeEntity> homeEntities = qunaerUtils.CopeWithHomeListAll(qHotel.shop_room_recommend_all);
            CompleteHotel.setQhotel(qHotel);
            CompleteHotel.setAroundFacility(aroundFacility);
            CompleteHotel.setParams(params);
            CompleteHotel.setQvaluate(qvaluate);
            CompleteHotel.setqHomeEntityList(homeEntities);
            CompleteHotelLists.add(CompleteHotel);
            System.out.println((i++)+"---------------------------");
        }
        return CompleteHotelLists;
    }


    //    public static void getAllAroundFacility() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException, NoSuchMethodException, ClassNotFoundException {
//        MongoCollection<Document> collection1 = localServiceClient.getDatabase("hotel").getCollection("xiechenghotel");
//        FindIterable<Document> documents = collection1.find();
//        MongoCursor<Document> iterator = documents.iterator();
//
//        HashSet<String> stringHashSet = new HashSet<>();
////        String str = "{\"娱乐\": [\"柏丽黛丝千岛湖\", \"千岛湖世界钱币文化博物馆\", \"千岛湖夏妮洗浴休闲中心\", \"千岛湖画家画廊\", \"杭州千岛湖游艇假日俱乐部\"], \"餐饮\": [\"千岛湖天晟卤味\", \"千岛湖鱼味馆渔人码头店\", \"千岛湖野生鱼馆\", \"千岛湖度假村喜洋洋酒楼\", \"千岛湖鱼味馆\"]}";
//        Pattern compileValue = Pattern.compile("((?<=\\s\\[).*?(?=\\]))");
//        Pattern compileKey = Pattern.compile("(\".{2,5}\"):\\s");
//
//        while (iterator.hasNext()) {
//            Document document = iterator.next();
//            XieChengHotel xiehotel = (XieChengHotel) DocumentConvextToModel("com.Neo4j.MongoDBPac.xiechengJavaBean.XieChengHotel", document);
//            String str=xiehotel.shop_around_facilities;
//            if(str.equals("{}")) continue;
//            Matcher matcherValue = compileValue.matcher(str);
//            Matcher matcherKey = compileKey.matcher(str);
//            System.out.println(str);
//            while (matcherValue.find() && matcherKey.find()) {
//                if (matcherKey != null) {
//                    stringHashSet.add(matcherKey.group());
//                    System.out.println(matcherKey.group() + "  " + matcherValue.group());
//                }
//            }
//            System.out.println("finish one str");
//
//        }
//        System.out.println(stringHashSet.toString());
//    }
}

