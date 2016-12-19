package main.java.com.code.imgur;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import main.java.com.code.tumblr.bean.ImgurEntity;
import main.java.com.code.tumblr.util.FileUtil;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Code.Ai on 16/7/12.
 */
public class ImgurHtml {
    // 解析主页 循环获取资源列表
    public static List<String> rootHtml(String rootUrl) throws IOException {
        Document rootHtml = Jsoup.connect(rootUrl).get();
        //Document rootHtml = disguise(rootUrl);
        // 获取 div id
        Elements pointerLinks = rootHtml.select("div.post");
        //1.1将连接添加到列表中
        List<String> pointerLinksList = new ArrayList<>();
        for(Element imagePointer : pointerLinks){
            String pointer = imagePointer.attr("id");
            System.out.println(pointer);
            FileUtil.saveToFileOneByOne("/Users/codeai/Desktop/hash.txt",pointer,"");
            FileUtil.saveToFileOneByOne("/Users/codeai/Desktop/url.txt","http://i.imgur.com/" + pointer + ".mp4","url");
            pointerLinksList.add("http://i.imgur.com/" + pointer + ".mp4");


            //FileUtil.saveToFileOneByOne("/Users/codeai/Desktop/gif.txt",pointer,"gif");
            //String url = childHtml(pointer);
            //FileUtil.saveToFileOneByOne("/Users/codeai/Desktop/url.txt",url,"url");
            //pointerLinksList.add(url);
        }
        return pointerLinksList;
    }

    /**
     * 获取 hash
     * @param rootUrl
     * @return
     * @throws IOException
     */
    public static List<String> getImages(String rootUrl) throws IOException {
        Document rootHtml = Jsoup.connect(rootUrl).get();
        //Document rootHtml = disguise(rootUrl);
        // 获取 div id
        Elements pointerLinks = rootHtml.select("div.post");
        //1.1将连接添加到列表中
        List<String> pointerLinksList = new ArrayList<>();
        for(Element imagePointer : pointerLinks){
            String pointer = imagePointer.attr("id");
            String url = "http://i.imgur.com/" + pointer + ".jpg";
            pointerLinksList.add(url);
        }
        return pointerLinksList;
    }

    /**
     * 第一种解析方式:根据 rootHtml 获取的 pointer 构建 url http://imgur.com/r/nsfw_gifs/pointer 然后获取 html 页面再解析
     * 造成获取了过多的无用信息
     * @param pointer
     * @return
     * @throws IOException
     */
    public static String childHtml(String pointer) throws IOException {
        //Document childHtml = Jsoup.connect("http://imgur.com/r/nsfw_gifs/"+pointer).get();
        Document childHtml = Jsoup.connect("http://i.imgur.com/" + pointer).get();
        //Document childHtml = disguise("http://i.imgur.com/" + pointer);
//        Elements pointerLinks = childHtml.select("div.video-container");
        Element pointerNode = childHtml.getElementById(pointer);
        Elements scriptNode = pointerNode.select("script");
        String scriptString = scriptNode.get(0).html();
        // 从 { 开始截取 直到 } 然后转换为 json 对象
        String imgurString = scriptString.substring(scriptString.indexOf("{"));
        // 将 json 格式的字符串转换为 json 对象
        // JSONObject jsonObject = JSON.parseObject(imgurString);
        // System.out.println(jsonObject.get("size"));
        // 将 json 格式的字符串转换为JavaBean 对象
        ImgurEntity imgurEntity = JSON.parseObject(imgurString,ImgurEntity.class);
        //System.out.println(imgurEntity.getSize()/1024);
        String url = "";
        if(imgurEntity.getPrefer_video() || imgurEntity.getSize()/1024 >= 20000) {
            url = "http://120.52.73.67/i.imgur.com/" + imgurEntity.getHash() + ".mp4";
        }
        else{
            url = "http://i.imgur.com/" + imgurEntity.getHash() + ".gif";
        }
        System.out.println(url);
        return url;
    }

    /**
     * 请求头伪装为浏览器
     * @return
     * @throws IOException
     */
    public static Document disguise(String url) throws IOException {
        Connection          connect = Jsoup.connect(url);
        Map<String, String> header  = new HashMap<>();
        header.put("Host", "imgur.com");
        header.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36");
        header.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        header.put("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
        header.put("Accept-Encoding", "gzip, deflate, sdch");
        header.put("Connection", "keep-alive");
        Connection data = connect.data(header);
        //System.out.println(document.html());
        return data.get();
    }
}
