package cc.anjun.wechat.domain.res;

import cc.anjun.wechat.utils.UploadUtil;
import cc.anjun.wechat.utils.WeiXinUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Date;

public class ImageMessageUtil implements BaseMessageUtil<ImageMessage> {
    private XmlMapper xmlMapper = new XmlMapper();
    /**
     * 将信息转为xml格式
     */
    public String messageToxml(ImageMessage imageMessage) {
        try {
            return xmlMapper.writeValueAsString(imageMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }
    /**
     * 封装信息
     */
    public String initMessage(String FromUserName, String ToUserName) {
        Image image = new Image();
        image.setMediaId(getmediaId());
        ImageMessage message = new ImageMessage();
        message.setFromUserName(ToUserName);
        message.setToUserName(FromUserName);
        message.setCreateTime(new Date().getTime());
        message.setImage(image);
        return messageToxml(message);
    }
    /**
     * 获取Media
     * @return
     */
    public String getmediaId(){
        String path = "f:/1.jpg";
        String accessToken = WeiXinUtil.getAccess_Token();
        String mediaId = null;
        try {
            mediaId = UploadUtil.upload(path, accessToken, "image");

        } catch (KeyManagementException | NoSuchAlgorithmException
                | NoSuchProviderException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return mediaId;
    }
}
