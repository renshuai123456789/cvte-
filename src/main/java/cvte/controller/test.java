
package cvte.controller;

import cvte.dao.userMapper;
import cvte.service.serviceImp.userServiceImp;
import cvte.vo.cvte_user;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test {
    userMapper user;
    public static void main(String[] args)
    {
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        userMapper mapper=context.getBean(userMapper.class);
        mapper.insertuser(new cvte_user("sss","sdfds","dsfds","1000000000",1));
//        userServiceImp imp=context.getBean(userServiceImp.class);
//        System.out.println(imp.getUser("renshuai","123457").toString());
    }
}
