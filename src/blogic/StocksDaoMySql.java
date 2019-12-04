package blogic;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StocksDaoMySql implements iStocksDao {

    private final String URL = "jdbc:mysql://localhost/stocksregister";
    private final String USER = "root";
    private final String PASSWORD = "";

    @Override
    public void create(Stocks stocks) {

        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = conn.createStatement();

            String query = "INSERT INTO " + Constant.TABLE_NAME + " ( " +
                    Constant.FIELDS_STOCKS.amountAuthorizedCapital + "," +
                    Constant.FIELDS_STOCKS.EDRPOU_institutions + "," +
                    Constant.FIELDS_STOCKS.quantity + "," +
                    Constant.FIELDS_STOCKS.nominalValue + "," +
                    Constant.FIELDS_STOCKS.totalNominalValue + "," +
                    Constant.FIELDS_STOCKS.stateDutyPaid + "," +
                    Constant.FIELDS_STOCKS.releaseDate + "," +
                    Constant.FIELDS_STOCKS.fullNameOwner + "," +
                    Constant.FIELDS_STOCKS.comment + "," +
                    Constant.FIELDS_STOCKS.ID + "," +
                    Constant.FIELDS_STOCKS.allChangeHistory +
                    ") VALUES (" +
                    stocks.getAmountAuthorizedCapital() + ", '" + stocks.getEDRPOU_institutions() + "', " +
                    stocks.getQuantity() + ", " + stocks.getNominalValue() + ", " + stocks.getTotalNominalValue() +
                    ", " + stocks.getStateDutyPaid() + ", '" + stocks.getReleaseDate() + "', '" + stocks.getFullNameOwner() +
                    "', '" + stocks.getComment() + "', " + stocks.getId() + ", '" + stocks.getAllChangeHistory() + "');";
            System.out.println(query);
            int rs = statement.executeUpdate(query);
            statement.close();
            conn.close();
            System.out.println("create stocks successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public ArrayList<Stocks> read() {
        ArrayList<Stocks> stocksArrayList = makeAnOperation("SELECT * FROM " + Constant.TABLE_NAME);
        System.out.println("read(), read all stocks successfully");
        printList(stocksArrayList);
        return stocksArrayList;
    }

    @Override
    public void update(Stocks stocks) {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stmt = connection.createStatement();
            String query = "UPDATE " + Constant.TABLE_NAME + " SET " +
                    Constant.FIELDS_STOCKS.amountAuthorizedCapital + " = " + stocks.getAmountAuthorizedCapital() + ", " +
                    Constant.FIELDS_STOCKS.EDRPOU_institutions + " = '" + stocks.getEDRPOU_institutions() + "', " +
                    Constant.FIELDS_STOCKS.quantity + " = " + stocks.getQuantity() + ", " +
                    Constant.FIELDS_STOCKS.nominalValue + " = " + stocks.getNominalValue() + ", " +
                    Constant.FIELDS_STOCKS.stateDutyPaid + " = " + stocks.getStateDutyPaid() + ", " +
                    Constant.FIELDS_STOCKS.releaseDate + " ='" + stocks.getReleaseDate() + "', " +
                    Constant.FIELDS_STOCKS.fullNameOwner + " ='" + stocks.getFullNameOwner() + "'," +
                    Constant.FIELDS_STOCKS.comment + " = '" + stocks.getComment() + "'" +
                    "WHERE ID = " + stocks.getId();
            int rs = stmt.executeUpdate(query);
            stmt.close();
            connection.close();
            System.out.println("update(), update stocks successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(int id) {

        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            String query = "DELETE FROM " + Constant.TABLE_NAME + " WHERE ID = " + id;
            int rs = stmt.executeUpdate(query);
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public ArrayList<Stocks> allStocsInOneInstitution(String EDRPOU_institutions) {
        ArrayList<Stocks> arrayList =  makeAnOperation("SELECT * FROM " + Constant.TABLE_NAME + " WHERE " + Constant.FIELDS_STOCKS.EDRPOU_institutions + " = " + EDRPOU_institutions);
        printList(arrayList);
        return arrayList;
    }

    @Override
    public ArrayList<Stocks> sort(String nameStockField) {
        if(nameStockField==null&&nameStockField.isEmpty())throw new NullPointerException();
        String fieldEnum = Arrays.toString(Constant.FIELDS_STOCKS.values());
        if(fieldEnum.contains(nameStockField)==true){
            ArrayList<Stocks> arrayListSorted = makeAnOperation("SELECT * FROM "+Constant.TABLE_NAME+ " order by "+nameStockField);
            System.out.println("sort(), successfully");
            printList(arrayListSorted);
         return arrayListSorted;
        }
        else throw new IllegalArgumentException("There is no such field: "+ nameStockField);
    }

    @Override
    public ArrayList<Stocks> filtration(String nameStockField, String valueStockField) {
        if((nameStockField==null&&nameStockField.isEmpty())&&(valueStockField==null&&valueStockField.isEmpty()))throw new NullPointerException();
        String fieldEnum = Arrays.toString(Constant.FIELDS_STOCKS.values());
        if(fieldEnum.contains(nameStockField)==true){
            ArrayList<Stocks> arrayListFiltred = makeAnOperation("SELECT * FROM "+Constant.TABLE_NAME+ " where "+nameStockField+" = "+ valueStockField);
            System.out.println("filtration(), successfully");
          printList(arrayListFiltred);
            return  arrayListFiltred;
        }
        else throw new IllegalArgumentException("There is no such field: "+ nameStockField);

    }


    public String getAllPublicDataStocsInOneInstitution(String EDRPOU_institutions){
        String result="";
        for(Stocks stocks:allStocsInOneInstitution(EDRPOU_institutions)){
            result+=stocks.getPublicDataInJsonFormat()+"\n";
        }
        System.out.println("getAllPublicDataStocsInOneInstitution(),"+result);
        return result;
    }

    public String getAllPrivateDataStocsInOneInstitution(String EDRPOU_institutions){
        String result="";
        for(Stocks stocks:allStocsInOneInstitution(EDRPOU_institutions)){
            result+=stocks.getPrivateDataInJsonFormat()+"\n";
        }
        System.out.println("getAllPrivateDataStocsInOneInstitution(),"+result);
        return result;
    }

    private ArrayList<Stocks> makeAnOperation(String query) {
        ArrayList<Stocks> stocksArrayList = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();

            ResultSet result = stmt.executeQuery(query);

            while (result.next()) {
                Stocks stocks = new Stocks(
                        result.getInt(1),
                        result.getString(2),
                        result.getInt(3),
                        result.getDouble(4),
                        result.getDouble(6),
                        result.getString(7),
                        result.getString(8),
                        result.getString(9),
                        result.getInt(10),
                        result.getString(11)
                );
                stocksArrayList.add(stocks);

            }
            printList(stocksArrayList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stocksArrayList;
    }

    public void printList(ArrayList<Stocks> arrayList){
        for (Stocks s:arrayList){
            System.out.println(s.toString());
        }
    }

}
