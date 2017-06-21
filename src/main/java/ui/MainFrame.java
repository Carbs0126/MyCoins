package ui;

import constants.Constant;
import net.MyCoinHttps;
import net.entity.HuoBiBitcoinJson;
import rx.Subscriber;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Enumeration;

public class MainFrame extends JFrame implements ActionListener {

    private String[][] menuNameMnemonics = {{"设置", "s", "settings"}, {"短信提示", "m", "message"}, {"关于", "e", "about"}};
    private JMenuBar menuBar;
    private JLabel bitTitleLabel;
    private JLabel liteTitleLabel;
    private JLabel bitValueLabel;
    private JLabel liteValueLabel;
    private JPanel containerLeft;
    private JPanel containerRight;
    private JPanel containerBottom;
    private JButton startButton;
    private JButton stopButton;
    private boolean isRunning;

    public MainFrame() {
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        for (String[] menuNameMnemonic : menuNameMnemonics) {
            menuBar.add(createMenu(menuNameMnemonic[0], menuNameMnemonic[1], menuNameMnemonic[2]));
        }

        JMenu settingsMenu = getMenu("settings");
        createMenuNamedSettings(settingsMenu);

        JMenu aboutMenu = getMenu("about");
        createMenuNamedAbout(aboutMenu);

        JMenu messageMenu = getMenu("message");
        createMenuNamedMessage(messageMenu);
    }

    private JMenu createMenuNamedSettings(JMenu menu) {
        JMenuItem menuItem1 = createMenuItem("数据源", "settings_website", "s", KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_MASK));
        menuItem1.addActionListener(this);
        menu.add(menuItem1);
        JMenuItem menuItem2 = createMenuItem("刷新间隔", "refresh_interval", "i", KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.ALT_MASK));
        menuItem2.addActionListener(this);
        menu.add(menuItem2);
        JMenuItem menuItem3 = createMenuItem("提示方式", "tips_way", "t", KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.ALT_MASK));
        menuItem3.addActionListener(this);
        menu.add(menuItem3);
        return menu;
    }

    private JMenu createMenuNamedAbout(JMenu menu) {
        JMenuItem menuItem1 = createMenuItem("软件功能", "software_function", "f", KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.ALT_MASK));
        menuItem1.addActionListener(this);
        menu.add(menuItem1);
        return menu;
    }

    private JMenu createMenuNamedMessage(JMenu menu) {
        JMenuItem menuItem1 = createMenuItem("短信中心", "message", "m", KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.ALT_MASK));
        menuItem1.addActionListener(this);
        menu.add(menuItem1);
        return menu;
    }

    private JMenu getMenu(String menuName) {
        /*
         * 根据名称从menuBar中查找menu并返回
         */
        JMenu menu;
        for (int i = 0; i < menuBar.getMenuCount(); i++) {
            menu = menuBar.getMenu(i);
            if (menu.getName().equals(menuName))
                return menu;
        }
        return null;
    }

    private JMenu createMenu(String name, String mnemonic, String menuName) {
        JMenu menu = new JMenu(name);
        if (mnemonic != null) {
            menu.setMnemonic(mnemonic.toCharArray()[0]);
        }
        if (menuName != null) {
            menu.setName(menuName);
        }
        return menu;
    }

    private JMenuItem createMenuItem(String text, String name, String mnemonic, KeyStroke keyStroke) {
        JMenuItem menuItem = new JMenuItem(text);
        if (mnemonic != null)
            menuItem.setMnemonic(mnemonic.toCharArray()[0]);
        if (keyStroke != null)
            menuItem.setAccelerator(keyStroke);
        if (name != null)
            menuItem.setName(name);
        return menuItem;
    }


    private void addContents() {

        startButton = new JButton("开始");
        startButton.setName("start");
        startButton.addActionListener(this);
        stopButton = new JButton("停止");
        stopButton.setName("stop");
        stopButton.addActionListener(this);
        startButton.setFont(new Font("黑体", Font.PLAIN, 14));
        stopButton.setFont(new Font("黑体", Font.PLAIN, 14));

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        //container bottom
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        gbc.weightx = 2;
        gbc.weighty = 4;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        gbc.fill = GridBagConstraints.NONE;

        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.ipadx = 0;
        gbc.ipady = 0;

        containerBottom = new JPanel();
        GridLayout gB = new GridLayout(1, 3);
        gB.setHgap(16);

        containerBottom.setLayout(gB);
        containerBottom.add(startButton);
        containerBottom.add(stopButton);

        panel.add(containerBottom, gbc);

        //container left
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.ipadx = 0;
        gbc.ipady = 0;
        containerLeft = new JPanel();

        GridLayout gL = new GridLayout(4, 1);
        gL.setVgap(32);

        containerLeft.setLayout(gL);

        bitTitleLabel = new JLabel("比特币");
        bitTitleLabel.setFont(new Font("黑体", Font.PLAIN, 16));
        bitTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bitValueLabel = new JLabel("100");
        bitValueLabel.setFont(new Font("黑体", Font.PLAIN, 20));
        bitValueLabel.setHorizontalAlignment(SwingConstants.CENTER);

        containerLeft.add(bitTitleLabel);
        containerLeft.add(bitValueLabel);
        panel.add(containerLeft, gbc);

        //container right
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.ipadx = 0;
        gbc.ipady = 0;

        containerRight = new JPanel();
        GridLayout gR = new GridLayout(4, 1);
        gR.setVgap(32);

        containerRight.setLayout(gR);
        liteTitleLabel = new JLabel("莱特币");
        liteTitleLabel.setFont(new Font("黑体", Font.PLAIN, 16));
        liteTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        liteValueLabel = new JLabel("200");
        liteValueLabel.setFont(new Font("黑体", Font.PLAIN, 20));
        liteValueLabel.setHorizontalAlignment(SwingConstants.CENTER);

        containerRight.add(liteTitleLabel);
        containerRight.add(liteValueLabel);
        panel.add(containerRight, gbc);

        this.add(panel);
    }

    public static void main(String[] args) {
        InitGlobalFont(new Font("宋体", Font.PLAIN, 12));
        MainFrame frame = new MainFrame();
        frame.setTitle("My Coins");
        frame.setIconImage(new ImageIcon(Constant.UI.iconPath).getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Constant.FrameSize.FrameWidth, Constant.FrameSize.FrameHeight);
        frame.addContents();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JMenuItem) {
            switch (((JMenuItem) e.getSource()).getName()) {
                case "settings_website":
                    break;
                case "refresh_interval":
                    break;
                case "tips_way":
                    break;
                case "software_function":
                    break;
            }
            return;
        }
        if (e.getSource() instanceof JButton) {
            switch (((JButton) e.getSource()).getName()) {
                case "start":
                    MainFrame.this.setTitle("My Coins - 正在运行");
                    isRunning = true;
                    getInfoAndRun();
                    break;
                case "stop":
                    MainFrame.this.setTitle("My Coins - 已停止运行");
                    isRunning = false;
                    break;
            }
            return;
        }
    }

    private static void InitGlobalFont(Font font) {
        FontUIResource fontRes = new FontUIResource(font);
        for (Enumeration<Object> keys = UIManager.getDefaults().keys();
             keys.hasMoreElements(); ) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, fontRes);
            }
        }
    }

    private void getInfoAndRun(){
        Runnable runnable = new Runnable() {
            public void run() {
                while (isRunning) {
                    getCoinPrice();
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

    private void getCoinPrice(){
        System.out.print("getCoinPrice");
        MyCoinHttps.getInstance().getBitcoinPrice(getBitValueSubscriber());
        MyCoinHttps.getInstance().getLitecoinPrice(getLiteValueSubscriber());
    }

    private Subscriber getBitValueSubscriber() {
        return new Subscriber<HuoBiBitcoinJson>() {
            public void onCompleted() {
            }
            public void onError(Throwable e) {
                e.printStackTrace();
            }
            public void onNext(HuoBiBitcoinJson json) {
                if (json != null && json.datas != null){
                    bitValueLabel.setText(json.datas.ticker.buy);
                    return;
                }
            }
        };
    }

    private Subscriber getLiteValueSubscriber() {
        return new Subscriber<HuoBiBitcoinJson>() {
            public void onCompleted() {
            }
            public void onError(Throwable e) {
                e.printStackTrace();
            }
            public void onNext(HuoBiBitcoinJson json) {
                if (json != null && json.datas != null){
                    liteValueLabel.setText(json.datas.ticker.buy);
                    return;
                }
            }
        };
    }
}