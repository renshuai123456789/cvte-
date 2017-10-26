package cvte.changeway;

import com.alibaba.fastjson.JSON;
import cvte.dao.blackpeopleMapper;

import cvte.util.JedisPoolUtil;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.connection.jedis.JedisUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.sql.Timestamp;
import java.util.*;

public class TEST {
    public static void main(String[]args) {
//        change a =new change();
//        a.changeurl("https://www.zhihu.com/question/29270034/answer/46446911");
          ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
//        blackpeopleMapper blackpeopleMapper=context.getBean(cvte.dao.blackpeopleMapper.class);
//
//        Timestamp temp=new Timestamp(blackpeopleMapper.getPeople(1).getStarttime().getTime());
//        System.err.println(temp.getTime());
//        System.err.println(new Date().getTime());getTime
//        long s=915855746-860800000;
//               915982891

        JedisPool jedisPool= JedisPoolUtil.getJedisPoolInstance();
        Jedis jedis=null;
        try
        {

        }
        catch (Exception e)
        {System.err.println(e.getMessage());}





    }
}
