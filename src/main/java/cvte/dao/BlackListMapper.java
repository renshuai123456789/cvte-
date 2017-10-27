package cvte.dao;

import cvte.vo.BlackList;
import org.springframework.stereotype.Service;

@Service
public interface BlackListMapper {

     BlackList getPeople(int id);

     void updatePeople(BlackList p);

     void insertPeople(BlackList p);

     void delete(int id);
}
