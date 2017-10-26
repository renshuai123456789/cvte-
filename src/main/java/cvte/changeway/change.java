
package cvte.changeway;



import redis.clients.jedis.Jedis;

import java.security.MessageDigest;

public class change {



    volatile static int i=1;
    /*
    转换算法
     */
    public String changeurl(String url)
    {
        /*
        7位的短连接,26个字母
         */

        String[] str=url.split("//");
        for(int i=0;i<str.length;i++) {
            System.out.println(str[i]);
        }

        String shorturl="http://t.cn/";
        String[] chars = new String[] { "a" , "b" , "c" , "d" , "e" , "f" , "g" , "h" ,
                "i" , "j" , "k" , "l" , "m" , "n" , "o" , "p" , "q" , "r" , "s" , "t" ,
                "u" , "v" , "w" , "x" , "y" , "z" , "0" , "1" , "2" , "3" , "4" , "5" ,
                "6" , "7" , "8" , "9" , "A" , "B" , "C" , "D" , "E" , "F" , "G" , "H" ,
                "I" , "J" , "K" , "L" , "M" , "N" , "O" , "P" , "Q" , "R" , "S" , "T" ,
                "U" , "V" , "W" , "X" , "Y" , "Z"
        };

        MessageDigest md5=null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
        char[] charArray = url.toCharArray(); //将字符串转换为字符数组
        byte[] byteArray = new byte[charArray.length]; //创建字节数组

        for (int i = 0; i < charArray.length; i++){
            byteArray[i] = (byte) charArray[i];
        }

        //将得到的字节数组进行MD5运算
        byte[] md5Bytes = md5.digest(byteArray);

        StringBuffer md5Str= new StringBuffer();

        for (int i = 0; i < md5Bytes.length; i++){
            if (Integer.toHexString(0xFF & md5Bytes[i]).length() == 1)
                md5Str.append("0").append(Integer.toHexString(0xFF & md5Bytes[i]));
            else
                md5Str.append(Integer.toHexString(0xFF & md5Bytes[i]));
        }

        String url2=md5Str.toString();
        System.out.println(url2.length());

        String[] resUrl = new String[4];


            // 把加密字符按照 8 位一组 16 进制与 0x3FFFFFFF 进行位与运算
            String sTempSubString = url2.substring(i * 8, i * 8 + 8);

            // 这里需要使用 long 型来转换，因为 Inteper .parseInt() 只能处理 31 位 , 首位为符号位 , 如果不用 long ，则会越界
            long lHexLong = 0x3FFFFFFF & Long.parseLong (sTempSubString, 16);
            String outChars = "" ;
            for ( int j = 0; j < 7; j++) {
                // 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引
                long index = 0x0000003D & lHexLong;
                // 把取得的字符相加
                outChars += chars[( int ) index];
                // 每次循环按位右移 5 位
                lHexLong = lHexLong >> 5;

            }
            shorturl+=outChars;

            System.out.println(shorturl);



        return shorturl;
    }


}
