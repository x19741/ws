package spring.util;

public interface UserConstantInterface {

    // 请求的网址
    public static final String WX_LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session";
    // 你的appid
    //public static final String WX_LOGIN_APPID = "wxc23397e513d8dbda";     //罗
    public static final String WX_LOGIN_APPID = "wx390e97109ec509e1";       //宏
    //public static final String WX_LOGIN_APPID = "wx33c38f7aab9ce704";     //文


    // 你的密匙
    //public static final String WX_LOGIN_SECRET = "ef9ff72bd19454ab960f900e95910270";
    public static final String WX_LOGIN_SECRET = "47216c2ad750771661c9bfffe674e58a";
    //public static final String WX_LOGIN_SECRET = "b147c8a58a7d5f62e859da1fe90ea2de";


    // 固定参数
    public static final String WX_LOGIN_GRANT_TYPE = "authorization_code";

}
