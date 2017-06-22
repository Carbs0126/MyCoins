package constants;

import java.io.File;

/**
 * Created by Rick.Wang on 2017/6/21.
 */
public class Constant {

    public static final boolean release = true;
    public static class FrameSize{
        public static final int FrameWidth = 400;
        public static final int FrameHeight = 300;
    }

    public static class UI{

        public static final String prefixPath = release ? System.getProperty("user.dir") :
                System.getProperty("user.dir") + File.separator + "src" + File.separator + "main";

        public static final String iconPath = prefixPath + File.separator + "resources" + File.separator + "icon" + File.separator + "coin.png";

        /*public static final String iconPath = release ?
                System.getProperty("user.dir")
                        + File.separator + "resources" + File.separator + "icon" + File.separator + "coin.png"
                :
                System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                        + File.separator + "resources" + File.separator + "icon" + File.separator + "coin.png";*/
    }
    public static class NetConfig{
        public static final String BaseUrl = "https://www.btc123.com/api/";
    }
}
