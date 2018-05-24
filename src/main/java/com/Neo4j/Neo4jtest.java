package com.Neo4j;

import com.Neo4j.MongoDBPac.FiledNameReplace;
import com.Neo4j.MongoDBPac.xiechengJavaBean.*;
import org.neo4j.driver.v1.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.neo4j.driver.v1.Values.parameters;

public class Neo4jtest implements AutoCloseable {

    private final Driver driver;

    public Neo4jtest(String uri, String user, String password) {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }

    @Override
    public void close() throws Exception {
        driver.close();
    }

    public void printGreeting(final String message) {

        try (Session session = driver.session()) {
            String greeting = session.writeTransaction(new TransactionWork<String>() {
                @Override
                public String execute(Transaction tx) {
                    StatementResult result = tx.run("match(m:Dept) where m.dname={datas} RETURN m.location", parameters("datas", message));
                    return result.single().get(0).asString();
                }
            });

            System.out.println(greeting);
        }
    }


    public void CreateTheNode() {
        try (Session session = driver.session()) {
            StatementResult o = session.writeTransaction(new TransactionWork<StatementResult>() {
                @Override
                public StatementResult execute(Transaction tx) {
                    StatementResult statement = tx.run("create(a:Greeting{name:{name}})  create(b:State{title:{title}})  create (b)-[re:Relationship{reName:\"zhang\"}]->(a)", parameters("name", "Arthur", "title", "标题"));
                    return statement;
                }
            });
            while (o.hasNext()) {
                Record next = o.next();
                System.out.println(next.asMap().toString());
            }
        }
    }


//    public static void main(String... args) throws Exception {
//        try (Neo4jtest greeter = new Neo4jtest("bolt://192.168.199.202:7687", "neo4j", "09120912")) {
//            greeter.CreateTheNode();
//        }
//    }


    public void CreateTheHotelNeo4jNode(XieChengHotel xiehotel, hotelfacility hotelfacilitys, hotelPolicy hotelpolicy, hotelIntroduction introduction, hotelAroundFacility aroundFacility, ImpressionAndDianping statics_impression, ImpressionAndDianping statics_dianpin, List<hotelHomeList> homeInfos) {

        //酒店总体信息
        String shope_name = xiehotel.shop_name;
        String shope_address = xiehotel.shop_address;
        String shope_url = xiehotel.shop_url;
        float Shope_gust_sumRank = xiehotel.shop_grade;
        String onlyValue = xiehotel._id;
        //皇冠数量
        String huanguanshu = xiehotel.shop_rate;
        String other = introduction.other.substring(1, introduction.other.length() - 1);
        String label = introduction.label;
        String infos = introduction.info;

        Session session = driver.session();
        StatementResult o = session.writeTransaction(new TransactionWork<StatementResult>() {
            @Override
            public StatementResult execute(Transaction tx) {
                //添加酒店节点
//                StatementResult run = tx.run("match(xiecheng:XCHotel{hotel_id:\"" + onlyValue + "\"})return xiecheng");
//                if (!run.hasNext()) {
//                    StatementResult statement = tx.run("create(xiecheng:XCHotel{hotel_id:\""+onlyValue+"\",Shope_name:\"" + shope_name + "\",Shop_address:\"" + shope_address + "\"," +
//                            "Shop_sepcific_introduce:\"" + other + "\",shope_url:\""+shope_url+"\"})");
//                    return statement;
//                }
//                System.out.println("已经存在数据:"+shope_url);

                StatementResult statement = tx.run("merge(xiecheng:XCHotel{hotel_id:\""+onlyValue+"\",Shope_name:\"" + shope_name + "\",Shop_address:\"" + shope_address + "\"," +
                            "Shop_sepcific_introduce:\"" + other + "\",shope_url:\""+shope_url+"\"})");


                return null;
            }
        });


        //房型信息创建
        for (hotelHomeList hotelLits : homeInfos) {
            RoomDetaill room_detail = hotelLits.room_detail;
            String decreasebed = room_detail.decreasebed;
            String bedthroom = room_detail.bedthroom;
            String bedType = room_detail.bedType;
            String buildingArea = room_detail.buildingArea;
            String convenient_facility = room_detail.convenient_facility;
            String floor = room_detail.floor;
            String homeArrangeNoSmorkingPlace = room_detail.homeArrangeNoSmorkingPlace;
            String homeType = room_detail.homeType;
            String increasebed = room_detail.increasebed;
            String merageincreanddecrebed ;
            if (increasebed == null || "".equals(increasebed)) {
                merageincreanddecrebed = increasebed;
            }else{
                merageincreanddecrebed = decreasebed;
            }

            session.writeTransaction(new TransactionWork<StatementResult>() {
                @Override
                public StatementResult execute(Transaction tx) {
                    //添加房型节点
//                    StatementResult run = tx.run("match(home:Home{hotel_id:\"" + onlyValue + "\",HomeType:\""+homeType+"\",jianzhumianji:\""+buildingArea+"\",floor:\""+floor+"\",bianlisheshi:\""+convenient_facility+"\"})return home");
//                    if (!run.hasNext()) {
//                        StatementResult statement = tx.run("create(ho:Home{hotel_id:\""+onlyValue+"\",HomeType:\""+homeType+"\",jianzhumianji:\""+buildingArea+"\",floor:\""+floor+"\",chuanxing:\""+bedType+"\",kejiachuang:\""+merageincreanddecrebed+"\",beizhuxinxi:\""+homeArrangeNoSmorkingPlace+"\",bianlisheshi:\""+convenient_facility+"\",yushi:\""+bedthroom+"\"})");
//                    }
                    StatementResult statement = tx.run("merge(ho:Home{hotel_id:\""+onlyValue+"\",HomeType:\""+homeType+"\",jianzhumianji:\""+buildingArea+"\",floor:\""+floor+"\",chuanxing:\""+bedType+"\",kejiachuang:\""+merageincreanddecrebed+"\",beizhuxinxi:\""+homeArrangeNoSmorkingPlace+"\",bianlisheshi:\""+convenient_facility+"\",yushi:\""+bedthroom+"\"})");

                    //添加关系节点
//                    StatementResult run2 = tx.run("match (xiecheng:XCHotel{hotel_id:\"" + onlyValue + "\"})-[hr:IncludeHomeTypes{name:\"包含的房间类型\"}]->(ho:Home{hotel_id:\"" + onlyValue + "\"}) return hr");
//                    if (!run2.hasNext()) {
//                            tx.run("match (xiecheng:XCHotel{hotel_id:\"" + onlyValue + "\"}),(ho:Home{hotel_id:\"" + onlyValue + "\"}) create (xiecheng)-[hr:IncludeHomeTypes{name:\"包含的房间类型\"}]->(ho) return hr");
//                    }

                    tx.run("match (xiecheng:XCHotel{hotel_id:\"" + onlyValue + "\"}),(ho:Home{hotel_id:\"" + onlyValue + "\"}) merge (xiecheng)-[hr:IncludeHomeTypes{name:\"包含的房间类型\"}]->(ho) return hr");


                    List<RoomInfoList> room_info_list = hotelLits.getRoom_info_list();
                    for (RoomInfoList roomlist : room_info_list) {
                        String chuangxing = roomlist.getChuangxing();
                        String fangjia = roomlist.getFangjia();
                        String zhengce = roomlist.getZhengce();
                        String mayidu = roomlist.getMayidu();
                        String ruzhurenshu = roomlist.getRuzhurenshu();
                        String kuangdai = roomlist.getKuangdai();
                        String zaocan = roomlist.getZaocan();
                        try {
//                            StatementResult run1 = tx.run("match(hm1:HomeSpecificInfos{hotel_id:\"" + onlyValue + "\",chuangxing:\"" + chuangxing + "\",fangjia:\"" + fangjia + "\",zhengce:\"" + zhengce + "\",mayidu:\"" + mayidu + "\",ruzhurenshu:\"" + ruzhurenshu + "\",kuangdai:\"" + kuangdai + "\",zaocan:\"" + zaocan + "\"}) return hm1");
//                            if(!run1.hasNext()){
//                                tx.run("create(hm1:HomeSpecificInfos{hotel_id:\""+onlyValue+"\",chuangxing:\""+chuangxing+"\",fangjia:\""+fangjia+"\",zhengce:\""+zhengce+"\",mayidu:\""+mayidu+"\",ruzhurenshu:\""+ruzhurenshu+"\",kuangdai:\""+kuangdai+"\",zaocan:\""+zaocan+"\"})");
//                            }

                            tx.run("merge(hm1:HomeSpecificInfos{hotel_id:\""+onlyValue+"\",chuangxing:\""+chuangxing+"\",fangjia:\""+fangjia+"\",zhengce:\""+zhengce+"\",mayidu:\""+mayidu+"\",ruzhurenshu:\""+ruzhurenshu+"\",kuangdai:\""+kuangdai+"\",zaocan:\""+zaocan+"\"})");

                            //添加关系节点
//                            StatementResult run3 = tx.run("match(ho:Home{hotel_id:\"" + onlyValue + "\",HomeType:\"" + homeType + "\",jianzhumianji:\"" + buildingArea + "\",floor:\"" + floor + "\",chuanxing:\"" + bedType + "\",kejiachuang:\"" + merageincreanddecrebed + "\",beizhuxinxi:\"" + homeArrangeNoSmorkingPlace + "\",bianlisheshi:\"" + convenient_facility + "\",yushi:\"" + bedthroom + "\"})-[str:HomeSpecficRelationship{name:\"某种房间包含的具体型号信息\"}]-> (hm1:HomeSpecificInfos{hotel_id:\"" + onlyValue + "\",chuangxing:\"" + chuangxing + "\",fangjia:\"" + fangjia + "\",zhengce:\"" + zhengce + "\",mayidu:\"" + mayidu + "\",ruzhurenshu:\"" + ruzhurenshu + "\",kuangdai:\"" + kuangdai + "\",zaocan:\"" + zaocan + "\"}) return str");
//                            if (!run3.hasNext()) {
//                                tx.run("match(ho:Home{hotel_id:\"" + onlyValue + "\",HomeType:\"" + homeType + "\",jianzhumianji:\"" + buildingArea + "\",floor:\"" + floor + "\",chuanxing:\"" + bedType + "\",kejiachuang:\"" + merageincreanddecrebed + "\",beizhuxinxi:\"" + homeArrangeNoSmorkingPlace + "\",bianlisheshi:\"" + convenient_facility + "\",yushi:\"" + bedthroom + "\"}),(hm1:HomeSpecificInfos{hotel_id:\""+onlyValue+"\",chuangxing:\""+chuangxing+"\",fangjia:\""+fangjia+"\",zhengce:\""+zhengce+"\",mayidu:\""+mayidu+"\",ruzhurenshu:\""+ruzhurenshu+"\",kuangdai:\""+kuangdai+"\",zaocan:\""+zaocan+"\"}) create (ho)-[str:HomeSpecficRelationship{name:\"某种房间包含的具体型号信息\"}]->(hm1) return str");
//                            }
                            tx.run("match(ho:Home{hotel_id:\"" + onlyValue + "\",HomeType:\"" + homeType + "\",jianzhumianji:\"" + buildingArea + "\",floor:\"" + floor + "\",chuanxing:\"" + bedType + "\",kejiachuang:\"" + merageincreanddecrebed + "\",beizhuxinxi:\"" + homeArrangeNoSmorkingPlace + "\",bianlisheshi:\"" + convenient_facility + "\",yushi:\"" + bedthroom + "\"}),(hm1:HomeSpecificInfos{hotel_id:\""+onlyValue+"\",chuangxing:\""+chuangxing+"\",fangjia:\""+fangjia+"\",zhengce:\""+zhengce+"\",mayidu:\""+mayidu+"\",ruzhurenshu:\""+ruzhurenshu+"\",kuangdai:\""+kuangdai+"\",zaocan:\""+zaocan+"\"}) merge (ho)-[str:HomeSpecficRelationship{name:\"某种房间包含的具体型号信息\"}]->(hm1) return str");


                        } catch (Exception e) {
                            System.out.println(roomlist);
                            System.out.println(xiehotel.shop_room_recommend_all);
                            e.printStackTrace();
                            break;

                        }

                    }
                    return null;
                }
            });

        }

        System.out.println(label);
        //酒店标签信息
        if (label != null && !"".equals(label)) {
            System.out.println("标签:" + label);
            System.out.println("特色信息:" + infos);
            String updatelabels = label.replace("\"", "");
            String[] signalLabels = updatelabels.split(",");
            for (String signalLabel : signalLabels) {
                session.writeTransaction(new TransactionWork<StatementResult>() {
                    @Override
                    public StatementResult execute(Transaction tx) {
                        String labeldescription = "";//保存对应的描述信息,没有描述信息直接""
//                        tx.run("merge(sf:HotelLabel{name:\"" + signalLabel + "\"})");
//                        tx.run("match (sf:HotelLabel{name:\""+signalLabel+"\"}),(xiecheng:XCHotel{hotel_id:\""+onlyValue+"\"}) merge (xiecheng)-[rs:HotelLabelInfos{name:\"酒店标签信息\"}]->(sf) return rs");
                        if (infos != null && infos != "" && (!"{}".equals(infos)) && infos.contains("\""+signalLabel+"\":")) {
                            Pattern compile = Pattern.compile("(\"("+signalLabel+")\")+?:\\s(\"(.*?)\")+?");
                            Matcher matcher =
                                    compile.matcher(infos);
                            matcher.find();
                            System.out.println(matcher.group(2) + " " + matcher.group(4));
//                            tx.run("merge(sf:HotelLabelDescription{name:\"" + signalLabel + "\",description:\"" + matcher.group(4) + "\"})");
//                            tx.run("match(sf:HotelLabel{name:\"" + signalLabel + "\"}),(sm:HotelLabelDescription{name:\"" + signalLabel + "\",description:\"" + matcher.group(4) + "\"}) merge(sf)-[rs:DetailHotelLabelInfos{name:\"具体的标签信息\"}]->(sm)");
                            labeldescription=matcher.group(4);
                        }
                        tx.run("merge(sf:HotelLabel{name:\"" + signalLabel + "\",description:\"" + labeldescription + "\"})");
                        tx.run("match (sf:HotelLabel{name:\""+signalLabel+"\",description:\""+labeldescription+"\"}),(xiecheng:XCHotel{hotel_id:\""+onlyValue+"\"}) merge (xiecheng)-[rs:HotelLabelInfos{name:\"酒店标签信息\"}]->(sf) return rs");

                        return null;
                    }
                });
            }
        }




        //添加房型设施节点

        Class<? extends hotelfacility> aClassFacilitys = hotelfacilitys.getClass();
       final Field[] fields =aClassFacilitys.getFields();
       StatementResult os = session.writeTransaction(new TransactionWork<StatementResult>() {
                @Override
                public StatementResult execute(Transaction tx) {
                    try {
                        //添加酒店节点
                        for (Field field : fields)
                        {
                            FiledNameReplace annotation = field.getAnnotation(FiledNameReplace.class);
                            String reallName = annotation.name();
                            String methodName = annotation.GetMethodName();
                            Method method = aClassFacilitys.getMethod(methodName);
                            String invoke = (String) method.invoke(hotelfacilitys);
                            if (invoke == null || "".equals(invoke)) {
                                continue;
                            }
                            String replace = invoke.replace("\"", "");
//                            StatementResult run1 = tx.run("match(sf3:Shope_facility{name:\"" + reallName + "\",shuxing:\"" + replace  + "\"}) return sf3");
//                            if(!run1.hasNext()){
//                                StatementResult run = tx.run("create(sf3:Shope_facility{name:\""+reallName+"\",shuxing:\""+replace +"\"})");
//                            }
                            StatementResult run = tx.run("merge(sf3:Shope_facility{name:\""+reallName+"\",shuxing:\""+replace +"\"})");


                            //添加关系节点
//                            StatementResult run3 = tx.run("match(xiecheng:XCHotel{hotel_id:\"" + onlyValue + "\"})-[str:hotelIncludeFacilityRelationship{name:\"酒店包含的设施\"}]->(sf3:Shope_facility{name:\""+reallName+"\",shuxing:\""+replace +"\"}) return str");
//                            if (!run3.hasNext()) {
//                                tx.run("match(xiecheng:XCHotel{hotel_id:\"" + onlyValue + "\"}),(sf3:Shope_facility{name:\"" + reallName + "\",shuxing:\"" + replace + "\"}) create(xiecheng)-[str:hotelIncludeFacilityRelationship{name:\"酒店包含的设施\"}]->(sf3) return str");
//                            }

                            tx.run("match(xiecheng:XCHotel{hotel_id:\"" + onlyValue + "\"}),(sf3:Shope_facility{name:\"" + reallName + "\",shuxing:\"" + replace + "\"}) merge(xiecheng)-[str:hotelIncludeFacilityRelationship{name:\"酒店包含的设施\"}]->(sf3) return str");

                        }

                    } catch (Exception e) {

                        e.printStackTrace();

                    }
                    return null;
                }
            });

       //政策
        Class<? extends hotelPolicy> hotelpolicyClass = hotelpolicy.getClass();
        Field[] fields1 = hotelpolicyClass.getFields();
        StatementResult om = session.writeTransaction(new TransactionWork<StatementResult>() {
            @Override
            public StatementResult execute(Transaction tx) {
                for (Field field : fields1) {
                    try {
                        FiledNameReplace annotation = field.getAnnotation(FiledNameReplace.class);
                        String reallName = annotation.name();
                        String methodName = annotation.GetMethodName();
                        Method method = hotelpolicyClass.getMethod(methodName);
                        String invoke = (String) method.invoke(hotelpolicy);
                        if (invoke == null || "".equals(invoke)) {
                            continue;
                        }
                        String replace = invoke.replace("\"", "");
                        if (reallName.equals("可用支付方式")) {//单个数据添加
                            String[] split = replace.split(",");
                            for (String sp : split) {
//                                StatementResult run1 = tx.run("match(sf3:Shope_policy{name:\"" + reallName + "\",shuxing:\"" + sp + "\"}) return sf3");
//                                if (!run1.hasNext()) {
//                                    StatementResult run = tx.run("create(sf3:Shope_policy{name:\"" + reallName + "\",shuxing:\"" + sp+ "\"})");
//                                }

                                if("".equals(sp)) continue;
                                StatementResult run = tx.run("merge(sf3:Shope_policy{name:\"" + reallName + "\",shuxing:\"" + sp+ "\"})");

//                                //添加节点关系
//                                StatementResult run1 = tx.run("match(xiecheng:XCHotel{hotel_id:\"" + onlyValue + "\"})-[str:hotlPolicyRelationship{name:\"酒店包含的政策\"}]->(sf3:Shope_policy{name:\"" + reallName + "\",shuxing:\"" + sp + "\"}) return str");
//                                if (!run.hasNext()) {
//                                    tx.run("match(xiecheng:XCHotel{hotel_id:\"" + onlyValue + "\"}),(sf3:Shope_policy{name:\"" + reallName + "\",shuxing:\"" + sp + "\"}) create(xiecheng)-[str:hotlPolicyRelationship{name:\"酒店包含的政策\"}]->(sf3) return str");
//                                }
                                    tx.run("match(xiecheng:XCHotel{hotel_id:\"" + onlyValue + "\"}),(sf3:Shope_policy{name:\"" + reallName + "\",shuxing:\"" + sp + "\"}) merge(xiecheng)-[str:hotlPolicyRelationship{name:\"酒店包含的政策\"}]->(sf3) return str");



                            }

                        } else {
//                            StatementResult run1 = tx.run("match(sf3:Shope_policy{name:\"" + reallName + "\",shuxing:\"" + replace + "\"}) return sf3");
//                            if (!run1.hasNext()) {
//                                StatementResult run = tx.run("create(sf3:Shope_policy{name:\"" + reallName + "\",shuxing:\"" + replace + "\"})");
//                            }
                            StatementResult run = tx.run("merge(sf3:Shope_policy{name:\"" + reallName + "\",shuxing:\"" + replace + "\"})");


                            //添加节点关系
//                            StatementResult run1 = tx.run("match(xiecheng:XCHotel{hotel_id:\"" + onlyValue + "\"})-[str:hotlPolicyRelationship{name:\"酒店包含的政策\"}]->(sf3:Shope_policy{name:\"" + reallName + "\",shuxing:\"" + replace + "\"}) return str");
//                            if (!run.hasNext()) {
//                                tx.run("match(xiecheng:XCHotel{hotel_id:\"" + onlyValue + "\"}),(sf3:Shope_policy{name:\"" + reallName + "\",shuxing:\"" + replace + "\"}) create(xiecheng)-[str:hotlPolicyRelationship{name:\"酒店包含的政策\"}]->(sf3) return str");
//                            }

                            tx.run("match(xiecheng:XCHotel{hotel_id:\"" + onlyValue + "\"}),(sf3:Shope_policy{name:\"" + reallName + "\",shuxing:\"" + replace + "\"}) merge(xiecheng)-[str:hotlPolicyRelationship{name:\"酒店包含的政策\"}]->(sf3) return str");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
        });



        //周边
        Class<? extends hotelAroundFacility> aroundFacilityClass = aroundFacility.getClass();
        Field[] fieldss = aroundFacilityClass.getFields();
        StatementResult sm = session.writeTransaction(new TransactionWork<StatementResult>() {
            @Override
            public StatementResult execute(Transaction tx) {
                for (Field field : fieldss) {
                    try {
                        FiledNameReplace annotation = field.getAnnotation(FiledNameReplace.class);
                        String reallName = annotation.name();
                        String methodName = annotation.GetMethodName();
                        Method method = aroundFacilityClass.getMethod(methodName);
                        String invoke = (String) method.invoke(aroundFacility);
                        if (invoke == null || "".equals(invoke)) continue;
                            String replace = invoke.replace("\"", "");
//                            StatementResult run1 = tx.run("match(sf3:Shope_around_facility{name:\"" + reallName + "\",shuxing:\"" + replace + "\"}) return sf3");
//                            if (!run1.hasNext()) {
//                                StatementResult run = tx.run("create(sf3:Shope_around_facility{name:\"" + reallName + "\",shuxing:\"" + replace + "\"})");
//                            }
                        StatementResult run = tx.run("merge(sf3:Shope_around_facility{name:\"" + reallName + "\",shuxing:\"" + replace + "\"})");


//                        //添加节点关系
//                        StatementResult run1 = tx.run("match(xiecheng:XCHotel{hotel_id:\"" + onlyValue + "\"})-[str:ShopeAroundFacilityRelationship{name:\"酒店周边设施\"}]->(sf3:Shope_around_facility{name:\"" + reallName + "\",shuxing:\"" + replace + "\"}) return str");
//                        if (!run.hasNext()) {
//                             tx.run("match(xiecheng:XCHotel{hotel_id:\"" + onlyValue + "\"}),(sf3:Shope_around_facility{name:\"" + reallName + "\",shuxing:\"" + replace + "\"}) create(xiecheng)-[str:ShopeAroundFacilityRelationship{name:\"酒店周边设施\"}]->(sf3) return str");
//                        }
                        tx.run("match(xiecheng:XCHotel{hotel_id:\"" + onlyValue + "\"}),(sf3:Shope_around_facility{name:\"" + reallName + "\",shuxing:\"" + replace + "\"}) merge(xiecheng)-[str:ShopeAroundFacilityRelationship{name:\"酒店周边设施\"}]->(sf3) return str");




                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
        });

        //点评和印象


        StatementResult dianpingheyinxiang = session.writeTransaction(new TransactionWork<StatementResult>() {
            @Override
            public StatementResult execute(Transaction tx) {

                Map<String, String> impressions =
                        statics_impression.getParams();
                Set<String> keys =
                        impressions.keySet();
                for (String key : keys) {
                    String strValue = impressions.get(key);
                    String updatekey = key.replace("\"", "");
                    String updateValue = strValue.replace("\"", "");
//                    StatementResult run1 = tx.run("match(sf3:Shope_gust_impression{name:\"" + updatekey + "\",shuxing:\"" + updateValue + "\"}) return sf3");
//                    if (!run1.hasNext()) {
//                        StatementResult run = tx.run("create(sf3:Shope_gust_impression{name:\"" + updatekey + "\",shuxing:\"" + updateValue+ "\"})");
//                    }
                    if("".equals(updatekey)||"".equals(updateValue)) continue;

                    StatementResult run = tx.run("merge(sf3:Shope_gust_impression{name:\"" + updatekey + "\",shuxing:\"" + updateValue+ "\"})");


                 //添加关系
//                    StatementResult run = tx.run("match(xiecheng:XCHotel{hotel_id:\"" + onlyValue + "\"})-[str:ShopeZhukeImpressionRelationship{name:\"酒店住客印象\"}]->(sf3:Shope_gust_impression{name:\"" + updatekey + "\",shuxing:\"" + updateValue + "\"}) return sf3");
//                    if (!run.hasNext()) {
//                        tx.run("match(xiecheng:XCHotel{hotel_id:\"" + onlyValue + "\"}),(sf3:Shope_gust_impression{name:\"" + updatekey + "\",shuxing:\"" + updateValue + "\"}) create (xiecheng)-[str:ShopeZhukeImpressionRelationship{name:\"酒店住客印象\"}]->(sf3) return sf3");
//                    }

                    tx.run("match(xiecheng:XCHotel{hotel_id:\"" + onlyValue + "\"}),(sf3:Shope_gust_impression{name:\"" + updatekey + "\",shuxing:\"" + updateValue + "\"}) merge (xiecheng)-[str:ShopeZhukeImpressionRelationship{name:\"酒店住客印象\"}]->(sf3) return sf3");

                }
                Map<String, String> dianping = statics_dianpin.getParams();

                Set<String> dpkeys = dianping.keySet();
                for (String key : dpkeys) {
                    String strValue = dianping.get(key);
                    if (!key.equals(strValue)) {//点评不想等的时候，才是我们想要的
                        String updatekey = key.replace("\"", "");
                        String updateValue = strValue.replace("\"", "");
                        int intValue = Integer.parseInt(updateValue);
//                        StatementResult run1 = tx.run("match(sf3:Shope_gust_comments{name:\"" + updatekey + "\",shuxing:\"" + intValue + "\"}) return sf3");
//                        if (!run1.hasNext()) {
//                            StatementResult run = tx.run("create(sf3:Shope_gust_comments{name:\"" + updatekey + "\",shuxing:\"" + intValue+ "\"})");
//                        }
                        StatementResult run = tx.run("merge(sf3:Shope_gust_comments{name:\"" + updatekey + "\",shuxing:\"" + intValue+ "\"})");

                        //添加关系
//                        StatementResult run = tx.run("match(xiecheng:XCHotel{hotel_id:\"" + onlyValue + "\"})-[str:ShopeZhukeDianpingRelationship{name:\"酒店住客点评\"}]->(sf3:Shope_gust_comments{name:\"" + updatekey + "\",shuxing:\"" + intValue + "\"}) return str");
//                        if (!run.hasNext()) {
//                            tx.run("match(xiecheng:XCHotel{hotel_id:\"" + onlyValue + "\"}),(sf3:Shope_gust_comments{name:\"" + updatekey + "\",shuxing:\"" + intValue + "\"}) create (xiecheng)-[str:ShopeZhukeDianpingRelationship{name:\"酒店住客点评\"}]->(sf3) return str");
//                        }

                        tx.run("match(xiecheng:XCHotel{hotel_id:\"" + onlyValue + "\"}),(sf3:Shope_gust_comments{name:\"" + updatekey + "\",shuxing:\"" + intValue + "\"}) merge (xiecheng)-[str:ShopeZhukeDianpingRelationship{name:\"酒店住客点评\"}]->(sf3) return str");

                    }
                }
                return null;
            }
        });

    session.close();
    }

}
