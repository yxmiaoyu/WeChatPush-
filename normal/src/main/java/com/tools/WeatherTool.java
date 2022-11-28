package com.tools;

import com.alibaba.fastjson.JSONObject;
import com.entity.Weather;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * @author:YiXia
 * @create: 2022-11-17 11:30:38
 * @Description: 天气
 */

@Slf4j
@Component
public class WeatherTool {

    private Weather weather = null;


    public Weather getWeather(String cityCode) {

        // 石家庄 101090101
        // 广平 101091012


        try {
            URL url = new URL("http://t.weather.itboy.net/api/weather/city/" + cityCode);
            InputStreamReader isReader = new InputStreamReader(url.openStream(), "UTF-8");//“UTF- 8”万国码，可以显示中文，这是为了防止乱码
            BufferedReader br = new BufferedReader(isReader);//采用缓冲式读入
            String str = br.readLine();
//            log.info("查询出的天气json信息：" + str);
            //{"message":"success感谢又拍云(upyun.com)提供CDN赞助","status":200,"date":"20221117","time":"2022-11-17 16:00:48","cityInfo":{"city":"石家庄市","citykey":"101090101","parent":"河北","updateTime":"15:16"},"data":{"shidu":"37%","pm25":88.0,"pm10":153.0,"quality":"轻度","wendu":"14","ganmao":"儿童、老年人及心脏、呼吸系统疾病患者人群应减少长时间或高强度户外锻炼","forecast":[{"date":"17","high":"高温 16℃","low":"低温 5℃","ymd":"2022-11-17","week":"星期四","sunrise":"07:05","sunset":"17:09","aqi":96,"fx":"北风","fl":"1级","type":"晴","notice":"愿你拥有比阳光明媚的心情"},{"date":"18","high":"高温 11℃","low":"低温 6℃","ymd":"2022-11-18","week":"星期五","sunrise":"07:06","sunset":"17:08","aqi":99,"fx":"北风","fl":"2级","type":"阴","notice":"不要被阴云遮挡住好心情"},{"date":"19","high":"高温 19℃","low":"低温 6℃","ymd":"2022-11-19","week":"星期六","sunrise":"07:07","sunset":"17:07","aqi":98,"fx":"东风","fl":"1级","type":"晴","notice":"愿你拥有比阳光明媚的心情"},{"date":"20","high":"高温 12℃","low":"低温 1℃","ymd":"2022-11-20","week":"星期日","sunrise":"07:08","sunset":"17:07","aqi":91,"fx":"北风","fl":"2级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"21","high":"高温 13℃","low":"低温 2℃","ymd":"2022-11-21","week":"星期一","sunrise":"07:09","sunset":"17:06","aqi":79,"fx":"东北风","fl":"2级","type":"阴","notice":"不要被阴云遮挡住好心情"},{"date":"22","high":"高温 13℃","low":"低温 3℃","ymd":"2022-11-22","week":"星期二","sunrise":"07:10","sunset":"17:06","aqi":101,"fx":"南风","fl":"2级","type":"霾","notice":"雾霾来袭，戴好口罩再出门"},{"date":"23","high":"高温 11℃","low":"低温 5℃","ymd":"2022-11-23","week":"星期三","sunrise":"07:11","sunset":"17:05","aqi":110,"fx":"北风","fl":"1级","type":"霾","notice":"雾霾来袭，戴好口罩再出门"},{"date":"24","high":"高温 11℃","low":"低温 6℃","ymd":"2022-11-24","week":"星期四","sunrise":"07:12","sunset":"17:05","aqi":101,"fx":"北风","fl":"2级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"25","high":"高温 14℃","low":"低温 2℃","ymd":"2022-11-25","week":"星期五","sunrise":"07:13","sunset":"17:04","aqi":70,"fx":"西北风","fl":"3级","type":"晴","notice":"愿你拥有比阳光明媚的心情"},{"date":"26","high":"高温 9℃","low":"低温 0℃","ymd":"2022-11-26","week":"星期六","sunrise":"07:14","sunset":"17:04","aqi":62,"fx":"南风","fl":"2级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"27","high":"高温 4℃","low":"低温 0℃","ymd":"2022-11-27","week":"星期日","sunrise":"07:15","sunset":"17:03","aqi":78,"fx":"北风","fl":"2级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"28","high":"高温 6℃","low":"低温 -1℃","ymd":"2022-11-28","week":"星期一","sunrise":"07:16","sunset":"17:03","aqi":78,"fx":"东北风","fl":"1级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"29","high":"高温 6℃","low":"低温 -1℃","ymd":"2022-11-29","week":"星期二","sunrise":"07:17","sunset":"17:03","aqi":99,"fx":"北风","fl":"1级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"30","high":"高温 9℃","low":"低温 0℃","ymd":"2022-11-30","week":"星期三","sunrise":"07:18","sunset":"17:02","aqi":96,"fx":"北风","fl":"3级","type":"霾","notice":"雾霾来袭，戴好口罩再出门"},{"date":"01","high":"高温 7℃","low":"低温 0℃","ymd":"2022-12-01","week":"星期四","sunrise":"07:19","sunset":"17:02","aqi":75,"fx":"西北风","fl":"3级","type":"晴","notice":"愿你拥有比阳光明媚的心情"}],"yesterday":{"date":"16","high":"高温 16℃","low":"低温 5℃","ymd":"2022-11-16","week":"星期三","sunrise":"07:04","sunset":"17:09","aqi":112,"fx":"南风","fl":"1级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"}}}

            //使用Fastjson处理天气信息，封装Java对象
            if (str != null && str != "") {
                //开始处理天气Json
                weather = JSONObject.parseObject(str, Weather.class);
            }

            //网上资源使用结束后，数据流及时关闭
            br.close();
            isReader.close();
        } catch (Exception exp) {
            System.out.println(exp);
        }
        return weather;
    }


}






