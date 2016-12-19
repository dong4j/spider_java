package main.java.com.code.gifjizz.com;

import main.java.com.code.tumblr.util.FileUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Code.Ai on 16/8/8.
 */
public class Gifjizz {
    // 解析主页 循环获取资源列表
    public static List<String> gifjizzHtml(String rootUrl) throws IOException {
        Document rootHtml = Jsoup.connect(rootUrl).get();
//        System.out.println(rootHtml);
        Elements pointerLinks = rootHtml.select("img.big");
        //1.1将连接添加到列表中
        List<String> pointerLinksList = new ArrayList<>();
        for (Element imagePointer : pointerLinks) {
            String pointer = imagePointer.attr("src");
            System.out.println(pointer);
            pointerLinksList.add(pointer);
        }
        return pointerLinksList;
//        return null;
    }
}
