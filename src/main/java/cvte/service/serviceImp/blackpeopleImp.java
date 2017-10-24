package cvte.service.serviceImp;

import cvte.dao.blackpeopleMapper;

import cvte.vo.blackpeople;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;

public class blackpeopleImp implements blackpeople {

    @Autowired
    blackpeopleMapper mapper;

    @Override
    public boolean getpeople(int id) {

        blackpeople people= (blackpeople) mapper.getPeople(id);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);

        Date startdate=people.getStartime();
        Date lasttime=people.getLasttime();
        long minute=lasttime.getTime()-startdate.getTime();
        if()

    }
}
