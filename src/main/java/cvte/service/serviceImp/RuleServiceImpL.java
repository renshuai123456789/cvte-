package cvte.service.serviceImp;

import cvte.dao.RuleMapper;
import cvte.service.RuleService;
import cvte.util.JedisPoolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class RuleServiceImpL implements RuleService {

    @Autowired
    RuleMapper rule;

    JedisPool pool= JedisPoolUtil.getJedisPoolInstance();

    public boolean changRule(String urlNum, String urlTime)
    {

        Jedis redis=pool.getResource();
        int i=Integer.parseInt(urlTime);
        int j=Integer.parseInt(urlNum);
        try
        {
            redis.set("rulenum", urlNum);
            redis.set("rulelooknum", urlTime);
            rule.updateRule2(i,j);
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }


        return true;
    }

    public boolean changRule1(String url)
    {

        Jedis j=pool.getResource();
        j.set("ruletime",url);
        int i=Integer.parseInt(url);
        rule.updateRule1(i);
        return true;
    }
}
