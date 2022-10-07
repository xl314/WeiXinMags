package lpssy.utils;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import lpssy.domain.TokenInfo;

import java.util.logging.Logger;


public class VxUtil {
    // 公众号的 ID wx4b80efcf1a2b199b 测试号的 wx9c1d24219708514f
    private static final String AppID = "wx4b80efcf1a2b199b";
    //公众号的 72f705fd3f9d91ce2c2088d26676cc28 测试公众号的432192523d33cd1f4d64767b83a5d0fe
    private static final String appSecret= "72f705fd3f9d91ce2c2088d26676cc28";

    private static final Logger log = Logger.getAnonymousLogger();

    public static TokenInfo getToken(){
        String TOKEN_URL ="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
        String result = HttpUtil.get(String.format(TOKEN_URL, AppID, appSecret));
        return JSONUtil.toBean(result,TokenInfo.class);
    }


    public static void sendMessage(String message){
        String PUSH_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=%s";
        TokenInfo token = getToken();
        String result =  HttpUtil.post(String.format(PUSH_URL,token.getAccess_token()),message);
        log.info(result);
    }

}
