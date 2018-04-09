package cc.anjun.wechat.domain.trans;

import java.io.UnsupportedEncodingException;

public class TestTranslate {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String query = "苹果手机";
        String query1 = "Hello world";
        System.out.println(TransApi.getTransResult(query));
        System.out.println(TransApi.getTransResult(query1));
    }
}
