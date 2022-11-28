package com.tools;

import com.alibaba.fastjson.JSONObject;
import com.entity.Weather;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * @author:YiXia
 * @create: 2022-11-17 18:54:29
 * @Description: 恋爱工具类
 */


@Component
@Slf4j
public class LoveTool {


    private String birthday1 = "1111-11-11 02:45:00";
    private String birthday2 = "1111-11-11 02:45:00";
    private String anniversaries = "1111-11-11 02:45:00";
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    /**
     * 已经相爱多少天
     */
    @RequestMapping("/getLoveData")
    public String day() throws ParseException {
        //纪念日   今天
        Date date1 = simpleDateFormat.parse(anniversaries);
        Date date2 = new Date();
        return getReduce(date1, date2);
    }


    /**
     * 生日倒计时
     */
    @RequestMapping("/getBirthday1")
    public String birthday1() throws ParseException {
        //今天 下一个生日
        Date date1 = new Date();

        //判断今年有没有生日，修改生日字符串，不断判断
        //尝试新的一年,获得年字符串
        String birthdayYear = simpleDateFormat.format(new Date()).substring(0, 4);
        //获得新的生日
        String nextBirthday = birthday1.replace(birthday1.substring(0, 4), birthdayYear);
        //判断新生日与今天大小关系
        if (simpleDateFormat.parse(nextBirthday).getTime() - date1.getTime() < 0) {
            nextBirthday = birthday1.replace(birthday1.substring(0, 4), Integer.parseInt(birthdayYear) + 1 + "");
        }
        Date date2 = simpleDateFormat.parse(nextBirthday);
        return getReduce(date1, date2);
    }

    /**
     * 生日倒计时
     */
    @RequestMapping("/getBirthday2")
    public String birthday2() throws ParseException {
        //今天 下一个生日
        Date date1 = new Date();

        //判断今年有没有生日，修改生日字符串，不断判断
        //尝试新的一年,获得年字符串
        String birthdayYear = simpleDateFormat.format(new Date()).substring(0, 4);
        //获得新的生日
        String nextBirthday = birthday2.replace(birthday2.substring(0, 4), birthdayYear);
        //判断新生日与今天大小关系
        if (simpleDateFormat.parse(nextBirthday).getTime() - date1.getTime() < 0) {
            nextBirthday = birthday2.replace(birthday2.substring(0, 4), Integer.parseInt(birthdayYear) + 1 + "");
        }
        Date date2 = simpleDateFormat.parse(nextBirthday);
        return getReduce(date1, date2);
    }


    /**
     * 提取的共有属性方法
     * 查询两个日期之间差值
     */
    private String getReduce(Date date1, Date date2) throws ParseException {
        long format = (date2.getTime() - date1.getTime()) / (1000 * 60 * 60 * 24);
        return format + "";
    }


    @RequestMapping("/pipi")
    public String pipi() {
        String pipi="人家才不想给你说彩虹皮呢 o(*￣▽￣*)ブ";
        try {
            URL url = new URL("https://apis.tianapi.com/caihongpi/index?key=a4835342dc202bc73f96526cde8cb4a1");
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

            pipi = result.getString("content");
//            System.out.println("pipi="+pipi);

            br.close();//网上资源使用结束后，数据流及时关闭
            isReader.close();
        } catch (Exception exp) {
            System.out.println(exp);
        }

        return pipi;

    }


//    //格式化
//    Date date=new Date();
//    SimpleDateFormat format1 = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
//    //格式化String转String
//    String nowDate = format1.format(date.getTime());
//    //格式化String转Date
//    Date end = simpleDateFormat.parse(date2);

}
