
package cvte.controller;

import cvte.service.BlackListService;
import cvte.service.UrlService;
import cvte.service.serviceImp.BlackListImpl;
import cvte.service.serviceImp.UrlSerciceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LongToShort {


    @Autowired
    BlackListService people;

    @Autowired
    UrlService urlService;


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

             return urlService.urlShortChange(url,session,request);
        }else {
            return urlService.urlChange(url, session, request);
        }
    }


}
