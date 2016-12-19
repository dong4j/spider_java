package main.java.com.code.tumblr.util;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.SimpleFormatter;

/**
 * @author Code.Ai (Email: Code.Ai@outlook.com)
 * @version 0.1
 * @link http://blog.csdn.net/codeai
 * @date 16/2/28
 * @describe
 */

public class FileUtil {
    /**
     * 保存到文件
     * @param collection
     * @return
     * @throws IOException
     */
    public static Collection<String> saveToFile(Collection<String> collection) throws IOException {
        Iterator         iterator = collection.iterator();
        SimpleDateFormat sf       = new SimpleDateFormat("HH-mm-ss");
        File file = new File( "/Users/codeai/Desktop/"+ sf.format(new Date()) +".txt");
        FileWriter fw = new FileWriter(file,true);
        BufferedWriter writer = new BufferedWriter(fw);
        while(iterator.hasNext()){
            writer.write(iterator.next().toString());
            writer.newLine();
        }
        writer.flush();
        writer.close();
        fw.close();
        return collection;
    }

    public static void saveToFileOneByOne(String filePath, String pointer,String type) throws IOException {
        File file = new File(filePath);
        FileWriter fw = new FileWriter(file,true);
        BufferedWriter writer = new BufferedWriter(fw);
        writer.write(pointer);
        if(type.equals("url")){
            writer.newLine();
        }
        writer.flush();
        writer.close();
        fw.close();
    }


    /**
     * 去除重复的数据
     * @param
     * @throws IOException
     */
    public static void deleteDuplicate(String filePath) throws IOException {
        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String newLine = "";
        Set<String> urlSet = new HashSet<>();
        while((newLine = bufferedReader.readLine()) != null){
            urlSet.add(newLine);
        }
        file.delete();
        bufferedReader.close();
        fileReader.close();
        saveToFile(urlSet);
    }


    /**
     * 获取文件大小
     * @param fileUrl
     * @return
     * @throws Exception
     */
    public static Integer fileInfo(String fileUrl)throws Exception{
        URL           url      = new URL(fileUrl);
        URLConnection uc       = url.openConnection();
        String        fileName = uc.getHeaderField(6);
//        fileName = URLDecoder.decode(fileName.substring(fileName.indexOf("filename=")+9), "UTF-8");
//        System.out.println("文件名为："+fileName);
//        System.out.println("文件大小："+(uc.getContentLength()/1024)+"KB");
//        String path = "D:"+File.separator+fileName;
//        FileOutputStream os = new FileOutputStream(path);
//        InputStream is = uc.getInputStream();
//        byte[] b = new byte[1024];
//        int len = 0;
//        while((len=is.read(b))!=-1){
//            os.write(b,0,len);
//        }
//        os.close();
//        is.close();
//        System.out.println("下载成功,文件保存在："+path);
        return uc.getContentLength()/1024;
    }
}
