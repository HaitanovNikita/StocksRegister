package blogic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Stocks {
    @Expose
    private int  id,quantity;
    @Expose
    private long amountAuthorizedCapital;
    @ Expose
    private double totalNominalValue,nominalValue, stateDutyPaid;
    @ Expose
    private String EDRPOU_institutions,releaseDate,fullNameOwner = "",comment = "",allChangeHistory;

    private  SimpleDateFormat  formatForDateNow;
    private  StocksDaoMySql stocksDaoMySql;


    public Stocks(
            long amountAuthorizedCapital, String EDRPOU_institutions, int quantity,
            double nominalValue, double stateDutyPaid, String releaseDate, String fullNameOwner, String comment) {
        if (checkData(EDRPOU_institutions, releaseDate, fullNameOwner) == false) throw new IllegalArgumentException();
        initFields(amountAuthorizedCapital, EDRPOU_institutions, quantity, nominalValue, stateDutyPaid, releaseDate, fullNameOwner, comment);
        addChangeHistory("create action: " + toString());
    }

    public Stocks(
            long amountAuthorizedCapital, String EDRPOU_institutions, int quantity,
            double nominalValue, double stateDutyPaid, String releaseDate, String fullNameOwner, String comment, int id, String allChangeHistory) {
        if (checkData(EDRPOU_institutions, releaseDate, fullNameOwner) == false) throw new IllegalArgumentException();
        initFields(amountAuthorizedCapital, EDRPOU_institutions, quantity, nominalValue, stateDutyPaid, releaseDate, fullNameOwner, comment);
        this.allChangeHistory = allChangeHistory;
        this.id = id;

    }


    private void initFields(long amountAuthorizedCapital, String EDRPOU_institutions, int quantity,
                            double nominalValue, double stateDutyPaid, String releaseDate, String fullNameOwner, String comment) {
        this.amountAuthorizedCapital = amountAuthorizedCapital;
        this.EDRPOU_institutions = EDRPOU_institutions;
        this.quantity = quantity;
        this.totalNominalValue = nominalValue * (double) quantity;
        this.nominalValue = nominalValue;
        this.stateDutyPaid = stateDutyPaid;
        this.releaseDate = releaseDate;
        this.comment = comment;

        allChangeHistory = " ***  All change history  *** \n";
        this.fullNameOwner = fullNameOwner;
        stocksDaoMySql = new StocksDaoMySql();
        formatForDateNow = new SimpleDateFormat("'Date' yyyy-MM-dd 'and time' hh:mm:ss a");

    }

    private boolean checkData(String EDRPOU_institutions, String releaseDate, String fullNameOwner) {
        /*Проверка данных на валидность*/
        if (EDRPOU_institutions == null && releaseDate == null && fullNameOwner == null) return false;
        if (EDRPOU_institutions.matches("[-+]?\\d+") == false && EDRPOU_institutions.length() != 8) return false;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse(releaseDate);
            if (!formatter.format(date).equals(releaseDate)) return false;
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public String getPublicDataInJsonFormat(){
        return "JSON: {" +
                "\""+Constant.FIELDS_STOCKS.EDRPOU_institutions+"\":"+getEDRPOU_institutions()+", "+
                "\""+Constant.FIELDS_STOCKS.quantity+"\":"+getQuantity()+", "+
                "\""+Constant.FIELDS_STOCKS.totalNominalValue+"\":"+getTotalNominalValue()+", "+
                "\""+Constant.FIELDS_STOCKS.nominalValue+"\":"+getNominalValue()+", "+
                "\""+Constant.FIELDS_STOCKS.releaseDate+"\":"+getReleaseDate()+
                "}";
    }

    public String getPrivateDataInJsonFormat(){
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Stocks.class, new StocksConverter());
        Gson gson = builder.
                excludeFieldsWithoutExposeAnnotation()
                .create();
        return gson.toJson(this);
    }

    protected void addChangeHistory(String change) {
        allChangeHistory += "--" + formatForDateNow.format(new Date()) + " " + change + "\n";
    }

    public long getAmountAuthorizedCapital() {
        return amountAuthorizedCapital;
    }

    public String getEDRPOU_institutions() {
        return EDRPOU_institutions;
    }

    public String getComment() {
        return comment;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getNominalValue() {
        return nominalValue;
    }

    public double getTotalNominalValue() {
        return totalNominalValue;
    }

    public double getStateDutyPaid() {
        return stateDutyPaid;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getAllChangeHistory() {
        return allChangeHistory;
    }

    public String getFullNameOwner() {
        return fullNameOwner;
    }

    public int getId() {
        return id;
    }

    public void setAmountAuthorizedCapital(long amountAuthorizedCapital) {
        addChangeHistory("changing the " + Constant.FIELDS_STOCKS.amountAuthorizedCapital +" "+ this.amountAuthorizedCapital + " parameter to " + amountAuthorizedCapital);
        this.amountAuthorizedCapital = amountAuthorizedCapital;
        stocksDaoMySql.update(this);
    }

    public void setEDRPOU_institutions(String EDRPOU_institutions) {
        addChangeHistory("changing the " + Constant.FIELDS_STOCKS.EDRPOU_institutions +" "+ this.EDRPOU_institutions + " parameter to " + EDRPOU_institutions);
        this.EDRPOU_institutions = EDRPOU_institutions;
        stocksDaoMySql.update(this);
    }

    public void setComment(String comment) {
        addChangeHistory("changing the " + Constant.FIELDS_STOCKS.comment +" "+ this.comment + " parameter to " + comment);
        this.comment = comment;
        stocksDaoMySql.update(this);
    }

    public void setQuantity(int quantity) {
        addChangeHistory("changing the " + Constant.FIELDS_STOCKS.quantity +" "+ this.quantity + " parameter to " + quantity);
        this.quantity = quantity;
        addChangeHistory("changing the " + Constant.FIELDS_STOCKS.totalNominalValue +" "+ totalNominalValue + " parameter to " + nominalValue * (double) this.quantity);
        totalNominalValue = nominalValue * (double) this.quantity;
        stocksDaoMySql.update(this);
    }

    public void setNominalValue(double nominalValue) {
        addChangeHistory("changing the " + Constant.FIELDS_STOCKS.nominalValue +" "+ this.nominalValue + " parameter to " + nominalValue);
        this.nominalValue = nominalValue;
        addChangeHistory("changing the " + Constant.FIELDS_STOCKS.totalNominalValue +" "+ totalNominalValue + " parameter to " + this.nominalValue * (double) this.quantity);
        totalNominalValue = this.nominalValue * (double) quantity;
        stocksDaoMySql.update(this);
    }

    public void setStateDutyPaid(double stateDutyPaid) {
        addChangeHistory("changing the " + Constant.FIELDS_STOCKS.stateDutyPaid +" "+ this.stateDutyPaid + " parameter to " + stateDutyPaid);
        this.stateDutyPaid = stateDutyPaid;
        stocksDaoMySql.update(this);
    }

    public void setReleaseDate(String releaseDate) {
        addChangeHistory("changing the " + Constant.FIELDS_STOCKS.releaseDate +" "+ this.releaseDate + " parameter to " + releaseDate);
        this.releaseDate = releaseDate;
        stocksDaoMySql.update(this);
    }

    public void setFullNameOwner(String fullNameOwner) {
        addChangeHistory("changing the " + Constant.FIELDS_STOCKS.fullNameOwner +" "+ this.fullNameOwner + " parameter to " + fullNameOwner);
        this.fullNameOwner = fullNameOwner;
        stocksDaoMySql.update(this);
    }

    public Stocks jsonToStocks(String jsonStockString){
        if (jsonStockString==null&&jsonStockString.isEmpty()==true){ throw new IllegalArgumentException("Argument "+ jsonStockString+" is not a valid!");}
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.
                excludeFieldsWithoutExposeAnnotation()
                .create();
        Type type = new TypeToken<Stocks>() {}.getType();
        return gson.fromJson(jsonStockString,type);
    }


    @Override
    public String toString() {
        return "Stock: ID:" + id + ",\n" +
                "comment: " + comment + ",\n" +
                "amount authorized capital: " + amountAuthorizedCapital + ",\n" +
                "EDRPOU institutions:'" + EDRPOU_institutions + "',\n" +
                "quantity: '" + quantity + "',\n" +
                "total nominal value: " + totalNominalValue + ",\n" +
                "nominal value:" + nominalValue + ",\n" +
                "state duty paid: " + stateDutyPaid + ",\n" +
                "release date: '" + releaseDate + "',\n" +
                "owner: " + fullNameOwner + ",\n"
                ;
    }

}
