package cvte.Handler;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Service
public class UserHandler implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        StringBuffer url=httpServletRequest.getRequestURL();
        String url2=new String(url);
        if(url2.contains("login.do")||url2.contains("login.jsp"))
        {
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
