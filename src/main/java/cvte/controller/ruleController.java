package cvte.controller;

import cvte.service.serviceImp.ruleServiceImp;

import cvte.util.JedisPoolUtil;
import cvte.vo.cvte_user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpSession;

@Controller
public class ruleController {




    @Autowired
    ruleServiceImp rule;

    JedisPool pool= JedisPoolUtil.getJedisPoolInstance();


    @RequestMapping(value= "/rulechange.do",method = RequestMethod.POST)
    public String change1(String url)
    {

        rule.changrule1(url);


        return "changerule";
    }

    @RequestMapping(value = "/gotorule.do",method = RequestMethod.GET)
    public String tiaohuzn(HttpSession session, Model map)
    {
        if(((cvte_user)session.getAttribute("name")).getPower()==1) {

            Jedis redis=pool.getResource();
            map.addAttribute("ruletime",redis.get("ruletime"));
            map.addAttribute("rulelooknum",redis.get("rulelooknum"));
            map.addAttribute("rulenum",redis.get("rulenum"));
            return "changerule";
        }
        else
            return "login";
    }

    @RequestMapping(value= "/rulechange2.do",method = RequestMethod.POST)
    public String change2(String urltime,String urlnum)
    {

        rule.changrule(urlnum,urltime);
        return "changerule";
    }

}
