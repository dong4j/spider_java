package main.java.com.code.gifjizz.com;

import main.java.com.code.imgur.ImgurHtml;
import main.java.com.code.tumblr.util.FileUtil;
import main.java.com.code.tumblr.util.SpiderUtil;
import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by Code.Ai on 16/8/8.
 * 
 */
public class GifjizzTest {
    private String url = "http://img3.gifjizz.com/?p=";
    @Test
    public void rootHtml() throws Exception {
//        List<String> list       = Gifjizz.gifjizzHtml(url + 1);
        for (int i = 21; i < 61; i++) {
            List<String> list       = Gifjizz.gifjizzHtml(url + i);
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

}