package demo;

import cc.anjun.wechat.utils.WeiXinUtil;

public  class Demo{
    public static void main(String[] args) {
        String access_token = WeiXinUtil.getAccess_Token();
        System.out.println("调用成功access_token:"+access_token);
    }
}
