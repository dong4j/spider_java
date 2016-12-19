package main.java.com.code.tumblr.util;

import main.java.com.code.tumblr.bean.Source;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author Code.Ai (Email: Code.Ai@outlook.com)
 * @version 0.1
 * @link http://blog.csdn.net/codeai
 * @date 16/2/27
 * @describe
 */

public class SpiderUtil {
    /**
     * 得到 html5的 video 标签对象,取其中的 source 属性集合
     * @return
     */
    public static List<String> getImageList(int type,String url,int page){
        List<String> sourceList = new ArrayList<String>();
        try{
            //1. 解析主页
            Document  indexHtml = Jsoup.connect(url+"page/"+page).get();
            List<String> pointerLinksList = UrlUtil.analysisImageIndexHtml(indexHtml);
            for(String pointerLink : pointerLinksList){
                Document  sourceHtml = Jsoup.connect(pointerLink).get();
                List<String> imageList = UrlUtil.analysisImagePointer(sourceHtml,type);
                sourceList.addAll(imageList);
            }
            int a = 1;
            for(String s : sourceList){
                System.out.println(s);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return sourceList;
    }

    public static List<String> getVideoList(String url,int page) {
        List<String> sourceList = new ArrayList<String>();
        try{
            //1. 解析主页
            System.out.println(url+"page/"+page);
            Document  indexHtml = Jsoup.connect(url+"page/"+page).timeout(10000).get();
//            Document  indexHtml = Jsoup.connect(url+"page/"+page).proxy("127.0.0.1",1080).get();
            System.out.println(indexHtml.toString());
            List<String> pointerLinksList = UrlUtil.analysisVideoIndexHtml(indexHtml);
            //for(String s : pointerLinksList){
            //    System.out.println(s);
            //}
            for(String pointerLink : pointerLinksList){
                Document  sourceHtml = Jsoup.connect(pointerLink.replaceAll("https","http")).get();
                sourceList.add(UrlUtil.analysisVideoPointer(sourceHtml));
            }
            int a = 1;
            //for(String s : sourceList){
            //    System.out.println((a++)+": "+s);
            //}
        }catch (SocketTimeoutException ex){
            ex.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return sourceList;
    }
}
