package cvte.dao;

import cvte.pojo.CvteRule;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public interface RuleMapper {

     CvteRule getRule();

     void updateRule2(@Param("ruletime")int ruletime,@Param("rulenum") int rulenum);

     void updateRule1(@Param("rulelooknum") int rulelooknum);
}
