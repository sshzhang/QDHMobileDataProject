package com.Neo4j.FTP;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.net.SocketException;

public class FTPUtils {
    private final static Log logger = LogFactory.getLog(FTPUtils.class);
    public static FTPClient getFTPClient(String ftpHost, String ftpUserName,
                                         String ftpPassword) {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient = new FTPClient();
            ftpClient.connect(ftpHost);// 连接FTP服务器
            ftpClient.login(ftpUserName, ftpPassword);// 登陆FTP服务器
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                logger.info("未连接到FTP，用户名或密码错误。");
                ftpClient.disconnect();
            } else {
                logger.info("FTP连接成功。");
            }
        } catch (SocketException e) {
            e.printStackTrace();
            logger.info("FTP的IP地址可能错误，请正确配置。");
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("FTP的端口错误,请正确配置。");
        }
        return ftpClient;
    }

    /*
    * 从FTP服务器下载文件
    *
    * @param ftpHost FTP IP地址
    *
    * @param ftpUserName FTP 用户名
    *
    * @param ftpPassword FTP用户名密码
    *
    * @param ftpPort FTP端口
    *
    * @param ftpPath FTP服务器中文件所在路径 格式： ftptest/aa
    *
    * @param localPath 下载到本地的位置 格式：H:/download
    *
    * @param fileName 文件名称
    */
    public static void downloadFtpFile(String ftpHost, String ftpUserName,
                                       String ftpPassword, String ftpPath, String localPath,
                                       String fileName) {

        FTPClient ftpClient = null;
        try {
            ftpClient = getFTPClient(ftpHost, ftpUserName, ftpPassword);
            ftpClient.setControlEncoding("UTF-8"); // 中文支持
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            System.out.println(ftpClient.changeWorkingDirectory(ftpPath));
            FTPFile[] ftpFiles = ftpClient.listFiles();
            System.out.println(ftpFiles);

            System.out.println(ftpClient.changeWorkingDirectory("/to_qiandaohu/month_consume_report"));
            FTPFile[] ftpFiles1 = ftpClient.listFiles();
            System.out.println(ftpFiles1);
//            File localFile = new File(localPath + File.separatorChar + fileName);
//            OutputStream os = new FileOutputStream(localFile);
//            ftpClient.retrieveFile(fileName, os);
//            os.close();
            ftpClient.logout();

        } catch (FileNotFoundException e) {
            logger.error("没有找到" + ftpPath + "文件");
            e.printStackTrace();
        } catch (SocketException e) {
            logger.error("连接FTP失败.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("文件读取错误。");
            e.printStackTrace();
        }

    }

    public static void main(String... args) {

        //180.167.118.197  ftp.unionpaysmart.com
        FTPUtils.downloadFtpFile("180.167.118.197", "qiandaohu", "qiandaohulvwei!312@", "to_qiandaohu/day_report", "A:/resources/videoInformation/nf_prize_dataset.tar/download/download", "daily_hour_report_20180506.csv");


    }

}
