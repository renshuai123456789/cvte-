
package cvte.controller;

import cvte.vo.cvte_user;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class longtoshort {

    @RequestMapping()
    public boolean change(String url, HttpSession session)
    {
        cvte_user user=(cvte_user) session.getAttribute("name");


    }


}
