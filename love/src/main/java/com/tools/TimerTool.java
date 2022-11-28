//package com.tools;
//
//import com.controller.MsgController;
//import com.entity.ReturnData;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Timer;
//import java.util.TimerTask;
//
///**
// * @author:YiXia
// * @create: 2022-11-17 22:00:39
// * @Description: 定时器类
// */
//
//@Slf4j
//@Component
//public class TimerTool {
//
//    //上次时间
//    private String lastTime = "2022-11-17 02:45:00";
//    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    private Date date2 = new Date();
//    private Date date1 = null;
//    private long returnTime;
//    private boolean returnJudge1;
//    private Timer timer = new Timer();
//
//
//
//    @Autowired
//    WechatMsgTool wechatMsgTool;
//    @Autowired
//    MsgController msgController;
//
//
//
//    public static void main(String[] args) throws ParseException, InterruptedException {
//        log.info("timerTool执行静态方法");
//
//    }
//
//    public void daYiMa() throws InterruptedException, ParseException {
//        returnTime = (28 - (date2.getTime() - date1.getTime()) / (1000 * 60 * 60 * 24)) % 28;
//    }
//
//
//
//
//    public void push() throws ParseException, InterruptedException {
//        //获得小时
//        int hour = Integer.parseInt(simpleDateFormat.format(date2.getTime()).substring(11, 13));
//        int minutes = Integer.parseInt(simpleDateFormat.format(System.currentTimeMillis()).substring(14, 16));
//        int second = Integer.parseInt(simpleDateFormat.format(System.currentTimeMillis()).substring(17, 19));
//        if (hour == 6 && minutes==0 && second==0) {
//            msgController.fun();
//        } else if (hour == 23 && minutes==0 && second==0) {
//            msgController.fun();
//        }
//
//
//    }
//
//    public ReturnData regular() throws InterruptedException {
//        //设置Timer定时器
//        TimerTask timerTask = new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println("进入定时器,当前时间：" + simpleDateFormat.format(new Date().getTime()));
//                try {
//                    date1 = simpleDateFormat.parse(lastTime);
//                    date2 = new Date();
//                    //开始执行大姨妈子程序
//                    daYiMa();
//                    //开始执行定时推送子程序
//                    push();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//
//
//            }
//        };
//        timer.cancel();
//        timer = new Timer();
//        // 开始执行任务 (延迟0毫秒执行，每分钟执行一次)
//        timer.schedule(timerTask, 0, 1000);
//        //主线程休眠0.1秒，等上面的定时器运行
//        Thread.sleep(100);
//        ReturnData returnData = new ReturnData();
//        returnData.setRegularJudge(returnJudge1);
//        returnData.setDaYiMa(returnTime);
//        return returnData;
//
//    }
//
//
////    //格式化
////    Date date=new Date();
////    SimpleDateFormat format1 = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
////    //格式化String转String
////    String nowDate = format1.format(date.getTime());
////    //格式化String转Date
////    Date end = simpleDateFormat.parse(date2);
//
//
//}
