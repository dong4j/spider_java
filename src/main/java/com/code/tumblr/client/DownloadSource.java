package main.java.com.code.tumblr.client;

import main.java.com.code.tumblr.util.UrlUtil;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Code.Ai (Email: Code.Ai@outlook.com)
 * @version 0.1
 * @link http://blog.csdn.net/codeai
 * @date 16/2/27
 * @describe
 */

public class DownloadSource {
    /**
     * 多线程下载
     * 说明：
     * 每一个线程下载的位置计算方式：
     * 开始位置：
     * (线程id - 1)*每一块大小
     * 结束位置：
     * (线程id*每一块大小) - 1
     *  ---注意有时候不一定能够整除，所以最后一个线程的结束位置应该是文件的末尾
     *
     *  步骤：
     *  1.本地创建一个大小跟服务器文件相同的临时文件
     *  2.计算分配几个线程去下载服务器上的资源，知道每个线程下载文件的位置
     *  3.开启三个线程，每一个线程下载对应位置的文件
     *  4.如果所有的线程，都把自己的数据下载完毕后，服务器上的资源都被下载到本地了
     */
    public void downVideos(String destUrl, String fileName) throws IOException {
        System.out.println("fileName:"+fileName);
        //1.连接服务器，获取一个文件，获取文件的长度，在本地创建一个跟服务器一样大小的临时文件
        URL               url  = new URL(destUrl);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setConnectTimeout(5000);
        //conn.setRequestMethod("GET");
        long code = conn.getResponseCode();
        if (code == 200) {
            //服务器端返回的数据的长度，实际上就是文件的长度
            int length = conn.getContentLength();
            System.out.println("文件总长度："+length);
            //在客户端本地创建出来一个大小跟服务器端一样大小的临时文件
            RandomAccessFile raf = new RandomAccessFile(fileName, "rwd");
            //指定创建的这个文件的长度
            raf.setLength(length);
            //raf.close();
            //假设是3个线程去下载资源。
            //平均每一个线程下载的文件大小.
            int blockSize = length / UrlUtil.VIDEO_THREAD;
            for (int threadId = 1; threadId <= UrlUtil.VIDEO_THREAD; threadId++) {
                //第一个线程下载的开始位置
                int startIndex = (threadId - 1) * blockSize;
                int endIndex = threadId * blockSize - 1;
                if (threadId == UrlUtil.VIDEO_THREAD) {//最后一个线程下载的长度要稍微长一点
                    endIndex = length;
                }
                System.out.println("线程："+threadId+"下载:---"+startIndex+"--->"+endIndex);
                new SaveFile(destUrl, fileName, threadId, startIndex, endIndex,raf).start();
            }

        }else {
            System.out.printf("服务器错误!");
        }
    }

    //单线程下载图片
    public void downImages(String destUrl, String fileName) throws IOException{
        long                begintime = System.currentTimeMillis();
        FileOutputStream    fos       = null;
        BufferedInputStream bis       = null;
        HttpURLConnection   httpUrl   = null;
        //类 URL 代表一个统一资源定位符，它是指向互联网“资源”的指针
        URL url = null;
        byte[] buf = new byte[1024];
        int size = 0;
        url = new URL(destUrl);
        System.out.println("URL is " + url.toString());
        //System.out.println("protocol is     " + url.getProtocol());
        //System.out.println("authority is    " + url.getAuthority());
        //System.out.println("file name is    " + url.getFile());
        //System.out.println("host is         " + url.getHost());
        //System.out.println("path is         " + url.getPath());
        //System.out.println("port is         " + url.getPort());
        //System.out.println("default port is " + url.getDefaultPort());
        //System.out.println("query is        " + url.getQuery());
        //System.out.println("ref is          " + url.getRef());

        //获取HttpURLConnection 对象
        httpUrl = (HttpURLConnection) url.openConnection();
        //连接
        httpUrl.connect();

        //System.out.println(" content-encode：    "+httpUrl.getContentEncoding());
        //获取资源长度
        System.out.println("content-length：    "+httpUrl.getContentLength());
        //获取资源类型
        //System.out.println(" content-type：      "+httpUrl.getContentType());
        //System.out.println(" date：              "+httpUrl.getDate());

        bis = new BufferedInputStream(httpUrl.getInputStream());
        fos = new FileOutputStream(fileName);
        while ((size = bis.read(buf)) != -1) {
            fos.write(buf, 0, size);
        }
        System.out.println("执行时间为："+(System.currentTimeMillis()-begintime)+"毫秒");
        fos.close();
        bis.close();
        httpUrl.disconnect();
    }

    //多线程下载图片
    public void downImagesForThread(String destUrl, String fileName) throws IOException{
        //1.连接服务器，获取一个文件，获取文件的长度，在本地创建一个跟服务器一样大小的临时文件
        URL               url  = new URL(destUrl);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
        int code = conn.getResponseCode();
        if (code == 200) {
            //服务器端返回的数据的长度，实际上就是文件的长度
            int length = conn.getContentLength();
            System.out.println("文件总长度："+length);
            //在客户端本地创建出来一个大小跟服务器端一样大小的临时文件
            RandomAccessFile raf = new RandomAccessFile(fileName, "rwd");
            //指定创建的这个文件的长度
            raf.setLength(length);
            raf.close();
            //假设是3个线程去下载资源。
            //平均每一个线程下载的文件大小.
            int blockSize = length / UrlUtil.IMAGE_THREAD;
            for (int threadId = 1; threadId <= UrlUtil.IMAGE_THREAD; threadId++) {
                //第一个线程下载的开始位置
                int startIndex = (threadId - 1) * blockSize;
                int endIndex = threadId * blockSize - 1;
                if (threadId == UrlUtil.IMAGE_THREAD) {//最后一个线程下载的长度要稍微长一点
                    endIndex = length;
                }
                System.out.println("线程："+threadId+"下载:---"+startIndex+"--->"+endIndex);
                //new SaveFile(destUrl, fileName, threadId, startIndex, endIndex).start();
            }

        }else {
            System.out.printf("服务器错误!");
        }
    }
}
