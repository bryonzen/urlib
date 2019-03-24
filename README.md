## 示例
* **发送get请求**
```java
 UrlResp res = Urllib.builder()
         .setUrl("http://httpbin.org")
         .addPathVariable("get")
         .addQuery("keyword", "csdn")
         .addHeader("User-Agent", "Chrome")
         .addHeader("Content-Type", "text/html")
         .addCookies("JSESSIONID=2454;aie=adf")
         .addCookie("username", "bin")
         .get();

 if (res.getResponseCode() == UrlResp.HTTP_OK){
     System.out.println(res.getText());
 }
```

打印结果

![在这里插入图片描述](https://img-blog.csdnimg.cn/20190324154819597.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM3OTc3MTA2,size_16,color_FFFFFF,t_70)
* **发送post请求并携带表单数据**
```java
public void test(){
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

    if (res.getResponseCode() == UrlResp.HTTP_OK){
        System.out.println(res.getText());
    }
}
```

打印结果

![在这里插入图片描述](https://img-blog.csdnimg.cn/2019032415564386.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM3OTc3MTA2,size_16,color_FFFFFF,t_70)
* **发送post请求并携带json格式数据**

构造json数据稍微复杂一点，毕竟不能像python和php语言。

```java
public void test(){
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
```

打印结果

![在这里插入图片描述](https://img-blog.csdnimg.cn/20190324160739729.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM3OTc3MTA2,size_16,color_FFFFFF,t_70)
* **保存二进制文件**
```java
@Test
public void test() throws IOException {
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
```

输出结果

![在这里插入图片描述](https://img-blog.csdnimg.cn/20190324161349462.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM3OTc3MTA2,size_16,color_FFFFFF,t_70)
