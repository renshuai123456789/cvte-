package cvte.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface UrlService {
     String urlChange(String url, HttpSession session, HttpServletRequest request);
     String urlShortChange(String url, HttpSession session, HttpServletRequest request);
}
