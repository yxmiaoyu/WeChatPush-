package com.entity;

import lombok.Data;

import java.util.List;

/**
 * @author:YiXia
 * @create: 2022-11-17 17:10:34
 * @Description: 天气实体类
 */


@Data
public class Weather {

    private String message;
    private String status;
    private String date;
    /**
     * 查询时间
     */
    private String time;
    /**
     * 城市信息
     */
    private CityInfo cityInfo;

    private Data data;

    @lombok.Data
    public static class CityInfo {
        /**
         * 城市名
         */
        private String city;
        /**
         * 省份名
         */
        private String parent;
        /**
         * 城市编号
         */
        private String cityKey;
        /**
         * 天气数据
         */
        private String updateTime;

    }


    @lombok.Data
    public static class Data {

        private String shiDu;
        private String pm25;
        private String pm10;
        /**
         * 空气质量
         */
        private String quality;
        private String wenDu;
        /**
         * 建议
         */
        private String ganMao;
        private List<Forecast> forecast;


        @lombok.Data
        public static class Forecast {
            /**
             * 日期
             */
            private String data;
            private String high;
            private String low;
            /**
             * 完整日期
             */
            private String ymd;
            private String week;
            private String sunRise;
            private String sunSet;
            /**
             * 空气质量
             */
            private String aqi;
            private String fx;
            private String fl;
            /**
             * 天气情况
             */
            private String type;
            /**
             * 每日话语
             */
            private String notice;


        }


    }


}
