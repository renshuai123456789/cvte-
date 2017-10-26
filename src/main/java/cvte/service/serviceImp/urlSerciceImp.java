package cvte.service.serviceImp;

import cvte.changeway.change;
import cvte.changeway.md5;
import cvte.dao.blackpeopleMapper;
import cvte.dao.cvteurlMapper;
import cvte.dao.ruleMapper;
import cvte.dao.userMapper;
import cvte.pojo.cvte_rule;
import cvte.service.blackpeopleService;
import cvte.service.urlService;
import cvte.util.JedisPoolUtil;
import cvte.vo.blackpeople;
import cvte.vo.cvte_url;
import cvte.vo.cvte_user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Service
public class urlSerciceImp implements urlService {

    @Autowired
    blackpeopleService bimp;

    @Autowired
    userMapper userdao;

    @Autowired
    blackpeopleMapper bdao;

    @Autowired
    ruleMapper rule;

    @Autowired
    cvteurlMapper urlmapper;

    JedisPool pool= JedisPoolUtil.getJedisPoolInstance();

    md5 md=new md5();

    @Override
    public String urlShortChange(String url, HttpSession session, HttpServletRequest request) {

        Jedis redis=pool.getResource();
        String ip=request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        /*
        将要转化的url弄成相应的MD5
         */

        if(session.getAttribute("time")==null)
        {
            session.setAttribute("time",new Date());
            session.setAttribute("num",0);
            System.err.println(session.getAttribute("num"));
        }else
        {
            Integer a=(Integer) session.getAttribute("num");
            a++;
            session.setAttribute("num",a);
            System.err.println(a+"renshuai");
        }
        Date sdate=new Date();
        Date ldate=(Date) session.getAttribute("time");
        long s=(sdate.getTime()-ldate.getTime())/1000;
        int num=(int)session.getAttribute("num");
        //如果10秒内点击超过15下，那么将会加入黑名单。
        //默认的黑名单时间为15分钟。
        synchronized (urlSerciceImp.class) {


            cvte_rule rule1=null;
            int rule_num;
            int rule_time;
            if(redis.get("rulenum")==null&&redis.get("rulelooknum")==null)
            {

                rule1 = rule.getRule();
                rule_num = rule1.getRulelooknum();
                rule_time= rule1.getRuletime();
            }else
            {
                rule_num=Integer.parseInt(redis.get("rulenum"));
                rule_time=Integer.parseInt(redis.get("rulelooknum"));
            }

            if(s>rule_time)
            {
                session.setAttribute("time",new Date());
            }






            if (((Integer) session.getAttribute("gettime")) == 0) {
                if ((s < rule_time && num > rule_num)) {
                    System.err.println("这里面执行了哦");
                    int userid = ((cvte_user) session.getAttribute("name")).getId();
                    if (bdao.getPeople(userid) != null) {
                        try {
                            System.err.println("兄弟加油啊");
                            bdao.updatePeople(new blackpeople(userid, new Date(), new Date()));
                            return "兄弟你操作太快了，黑名单中出现了你，15分钟后再来试试吧";
                        } catch (Exception e) {
                            System.err.println(e.getMessage());
                        }

                    } else {
                        blackpeople p = new blackpeople(userid, new Date(), new Date());
                        bdao.insertPeople(p);
                        session.setAttribute("gettime", 1);
                        return "你被加入黑名单了";
                    }
                } else {

                    System.err.println("这步要加入redis");
                    if(redis.get(url)==null)
                    {
                        cvte_url cvte_url=urlmapper.selectByShortUrl(url);
                        if(cvte_url==null)
                            return this.urlChange(url,session,request);
                        redis.set(url,cvte_url.getLong_url());
                        return cvte_url.getLong_url();
                    }else
                    {
                        return redis.get(url);
                    }


                }
            }
        }


        return "不可预知的错误发生了";
    }


    @Override
    public String urlChange(String url, HttpSession session, HttpServletRequest request) {

        Jedis redis=pool.getResource();
        String ip=request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        /*
        将要转化的url弄成相应的MD5
         */



        int id=((cvte_user)session.getAttribute("name")).getId();
        System.err.println("查看是否解禁成功");
        blackpeople people1=bdao.getPeople(id);

        if(people1!=null)
        {

            int time;
            if(redis.get("ruletime")==null)
            {
                time=rule.getRule().getRuletime();
            }
            else
            {
                time=Integer.parseInt(redis.get("ruletime"));
            }
            if(bimp.getpeople(id,time))
            {
                bdao.delete(id);
                change ch=new change();
                String shorturl=null;
                if(redis.get(url)==null)
                {
                    if(urlmapper.selectByLongUrl(url)==null)
                    {
                       shorturl= ch.changeurl(url);
                       System.err.println("这里应该开始加入url了");
                       urlmapper.insert(new cvte_url(url,shorturl,new Date()));
                       redis.set(url,shorturl);
                       return shorturl;
                    }else
                    {
                        cvte_url urls=urlmapper.selectByLongUrl(url);
                        redis.set(url,urls.getShort_url());
                        return urls.getShort_url();
                    }
                }else
                {
                    return redis.get(url);
                }
            }else
            {
                System.err.println("黑名单人员++++++++++");
                return "你个mmp没有权限了，辣鸡,去等15分钟吧";
            }
        }


        System.err.println(session.getAttribute("time"));
        if(session.getAttribute("time")==null)
        {

            session.setAttribute("time",new Date());
            session.setAttribute("num",0);
            System.err.println(session.getAttribute("num"));
        }else
        {
            Integer a=(Integer) session.getAttribute("num");
            a++;
            session.setAttribute("num",a);
            System.err.println(a+"renshuai");
        }
        Date sdate=new Date();
        Date ldate=(Date) session.getAttribute("time");
        long s=(sdate.getTime()-ldate.getTime())/1000;
        int num=(int)session.getAttribute("num");
        //如果10秒内点击超过15下，那么将会加入黑名单。
        //默认的黑名单时间为15分钟。

        synchronized (urlSerciceImp.class) {


            cvte_rule rule1=null;
            int rule_num;
            int rule_time;
            if(redis.get("rulenum")==null&&redis.get("rulelooknum")==null)
            {
                rule1 = rule.getRule();
                rule_num = rule1.getRulelooknum();
                rule_time= rule1.getRuletime();
            }else
            {
                rule_num=Integer.parseInt(redis.get("rulenum"));
                rule_time=Integer.parseInt(redis.get("rulelooknum"));
            }

            if(s>rule_time)
            {
                session.setAttribute("time",new Date());
            }





            if (((Integer) session.getAttribute("gettime")) == 0) {
                if ((s < rule_time && num > rule_num)) {
                    System.err.println("这里面执行了哦");
                    int userid = ((cvte_user) session.getAttribute("name")).getId();
                    if (bdao.getPeople(userid) != null) {
                        try {
                            System.err.println("兄弟加油啊");
                            bdao.updatePeople(new blackpeople(userid, new Date(), new Date()));
                            return "兄弟你操作太快了，黑名单中出现了你，15分钟后再来试试吧";
                        } catch (Exception e) {
                            System.err.println(e.getMessage());
                        }

                    } else {
                        blackpeople p = new blackpeople(userid, new Date(), new Date());
                        bdao.insertPeople(p);
                        session.setAttribute("gettime", 1);
                        return "你被加入黑名单了";
                    }
                } else {

                    change ch=new change();
                    String shorturl=null;
                    if(redis.get(url)==null)
                    {
                        if(urlmapper.selectByLongUrl(url)==null)
                        {
                            shorturl= ch.changeurl(url);
                            urlmapper.insert(new cvte_url(url,shorturl,new Date()));
                            redis.set(url,shorturl);
                            return shorturl;
                        }else
                        {
                            cvte_url urls=urlmapper.selectByLongUrl(url);
                            redis.set(url,urls.getShort_url());
                            return urls.getShort_url();
                        }
                    }else
                    {
                        return redis.get(url);
                    }
                }
            }
        }


        return "不可预知的错误发生了";
    }

}
