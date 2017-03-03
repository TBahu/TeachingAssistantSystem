package ustc.sse.sa16225300.util;

import java.util.Random;

public class PwdGenerator {
	//生成8位长的密码
	public static String getPwd(){  
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";  
        Random random = new Random();  
        StringBuffer sb = new StringBuffer();  
          
        for(int i = 0 ; i < 8; ++i){  
            int number = random.nextInt(62);//[0,62)  
            sb.append(str.charAt(number));  
        }  
        return sb.toString();  
    } 
}
