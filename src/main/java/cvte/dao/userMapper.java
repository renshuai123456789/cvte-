package cvte.dao;

import cvte.vo.cvte_user;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public interface userMapper {

    public void insertuser(cvte_user c);

    public cvte_user selectOne(int id);

    public cvte_user selectByName(@Param("name")String name,@Param("pwd")String pwd);

    public void insertTest(String username);
}
