package ui;

/**
 * Created by Rick.Wang on 2017/6/21.
 */

import constants.Constant;
import net.HuoBiBitcoinJson;
import net.MyCoinHttps;
import rx.Subscriber;
import util.UiUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {

    public static void main(String[] args) {
        JFrame frame = new JFrame("MyCoins");
        frame.setSize(Constant.FrameWidth, Constant.FrameHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);

        placeComponents(panel);

        UiUtil.setFrameCenterInWindow(frame);
        frame.setVisible(true);

    }

    private static JLabel userLabel;

    private static void placeComponents(JPanel panel) {

        panel.setLayout(null);

        userLabel = new JLabel("User:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        /*
         * 创建文本域用于用户输入
         */
        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        // 输入密码的文本域
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        /*
         *这个类似用于输入的文本域
         * 但是输入的信息会以点号代替，用于包含密码的安全性
         */
        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 50, 165, 25);
        panel.add(passwordText);

        // 创建登录按钮
        JButton loginButton = new JButton("login");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getInfoAndRun();
            }
        });
    }

    private static void getInfoAndRun(){
        Runnable runnable = new Runnable() {
            public void run() {
                while (true) {
                    getNetCustomers();
                    try {
                        Thread.sleep(2000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread d = new Thread(runnable);
        d.start();
    }

    private static void getNetCustomers(){
        System.out.print("getNetCustomers");
        MyCoinHttps.getInstance().getCustomerList(getCustomerListSubscriber());
    }

    private static Subscriber getCustomerListSubscriber() {
        return new Subscriber<HuoBiBitcoinJson>() {
            public void onCompleted() {
                hideRefreshing();
            }
            public void onError(Throwable e) {
                e.printStackTrace();
                showToast("无法获取客户列表 " + e.getMessage());
                hideRefreshing();
                onUpdateError();
            }
            public void onNext(HuoBiBitcoinJson json) {
                if (json == null) {
                    showToast("获取的客户列表为空");
                    hideRefreshing();
                    onUpdateError();
                    return;
                }
                if (json.datas != null){
                    System.out.print("json.datas");
                    userLabel.setText(json.datas.ticker.buy);

                    hideRefreshing();
                    return;
                }
            }
        };
    }

    private static void hideRefreshing(){

    }

    private static void onUpdateError(){

    }

    private static void showToast(String message){

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
}