
package cvte.controller;

import cvte.service.serviceImp.userServiceImp;
import cvte.vo.cvte_user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller

public class urlget {

    @Autowired
    userServiceImp imp;



    @RequestMapping(value = "/login.do")
    @ResponseBody
    public boolean login(String username,String password,HttpSession session)
    {
        System.err.println(username);
        System.err.println(password);
        cvte_user userss=imp.getUser(username,password);
        System.out.println(userss.toString());

        if(imp.getUser(username,password)==null)
        {
            return false;
        }else
        {
            if(userss.getDelete1()!=1) {
                session.setAttribute("name", userss);
                return true;
            }else
            {
                return false;
            }
        }



    }

    @RequestMapping(value = "/sign.do",method = RequestMethod.POST)
    @ResponseBody
    public boolean sign(cvte_user user, HttpSession session)
    {

        user.setDelete1(0);
        System.out.println(user.toString());
        if(imp.inertUser(user)) {
            session.setAttribute("name",user);
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
