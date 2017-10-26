package cvte.dao;

import cvte.vo.blackpeople;
import cvte.vo.cvte_url;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface cvteurlMapper {

     public void insert(cvte_url url);

     public cvte_url selectByLongUrl(String longurl);

     public cvte_url selectByShortUrl(String shorturl);

}
