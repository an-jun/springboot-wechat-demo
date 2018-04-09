package cc.anjun.wechat.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
@Component
public class CheckUtil {
    @Value("${wechat.token}")
    public  void setToken(String token) {
        CheckUtil.token = token;
    }

    private static String token ;
    public  static boolean checkSignature(String signature,String timestamp,String nonce){
        String[] ar = new String[]{token,timestamp,nonce};
        Arrays.sort(ar);
        StringBuffer buffer = new StringBuffer();
        for(int i=0;i<ar.length;i++){
            buffer.append(ar[i]);
        }
        String temp = SHA1.encode(buffer.toString());
        return signature.equals(temp);
    }
}
