package cvte.service.serviceImp;

import cvte.dao.ruleMapper;
import cvte.util.JedisPoolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class ruleServiceImp {

    @Autowired
    ruleMapper rule;

    JedisPool pool= JedisPoolUtil.getJedisPoolInstance();

    public boolean changrule(String urlnum,String urltime)
    {

        Jedis redis=pool.getResource();
        int i=Integer.parseInt(urltime);
        int j=Integer.parseInt(urlnum);
        try
        {
            redis.set("rulenum",urlnum);
            redis.set("rulelooknum",urltime);
            System.err.println(redis.get("rulenum"));
            System.err.println(redis.get("rulelooknum"));
            rule.updateRule2(i,j);
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        System.out.println(urltime+" "+urlnum);
        return true;
    }

    public boolean changrule1(String url)
    {

        Jedis j=pool.getResource();
        System.out.println("开始了");
        System.err.println(j.get("ruletime"));
        j.set("ruletime",url);
        System.err.println(j.get("ruletime"));
        System.out.println("结束了");
        int i=Integer.parseInt(url);

        rule.updateRule1(i);
        return true;
    }
}
