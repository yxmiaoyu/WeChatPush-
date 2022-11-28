//package com.controller;
//
//import com.alibaba.fastjson.JSONObject;
//import com.entity.DataEntity;
//import com.entity.Weather;
//import com.tools.LoveTool;
//import com.tools.SleepTool;
////import com.tools.TimerTool;
//import com.tools.WeatherTool;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.net.URL;
//import java.text.ParseException;
//
///**
// * @author:YiXia
// * @create: 2022-11-17 17:01:05
// * @Description: 测试的控制层
// */
//
//
//@Slf4j
//@RestController
//@RequestMapping("/test")
//public class TestController {
//
//
//    @Autowired
//    WeatherTool weatherTool;
//    @Autowired
//    LoveTool loveTool;
//    @Autowired
//    TimerTool timerTool;
//    @Autowired
//    SleepTool sleepTool;
//
//    @RequestMapping("/weather")
//    public String weather() {
//
//        try {
//            URL url = new URL("http://t.weather.itboy.net/api/weather/city/101090101");
//            InputStreamReader isReader = new InputStreamReader(url.openStream(), "UTF-8");//“UTF- 8”万国码，可以显示中文，这是为了防止乱码
//            BufferedReader br = new BufferedReader(isReader);//采用缓冲式读入
//            String str = br.readLine();
////            log.info("查询天气信息：" + str);
//
//
////博主方案
////            while(str  != null ){
////                String regex="\\p{Punct}+";
////                String digit[]=str.split(regex);
////                System.out.println('\n'+"城市:"+digit[22]+digit[18]);
////                System.out.println('\n'+"时间:"+digit[49]+"年"+digit[50]+"月"+digit[51]+"日"+digit[53]);
////                System.out.println('\n'+"温度:"+digit[47]+"~"+digit[45]);
////                System.out.println('\n'+"天气:"+digit[67]+" "+digit[63]+digit[65]);
////                System.out.println('\n'+digit[69]);
////            }
//
//
//            //使用FastJson转成java对象
//            Weather weather = JSONObject.parseObject(str, Weather.class);
//            System.out.println(weather);
//
//
//            br.close();//网上资源使用结束后，数据流及时关闭
//            isReader.close();
//        } catch (Exception exp) {
//            System.out.println(exp);
//        }
//
//
//        return "";
//    }
//
//
//    @RequestMapping("/love")
//    public void love() throws ParseException {
//        System.out.println(loveTool.day());
//        System.out.println(loveTool.birthday1());
//        System.out.println(loveTool.birthday2());
//        System.out.println(loveTool.pipi());
//    }
//
//
//    @RequestMapping("/dayima")
//    public void daYiMa() throws ParseException, InterruptedException {
//        System.out.println(timerTool.regular());
//        log.info("打印returnData" + timerTool.regular());
//    }
//
//
////    @RequestMapping("/sleepStory")
////    public void sleepStory() {
////        String story = sleepTool.sleepStory();
////        System.out.println("story="+story);
////    }
//
//
//}
