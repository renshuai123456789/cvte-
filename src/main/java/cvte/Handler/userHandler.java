package cvte.Handler;

import cvte.vo.cvte_user;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Service
public class userHandler implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        StringBuffer url=httpServletRequest.getRequestURL();
        String url2=new String(url);
        if(url2.contains("login.do")||url2.contains("login.jsp"))
        {
            System.out.println(url);
            return true;
        }
        else
        {
            HttpSession session=httpServletRequest.getSession();
            if(session.getAttribute("name")==null)
            {
                httpServletResponse.sendRedirect("http://localhost:8080/login.jsp");
                return false;
            }else
            {
                StringBuffer buffer=httpServletRequest.getRequestURL();
                String url21=new String(buffer);
//                if(url21.contains(".do"))
//                {
//                    if(((cvte_user)httpServletRequest.getSession().getAttribute("name")).getPower()==1)
//                        return true;
//                    else
//                    {
//                        httpServletResponse.sendRedirect("http://localhost:8080/login.jsp");
//                        return false;
//                    }
//                }else
//                {
//                    return true;
//                }
                return true;
            }


        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
