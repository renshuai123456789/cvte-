
package cvte.controller;

import cvte.service.serviceImp.blackpeopleImp;
import cvte.service.serviceImp.urlSerciceImp;
import cvte.vo.blackpeople;
import cvte.vo.cvte_user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class longtoshort {


    @Autowired
    blackpeopleImp people;

    @Autowired
    urlSerciceImp imp;


    @RequestMapping(value = "getService.do" ,method = RequestMethod.POST)
    @ResponseBody
    public String change(String url,HttpSession session, HttpServletRequest request)
    {
        /*
        判断是短链接还是长链接
         */

        String[] ss=url.split("/");
        System.out.println(ss[ss.length-1].length());
        if(ss[ss.length-1].length()<=7)
        {
            /*
            短链服务
             */
            System.out.println("从这里开始了端服务");
             return imp.urlShortChange(url,session,request);
        }else {
            return imp.urlChange(url, session, request);
        }
    }


}
