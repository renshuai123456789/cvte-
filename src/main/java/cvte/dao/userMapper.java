package cvte.dao;

import cvte.vo.CvteUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public interface UserMapper {

     void insertUser(CvteUser c);

     CvteUser selectOne(int id);

     CvteUser selectByName(@Param("name")String name, @Param("pwd")String pwd);


}
