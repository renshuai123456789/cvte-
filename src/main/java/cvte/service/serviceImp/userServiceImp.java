package cvte.service.serviceImp;


import cvte.dao.blackpeopleMapper;
import cvte.dao.ruleMapper;
import cvte.dao.userMapper;
import cvte.pojo.cvte_rule;
import cvte.service.userService;

import cvte.vo.blackpeople;
import cvte.vo.cvte_user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Service
public class userServiceImp implements userService {

    @Autowired
    userMapper userdao;

    @Autowired
    blackpeopleMapper bdao;

    @Autowired
    ruleMapper rule;



    @Override
    public boolean inertUser(cvte_user user) {
        try{
            userdao.insertuser(user);
            System.out.println(user);
            return true;
        }catch (Exception e)
        {
            System.out.println(e.getMessage()+"人帅");
            return false;
        }
    }

    @Override
    public cvte_user getUser(String name, String pwd) {
          cvte_user users=userdao.selectByName(name,pwd);

          return userdao.selectByName(name,pwd);




    }



}
