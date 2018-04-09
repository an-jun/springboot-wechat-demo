package cc.anjun.wechat.domain.res;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.util.Date;

public class TextMessageUtil {
    private XmlMapper xmlMapper = new XmlMapper();

    public String messageToxml(MessageText message) {
        try {
            return xmlMapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }
    /**
     * 封装发送消息对象,封装时，需要将调换发送者和接收者的关系
     * @param FromUserName
     * @param ToUserName
     * @param content
     */
    public  String initMessage(String FromUserName, String ToUserName,String content) {
        MessageText text = new MessageText();
        text.setToUserName(FromUserName);
        text.setFromUserName(ToUserName);
        text.setContent(content);
        text.setCreateTime(new Date().getTime());
        text.setMsgType("text");
        return  messageToxml(text);
    }
}


