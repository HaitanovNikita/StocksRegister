package blogic;

import java.util.ArrayList;

public interface iStocksDao {

    void create(Stocks stocks);
    ArrayList<Stocks> read();
    void update(Stocks stocks) ;
    void delete(int id);
    ArrayList<Stocks> allStocsInOneInstitution(String EDRPOU_institutions);
    ArrayList<Stocks> sort(String nameStockField);
    ArrayList<Stocks> filtration(String nameStockField, String valueStockField);



}
