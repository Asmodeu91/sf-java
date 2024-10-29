package org.example;
public class YandexApiResponse {
    public String now_dt;
    public Forecasts[] forecasts;

    public Fact fact;
    class Forecasts {
        public String sunrise;
        public Parts parts;
        class Parts {
            public Night night;
            class Night {
                public String temp_avg;
            }
        }
    }
    class Fact {
        public String temp;
    }
}
