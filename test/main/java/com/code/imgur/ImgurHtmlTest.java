package main.java.com.code.imgur;

import main.java.com.code.tumblr.util.FileUtil;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Code.Ai on 16/7/12.
 */
public class ImgurHtmlTest {
    private String rootUrl   = "http://imgur.com/r/NSFW_GIF/new/page/";
    private String nsfw_gifs = "http://imgur.com/r/nsfw_gifs/new/page/";
    private String hentai = "http://imgur.com/r/hentai/new/page/";
    private String nsfw = "http://imgur.com/r/nsfw/new/page/";
    private String asian = "http://imgur.com/r/AsianNSFW/new/page/";
    private String japan = "http://imgur.com/r/NSFW_Japan/new/page/";

    @Test
    public void rootHtml() throws Exception {
        //todo 每次获取新数据时 判断上一次获取的最后一条数据是否在此次数据当中,如果有 不保存
        for (int i = 0; i < 2; i++) {
            List<String> list       = ImgurHtml.rootHtml(asian + i + "/hit?scrolled");
            Set<String>  filterUrls = new HashSet<>();
            for(String url : list){
                // 文件小于20M 的才存储
                //if(FileUtil.fileInfo(url) <= 20000){
                //    filterUrls.add(url);
                //}
                filterUrls.add(url);
            }
            FileUtil.saveToFile(filterUrls);
        }
    }

    @Test
    public void childHtml() throws Exception {
        ImgurHtml.childHtml("ady2lkY");
    }

    @Test
    public void getImageTest() throws IOException {
        for (int i = 0; i < 5; i++) {
            List<String> list       = ImgurHtml.getImages(nsfw + i + "/hit?scrolled");
            Set<String>  filterUrls = new HashSet<>();
            for(String url : list){
                // 文件小于20M 的才存储
                //if(FileUtil.fileInfo(url) <= 20000){
                //    filterUrls.add(url);
                //}
                filterUrls.add(url);
                System.out.println(url);
            }
            FileUtil.saveToFile(filterUrls);
        }

    }

    @Test
    public void disguise() throws Exception {

    }

    @Test
    public void handleFileTest() throws IOException {
        String filePath = "/Users/codeai/Desktop/aa.txt";
        FileUtil.deleteDuplicate(filePath);
    }

    @Test
    public void filterUrl() throws Exception {
        File           file           = new File("/Users/codeai/Desktop/11-29-54.txt");
        FileReader     fileReader     = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String         newLine        = "";
        Set<String>    urlSet         = new HashSet<>();
        while ((newLine = bufferedReader.readLine()) != null) {
            if (FileUtil.fileInfo(newLine) <= 20000) {
                urlSet.add(newLine);
                System.out.println(newLine);
            }
        }
        FileUtil.saveToFile(urlSet);
        bufferedReader.close();
        fileReader.close();
    }
}