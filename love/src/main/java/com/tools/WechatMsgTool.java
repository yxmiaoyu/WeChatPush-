package com.tools;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.entity.Weather;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.entity.DataEntity;

@Component
@Slf4j
public class WechatMsgTool {


    @Autowired
    WeatherTool weatherTool;
    @Autowired
    LoveTool loveTool;
//    @Autowired
//    TimerTool timerTool;
    @Autowired
    SleepTool sleepTool;

    //获取到的天气信息
    private Weather weather;
    private Weather weather1; //101090101
    private Weather weather2; //101091012
    private Timer timer = new Timer();
    private String lastTime = "1111-11-11 02:45:00";
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Date date2 = new Date();
    private Date date1 = simpleDateFormat.parse(lastTime);
    private String userID;

    public WechatMsgTool() throws ParseException {
    }


    public static void main(String[] args) throws ParseException, InterruptedException {
        WechatMsgTool wm = new WechatMsgTool();
        wm.SendWeChatMsg(wm.getToken(), "okVyw6jZUBmCkPsGAXZUE1GvktdA");
    }

    /**
     * 获取token
     *
     * @return token
     */
    public String getToken() {
        // 授予形式
        String grant_type = "client_credential";
        //应用ID
        String appid = "wxcbbadad4f789d3e0";
        //密钥
        String secret = "0f10c44acb1bf8052a23f88974436471";
        // 接口地址拼接参数
        String getTokenApi = "https://api.weixin.qq.com/cgi-bin/token?grant_type=" + grant_type + "&appid=" + appid
                + "&secret=" + secret;
        String tokenJsonStr = doGetPost(getTokenApi, "GET", null);
        JSONObject tokenJson = JSONObject.parseObject(tokenJsonStr);
        String token = tokenJson.get("access_token").toString();
        weather1 = weatherTool.getWeather("101091012");
        weather2 = weatherTool.getWeather("101090101");
        return token;
    }

    /***
     * 发送消息
     *
     * @param token
     */
    public void SendWeChatMsg(String token, String userID) throws ParseException, InterruptedException {
//        System.out.println("进入重复执行程序");
        this.userID = userID;
        // 接口地址
        String sendMsgApi = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + token;
        //openId
        String toUser = userID;
        //消息模板ID,获取现在时间，判断使用模板类型
        int hour = Integer.parseInt(simpleDateFormat.format(System.currentTimeMillis()).substring(11, 13));
        String template_id = null;
        if (hour < 15) {
            //早上模板
            template_id = "04duXcqxVagEmbhoJ8X7-OVpZaOX_49nk6QqTsfroE8";
        } else {
            //晚上模板
            template_id = "TWrawbzWHD17Grhv2tq9lM-Z4zRG5-t17FQie2paKW4";
        }
        //整体参数map
        Map<String, Object> paramMap = new HashMap<String, Object>();
        //消息主题显示相关map
        Map<String, Object> dataMap = new HashMap<String, Object>();
        //根据自己的模板定义内容和颜色,开始封装自定义参数
        //下面是天气数据
        if(userID.equals("okVyw6jZUBmCkPsGAXZUE1GvktdA")){
            weather=weather1;
        }else{
            weather=weather2;
        }
        dataMap.put("time", new DataEntity(weather.getTime().substring(0, 11) + " " + weather.getData().getForecast().get(0).getWeek(), "#ff4500"));
        dataMap.put("cityName", new DataEntity(weather.getCityInfo().getCity(), "#A020F0"));
        dataMap.put("provinceName", new DataEntity(weather.getCityInfo().getParent(), "#A020F0"));
        dataMap.put("type", new DataEntity(weather.getData().getForecast().get(0).getType(), "#A020F0"));
        dataMap.put("low", new DataEntity(weather.getData().getForecast().get(0).getLow(), "#A020F0"));
        dataMap.put("high", new DataEntity(weather.getData().getForecast().get(0).getHigh(), "#A020F0"));
        dataMap.put("pm25", new DataEntity(weather.getData().getPm25(), "#A020F0"));
        dataMap.put("quality", new DataEntity(weather.getData().getQuality(), "#A020F0"));
        dataMap.put("ganMao", new DataEntity(weather.getData().getGanMao(), "#A020F0"));
        dataMap.put("fengLi", new DataEntity(weather.getData().getForecast().get(0).getFl(), "#A020F0"));
        dataMap.put("fengXiang", new DataEntity(weather.getData().getForecast().get(0).getFx(), "#A020F0"));
        dataMap.put("notice", new DataEntity(weather.getData().getForecast().get(0).getNotice(), "#A020F0"));

        //下面是纪念日与生日数据
        if (loveTool.day() != null) {
            dataMap.put("anniversaries", new DataEntity(loveTool.day(), "#ff7f00"));
            dataMap.put("birthday1", new DataEntity(loveTool.birthday1(), "#ff7f00"));
            dataMap.put("birthday2", new DataEntity(loveTool.birthday2(), "#ff7f00"));
            dataMap.put("pipi", new DataEntity(loveTool.pipi(), "#ff7f00"));
        }

        //下面是晚安数据

        dataMap.put("str1", new DataEntity("乖，该睡觉觉了哦", "#A020F0"));
        dataMap.put("str2", new DataEntity(loveTool.pipi(), "#A020F0"));
        dataMap.put("str3", new DataEntity("", "#A020F0"));
        dataMap.put("str5", new DataEntity(loveTool.pipi(), "#A020F0"));
        dataMap.put("str7", new DataEntity("", "#A020F0"));
        dataMap.put("str9", new DataEntity(loveTool.pipi(), "#A020F0"));
        dataMap.put("str11", new DataEntity("", "#A020F0"));
        dataMap.put("str13", new DataEntity(loveTool.pipi(), "#A020F0"));

        //下面是大姨妈数据
        if (Integer.parseInt(daYiMa()) <= 3) {
            dataMap.put("daYiMa", new DataEntity("生理期还有 " + daYiMa() + " 天", "#ff0000"));
        }


        //下面封装参数不可以省略
        paramMap.put("touser", toUser);
        paramMap.put("template_id", template_id);
        paramMap.put("data", dataMap);
        //需要实现跳转网页的，可以添加下面一行代码实现跳转
        // paramMap.put("url","http://xxxxxx.html");
        System.out.println(doGetPost(sendMsgApi, "POST", paramMap));


        //判断当前是否发送
    }

    /**
     * 调用接口 post
     *
     * @param apiPath
     */
    public String doGetPost(String apiPath, String type, Map<String, Object> paramMap) {
//        System.out.println("开始微信推送");
        OutputStreamWriter out = null;
        InputStream is = null;
        String result = null;
        try {
            URL url = new URL(apiPath);// 创建连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod(type); // 设置请求方式
            connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
            connection.connect();
            if (type.equals("POST")) {
                out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码
                out.append(JSON.toJSONString(paramMap));
                out.flush();
                out.close();
            }
            // 读取响应
            is = connection.getInputStream();
            int length = (int) connection.getContentLength();// 获取长度
            if (length != -1) {
                byte[] data = new byte[length];
                byte[] temp = new byte[512];
                int readLen = 0;
                int destPos = 0;
                while ((readLen = is.read(temp)) > 0) {
                    System.arraycopy(temp, 0, data, destPos, readLen);
                    destPos += readLen;
                }
                result = new String(data, "UTF-8"); // utf-8编码
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    public void timer() throws InterruptedException {
        //设置Timer定时器
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                //每秒判断一次
//                System.out.println("进入定时器,当前时间：" + simpleDateFormat.format(System.currentTimeMillis()));
                int hour = Integer.parseInt(simpleDateFormat.format(date2.getTime()).substring(11, 13));
                int minutes = Integer.parseInt(simpleDateFormat.format(System.currentTimeMillis()).substring(14, 16));
                int second = Integer.parseInt(simpleDateFormat.format(System.currentTimeMillis()).substring(17, 19));

                try {
                    if (hour == 22 && minutes == 0 && second == 0) {
                        SendWeChatMsg(getToken(), "okVyw6jZUBmCkPsGAXZUE1GvktdA");
                        SendWeChatMsg(getToken(), "okVyw6sVS3velgTlo3GyYTVRooPU");
                    } else if (hour == 15 && minutes == 0 && second == 0) {
                        SendWeChatMsg(getToken(), "okVyw6jZUBmCkPsGAXZUE1GvktdA");
                        SendWeChatMsg(getToken(), "okVyw6sVS3velgTlo3GyYTVRooPU");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }


            }
        };
        // 开始执行任务 (延迟0毫秒执行，每分钟执行一次)
        timer.schedule(timerTask, 0, 1000);
        //主线程休眠0.1秒，等上面的定时器运行
        Thread.sleep(100);
    }


    public String daYiMa() throws InterruptedException, ParseException {
        return (28 - (date2.getTime() - date1.getTime()) / (1000 * 60 * 60 * 24)) % 28 + "";
    }


}
