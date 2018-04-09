package cc.anjun.wechat.domain.res;

import cc.anjun.wechat.domain.BaseMessage;

public class ImageMessage extends BaseMessage {

    private Image Image;//Image节点

    public Image getImage() {
        return Image;
    }

    public void setImage(Image image) {
        Image = image;
    }


}