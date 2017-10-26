package cvte.dao;

import cvte.pojo.cvte_rule;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public interface ruleMapper {

    public cvte_rule getRule();

    public void updateRule2(@Param("ruletime")int ruletime,@Param("rulenum") int rulenum);

    public void updateRule1(@Param("rulelooknum") int rulelooknum);
}
