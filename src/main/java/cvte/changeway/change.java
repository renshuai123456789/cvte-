
package cvte.changeway;



import java.security.MessageDigest;

public class Change {



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
        String[] resUrl = new String[4];
        String sTempSubString = url2.substring(i * 8, i * 8 + 8);
        long lHexLong = 0x3FFFFFFF & Long.parseLong (sTempSubString, 16);
        String outChars = "" ;
        for ( int j = 0; j < 7; j++) {
            long index = 0x0000003D & lHexLong;
            outChars += chars[( int ) index];
            lHexLong = lHexLong >> 5;
            }
            shorturl+=outChars;
        return shorturl;
    }


}
