package cvte.dao;

import cvte.vo.blackpeople;
import org.springframework.stereotype.Service;

@Service
public interface blackpeopleMapper {

    public blackpeople getPeople(int id);

}
