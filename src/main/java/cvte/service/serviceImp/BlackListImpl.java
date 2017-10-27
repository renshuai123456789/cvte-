package cvte.service.serviceImp;

import cvte.dao.BlackListMapper;


import cvte.dao.CvteUrlMapper;
import cvte.service.BlackListService;
import cvte.util.JedisPoolUtil;
import cvte.vo.BlackList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;

import java.util.Date;

@Service
public class BlackListImpl implements BlackListService {
    @Autowired
    BlackListMapper mapper;

    @Autowired
    CvteUrlMapper urlmapper;

    JedisPool pool=JedisPoolUtil.getJedisPoolInstance();


    /*
    这是进入后判断是否可以访问的黑名单
     */

    @Override
    public boolean insertpeople(BlackList p) {
        return false;
    }

    @Override
    public boolean getpeople(int id,int rule) {

        BlackList people=mapper.getPeople(id);
        Date startdate=people.getStarttime();
        long minute=new Date().getTime()-startdate.getTime();
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
    public BlackList getpeople(int id) {

        return mapper.getPeople(id);
    }


}
