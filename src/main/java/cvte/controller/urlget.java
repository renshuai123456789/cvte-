
package cvte.controller;

import cvte.dao.ruleMapper;
import cvte.pojo.cvte_rule;
import cvte.service.serviceImp.userServiceImp;

import cvte.util.JedisPoolUtil;
import cvte.vo.cvte_user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.util.Iterator;
import java.util.Map;

@Controller

public class urlget {

    @Autowired
    userServiceImp imp;

    @Autowired
    ruleMapper mapper;


    JedisPool pool= JedisPoolUtil.getJedisPoolInstance();

    @RequestMapping(value = "/login.do")
    @ResponseBody
    public boolean login(String username,String password,HttpSession session)
    {

        Jedis j=pool.getResource();
        System.err.println(username);
        System.err.println(password);
        cvte_user userss=imp.getUser(username,password);
        System.err.println("qwewqewqewqe");
        cvte_rule rule=mapper.getRule();
        try
        {
            j.set("ruletime",((Integer)rule.getRuletime()).toString());
            System.err.println(j.get("ruletime"));
            j.set("rulenum",((Integer)rule.getRulenum()).toString());
            System.err.println(j.get("ruletime"));
            j.set("rulelooknum",((Integer)rule.getRulelooknum()).toString());
            System.err.println(j.get("ruletime"));

        }catch(Exception e)
        {
            System.err.println(e.getMessage());
        }

        System.err.println("fdsfds");
        System.err.println(userss.toString());

        if(userss==null)
        {
            return false;
        }else
        {
            if(userss.getDelete1()!=1) {
                System.err.println("哪里错误了吗");
                session.setAttribute("name", userss);
                cvte_user userssss=(cvte_user) session.getAttribute("name");
                session.setAttribute("gettime", 0);
                System.err.println(userssss.toString());
                return true;
            }else
            {
                System.err.println("最后一部错了");
                return false;
            }
        }



    }

    @Cacheable(value = "usertest")
    @RequestMapping(value = "/sign.do",method = RequestMethod.POST)
    @ResponseBody
    public boolean sign(cvte_user user, HttpSession session)
    {

        user.setDelete1(0);
        user.setPower(0);
        System.out.println(user.toString());
        if(imp.inertUser(user)) {
            session.setAttribute("name",user);
            session.setAttribute("gettime", 0);
            System.err.println("错误了吗");
            return true;
        }
        else
        {
            System.err.println("user.");
            System.err.println("哪里错误了吗");
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
