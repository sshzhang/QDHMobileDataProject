package com.qdh.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbHelper {

    private Connection connection = null;

    public DbHelper() {
        connection = getConnection();
    }

    private Connection getConnection() {
        try {
            Class.forName(DbConfig.NAME);
            connection = DriverManager.getConnection(DbConfig.URL, DbConfig.USER_NAME, DbConfig.PWD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }


    private void freeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Map<Object, Object> findSimpleLine(String sql, List<Object> params) {
        Map<Object, Object> map = null;
        if (connection != null) {
            try {
                PreparedStatement statement = connection.prepareStatement(sql);
                int i = 1;
                if (params != null && params.size() > 0)
                    for (Object param : params) {
                        statement.setObject(i++, param);
                    }
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
//                    获取总的列数
                    int Columncount = statement.getMetaData().getColumnCount();
                    map = new HashMap<Object, Object>();
                    for (int j = 1; j <= Columncount; j++) {
                        map.put(statement.getMetaData().getColumnLabel(j), resultSet.getObject(j));
                    }
                }
                resultSet.close();
            } catch (Exception e) {

            } finally {
                freeConnection();
            }
        }
        return map;
    }


    public boolean hasTheData(String sql, List<Object> params) {//是否存在数据
        boolean flages = false;
        if (connection != null) {
            try {
                PreparedStatement statement = connection.prepareStatement(sql);
                int i = 1;
                if (params != null && params.size() > 0)
                    for (Object param : params) {
                        statement.setObject(i++, param);
                    }
                ResultSet resultSet = statement.executeQuery();
                flages = resultSet.next() ? true : false;
                resultSet.close();
            } catch (Exception e) {

            } finally {
                freeConnection();
            }
        }
        return flages;
    }

    public List<Map<Object, Object>> findMoreLines(String sql, List<Object> params) {
        List<Map<Object, Object>> lists = null;
        if (connection != null) {
            try {
                lists = new ArrayList<Map<Object, Object>>();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                int i = 1;
                if (params != null && params.size() > 0)
                    for (Object param : params) {
                        preparedStatement.setObject(i++, param);
                    }
                ResultSet resultSet = preparedStatement.executeQuery();
                int ColumnSize = preparedStatement.getMetaData().getColumnCount();
                while (resultSet.next()) {//还有下一个元素，初始情况是指向第一个元素的前一个元素，所以需要先调用next方法
                    Map<Object, Object> map = new HashMap<Object, Object>();
                    for (int j = 1; j <= ColumnSize; j++) {
//                        获取别名信息
//                        System.out.println(preparedStatement.getMetaData().getColumnLabel(j));
//                        只获取真实名称，不包括别名
//                        System.out.println(preparedStatement.getMetaData().getColumnName(j));
                        map.put(preparedStatement.getMetaData().getColumnLabel(j), resultSet.getObject(j));
                    }

                    lists.add(map);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                freeConnection();
            }
        }
        return lists;

    }

    // TODO 释放   preparedStatementd对象
    public boolean exeUpdateOneLine(String sql, List<Object> params) {
        boolean flage = false;
        PreparedStatement preparedStatement = null;
        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement(sql);
                int i = 1;
                if (params != null && params.size() > 0)
                    for (Object para : params) preparedStatement.setObject(i++, para);

                int count = preparedStatement.executeUpdate();
                preparedStatement.close();
                return count > 0;
            } catch (SQLException e) {
                return false;
            } finally {

                if (preparedStatement != null) try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                freeConnection();
            }
        }
        return flage;
    }


    public static void main(String... args) {
//        DbHelper dbHelper = new DbHelper();
//        List<Object> params = new ArrayList<Object>();
//        params.add(10060);
//        params.add(1001);
//        boolean bst = dbHelper.exeUpdateOneLine("delete from qdh_ykrs where spotId=? and kpiId=?", params);
//        System.out.println(bst);



    }

    //存储过程调用  有输入输出值的存储过程
    public Object exeSimpleDataProcedure(String sql, List<Object> params) {
        if (connection != null) {
            try {
                CallableStatement callableStatement = connection.prepareCall(sql);
                int i = 0;
                if (params != null && params.size() > 0)
                    for (Object param : params)
                        callableStatement.setObject(++i, param);
                callableStatement.registerOutParameter(++i, Types.OTHER);
                callableStatement.execute();
                Object anInt = callableStatement.getObject(i);
                callableStatement.close();
                return anInt;
            } catch (SQLException e) {
                return null;
            }
        }
        return null;
    }

    //存储过程，查询 有输入，没输出
    public List<Map<Object, Object>> exeComplicateQueryDataProcedure(String sql, List<Object> params) {

        if (connection != null) {
            try {
                CallableStatement callableStatement = connection.prepareCall(sql);
                int i = 0;
                if (params != null && params.size() > 0)
                    for (Object param : params) callableStatement.setObject(++i, param);
                ResultSet resultSet = callableStatement.executeQuery();
                List<Map<Object, Object>> results = new ArrayList<Map<Object, Object>>();
                int columnCount = resultSet.getMetaData().getColumnCount();
                while (resultSet.next()) {
                    Map<Object, Object> objectHashMap = new HashMap<Object, Object>();
                    for (int j = 1; j <= columnCount; j++) {
                        String key = resultSet.getMetaData().getColumnLabel(j);
                        objectHashMap.put(key, resultSet.getObject(key));
                    }
                    results.add(objectHashMap);
                }
                return results;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            } finally {
                freeConnection();
            }

        }

        return null;
    }


    public boolean BatchUpdateMoreLines(String sql, List<List<Object>> params) {

        if (connection != null) {
            try {
//                设置事物
                connection.setAutoCommit(false);
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                if (params != null && params.size() > 0) {
                    for (List<Object> param : params) {
                        int i = 1;
                        for (Object par : param)
                            preparedStatement.setObject(i++, par);
                        preparedStatement.executeUpdate();
                    }
                } else {
                    preparedStatement.executeUpdate();
                }

                connection.commit();
            } catch (SQLException e) {

                try {
                    connection.rollback();
                    return false;
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            } finally {
                freeConnection();
            }


        }
        return true;
    }

    //插入一条记录操作
    public boolean InsertSimpleLine(String sql, List<Object> params) {

        if (connection != null) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                if (params != null)
                    for (int i = 0; i < params.size(); i++) {
                        preparedStatement.setObject(i + 1, params.get(i));
                    }
                return preparedStatement.executeUpdate() > 0 ? true : false;

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                freeConnection();
            }
            return false;
        }
        return false;


    }

    //批量插入数据操作
    public boolean BacthInsertMoreLines(String sql, List<List<Object>> params) {

        if (connection != null) {

            try {
                connection.setAutoCommit(false);
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
//                for (Map<String, String> param : params) {//读取Excel中内容时，使用的遍历
//                    preparedStatement.setInt(1, Integer.parseInt(param.get("id")));
//                    preparedStatement.setInt(2, Integer.parseInt(param.get("prov_id")));
//                    preparedStatement.setString(3, param.get("prov_name"));
//                    preparedStatement.setInt(4, Integer.parseInt(param.get("city_id")));
//                    preparedStatement.setString(5, param.get("city_name"));
//                    preparedStatement.addBatch();
//                }
                for (List<Object> param : params) {
                    for (int i = 0; i < param.size(); i++) {
                        preparedStatement.setObject(i + 1, param.get(i));
                    }
                    preparedStatement.addBatch();
                }


                preparedStatement.executeBatch();
                connection.commit();

            } catch (SQLException e) {
                try {
                    connection.rollback();
                    return false;
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            } finally {
                freeConnection();
            }

        }
        return true;
    }

//    public static void main(String... args) {
//        DbHelper dbHelper = new DbHelper();
//        String SQL = "{CALL Category_ContentInfosUpdate(5,2)}";
//        List<Map<Object, Object>> listmaps = dbHelper.exeComplicateQueryDataProcedure(SQL, null);
//        System.out.println(listmaps.size());
//    }


}
