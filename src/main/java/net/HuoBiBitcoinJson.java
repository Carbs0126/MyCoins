package net;

/**
 * Created by Rick.Wang on 2017/6/21.
 */
public class HuoBiBitcoinJson {

    public String des;
    public String isSuc;
    public Datas datas;

    public static class Datas{
        public String cName;
        public String coinId;
        public String coinName;
        public String coinSign;
        public String exeByRate;
        public String isRecomm;
        public String marketValue;
        public String moneyType;
        public String name;
        public String symbol;
        public Ticker ticker;

        public String time;
        public String type;

        public static class Ticker{
            public String buy;
            public String buydollar;
            public String dollar;
            public String high;
            public String highdollar;
            public String last;
            public String low;
            public String lowdollar;
            public String riseRate;
            public String sell;
            public String selldollar;
            public String open;
            public String symbol;
            public String vol;
        }
    }
}

/*
{
    "des":"调用成功",
    "isSuc":true,
    "datas":{
        "cName":"火币网",
        "coinId":1,
        "coinName":"比特币",
        "coinSign":"btc",
        "exeByRate":1,
        "isRecomm":1,
        "marketValue":"47206150443",
        "moneyType":1,
        "name":"huobi",
        "symbol":"huobibtccny",
        "ticker":{
            "buy":"19700.00",
            "buydollar":"2884.88",
            "dollar":"2885.62",
            "high":"20489.00",
            "highdollar":"3000.42",
            "last":"19705.00",
            "low":"19000.00",
            "lowdollar":"2782.37",
            "open":19390,
            "riseRate":"2.02",
            "sell":"19705.00",
            "selldollar":"2885.62",
            "symbol":"btccny",
            "vol":"16497.856"
        },
        "time":"1498034552",
        "type":0
    }
}
 */