package com.util;

import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.sun.javafx.collections.MappingChange.Map;
/**
 * 阿里云短信服务！
 * @author 火禾
 *
 */
public class SmsUtil {
	public static boolean send(String phoneNum,String temlateCode,int phoneN) {
		 //连接阿里云
       DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4G9EbJp6iChd2ZdPpLLK", "PyNC1Xx64fFxctOXKV9E8KBlGoQzGC");
       IAcsClient client = new DefaultAcsClient(profile);

       //构建请求！！！
       CommonRequest request = new CommonRequest();
       request.setMethod(MethodType.POST);
       request.setDomain("dysmsapi.aliyuncs.com");//不要动
       request.setVersion("2017-05-25");//不要动
       request.setAction("Sendsms");

       //自定义的参数（手机号，验证码，签名，模板）
       request.putQueryParameter("PhoneNumbers", phoneNum);
       request.putQueryParameter("SignName", "huohe");
       request.putQueryParameter("TemplateCode", temlateCode);//"SMS_190270725"

       //构建一个短信验证码
       HashMap<String,Object> map = new HashMap<String, Object>();
       map.put("code",phoneN);
//       String m = "{"code","2233"}";
       System.out.println(JSONObject.toJSONString(map));

       request.putQueryParameter("TemplateParam", JSONObject.toJSONString(map));
       try {
           CommonResponse response = client.getCommonResponse(request);
           System.out.println(response.getData());
           return response.getHttpResponse().isSuccess();
       } catch (ServerException e) {
           e.printStackTrace();
       } catch (ClientException e) {
           e.printStackTrace();
       }
		return false;
   
	
	}
}
