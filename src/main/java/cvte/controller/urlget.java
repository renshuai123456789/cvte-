
package cvte.controller;

import cvte.dao.RuleMapper;
import cvte.pojo.CvteRule;
import cvte.service.serviceImp.UserServiceImpl;
import cvte.util.JedisPoolUtil;
import cvte.vo.CvteUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller

public class UrlGet {

    @Autowired
    UserServiceImpl imp;

    @Autowired
    RuleMapper mapper;


    JedisPool pool= JedisPoolUtil.getJedisPoolInstance();

    @RequestMapping(value = "/login.do",method = RequestMethod.POST )
    @ResponseBody
    public boolean login(String username,String password,HttpSession session)
    {

        Jedis j=pool.getResource();
        CvteUser userss=imp.getUser(username,password);
        CvteRule rule=mapper.getRule();
        try
        {
            j.set("ruletime",((Integer)rule.getRuletime()).toString());
            j.set("rulenum",((Integer)rule.getRulenum()).toString());
            j.set("rulelooknum",((Integer)rule.getRulelooknum()).toString());
        }catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
        if(userss==null)
        {
            return false;
        }else
        {
            if(userss.getDelete1()!=1) {
                session.setAttribute("name", userss);
                CvteUser userssss=(CvteUser) session.getAttribute("name");
                session.setAttribute("gettime", 0);
                return true;
            }else
            {
                return false;
            }
        }



    }


    @RequestMapping(value = "/sign.do",method = RequestMethod.POST)
    @ResponseBody
    public boolean sign(CvteUser user, HttpSession session)
    {

        user.setDelete1(0);
        user.setPower(0);
        if(imp.inertUser(user)) {
            session.setAttribute("name",user);
            session.setAttribute("gettime", 0);
            return true;
        }
        else
        {
            return false;
        }
    }

    @RequestMapping(value = "controller.do",method = RequestMethod.GET)
    public String redirect(Map map,HttpSession session)
    {
        map.put("name",session.getAttribute("name"));
        return "controller";
    }

}
