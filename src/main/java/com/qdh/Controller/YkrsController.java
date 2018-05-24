package com.qdh.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.qdh.Beans.SightBean;
import com.qdh.Beans.YkrsBean;
import com.qdh.Beans.YkrsBeans;
import com.qdh.DBDao.SightDao;
import com.qdh.DBDao.SightImpl;
import com.qdh.DBUtils.DbHelper;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YkrsController {//游客人数控制器
    //五分钟 十分钟  十五分钟  一个小时  一天
    private static final int[] kpiIds = new int[]{1001, 1002, 1003, 1004, 1005};
    private static final long[] period = new long[]{5 * 60, 10 * 60, 15 * 60, 60 * 60, 24 * 60 * 60};

    //每一个小时更新一次， 一个小时以前的数据
    public static void main(String... args) {

        ScheduledExecutorService scheduledExecutorService =
                Executors.newScheduledThreadPool(1);
        SightDao sightDao = new SightImpl();
        List<SightBean> allSightsByLevels = sightDao.findAllSightsByLevels(3);
        scheduledExecutorService.scheduleAtFixedRate(new MyThread(1001, allSightsByLevels), 2, period[0], TimeUnit.SECONDS);


//        String std = "[{\"name\":\"游客量\",\"result\":[{\"id\":3473700,\"kpiId\":\"1003\",\"kpiTime\":\"2018/04/21 00:00:00\",\"kpiValue\":\"0\",\"spotId\":\"10060\"},{\"id\":3474089,\"kpiId\":\"1003\",\"kpiTime\":\"2018/04/21 00:15:00\",\"kpiValue\":\"0\",\"spotId\":\"10060\"},{\"id\":3474449,\"kpiId\":\"1003\",\"kpiTime\":\"2018/04/21 00:30:00\",\"kpiValue\":\"0\",\"spotId\":\"10060\"},{\"id\":3474904,\"kpiId\":\"1003\",\"kpiTime\":\"2018/04/21 00:45:00\",\"kpiValue\":\"0\",\"spotId\":\"10060\"},{\"id\":3475261,\"kpiId\":\"1003\",\"kpiTime\":\"2018/04/21 01:00:00\",\"kpiValue\":\"0\",\"spotId\":\"10060\"},{\"id\":3475653,\"kpiId\":\"1003\",\"kpiTime\":\"2018/04/21 01:15:00\",\"kpiValue\":\"0\",\"spotId\":\"10060\"},{\"id\":3476009,\"kpiId\":\"1003\",\"kpiTime\":\"2018/04/21 01:30:00\",\"kpiValue\":\"0\",\"spotId\":\"10060\"},{\"id\":3476469,\"kpiId\":\"1003\",\"kpiTime\":\"2018/04/21 01:45:00\",\"kpiValue\":\"0\",\"spotId\":\"10060\"},{\"id\":3476826,\"kpiId\":\"1003\",\"kpiTime\":\"2018/04/21 02:00:00\",\"kpiValue\":\"0\",\"spotId\":\"10060\"},{\"id\":3477216,\"kpiId\":\"1003\",\"kpiTime\":\"2018/04/21 02:15:00\",\"kpiValue\":\"0\",\"spotId\":\"10060\"},{\"id\":3477581,\"kpiId\":\"1003\",\"kpiTime\":\"2018/04/21 02:30:00\",\"kpiValue\":\"0\",\"spotId\":\"10060\"},{\"id\":3478101,\"kpiId\":\"1003\",\"kpiTime\":\"2018/04/21 02:45:00\",\"kpiValue\":\"0\",\"spotId\":\"10060\"},{\"id\":3478458,\"kpiId\":\"1003\",\"kpiTime\":\"2018/04/21 03:00:00\",\"kpiValue\":\"0\",\"spotId\":\"10060\"},{\"id\":3478850,\"kpiId\":\"1003\",\"kpiTime\":\"2018/04/21 03:15:00\",\"kpiValue\":\"0\",\"spotId\":\"10060\"},{\"id\":3479206,\"kpiId\":\"1003\",\"kpiTime\":\"2018/04/21 03:30:00\",\"kpiValue\":\"0\",\"spotId\":\"10060\"},{\"id\":3479664,\"kpiId\":\"1003\",\"kpiTime\":\"2018/04/21 03:45:00\",\"kpiValue\":\"0\",\"spotId\":\"10060\"},{\"id\":3480021,\"kpiId\":\"1003\",\"kpiTime\":\"2018/04/21 04:00:00\",\"kpiValue\":\"0\",\"spotId\":\"10060\"},{\"id\":3480412,\"kpiId\":\"1003\",\"kpiTime\":\"2018/04/21 04:15:00\",\"kpiValue\":\"0\",\"spotId\":\"10060\"},{\"id\":3480772,\"kpiId\":\"1003\",\"kpiTime\":\"2018/04/21 04:30:00\",\"kpiValue\":\"0\",\"spotId\":\"10060\"},{\"id\":3481230,\"kpiId\":\"1003\",\"kpiTime\":\"2018/04/21 04:45:00\",\"kpiValue\":\"0\",\"spotId\":\"10060\"},{\"id\":3481586,\"kpiId\":\"1003\",\"kpiTime\":\"2018/04/21 05:00:00\",\"kpiValue\":\"0\",\"spotId\":\"10060\"},{\"id\":3481976,\"kpiId\":\"1003\",\"kpiTime\":\"2018/04/21 05:15:00\",\"kpiValue\":\"0\",\"spotId\":\"10060\"},{\"id\":3482337,\"kpiId\":\"1003\",\"kpiTime\":\"2018/04/21 05:30:00\",\"kpiValue\":\"0\",\"spotId\":\"10060\"},{\"id\":3482793,\"kpiId\":\"1003\",\"kpiTime\":\"2018/04/21 05:45:00\",\"kpiValue\":\"0\",\"spotId\":\"10060\"},{\"id\":3483151,\"kpiId\":\"1003\",\"kpiTime\":\"2018/04/21 06:00:00\",\"kpiValue\":\"0\",\"spotId\":\"10060\"},{\"id\":3483540,\"kpiId\":\"1003\",\"kpiTime\":\"2018/04/21 06:15:00\",\"kpiValue\":\"0\",\"spotId\":\"10060\"},{\"id\":3483898,\"kpiId\":\"1003\",\"kpiTime\":\"2018/04/21 06:30:00\",\"kpiValue\":\"0\",\"spotId\":\"10060\"},{\"id\":3484356,\"kpiId\":\"1003\",\"kpiTime\":\"2018/04/21 06:45:00\",\"kpiValue\":\"0\",\"spotId\":\"10060\"},{\"id\":3484715,\"kpiId\":\"1003\",\"kpiTime\":\"2018/04/21 07:00:00\",\"kpiValue\":\"0\",\"spotId\":\"10060\"},{\"id\":3485104,\"kpiId\":\"1003\",\"kpiTime\":\"2018/04/21 07:15:00\",\"kpiValue\":\"0\",\"spotId\":\"10060\"},{\"id\":3485464,\"kpiId\":\"1003\",\"kpiTime\":\"2018/04/21 07:30:00\",\"kpiValue\":\"0\",\"spotId\":\"10060\"},{\"id\":3485922,\"kpiId\":\"1003\",\"kpiTime\":\"2018/04/21 07:45:00\",\"kpiValue\":\"0\",\"spotId\":\"10060\"},{\"id\":3486278,\"kpiId\":\"1003\",\"kpiTime\":\"2018/04/21 08:00:00\",\"kpiValue\":\"0\",\"spotId\":\"10060\"},{\"id\":3486669,\"kpiId\":\"1003\",\"kpiTime\":\"2018/04/21 08:15:00\",\"kpiValue\":\"0\",\"spotId\":\"10060\"}],\"success\":true}]";
//
//        List<YkrsBeans> ykrsBeans = JSON.parseObject(std, new TypeReference<List<YkrsBeans>>() {
//        });
//
//        System.out.println(ykrsBeans);

    }


    private static class MyThread extends Thread {

        private int kpiId;
        private List<SightBean> spotIds;

        protected MyThread(int kpiId, List<SightBean> spotIds) {
            this.kpiId = kpiId;
            this.spotIds = spotIds;
        }

        @Override
        public void run() {
            //构造kpiTime

            String kpiTime = new SimpleDateFormat("yyyy/MM/dd").format(System.currentTimeMillis());
            for (SightBean sightBean : spotIds) {
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("kpiId", String.valueOf(kpiId)));
                nameValuePairs.add(new BasicNameValuePair("spotId", String.valueOf(sightBean.getArea_id())));
                nameValuePairs.add(new BasicNameValuePair("kpiTime", kpiTime));
                try {
                    String std = new RequestTrackleClass(nameValuePairs).TrackleDetail();
                    //解析数据
                    List<YkrsBeans> ykrsBeans = JSON.parseObject(std, new TypeReference<List<YkrsBeans>>() {
                    });
                    DbHelper dbHelper = new DbHelper();
                    YkrsBeans ykrsBeans1 = ykrsBeans.get(0);
                    List<YkrsBean> result = ykrsBeans1.getResult();
                    if (result == null || result.size() == 0) {//数据为空出现异常
                        List<Object> params = new ArrayList<Object>();
                        params.add(String.valueOf(sightBean.getArea_id()));
                        params.add(0);
                        params.add(0);
                        params.add(kpiId);
                        params.add(ykrsBeans1.getSuccess());
                        boolean bioflages = dbHelper.hasTheData("select * from qdh_ykrs where spotId=? and kpiTime=? and kpiValue=? and kpiId=? and success=?", params);
                        if (!bioflages) {
                            DbHelper helper1 = new DbHelper();
                            //先删除 在插入
                            List<Object> params1 = new ArrayList<Object>();
                            params1.add(String.valueOf(sightBean.getArea_id()));
                            params1.add(kpiId);
                            boolean sf = helper1.exeUpdateOneLine("delete from qdh_ykrs  where spotId=? and kpiId=?", params1);
                            System.out.println("删除qdh_ykrs中 spotId和kpiId" + "(" + sightBean.getArea_id() + "," + kpiId + ")"+" "+sf);
                            DbHelper helper2 = new DbHelper();
                            helper2.InsertSimpleLine("insert into qdh_ykrs(spotId,kpiTime,kpiValue,kpiId,success) values(?,?,?,?,?)", params);
                        }
                    } else {
                        YkrsBean ykrsBean = result.get(result.size() - 1);
                        List<Object> params = new ArrayList<Object>();
                        params.add(ykrsBean.getSpotId());
                        params.add(ykrsBean.getKpiTime());
                        params.add(ykrsBean.getKpiValue());
                        params.add(ykrsBean.getKpiId());
                        params.add(ykrsBeans1.getSuccess());
                        //存在最新元素
                        boolean bioflages = dbHelper.hasTheData("select * from qdh_ykrs where spotId=? and kpiTime=? and kpiValue=? and kpiId=? and success=?", params);
                        if (!bioflages) {
                            DbHelper helper1 = new DbHelper();
                            List<Object> params1 = new ArrayList<Object>();
                            params1.add(ykrsBean.getSpotId());
                            params1.add(ykrsBean.getKpiId());

                            boolean sf=helper1.exeUpdateOneLine("delete from qdh_ykrs where spotId=? and kpiId=?", params1);
                            System.out.println("删除qdh_ykrs中 spotId和kpiId" + "(" + ykrsBean.getSpotId() + "," + ykrsBean.getKpiId() + ")"+" "+sf);
                            DbHelper helper2 = new DbHelper();
                            helper2.InsertSimpleLine("insert into qdh_ykrs(spotId,kpiTime,kpiValue,kpiId,success) values(?,?,?,?,?)", params);
                        }
                    }
                    System.out.println(std);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }


    //请求数据的辅助类
    private static class RequestTrackleClass {
        private List<NameValuePair> valuePairs;
        private static final String url = "http://115.29.3.3:8080/qdhdc/ydrk/getYkrs";

        public RequestTrackleClass(List<NameValuePair> valuePairs) {
            this.valuePairs = valuePairs;
        }

        private static final CloseableHttpClient httpclient = HttpClients.createDefault();

        public String TrackleDetail() throws IOException {
            CloseableHttpResponse execute = null;
            try {
                HttpGet httpGet = new HttpGet(url + "?" + EntityUtils.toString(new UrlEncodedFormEntity(valuePairs, "utf-8")));
                execute = httpclient.execute(httpGet);
                int statusCode = execute.getStatusLine().getStatusCode();
                if (statusCode != 200) {
                    throw new RuntimeException("数据访问异常" + statusCode);
                }
                System.out.println(httpGet.getURI());
                HttpEntity entity =
                        execute.getEntity();
                String result = null;
                if (entity != null) {
                    result = EntityUtils.toString(entity, "utf-8");
                }
                return result;
            } finally {
                if (execute != null)
                    execute.close();
            }

        }
    }


}
