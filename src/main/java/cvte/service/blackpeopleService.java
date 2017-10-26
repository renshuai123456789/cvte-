package cvte.service;

import cvte.vo.blackpeople;

public interface blackpeopleService {

    public boolean insertpeople(blackpeople blackpeople);


    public boolean getpeople(int id,int rule);


    public blackpeople getpeople(int id);

}
