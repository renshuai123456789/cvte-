package cvte.service.serviceImp;


import cvte.dao.BlackListMapper;
import cvte.dao.RuleMapper;
import cvte.dao.UserMapper;
import cvte.service.UserService;

import cvte.vo.CvteUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userdao;

    @Autowired
    BlackListMapper bdao;

    @Autowired
    RuleMapper rule;



    @Override
    public boolean inertUser(CvteUser user) {
        try{
            userdao.insertUser(user);
            System.out.println(user);
            return true;
        }catch (Exception e)
        {
            System.out.println(e.getMessage()+"人帅");
            return false;
        }
    }

    @Override
    public CvteUser getUser(String name, String pwd) {
          CvteUser users=userdao.selectByName(name,pwd);

          return userdao.selectByName(name,pwd);




    }



}
