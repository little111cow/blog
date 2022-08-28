package com.littlecow.blog.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Java 爬取网页上的图片
 *
 * 第一步：获取页面的源代码；
 * 第二步：解析源代码，含有图片的标签，再找到图片标签里面的src；
 * 第三步：利用Java里面的net包，网络编程
 *
 * @author SJH
 */
public class DownloadImageUtils extends Thread{
    /**
     * 根据网页和编码获取网页内容和源代码
     * @param url
     * @param encoding
     */
    public static String getHtmlResourceByUrl(String url,String encoding){
        StringBuffer buffer   = new StringBuffer();
        URL urlObj            = null;
        URLConnection uc      = null;
        InputStreamReader in  = null;
        BufferedReader reader = null;

        try {
            // 建立网络连接
            urlObj = new URL(url);
            // 打开网络连接
            uc     = urlObj.openConnection();
            // 创建输入流
            in     = new InputStreamReader(uc.getInputStream(),encoding);
            // 创建一个缓冲写入流
            reader = new BufferedReader(in);

            String line = null;
            while ((line = reader.readLine()) != null) {
                // 一行一行追加
                buffer.append(line+"\r\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return buffer.toString();
    }

    /**
     * 根据图片的URL下载的图片到本地的filePath
     * @param filePath 文件夹
     * @param imageUrl 图片的网址
     */
    public static void downImages(String filePath,String imageUrl){
        // 截取图片的名称
        String fileName = imageUrl.substring(imageUrl.lastIndexOf("/"));

        //创建文件的目录结构
        File files = new File(filePath);
        if(!files.exists()){// 判断文件夹是否存在，如果不存在就创建一个文件夹
            files.mkdirs();
        }
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream is = connection.getInputStream();
            // 创建文件，并设置默认文件名
            File file = new File(filePath+fileName);
            FileOutputStream out = new FileOutputStream(file);
            int i = 0;
            while((i = is.read()) != -1){
                out.write(i);
            }
            is.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static List<String> getImageUrlsByKeyWord(String keyword){
        List<String> images = new ArrayList<>();
        //编码方式
        String encoding = "UTF-8";

        String url = null;
        try {
            url = "https://cn.bing.com/images/search?qs=n&form=QBIRMH&sp=-1&pq=hua%27cao&sc=10-7" +
                    "&cvid=A1AD706F49984BD09AD0A12100843EB6&ghsh=0&ghacc=0&first=1&tsc=ImageHoverTitle" +
                    "&q=" + URLEncoder.encode(keyword, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String htmlResource = getHtmlResourceByUrl(url, encoding);
        // 解析网页源代码
        Document document = Jsoup.parse(htmlResource);
        // 获取所有图片的地址
        Elements elements = document.getElementsByTag("img");
        System.out.println("-------------------------开始下载！----------------------------");
        for(Element element : elements){
            String imgSrc = element.attr("src");
            // 判断imgSrc是否为空且是否以"http://"或是"https://"开头
            if (!"".equals(imgSrc) && (imgSrc.startsWith("http://") || imgSrc.startsWith("https://"))) {
                System.out.println("\""+imgSrc +"\""+ ",");
                images.add(imgSrc);
            }
        }
        System.out.println("-------------------------下载完毕！----------------------------");
        return images;
    }

    public static String getRandomImageFromInternet(){
        List<String> imgs = new ArrayList<>();
        String[] keywords = new String[]{"风景", "花草", "宠物", "建筑", "河流", "女人", "卡通人物"};
        for (String keyword : keywords) {
            imgs.addAll(getImageUrlsByKeyWord(keyword));
        }
        Random random = new Random();
        return imgs.get(random.nextInt(imgs.size()));
    }
}


