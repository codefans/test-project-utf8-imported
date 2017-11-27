package com.codefans.practicetask.httprequest;

import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: ShengzhiCai
 * @Date: 2017-11-11 11:44
 */

public class HttpParams {

    public static final String GET = "get";
    public static final String POST = "post";

    /**
     * 参数名称
     */
    public static final String PARAM_URLKEY = "urlKey";
    public static final String PARAM_UTM_TERM = "utm_term";
    public static final String PARAM_UTM_SOURCE = "utm_source";
    public static final String PARAM_UTM_MEDIUM = "utm_medium";
    public static final String PARAM_FROM = "from";
    public static final String PARAM_ISAPP_INSTALLED = "isappinstalled";

    /**
     * 请求头
     */
    public static final String HEADER_ACCEPT = "Accept";
    public static final String HEADER_ACCEPT_ENCODING = "Accept-Encoding";
    public static final String HEADER_ACCEPT_LANGUAGE = "Accept-Language";
    public static final String HEADER_CACHE_CONTROL = "Cache-Control";
    public static final String HEADER_CONNECTION = "Connection";
    public static final String HEADER_COOKIE = "Cookie";
    public static final String HEADER_HOST = "Host";
    public static final String HEADER_UPGRADE_INSECURE_REQUESTS = "Upgrade-Insecure-Requests";
    public static final String HEADER_USER_AGENT = "User-Agent";
//    public static final String HEADER_ACCEPT = "";

    private List<NameValuePair> postParamList = new ArrayList<NameValuePair>();

    Map<String, String> requestParams = new HashMap<String, String>();

    public static Map<String, String> requestHeaders = new HashMap<String, String>();

    static {
        requestHeaders.put(HEADER_ACCEPT, "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        requestHeaders.put(HEADER_ACCEPT_ENCODING, "gzip, deflate, br");
        requestHeaders.put(HEADER_ACCEPT_LANGUAGE, "zh-CN,zh;q=0.9");
        requestHeaders.put(HEADER_CACHE_CONTROL, "max-age=0");
        requestHeaders.put(HEADER_CONNECTION, "keep-alive");
//        requestHeaders.put(HEADER_COOKIE, "webp=1; userTicket=fCcwmzZutKTcgcdnJiLkYTgJDbJDZnVwrUsTJcPP; token2=-czlwB2hlnmlYzPcN0bSoACc9XwAAAAAtgMAALTbKp0Jl3fBaac_x-087-XJO1hizwJ0Lq9yJT423ecpw28rZddUIJpqP4FzEp9DRQ; u=39284250; n=%E8%91%89%E5%AD%90%E5%A8%81; lt=-czlwB2hlnmlYzPcN0bSoACc9XwAAAAAtgMAALTbKp0Jl3fBaac_x-087-XJO1hizwJ0Lq9yJT423ecpw28rZddUIJpqP4FzEp9DRQ; ls=1489744905; _lxsdk_cuid=15c96435e79c8-087e6dc0b0695-50462f1d-100200-15c96435e79c8; abt=1498876469.0%7CADE; em=bnVsbA; om=bnVsbA; ttgr=278101; ppos=39.904647%2C116.45474; pposn=%E5%B7%AB%E5%B1%B1%E7%83%A4%E5%85%A8%E9%B1%BC; __mta=213619627.1489744818995.1498878279213.1498878288823.49; __utma=211559370.1155851786.1489744819.1497778377.1498876469.7; __utmc=211559370; __utmz=211559370.1489744819.1.1.utmcsr=baidu|utmccn=baidu|utmcmd=organic|utmcct=homepage; __utmv=211559370.|1=city=bj=1^3=dealtype=229=1; uuid=5f3202fb056f4198e467.1489744821.1.0.1; oc=qiBPn0x80zpGg4KXPDrHvsO6IA2AcXmOtBk0mPv2ROymM_j74jovGQ9e6WAL7qGQnDuVhLscuu3V4i48WPdlVkjnlnynU6-unytAR31JIbQSEzVC4q5X3xT9okioEIwjWdMobnoM8YNN_bPlDW4Yx4cL_hvn8RSj7hwCWuBlpiE; rvd=3592438%2C34312110; ci=1; rvct=1; mtcdn=K; _ga=GA1.3.1155851786.1489744819; _gid=GA1.3.545265699.1511584561; __mta=213619627.1489744818995.1498878288823.1511584561058.50; coupon_uuid=\"A124751EF2BD50C7DD807146E12C67BF5poCAOb%2BR6HCd8xpov5zTd6ajbcZ3O%2FLaSS0nzcBK6GpEIfLH0Lzi%2FJl7YGpPchsqFIDZegXV2iO3IGQYK7zUN7hP3%2BHbKWFprkyMHwGCkk%3D\"; JSESSIONID=1gtyzbwaaepnjpmii0i0yh4u7; _lx_utm=utm_term%3DAiphoneBgroupC8.4.1DweixinEwm-orderGF8A3848E9B6C216A4256215A50BE7FDCF1EC5C2E836B8CB464359A6CF70F782920171123112730467%26utm_source%3Dappshare%26utm_medium%3DiOSweb; __mta=213619627.1489744818995.1498878288823.1511596384635.50; grap_cookie_phone_ab=15010198727; h_cookie_phone=15010198727; h_w_uuid=a7f31309-df22-4261-8412-dc140dea84de; user_coupon_token=2080362; _lxsdk_s=%7C%7C0");
        requestHeaders.put(HEADER_HOST, "activity.waimai.meituan.com");
        requestHeaders.put(HEADER_UPGRADE_INSECURE_REQUESTS, "1");

        //windows chrome user-agent
//        requestHeaders.put(HEADER_USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.89 Safari/537.36");

        //phone user-agent
//        User-Agent: Mozilla/5.0 (iPhone; CPU iPhone OS 10_3_3 like Mac OS X) AppleWebKit/603.3.8 (KHTML, like Gecko) Mobile/14G60 MicroMessenger/6.5.21 NetType/WIFI Language/zh_CN
        requestHeaders.put(HEADER_USER_AGENT, "Mozilla/5.0 (iPhone; CPU iPhone OS 10_3_3 like Mac OS X) AppleWebKit/603.3.8 (KHTML, like Gecko) Mobile/14G60 MicroMessenger/6.5.21 NetType/WIFI Language/zh_CN");




    }


}
