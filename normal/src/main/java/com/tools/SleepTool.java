package com.tools;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;

/**
 * @author:YiXia
 * @create: 2022-11-18 21:47:12
 * @Description: 睡觉工具类
 */

@Component
@Slf4j
public class SleepTool {


    @RequestMapping("/sleepStory")
    public HashMap<String,String> sleepStory() {
        String title = "哦吼，服务器出问题了，今天没有小故事了哦";
        String content="";
        HashMap<String,String> map=new HashMap<>();
        try {
            URL url = new URL("https://apis.tianapi.com/story/index?key=a4835342dc202bc73f96526cde8cb4a1");
            InputStreamReader isReader = new InputStreamReader(url.openStream(), "UTF-8");//“UTF- 8”万国码，可以显示中文，这是为了防止乱码
            BufferedReader br = new BufferedReader(isReader);//采用缓冲式读入
            String str = br.readLine();
//            log.info("查询彩虹皮：" + str);
            //json字符串转换json对象
//            System.out.println("------------");
            JSONObject jsonObject = JSONObject.parseObject(str);
//            System.out.println(jsonObject);
            JSONObject result = jsonObject.getJSONObject("result");
//            System.out.println(result);
            JSONArray list = result.getJSONArray("list");
            title = list.getJSONObject(0).getString("title");
            content = list.getJSONObject(0).getString("content");
//            System.out.println(list);

            map.put("title",title);
            map.put("content",content);

//            System.out.println("story=" + title);

            br.close();//网上资源使用结束后，数据流及时关闭
            isReader.close();
        } catch (Exception exp) {
            System.out.println(exp);
        }

        return map;
    }


}
