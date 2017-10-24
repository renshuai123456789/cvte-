package cvte.service;

import cvte.vo.cvte_user;

public interface userService {
    public boolean  inertUser(cvte_user user);

    public cvte_user getUser(String name,String pwd);

}
