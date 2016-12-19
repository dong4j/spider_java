package main.java.com.code.tumblr.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.util.Collection;
import java.util.List;


/**
 * @author Code.Ai (Email: Code.Ai@outlook.com)
 * @version 0.1
 * @link http://blog.csdn.net/codeai
 * @date 16/2/27
 * @describe
 */
public class SpiderUtilTest {
    //String url = "http://iptiger123.tumblr.com/";
//    String url = "http://pandadandan.tumblr.com/";
//    String url = "http://uuuseee.tumblr.com/";
//    String url = "http://ayouman.tumblr.com/";
//    String url = "http://smwife.tumblr.com/";
//    String url = "http://annoyinglyhopefulstarlight.tumblr.com/";
//    String url = "http://piapiapia888.tumblr.com/";
//    String url = "http://uuuseee.tumblr.com/";
//    String url = "http://zzzzzxxxbbb.tumblr.com/";
    String url = "http://inhwanzz.tumblr.com/";



    @Test
    public void testGetImageList() throws Exception {
        int page = 1;
        while(true){
            Collection<String> list =  FileUtil.saveToFile(SpiderUtil.getImageList(UrlUtil.BIGIMAGE,url,page++));
            if(list.size() == 0){
                list =  FileUtil.saveToFile(SpiderUtil.getImageList(UrlUtil.BIGIMAGE,url,page++));
                int size = list.size();
                if(size == 0){
                    System.out.println("搜索完成,页码:"+page);
                    break;
                }
            }
        }
    }

    @Test
    public void testGetVideoList() throws Exception {
        int page = 1;
        //Collection<String> list =  FileUtil.saveToFile(SpiderUtil.getVideoList(url, 2));
        while(true){
            Collection<String> list =  FileUtil.saveToFile(SpiderUtil.getVideoList(url, page++));
            if(list.size() == 0){
                list =  FileUtil.saveToFile(SpiderUtil.getVideoList(url, page++));
                if(list.size() == 0){
                    System.out.println("搜索完成,页码:"+page);
                    break;
                }
            }
        }
        //FileUtil.deleteDuplicate(SpiderUtil.getVideoList());
    }
}