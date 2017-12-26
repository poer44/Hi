package utils;


import java.util.Random;

/**
 * Created by ZWJ on 2017/10/12/0012.
 */
public class PhoneCodeUtils {
    /**
     * 获取6位随机生成的验证码
     * @return
     */
    public static String getVerifyCode() {
        String[] verifyString = new String[] { "0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9" };
        Random random = new Random(System.currentTimeMillis());
        StringBuilder verifyBuilder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int rd = random.nextInt(10);
            verifyBuilder.append(verifyString[rd]);
        }
        String verifyCode = verifyBuilder.toString();
        return verifyCode;
    }
    /*public static  void main(String[] args){
        System.out.println(getVerifyCode());
    }*/
}
