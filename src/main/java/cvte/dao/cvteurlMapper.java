package cvte.dao;

import cvte.vo.CvteUrl;
import org.springframework.stereotype.Service;

@Service
public interface CvteUrlMapper {

      void insert(CvteUrl url);

      CvteUrl selectByLongUrl(String longurl);

      CvteUrl selectByShortUrl(String shorturl);

}
