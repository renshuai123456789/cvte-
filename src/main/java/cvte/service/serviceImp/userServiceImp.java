package cvte.service.serviceImp;


import cvte.dao.userMapper;
import cvte.service.userService;
import cvte.vo.cvte_user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userServiceImp implements userService {

    @Autowired
    userMapper userdao;

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
