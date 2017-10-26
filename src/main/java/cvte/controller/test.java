
package cvte.controller;

import cvte.dao.blackpeopleMapper;
import cvte.dao.cvteurlMapper;
import cvte.dao.ruleMapper;
import cvte.dao.userMapper;
import cvte.service.serviceImp.userServiceImp;
import cvte.vo.blackpeople;
import cvte.vo.cvte_url;
import cvte.vo.cvte_user;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
    userMapper user;
    public static void main(String[] args)
    {
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");

        cvteurlMapper mapper=context.getBean(cvteurlMapper.class);
        if(mapper.selectByLongUrl("dfdsf213asfdsaf")==null)
        {
            System.err.println("dsfdsf");
        }else
        {
           System.out.println( mapper.selectByShortUrl("dfdsf"));
        }


        //        userMapper mapper=context.getBean(userMapper.class);
//        mapper.insertuser(new cvte_user("sss","sdfds","dsfds","1000000000",1));
//        userServiceImp imp=context.getBean(userServiceImp.class);
//        System.out.println(imp.getUser("renshuai","123457").toString());
//        ruleMapper mapper=context.getBean(ruleMapper.class);
//        System.err.println(mapper.getRule().toString());
//
//        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        format.setLenient(false);
//        try{
//            Date b=new Date();
//
//            Thread.sleep(100);
//            Date a=new Date();
//            System.err.println("sdfdsf");
//            blackpeopleMapper mapper1=context.getBean(blackpeopleMapper.class);
//            mapper1.insertPeople(new blackpeople(2,a,b));
//            System.err.println(mapper1.getPeople(1));
//        }catch (Exception e)
//        {
//             System.err.println(e.getMessage());
//        }
    }
}
