package com.scs.web.uni_space.util;

/**
 * @ClassName SMSUtil
 * @Description 短信工具类
 * @Author xiaobinggan
 * @Date 2019/12/3 9:25 上午
 * @Version 1.0
 **/
public class SMSUtil {
    public static String send(String mobile) {
//        DefaultProfile profile = DefaultProfile.getProfile(
//                "cn-hangzhou",
//                "LTAI4FqFoaZ1cHStok8RfGK5",
//                "xz1hFNQiY0RAf9oOfMo9neajw0j6aD");
//        IAcsClient client = new DefaultAcsClient(profile);
//        CommonRequest request = new CommonRequest();
//        request.setMethod(MethodType.POST);
//        request.setDomain("dysmsapi.aliyuncs.com");
//        request.setVersion("2017-05-25");
//        request.setAction("SendSms");
//        request.putQueryParameter("RegionId", "cn-hangzhou");
//        request.putQueryParameter("PhoneNumbers", mobile);
//        request.putQueryParameter("SignName", "space");
//        request.putQueryParameter("TemplateCode", "SMS_179295580");
        String verifyCode = StringUtil.getVerifyCode();
//        request.putQueryParameter("TemplateParam", "{\"code\":" + verifyCode + "}");
//        try {
//            CommonResponse response = client.getCommonResponse(request);
//            System.out.println(response.getData());
//        } catch (ServerException e) {
//            e.printStackTrace();
//        } catch (ClientException e) {
//            e.printStackTrace();
//        }
        return verifyCode;
    }

    public static void main(String[] args) {
        System.out.println(send("17625835438"));
    }
}
