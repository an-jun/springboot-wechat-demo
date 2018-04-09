package cc.anjun.wechat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class WechatApplication {
    @RequestMapping("/")
	public @ResponseBody String
	test(){
		return "test";
	}
	public static void main(String[] args) {
		SpringApplication.run(WechatApplication.class, args);
	}
}
