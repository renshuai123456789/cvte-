package cvte.service;

import cvte.vo.CvteUser;

public interface UserService {
     boolean  inertUser(CvteUser user);

     CvteUser getUser(String name, String pwd);


}
