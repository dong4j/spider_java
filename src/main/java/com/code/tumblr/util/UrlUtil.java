package main.java.com.code.tumblr.util;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Code.Ai (Email: Code.Ai@outlook.com)
 * @version 0.1
 * @link http://blog.csdn.net/codeai
 * @date 16/2/27
 * @describe
 */

public class UrlUtil {
    public static final int VIDEO = 1;
    public static final int IMAGE = 2;
    public static final int VIEDO_REALElINK = 3;

    public static final int VIDEO_THREAD = 3;
    public static final int IMAGE_THREAD = 2;
    public static final int BIGIMAGE = 1;
    public static final int SMALLIMAGE = 2;

    /**
     * 负责拆分 url 只需要最后一个 "/" 后面的字符串
     * @param url
     */
    public static String splitUrl(int type,String url){
        String[] str_1 = url.split("/");
//        for(int i = 0 ; i < str_1.length; i++){
//            System.out.println(i+":"+str_1[i]);
//        }
        if(type == 1)       return str_1[5];
        else if(type ==3)   return str_1[3];
        else if(type ==2)   return str_1[4];
        else                return "";
    }

    public static String getRealUrl(String url){
        // https://vt.tumblr.com/tumblr_nz0wyfdRQV1uvt6zm.mp4
        return "https://vt.tumblr.com/" + url + ".mp4";
    }

    //解析图片主页
    public static List<String> analysisImageIndexHtml(Document indexHtml){
        Elements pointerLinks = indexHtml.select("iframe.photoset");
        //1.1将连接添加到列表中
        List<String> pointerLinksList = new ArrayList<String>();
        for(Element imagePointer : pointerLinks){
            String pointer = imagePointer.attr("src");
            pointerLinksList.add(pointer);
        }
        return pointerLinksList;
    }

    //解析图片引用
    public static List<String> analysisImagePointer(Document pointerHtml,int type){
        Elements sourceLinks = null;
        List<String> imageLinksList = new ArrayList<String>();
        if(type == BIGIMAGE){
            sourceLinks = pointerHtml.select("a");
            for(Element imageSource : sourceLinks){
                //大图
                String bigImage = imageSource.attr("href");
                imageLinksList.add(bigImage);
            }
        }else {
            sourceLinks = pointerHtml.select("img");
            for(Element imageSource : sourceLinks){
                //小图
                String smallImage = imageSource.attr("src");
                imageLinksList.add(smallImage);
            }
        }
        return imageLinksList;
    }

    //解析图片主页
    public static List<String> analysisVideoIndexHtml(Document indexHtml){
        Elements pointerLinks = indexHtml.select("iframe.tumblr_video_iframe");
        //1.1将连接添加到列表中
        List<String> pointerLinksList = new ArrayList<String>();
        for(Element videoPointer : pointerLinks){
            String pointer = videoPointer.attr("src");
            pointerLinksList.add(pointer);
        }
        return pointerLinksList;
    }

    public static String analysisVideoPointer(Document sourceHtml){
        String pointerLink = sourceHtml.select("source").attr("src");
        String realLink = getRealUrl(splitUrl(UrlUtil.VIDEO, pointerLink)).replaceAll("https","http");
        System.out.println(realLink);
        return realLink;
    }

}
