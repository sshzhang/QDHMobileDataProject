package com.Neo4j.MongoDBPac.qunaerJavaBean;

import com.Neo4j.MongoDBPac.FiledNameReplace;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class qunaerUtils {//去哪儿解析工具类


    //处理周边信息方法
    public static qAroundFacility CopeWithQAroundFacility(String aroundfacility) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {  //先测试一下所有可能的type集合

//        String aroundfacility = "[{\"neighbor_list\": [{\"大慈岩\": \"驾车约1.8小时，距离约82.5公里\"}, {\"里叶十里荷花景区\": \"驾车约1.6小时，距离约81.5公里\"}, {\"太真洞\": \"驾车约2.3小时，距离约99.2公里\"}], \"type\": \"景点\"}, {\"neighbor_list\": [{\"黄山屯溪国际机场\": \"驾车约3.7小时，距离约140.4公里\"}, {\"衢州机场\": \"驾车约2.3小时，距离约129.4公里\"}], \"type\": \"机场车站\"}, {\"neighbor_list\": [{\"秀水人家食府\": \"驾车约11分钟，距离约4.4公里\"}, {\"绿城千岛湖喜来登度假酒店采悦轩\": \"驾车约8分钟，距离约3.3公里\"}, {\"千岛湖好渔夫渔家私房菜\": \"驾车约11分钟，距离约4.6公里\"}], \"type\": \"美食\"}]";
        Class<?> aClass = Class.forName("com.Neo4j.MongoDBPac.qunaerJavaBean.qAroundFacility");
        Field[] fields = aClass.getFields();
        Object dest = aClass.newInstance();
        Pattern contentsPattern = Pattern.compile("(?<=\"neighbor_list\":\\s\\[).*?(?=\\])");
        Pattern typePattern = Pattern.compile("(?<=\"type\":\\s).*?(?=[,|\\}])");
        Matcher contentsmatcher =
                contentsPattern.matcher(aroundfacility);
        Matcher typematcher = typePattern.matcher(aroundfacility);

        while (typematcher.find() && contentsmatcher.find()) {
            String type = typematcher.group().replace("\"", "");
            String contents = contentsmatcher.group().replace("\"", "");
            //System.out.println("type:" + type);
            //System.out.println("contens:" + contents);
            List<qAroundDetailInfos> params = null;
            for (Field fr : fields) {
                FiledNameReplace annotation = fr.getAnnotation(FiledNameReplace.class);
                String name = annotation.name();
                if (name.equals(type)) {
                    String methodName = annotation.GetMethodName();
                    Method method = aClass.getMethod(methodName);
                    params = (List<qAroundDetailInfos>) method.invoke(dest);
                    break;
                }
            }

            //types.add(type);
            Pattern contentPattern = Pattern.compile("(\\{(.*?):\\s(.*?)\\})");
            Matcher contentMatch = contentPattern.matcher(contents);
            while (contentMatch.find()) {
                //System.out.println("key:" + contentMatch.group(2));
                //System.out.println("value:" + contentMatch.group(3));
                qAroundDetailInfos qAroundDetailInfos = new qAroundDetailInfos(contentMatch.group(2), contentMatch.group(3));
                params.add(qAroundDetailInfos);
            }
        }
        System.out.println(((qAroundFacility) dest).toString());
        return (qAroundFacility) dest;
    }


    //设施概况  包含基本信息 和设施信息
    public static Map<String, String> CopeWithQBasicFacility(String basicfacility) {

        Map<String, String> params = new HashMap<>();
//        String str = "[{\"联系方式\": [\"电话0571-65089999\"]}, {\"基本信息\": [\"2011年开业 302间客房 层高8层\"]}, {\"客栈简介\": [\"千岛湖润和建国度假酒店位于千岛湖镇梦姑路，紧邻千岛湖旅游码头，东依梦姑塘文化公园，旅游交通便捷。 这里的面积约4.5万平米，客房里配设了热带雨林花洒浴室、宽带、ipod时尚音响等设施；你可以在独立观景阳台上一览千岛风光，柔软的睡床让你一夜安睡到天亮。 建国中餐厅、精品餐厅、千月咖啡...\", \"查看全部\", \"千岛湖润和建国度假酒店位于千岛湖镇梦姑路，紧邻千岛湖旅游码头，东依梦姑塘文化公园，旅游交通便捷。 这里的面积约4.5万平米，客房里配设了热带雨林花洒浴室、宽带、ipod时尚音响等设施；你可以在独立观景阳台上一览千岛风光，柔软的睡床让你一夜安睡到天亮。 建国中餐厅、精品餐厅、千月咖啡厅和万天大堂吧里汇聚了风味美食，你可以一边吃饭，一边欣赏碧波荡漾的千岛湖。 闲暇时，不如带着孩子去儿童乐园、露天泳池、室内泳池玩耍；或是去花园走走，呼吸新鲜空气、聆听湖水轻轻拍岸；幸运的话，还能亲临鸡尾酒会与婚礼。 润和宴会大厅和各类中型会议室里配了先进的视听设施和高速无线网络，适合举办高端会务、宴会。 结束了繁忙的公务，可以去健身房、SPA会所、网球场和棋牌室放松一下，或是坐游艇游湖。喜欢安静的话，坡地钓鱼也是不错的选择。\"]}, {\"网络设施\": [\"\uE616公共区域及房间均提供wifi\"]}, {\"接送服务\": [\"酒店提供收费接站服务 酒店提供收费接机服务\"]}, {\"设施服务\": [\"\uE621宽带上网 \uE615免费市内电话 \uE621国际长途电话 \uE621吹风机 \uE62124小时热水 \uE621接待外宾 \uE621洗衣服务 \uE621行李寄存 \uE621看护小孩服务 \uE621租车 \uE621叫醒服务 \uE621西式餐厅 \uE621中式餐厅 \uE621残疾人设施 \uE621室外游泳池 \uE621室内游泳池 \uE621会议室 \uE621健身房 \uE621无烟房 \uE621商务中心 \uE621酒吧 \uE621棋牌室\"]}]";
        Pattern compile = Pattern.compile("\\{(.*?):\\s\\[(.*?)\\]\\}");
        Matcher matcher = compile.matcher(basicfacility);
        while (matcher.find()) {
            String key = matcher.group(1);
            String value = matcher.group(2);
            System.out.println("key:" + key);
            System.out.println("value:" + value);
            params.put(key, value);
        }
        return params;
    }

    //评论信息统计
    public static qevaluate CopeWithEvaluation(String str) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        //String str = "{\"comment_num_list\": [{\"全部\": \"\"}, {\"好评\": \"1549\"}, {\"中评\": \"63\"}, {\"差评\": \"44\"}], \"tag_list\": [\"设施\", \"服务\", \"位置\", \"美食\", \"美景\"]}";

        if (str == null || str.equals("")) {
            return null;
        }
        Class<?> aClass = Class.forName("com.Neo4j.MongoDBPac.qunaerJavaBean.qevaluate");
        Field[] declaredFields =
                aClass.getDeclaredFields();
        Object dest = aClass.newInstance();
        for (Field declaredFiled : declaredFields) {
            FiledNameReplace annotation = declaredFiled.getAnnotation(FiledNameReplace.class);
            String name = annotation.name();
            String methodName = annotation.MethodName();
            Class<?> datatypes = annotation.datatypes();
            if (Object[].class.isAssignableFrom(datatypes)) { //数组类型
                Pattern compile = Pattern.compile("(\"" + name + "\":\\s\\[(.*?)\\])");
                Matcher matcher = compile.matcher(str);
                matcher.find();
                String group = matcher.group(2).replace("\"", "");
                String[] split = group.split(",");
                Method method = aClass.getMethod(methodName, datatypes);
                method.invoke(dest, new Object[]{split});//有问题
            } else {//单个数据
                Pattern compile = Pattern.compile("(\\{\"" + name + "\":\\s(.*?)\\})");
                Matcher matcher = compile.matcher(str);
                matcher.find();
                Method method = aClass.getMethod(methodName, datatypes);
                String group = matcher.group(2).replace("\"", "");
                //默认是int不需要判断
                method.invoke(dest, Integer.parseInt(group));
            }
        }
        return (qevaluate) dest;
    }


    public static List<qHomeEntity> CopeWithHomeListAll(String allhomeInfos) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {


        List<qHomeEntity> qHomeEntities = new ArrayList<>();
        //String allhomeInfos = "[{\"room_list\": [[\"去哪儿直销\", \"豪华园景双床房(限时抢购)(超值抢)[含早]\", \"双早\", \"取消扣全款\", \"¥738\", \"¥738\", \"预 订\", \"4分钟前\"], [\"携程网团购\", \"【周六含早】豪华园景双人房\", \"含早餐\", \"随时退\", \"¥888\", \"¥888\", \"预 订\", \"32分钟前\"], [\"去哪儿直销\", \"豪华园景双床房(下午茶套餐)\", \"双早\", \"取消扣全款\", \"¥823\", \"¥823\", \"预 订\", \"4分钟前\"], [\"携程旅行网\", \"豪华园景双床房(限时抢购)(超值抢)[含早]\", \"双早\", \"取消扣全款\", \"¥738\", \"¥738\", \"预 订\", \"6分钟前\"], [\"去哪住\", \"豪华园景双床房\", \"双早\", \"取消扣全款\", \"¥767\", \"¥767\", \"预 订\", \"4分钟前\"], [\"携程旅行网\", \"豪华园景双床房\", \"双早\", \"取消扣全款\", \"¥767\", \"¥767\", \"预 订\", \"6分钟前\"], [\"携程网团购\", \"【周日至周四】豪华园景双人房\", \"含早餐\", \"随时退\", \"¥498\", \"¥498\", \"已订完\", \"32分钟前\"]], \"detail\": {\"facily_list\": [\"最多可入住2人\", \"提供有线宽带\", \"WIFI已覆盖\"], \"room_area\": [\"面积42-46㎡\", \"位于1-5层\", \"独立卫浴\", \"有窗\"]}, \"name\": \"豪华园景双人房\"}, {\"room_list\": [[\"去哪儿直销\", \"豪华景观双床房[含早](超值抢)\", \"双早\", \"取消扣全款\", \"¥828\", \"¥828\", \"预 订\", \"4分钟前\"], [\"去哪住\", \"豪华景观双床房(3间起订)\", \"双早\", \"取消扣全款\", \"¥798\", \"¥798\", \"预 订\", \"4分钟前\"], [\"去哪儿直销\", \"豪华景观双床房(下午茶套餐)\", \"双早\", \"取消扣全款\", \"¥889\", \"¥889\", \"预 订\", \"4分钟前\"], [\"携程旅行网\", \"豪华景观双床房(3间起订)\", \"双早\", \"取消扣全款\", \"¥798\", \"¥798\", \"预 订\", \"6分钟前\"], [\"携程旅行网\", \"豪华景观双床房[含早](超值抢)\", \"双早\", \"取消扣全款\", \"¥828\", \"¥828\", \"预 订\", \"6分钟前\"], [\"携程旅行网\", \"豪华景观双床房\", \"双早\", \"取消扣全款\", \"¥833\", \"¥833\", \"预 订\", \"6分钟前\"], [\"去哪住\", \"豪华景观双床房\", \"双早\", \"取消扣全款\", \"¥834\", \"¥834\", \"预 订\", \"4分钟前\"]], \"detail\": {\"facily_list\": [\"最多可入住2人\", \"提供有线宽带\", \"WIFI已覆盖\"], \"room_area\": [\"面积42-46㎡\", \"位于1-5层\", \"2张1.35m单人床单人床\", \"有窗\"]}, \"name\": \"豪华景观双人房\"}, {\"room_list\": [[\"去哪儿直销\", \"豪华湖景双床房[含早](超值抢)\", \"双早\", \"取消扣全款\", \"¥958\", \"¥958\", \"预 订\", \"4分钟前\"], [\"去哪儿直销\", \"豪华湖景双床房(下午茶套餐)\", \"双早\", \"取消扣全款\", \"¥1023\", \"¥1023\", \"预 订\", \"4分钟前\"], [\"携程旅行网\", \"豪华湖景双床房[含早](超值抢)\", \"双早\", \"取消扣全款\", \"¥958\", \"¥958\", \"预 订\", \"6分钟前\"], [\"去哪儿直销\", \"豪华湖景双床房(骑行套餐)\", \"无早\", \"取消扣全款\", \"¥978\", \"¥978\", \"预 订\", \"4分钟前\"], [\"去哪住\", \"豪华湖景双床房\", \"双早\", \"取消扣全款\", \"¥967\", \"¥967\", \"预 订\", \"4分钟前\"], [\"携程旅行网\", \"豪华湖景双床房\", \"双早\", \"取消扣全款\", \"¥967\", \"¥967\", \"预 订\", \"6分钟前\"], [\"携程旅行网\", \"豪华湖景双床房(骑行套餐)\", \"无早\", \"取消扣全款\", \"¥978\", \"¥978\", \"预 订\", \"6分钟前\"], [\"携程网团购\", \"【周日至周四】豪华湖景双人房\", \"含早餐\", \"随时退\", \"¥695\", \"¥695\", \"已订完\", \"32分钟前\"], [\"携程网团购\", \"【周六含早】豪华湖景双人房\", \"含早餐\", \"随时退\", \"¥1150\", \"¥1150\", \"已订完\", \"32分钟前\"]], \"detail\": {\"facily_list\": [\"最多可入住2人\", \"提供有线宽带\", \"WIFI已覆盖\"], \"room_area\": [\"面积42-46㎡\", \"位于1-6层\", \"2张1.35m单人床单人床\", \"独立卫浴\", \"有窗\"]}, \"name\": \"豪华湖景双人房\"}, {\"room_list\": [[\"去哪儿直销\", \"豪华湖景大床房[含早](超值抢)\", \"双早\", \"取消扣全款\", \"¥958\", \"¥958\", \"预 订\", \"4分钟前\"], [\"去哪儿直销\", \"豪华湖景大床房(下午茶套餐)\", \"双早\", \"取消扣全款\", \"¥1023\", \"¥1023\", \"预 订\", \"4分钟前\"], [\"携程旅行网\", \"豪华湖景大床房[含早](超值抢)\", \"双早\", \"取消扣全款\", \"¥958\", \"¥958\", \"预 订\", \"6分钟前\"], [\"去哪住\", \"豪华湖景大床房\", \"双早\", \"取消扣全款\", \"¥967\", \"¥967\", \"预 订\", \"4分钟前\"], [\"携程旅行网\", \"豪华湖景大床房\", \"双早\", \"取消扣全款\", \"¥967\", \"¥967\", \"预 订\", \"6分钟前\"], [\"携程网团购\", \"【周日至周四】豪华湖景大床房\", \"含早餐\", \"随时退\", \"¥695\", \"¥695\", \"已订完\", \"32分钟前\"]], \"detail\": {\"facily_list\": [\"最多可入住2人\", \"提供有线宽带\", \"WIFI已覆盖\"], \"room_area\": [\"面积42-46㎡\", \"位于1-6层\", \"1张2m双人床双人床\", \"独立卫浴\", \"有窗\"]}, \"name\": \"豪华湖景大床房\"}, {\"room_list\": [[\"去哪儿直销\", \"建国泳池大床房[含早](超值抢)\", \"双早\", \"取消扣全款\", \"¥958\", \"¥958\", \"预 订\", \"4分钟前\"], [\"去哪儿直销\", \"建国泳池大床房-(含快快租车30元租车券)\", \"双早\", \"取消扣全款\", \"¥988 减¥11\", \"¥977\", \"在线付¥988减¥11\", \"预 订\", \"32分钟前\"], [\"携程旅行网\", \"建国泳池大床房[含早](超值抢)\", \"双早\", \"取消扣全款\", \"¥958\", \"¥958\", \"预 订\", \"6分钟前\"], [\"去哪住\", \"建国泳池大床房\", \"双早\", \"取消扣全款\", \"¥967\", \"¥967\", \"预 订\", \"4分钟前\"], [\"携程旅行网\", \"建国泳池大床房\", \"双早\", \"取消扣全款\", \"¥967\", \"¥967\", \"预 订\", \"6分钟前\"]], \"detail\": {\"facily_list\": [\"最多可入住2人\", \"提供有线宽带\", \"WIFI已覆盖\"], \"room_area\": [\"面积42-46㎡\", \"位于1层\", \"1张2m双人床双人床\", \"有窗\"]}, \"name\": \"建国泳池大床房\"}, {\"room_list\": [[\"去哪儿直销\", \"建国泳池双床房[含早](超值抢)\", \"双早\", \"取消扣全款\", \"¥958\", \"¥958\", \"预 订\", \"4分钟前\"], [\"去哪儿直销\", \"建国泳池双床房-(含滴滴租车礼包)\", \"双早\", \"取消扣全款\", \"¥999 减¥12\", \"¥987\", \"在线付¥999减¥12\", \"预 订\", \"32分钟前\"], [\"携程旅行网\", \"建国泳池双床房[含早](超值抢)\", \"双早\", \"取消扣全款\", \"¥958\", \"¥958\", \"预 订\", \"6分钟前\"], [\"去哪住\", \"建国泳池双床房\", \"双早\", \"取消扣全款\", \"¥967\", \"¥967\", \"预 订\", \"4分钟前\"], [\"携程旅行网\", \"建国泳池双床房\", \"双早\", \"取消扣全款\", \"¥967\", \"¥967\", \"预 订\", \"6分钟前\"]], \"detail\": {\"facily_list\": [\"最多可入住2人\", \"提供有线宽带\", \"WIFI已覆盖\"], \"room_area\": [\"面积42-46㎡\", \"位于1层\", \"2张1.35m单人床单人床\", \"有窗\"]}, \"name\": \"建国泳池双床房\"}, {\"room_list\": [[\"去哪儿直销\", \"豪华亲子房(亲子房)\", \"含早餐\", \"取消扣全款\", \"¥988\", \"¥988\", \"预 订\", \"4分钟前\"], [\"去哪儿直销\", \"豪华亲子房\", \"含早餐\", \"取消扣全款\", \"¥989\", \"¥989\", \"预 订\", \"4分钟前\"], [\"携程旅行网\", \"豪华亲子房(亲子房)\", \"含早餐\", \"取消扣全款\", \"¥988\", \"¥988\", \"预 订\", \"6分钟前\"], [\"携程旅行网\", \"豪华亲子房\", \"含早餐\", \"取消扣全款\", \"¥989\", \"¥989\", \"预 订\", \"6分钟前\"]], \"detail\": {\"facily_list\": [\"最多可入住3人\", \"提供有线宽带\", \"WIFI已覆盖\"], \"room_area\": [\"面积45-75㎡\", \"位于1-4层\", \"1张1.8m双人床和1张1.1m单人床单人床和双人床\", \"有窗\"]}, \"name\": \"豪华亲子房\"}, {\"room_list\": [[\"去哪儿直销\", \"行政湖景大床房[含早](超值抢)\", \"双早\", \"取消扣全款\", \"¥1288\", \"¥1288\", \"预 订\", \"4分钟前\"], [\"去哪儿直销\", \"行政湖景大床房\", \"双早\", \"取消扣全款\", \"¥1300\", \"¥1300\", \"预 订\", \"4分钟前\"], [\"携程旅行网\", \"行政湖景大床房[含早](超值抢)\", \"双早\", \"取消扣全款\", \"¥1288\", \"¥1288\", \"预 订\", \"6分钟前\"], [\"携程旅行网\", \"行政湖景大床房\", \"双早\", \"取消扣全款\", \"¥1300\", \"¥1300\", \"预 订\", \"6分钟前\"]], \"detail\": {\"facily_list\": [\"最多可入住2人\", \"提供有线宽带\", \"WIFI已覆盖\"], \"room_area\": [\"面积70-79㎡\", \"位于1-6层\", \"1张2m双人床双人床\", \"有窗\"]}, \"name\": \"行政湖景大床房\"}, {\"room_list\": [[\"去哪儿直销\", \"豪华景观套房(大床)\", \"双早\", \"取消扣全款\", \"¥1788\", \"¥1788\", \"预 订\", \"4分钟前\"], [\"携程旅行网\", \"豪华景观套房(大床)\", \"双早\", \"取消扣全款\", \"¥1788\", \"¥1788\", \"预 订\", \"6分钟前\"]], \"detail\": {\"facily_list\": [\"最多可入住2人\", \"提供有线宽带\", \"WIFI已覆盖\"], \"room_area\": [\"面积90㎡\", \"位于2层\", \"1张2m双人床双人床\", \"有窗\"]}, \"name\": \"豪华景观套房\"}, {\"room_list\": [[\"去哪儿直销\", \"豪华湖景套房(大床)\", \"双早\", \"取消扣全款\", \"¥1988\", \"¥1988\", \"预 订\", \"4分钟前\"], [\"携程旅行网\", \"豪华湖景套房(大床)\", \"双早\", \"取消扣全款\", \"¥1988\", \"¥1988\", \"预 订\", \"6分钟前\"]], \"detail\": {\"facily_list\": [\"最多可入住2人\", \"提供有线宽带\", \"WIFI已覆盖\"], \"room_area\": [\"面积110㎡\", \"位于2层\", \"1张2m双人床双人床\", \"有窗\"]}, \"name\": \"豪华湖景套房\"}, {\"room_list\": [[\"去哪儿直销\", \"总统套房\", \"含早餐\", \"取消扣全款\", \"¥6888\", \"¥6888\", \"预 订\", \"4分钟前\"], [\"携程旅行网\", \"总统套房\", \"含早餐\", \"取消扣全款\", \"¥6888\", \"¥6888\", \"预 订\", \"6分钟前\"]], \"detail\": {\"facily_list\": [\"最多可入住4人\", \"提供有线宽带\", \"WIFI已覆盖\"], \"room_area\": [\"面积260㎡\", \"位于3层\", \"2张2m双人床双人床\", \"有窗\"]}, \"name\": \"总统套房\"}]";
        Class<?> aClass = Class.forName("com.Neo4j.MongoDBPac.qunaerJavaBean.qHomeDetail");
        Class<?> aClassRoomInfoList = Class.forName("com.Neo4j.MongoDBPac.qunaerJavaBean.qunaerRoomInfoList");
        Pattern room_compile = Pattern.compile("(?<=\"room_area\":\\s\\[)(.*?)(?=\\])");
        Pattern facility_compile = Pattern.compile("(?<=\"facily_list\":\\s\\[)(.*?)(?=\\])");
        Pattern listinfos_compile = Pattern.compile("(?<=\"room_list\":\\s\\[)(.*?\\])(?=\\])");
        Matcher room_matcher = room_compile.matcher(allhomeInfos);
        Matcher facility_matcher = facility_compile.matcher(allhomeInfos);
        Matcher listinfos_matcher = listinfos_compile.matcher(allhomeInfos);
        Field[] fields = aClass.getFields();
        Field[] RoomInfoListfields = aClassRoomInfoList.getFields();

        while (room_matcher.find() && facility_matcher.find() && listinfos_matcher.find()) {
            qHomeEntity homeEntity = new qHomeEntity();
            Object dest = aClass.newInstance();
            String content = room_matcher.group(1);
            String ucontent = content.replace("\"", "");
            String facility_content = facility_matcher.group(1);
            String ufacility_content = facility_content.replace("\"", "");
            String listinfos_content = listinfos_matcher.group();
            String ulistinfos_content = listinfos_content.replace("\"", "");
            for (Field field : fields) {
                FiledNameReplace annotation = field.getAnnotation(FiledNameReplace.class);
                String name = annotation.name();
                String methodName = annotation.MethodName();
                Class<?> datatypes = annotation.datatypes();
                String values = null;
                if (name.equals("room_area")) {
                    if (annotation.isRegex()) {//开启正则匹配
                        String regexExpression = annotation.RegexExpression();
                        Pattern compile = Pattern.compile(regexExpression);
                        Matcher matcher = compile.matcher(ucontent);
                        if (matcher.find()) {
                            values = matcher.group();
                        }
                    }
                } else if (name.equals("facily_list")) {
                    if (annotation.isKey()) {//开启关键字包含关系
                        String[] split = ufacility_content.split(",");
                        String key = annotation.key();
                        for (String sp : split) {
                            if (sp.contains(key)) { //包含
                                values = sp;
                            }
                        }
                    }
                }
                System.out.print(values + "   ");
                Method method = aClass.getMethod(methodName, datatypes);
                method.invoke(dest, values);
            }
            homeEntity.setHomeDetail((qHomeDetail) dest);

            //设置房型具体信息
            Pattern listinfo_pattern = Pattern.compile("(?<=\\[).*?前(?=\\])");
            Matcher listinfo_matcher = listinfo_pattern.matcher(ulistinfos_content);
            List<qunaerRoomInfoList> qunaerRoomInfoLists = new ArrayList<>();
            while (listinfo_matcher.find()) {
                Object det = aClassRoomInfoList.newInstance();
                String listinfo_content = listinfo_matcher.group();
                String[] split = listinfo_content.split(",");
                for (Field fr : RoomInfoListfields) {
                    FiledNameReplace annotation = fr.getAnnotation(FiledNameReplace.class);
                    if (annotation.isIndex()) {
                        int index = annotation.index();
                        String methodName = annotation.MethodName();
                        Class<?> datatypes = annotation.datatypes();
                        Method method = aClassRoomInfoList.getMethod(methodName, datatypes);
                        method.invoke(det, split[index]);
                    }
                }
                qunaerRoomInfoLists.add((qunaerRoomInfoList) det);
            }
            homeEntity.setRoomInfoLists(qunaerRoomInfoLists);
            qHomeEntities.add(homeEntity);
        }
        System.out.println(qHomeEntities);
        return qHomeEntities;
    }


    public static void main(String... args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //数组是协变的  当个元素是父子类关系   组成数组后关系依旧
//        System.out.println(Object[].class.isAssignableFrom(String[].class));
//        System.out.println(Number.class.isAssignableFrom(Integer.class));
//        System.out.println(Integer.class.isAssignableFrom(Number.class));
        CopeWithQBasicFacility("");
    }
}
