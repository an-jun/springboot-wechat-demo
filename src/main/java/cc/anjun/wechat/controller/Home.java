package cc.anjun.wechat.controller;

import cc.anjun.wechat.domain.res.ImageMessageUtil;
import cc.anjun.wechat.domain.res.TextMessageUtil;
import cc.anjun.wechat.domain.trans.TransApi;
import cc.anjun.wechat.utils.CheckUtil;
import cc.anjun.wechat.utils.MessageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@Controller
public class Home {
    @RequestMapping("/api")
    public @ResponseBody String login(@RequestParam("signature") String signature, @RequestParam("timestamp") String timestamp, @RequestParam("nonce") String nonce, @RequestParam(value = "echostr",defaultValue = "") String echostr, @RequestParam(value = "openid",defaultValue = "") String openid ,HttpServletRequest request) throws UnsupportedEncodingException {
        String ret="";
        if(CheckUtil.checkSignature(signature,timestamp,nonce)){
            System.out.println("checksignature success");
            ret = echostr;
        }else{
            System.out.println("checksignature fail");
            return "";
        }
        if(request.getMethod().equals("GET")){
            return  ret;
        }else if(request.getMethod().equals("POST")){
            Map<String,String> map = MessageUtil.xmlToMap(request);
            String ToUserName = map.get("ToUserName");
            String FromUserName = map.get("FromUserName");
            String MsgType = map.get("MsgType");
            String Content = map.get("Content");

            String message = null;
            //处理文本类型，实现输入1，回复相应的封装的内容
            if("text".equals(MsgType)){
                    TextMessageUtil textMessage = new TextMessageUtil();
                    message = textMessage.initMessage(FromUserName, ToUserName,Content);
                    return message;
            }
            if("text".equals(MsgType)){
                if("图片".equals(Content)){
                    ImageMessageUtil util = new ImageMessageUtil();
                    message = util.initMessage(FromUserName, ToUserName);
                }else{
                    TextMessageUtil textMessage = new TextMessageUtil();
                    message = textMessage.initMessage(FromUserName, ToUserName,Content);
                }
            }else if(Content.startsWith("翻译")){
                //设置翻译的规则，以翻译开头
                TextMessageUtil textMessage = new TextMessageUtil();
                //将翻译开头置为""
                String query = Content.replaceAll("^翻译", "");
                if(query==""){
                    //如果查询字段为空，调出使用指南
                    message = textMessage.initMessage(FromUserName, ToUserName,Content);
                }else{
                    message = textMessage.initMessage(FromUserName, ToUserName, TransApi.getTransResult(query));
                }
            }
            else{
                TextMessageUtil textMessage = new TextMessageUtil();
                message = textMessage.initMessage(FromUserName, ToUserName,"您输入的内容是:"+Content);
            }
        }
        return "";
    }
}
