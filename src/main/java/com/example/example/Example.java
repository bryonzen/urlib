package com.example.example;

import com.example.net.Jxpath;
import com.example.net.UrlResp;
import com.example.net.Urllib;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Example {
    public static void main(String[] args) throws IOException {
//        getTest();
//        postWithFormDataTest();
//        postWithJsonDataTest();
//        saveBinaryFile();
        jxpathTest();
    }

    /**
     * 发送简单请求
     */
    public static void getTest() {
        UrlResp res = Urllib.builder()
                .setUrl("http://httpbin.org")
                .addPathVariable("get")
                .addQuery("keyword", "csdn")
                .addHeader("User-Agent", "Chrome")
                .addHeader("Content-Type", "text/html")
                .addCookies("JSESSIONID=2454;aie=adf")
                .addCookie("username", "bin")
                .get();

        if (res.getResponseCode() == UrlResp.HTTP_OK) {
            System.out.println(res.getText());
        }
    }

    /**
     * 发送携带表单数据的请求
     */
    public static void postWithFormDataTest() {
        UrlResp res = Urllib.builder()
                .setUrl("http://httpbin.org")
                .addPathVariable("post")
                .addQuery("keyword", "csdn")
                .addHeader("User-Agent", "Chrome")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addCookies("JSESSIONID=2454;aie=adf")
                .addCookie("yes", "no")
                .addPostData("username", "zhangsan")
                .addPostData("password", "154134513")
                .addPostData("gender", "male")
                .addPostData("age", 18)
                .post();

        if (res.getResponseCode() == UrlResp.HTTP_OK) {
            System.out.println(res.getText());
        }

    }

    /**
     * 发送携带json数据的请求
     */
    public static void postWithJsonDataTest(){
        Map data = new HashMap();

        Map keyword1 = new HashMap();
        keyword1.put("value", "98gadf");
        data.put("keyword1", keyword1);

        Map keyword2 = new HashMap();
        keyword1.put("value", "9fghfsgdf");
        data.put("keyword2", keyword1);

        List list = new ArrayList();
        list.add("a");
        list.add("b");
        list.add("c");

        UrlResp res = Urllib.builder()
                .setUrl("http://httpbin.org")
                .addPathVariable("post")
                .addHeader("User-Agent", "Chrome")
                .addHeader("Content-Type", "application/json")
                .addPostData("touser", "orafd98bu")
                .addPostData("template_id", "34589u")
                .addPostData("page", 1)
                .addPostData("form_id", 345)
                .addPostData("data", data)
                .addPostData("list", list)
                .post();

        if (res.getResponseCode() == UrlResp.HTTP_OK){
            System.out.println(res.getText());
        }
    }

    /**
     * 保存二进制文件
     * @throws IOException
     */
    public static void saveBinaryFile() throws IOException {
        UrlResp res = Urllib.builder()
                .setUrl("https://img-blog.csdnimg.cn/20190324160739729.png")
                .addHeader("User-Agent", "Chrome")
                .get();

        if (res.getResponseCode() == UrlResp.HTTP_OK){
            OutputStream os = new BufferedOutputStream(
                    new FileOutputStream(
                            new File("photo.png")
                    )
            );
            os.write(res.getByteContent());
        }
    }

    public static void jxpathTest(){
        UrlResp res = Urllib.builder()
                .setUrl("https://blog.csdn.net/qq_37977106/article/details/86562802")
                .get();

        if (res.getResponseCode() == UrlResp.HTTP_OK) {
            String html = res.getText();

            Jxpath jxpath = new Jxpath(html);

            String title = jxpath.getString("//*[@id=\"mainBox\"]/main/div[1]/div/div/div[1]/h1").trim();
            Double like = jxpath.getNumber("//*[@id=\"asideProfile\"]/div[2]/dl[3]/dd/span");
            String visit = jxpath.getString("//*[@id=\"asideProfile\"]/div[3]/dl[2]/dd").trim();


            System.out.println(
                    "title=" + title + "\r\n" +
                    "like=" + like.intValue() + "\r\n" +
                    "visit=" + visit);
        }
    }
}
