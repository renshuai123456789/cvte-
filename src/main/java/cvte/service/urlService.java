package cvte.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface urlService {
    public String urlChange(String url, HttpSession session, HttpServletRequest request);
    public String urlShortChange(String url, HttpSession session, HttpServletRequest request);
}
