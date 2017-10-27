package cvte.controller;

import cvte.service.RuleService;
import cvte.service.serviceImp.RuleServiceImpL;

import cvte.util.JedisPoolUtil;
import cvte.vo.CvteUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpSession;

@Controller
public class RuleController {




    @Autowired
    RuleService ruleService;

    JedisPool pool= JedisPoolUtil.getJedisPoolInstance();


    @RequestMapping(value= "/rulechange.do",method = RequestMethod.POST)
    public String change1(String url)
    {

        ruleService.changRule1(url);


        return "changerule";
    }

    @RequestMapping(value = "/gotorule.do",method = RequestMethod.GET)
    public String tiaohuzn(HttpSession session, Model map)
    {
        if(((CvteUser)session.getAttribute("name")).getPower()==1) {

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

        ruleService.changRule(urlnum,urltime);
        return "changerule";
    }

}
