package cvte.service.serviceImp;

import cvte.dao.blackpeopleMapper;


import cvte.dao.cvteurlMapper;
import cvte.service.blackpeopleService;
import cvte.util.JedisPoolUtil;
import cvte.vo.blackpeople;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class blackpeopleImp implements blackpeopleService {
    @Autowired
    blackpeopleMapper mapper;

    @Autowired
    cvteurlMapper urlmapper;

    JedisPool pool=JedisPoolUtil.getJedisPoolInstance();


    /*
    这是进入后判断是否可以访问的黑名单
     */

    @Override
    public boolean insertpeople(blackpeople p) {
        return false;
    }

    @Override
    public boolean getpeople(int id,int rule) {

        blackpeople people=mapper.getPeople(id);
        Date startdate=people.getStarttime();
        Date lasttime=people.getLasttime();
        Timestamp timp=new Timestamp(startdate.getTime());
        long minute=new Date().getTime()-startdate.getTime();
        System.err.println("查看是否解禁成功");
        System.err.println(minute/(1000*60)+"  "+rule+"fadsfdsafdsafdsafdsajfdsajfdsakfsajfjdsalkf");

        if(minute/60000>rule)
        {
            return true;
        }else
        {
            return false;
        }
    }

    /*
    获取要加入的黑名单人员信息
     */
    @Override
    public blackpeople getpeople(int id) {

        return mapper.getPeople(id);
    }


}
