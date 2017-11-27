package com.codefans.practicetask.luckred;

import com.codefans.practicetask.httprequest.HttpParams;
import com.codefans.practicetask.httprequest.HttpRequest;
import com.codefans.practicetask.httprequest.HttpResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ShengzhiCai
 * @Date: 2017-11-25 16:47
 * 拼手气红包
 *
 */

public class FightLuckRed {

    public static void main(String[] args) {
        FightLuckRed flr = new FightLuckRed();
        flr.startup();
    }

    public void startup() {

//        https://activity.waimai.meituan.com/coupon/sharechannelredirect/B2EA8E1ABA8B47EA82DB475BA17B517D?urlKey=0AE472E0D83C494B9A5AFBF7B625A275&state=123&uiId=0&code=003TJWia2XxKjO05gria2wCIia2TJWig

//        https://activity.waimai.meituan.com/coupon/sharechannel/B2EA8E1ABA8B47EA82DB475BA17B517D?urlKey=8AFD02EA0A214DB79360CDFDF8DD57C5&utm_term=AiphoneBwaimaiC6.0.2DweixinEwm-orderG3840C77EA75B521BCC15AD435F598C454A99D4E1D4F35F5A5C7D3236DC9C206420171125103405902&utm_source=appshare&utm_medium=iOSweb&from=groupmessage&isappinstalled=0

        //https://activity.waimai.meituan.com/coupon/sharechannel/B2EA8E1ABA8B47EA82DB475BA17B517D
        // ?urlKey=8AFD02EA0A214DB79360CDFDF8DD57C5
        // &utm_term=AiphoneBwaimaiC6.0.2DweixinEwm-orderG3840C77EA75B521BCC15AD435F598C454A99D4E1D4F35F5A5C7D3236DC9C206420171125103405902
        // &utm_source=appshare
        // &utm_medium=iOSweb
        // &from=groupmessage
        // &isappinstalled=0

//        https://activity.waimai.meituan.com/coupon/sharechannel/B2EA8E1ABA8B47EA82DB475BA17B517D?urlKey=AE435D2A963C411C935234E064507CDE&utm_term=AiphoneBgroupC8.4.1DweixinEwm-orderGF8A3848E9B6C216A4256215A50BE7FDCF1EC5C2E836B8CB464359A6CF70F782920171116111317447&utm_source=appshare&utm_medium=iOSweb
//        https://activity.waimai.meituan.com/coupon/sharechannel/B2EA8E1ABA8B47EA82DB475BA17B517D?urlKey=80AD21945B5B4346A92ACB7BC64CA560&from=groupmessage&isappinstalled=0
        String url = "https://activity.waimai.meituan.com/coupon/sharechannel/B2EA8E1ABA8B47EA82DB475BA17B517D";
        String method = HttpParams.GET;

        Map<String, String> requestHeaders = HttpParams.requestHeaders;


        Map<String, String> requestParams = new HashMap<String, String>();
        requestParams.put(HttpParams.PARAM_URLKEY, "8AFD02EA0A214DB79360CDFDF8DD57C5");
        requestParams.put(HttpParams.PARAM_UTM_TERM, "AiphoneBwaimaiC6.0.2DweixinEwm-orderG3840C77EA75B521BCC15AD435F598C454A99D4E1D4F35F5A5C7D3236DC9C206420171125103405902");
        requestParams.put(HttpParams.PARAM_UTM_SOURCE, "appshare");
        requestParams.put(HttpParams.PARAM_UTM_MEDIUM, "iOSweb");
        requestParams.put(HttpParams.PARAM_FROM, "groupmessage");
        requestParams.put(HttpParams.PARAM_ISAPP_INSTALLED, "0");


        HttpRequest httpRequest = new HttpRequest(url, method, requestHeaders, requestParams);
        HttpResponse httpResponse = httpRequest.call();
        int responseCode = httpResponse.getResponseCode();
        String responseMsg = httpResponse.getResponseMsg();
        System.out.println("responseCode:" + responseCode);
        System.out.println("responseMsg:" + responseMsg);

    }

}
