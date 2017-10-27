package cvte.service;

import cvte.vo.BlackList;

public interface BlackListService {

    public boolean insertpeople(BlackList blackpeople);


    public boolean getpeople(int id,int rule);


    public BlackList getpeople(int id);

}
