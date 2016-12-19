package main.java.com.code.tumblr.client;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
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

public class SaveFile extends Thread{
    private int threadId;
    private int startIndex;
    private int endIndex;
    private String path;
    private String fileName;
    private RandomAccessFile raf;
    /**
     * @param path 下载文件在服务器上的路径
     * @param threadId 线程Id
     * @param startIndex 线程下载的开始位置
     * @param endIndex  线程下载的结束位置
     */
    public SaveFile(String path, String fileName, int threadId, int startIndex, int endIndex,RandomAccessFile raf) {
        super();
        this.path = path;
        this.fileName = fileName;
        this.threadId = threadId;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.raf = raf;
    }

    @Override
    public void run() {
        System.out.println("线程: "+threadId+" 开始下载");
        try {
            long            begintime = System.currentTimeMillis();
            URL               url  = new URL(path);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            //conn.setConnectTimeout(5000);
            //conn.setRequestMethod("GET");
            ////重要:请求服务器下载部分文件 指定文件的位置
            //conn.setRequestProperty("Range", "bytes="+startIndex+"-"+endIndex);
            ////从服务器请求全部资源返回200 ok如果从服务器请求部分资源 返回 206 ok
            //int code = conn.getResponseCode();
            //System.out.println("code:"+code);
            conn.connect();
            FileOutputStream    fos       = null;
            BufferedInputStream bis       = null;
            InputStream      is  = conn.getInputStream();//已经设置了请求的位置，返回的是当前位置对应的文件的输入流
            //RandomAccessFile raf = new RandomAccessFile(fileName, "rwd");
            //随机写文件的时候从哪个位置开始写
            raf.seek(startIndex);//定位文件

            byte[] buf = new byte[1024];
            int size = 0;
            //int len = 0;
            //byte[] buffer = new byte[1024];
            //while ((len = is.read(buffer)) != -1) {
            //    raf.write(buffer, 0, len);
            //}
            bis = new BufferedInputStream(conn.getInputStream());
            fos = new FileOutputStream(fileName);
            while ((size = bis.read(buf)) != -1) {
                fos.write(buf, 0, size);
            }

            is.close();
            raf.close();
            conn.disconnect();
            System.out.println("线程："+threadId+"下载完毕");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
