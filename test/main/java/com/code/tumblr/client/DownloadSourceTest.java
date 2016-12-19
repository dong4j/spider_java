package main.java.com.code.tumblr.client;

import main.java.com.code.tumblr.bean.Source;
import main.java.com.code.tumblr.util.SpiderUtil;
import main.java.com.code.tumblr.util.UrlUtil;

import java.util.Date;
import java.util.List;


/**
 * @author Code.Ai (Email: Code.Ai@outlook.com)
 * @version 0.1
 * @link http://blog.csdn.net/codeai
 * @date 16/2/27
 * @describe
 */
public class DownloadSourceTest {
    String savePath = "/Users/codeai/Desktop/download/video/";
    @org.junit.Test
    public void testDownVideos() throws Exception {
        List<String> sources   = SpiderUtil.getVideoList("",1);
        for(String source : sources){
            System.out.println(source);
            new DownloadSource().downVideos(source,savePath + UrlUtil.splitUrl(UrlUtil.VIEDO_REALElINK,source));
        }
        //new DownloadSource().downVideos("http://vt.tumblr.com/tumblr_nvcbl8JRrY1ufwk6d.mp4","/Users/codeai/Desktop/" + UrlUtil.splitUrl(UrlUtil.VIEDO_REALElINK,"http://vt.tumblr.com/tumblr_nvcbl8JRrY1ufwk6d.mp4"));
    }

    @org.junit.Test
    public void testDownImages() throws Exception {
        List<String> sources   = SpiderUtil.getImageList(UrlUtil.BIGIMAGE,"url",1);
        long         startTime = new Date().getTime();
        for(String source : sources){
            //下载图片
            new DownloadSource().downImages(source, savePath + UrlUtil.splitUrl(UrlUtil.IMAGE,source));
            //new DownloadSource().downImagesForThread(source, "/Users/codeai/Desktop/download/image/" + UrlUtil.splitUrl(UrlUtil.IMAGE,source));
        }
        long endTime = new Date().getTime();
        System.out.println("花费时间:"+(endTime - startTime));
    }
}