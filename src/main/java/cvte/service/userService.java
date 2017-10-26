package cvte.service;

import cvte.vo.cvte_user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface userService {
    public boolean  inertUser(cvte_user user);

    public cvte_user getUser(String name,String pwd);


}
