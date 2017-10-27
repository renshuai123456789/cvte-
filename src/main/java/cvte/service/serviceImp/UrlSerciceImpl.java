package cvte.service.serviceImp;

import cvte.changeway.Change;
import cvte.changeway.Md5;
import cvte.dao.BlackListMapper;
import cvte.dao.CvteUrlMapper;
import cvte.dao.RuleMapper;
import cvte.dao.UserMapper;
import cvte.pojo.CvteRule;
import cvte.service.BlackListService;
import cvte.service.UrlService;
import cvte.util.JedisPoolUtil;
import cvte.vo.BlackList;
import cvte.vo.CvteUrl;
import cvte.vo.CvteUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Service
public class UrlSerciceImpl implements UrlService {

    @Autowired
    BlackListService bimp;

    @Autowired
    UserMapper userdao;

    @Autowired
    BlackListMapper bdao;

    @Autowired
    RuleMapper rule;

    @Autowired
    CvteUrlMapper urlmapper;

    JedisPool pool= JedisPoolUtil.getJedisPoolInstance();

    Md5 md=new Md5();

    @Override
    public String urlShortChange(String url, HttpSession session, HttpServletRequest request) {

        Jedis redis=pool.getResource();
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
        synchronized (UrlSerciceImpl.class) {


            CvteRule rule1=null;
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
                    int userid = ((CvteUser) session.getAttribute("name")).getId();
                    if (bdao.getPeople(userid) != null) {
                        try {
                            System.err.println("兄弟加油啊");
                            bdao.updatePeople(new BlackList(userid, new Date(), new Date()));
                            return "兄弟你操作太快了，黑名单中出现了你，15分钟后再来试试吧";
                        } catch (Exception e) {
                            System.err.println(e.getMessage());
                        }

                    } else {
                        BlackList p = new BlackList(userid, new Date(), new Date());
                        bdao.insertPeople(p);
                        session.setAttribute("gettime", 1);
                        return "你被加入黑名单了";
                    }
                } else {
                    if(redis.get(url)==null)
                    {
                        CvteUrl cvte_url=urlmapper.selectByShortUrl(url);
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
        /*
        将要转化的url弄成相应的MD5
         */
        int id=((CvteUser)session.getAttribute("name")).getId();
        BlackList people1=bdao.getPeople(id);
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
                Change ch=new Change();
                String shorturl=null;
                if(redis.get(url)==null)
                {
                    if(urlmapper.selectByLongUrl(url)==null)
                    {
                       shorturl= ch.changeurl(url);
                       urlmapper.insert(new CvteUrl(url,shorturl,new Date()));
                       redis.set(url,shorturl);
                       return shorturl;
                    }else
                    {
                        CvteUrl urls=urlmapper.selectByLongUrl(url);
                        redis.set(url,urls.getShort_url());
                        return urls.getShort_url();
                    }
                }else
                {
                    return redis.get(url);
                }
            }else
            {
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

        synchronized (UrlSerciceImpl.class) {


            CvteRule rule1=null;
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
                    int userid = ((CvteUser) session.getAttribute("name")).getId();
                    if (bdao.getPeople(userid) != null) {
                        try {
                            bdao.updatePeople(new BlackList(userid, new Date(), new Date()));
                            return "兄弟你操作太快了，黑名单中出现了你，15分钟后再来试试吧";
                        } catch (Exception e) {
                            System.err.println(e.getMessage());
                        }

                    } else {
                        BlackList p = new BlackList(userid, new Date(), new Date());
                        bdao.insertPeople(p);
                        session.setAttribute("gettime", 1);
                        return "你被加入黑名单了";
                    }
                } else {
                    Change ch=new Change();
                    String shorturl=null;
                    if(redis.get(url)==null)
                    {
                        if(urlmapper.selectByLongUrl(url)==null)
                        {
                            shorturl= ch.changeurl(url);
                            urlmapper.insert(new CvteUrl(url,shorturl,new Date()));
                            redis.set(url,shorturl);
                            return shorturl;
                        }else
                        {
                            CvteUrl urls=urlmapper.selectByLongUrl(url);
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
