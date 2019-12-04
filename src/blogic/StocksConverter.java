package blogic;

import com.google.gson.*;

import java.lang.reflect.Type;

public class StocksConverter implements JsonSerializer<Stocks>, JsonDeserializer<Stocks> {

    @Override
    public JsonElement serialize(Stocks stocks, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", stocks.getId());
        jsonObject.addProperty("quantity", stocks.getQuantity());
        jsonObject.addProperty("amountAuthorizedCapital", stocks.getAmountAuthorizedCapital());
        jsonObject.addProperty("totalNominalValue", stocks.getTotalNominalValue());
        jsonObject.addProperty("nominalValue", stocks.getNominalValue());
        jsonObject.addProperty("stateDutyPaid", stocks.getStateDutyPaid());
        jsonObject.addProperty("EDRPOU_institutions", stocks.getEDRPOU_institutions());
        jsonObject.addProperty("releaseDate", stocks.getReleaseDate());
        jsonObject.addProperty("fullNameOwner", stocks.getFullNameOwner());
        jsonObject.addProperty("comment", stocks.getComment());
        jsonObject.addProperty("allChangeHistory", stocks.getAllChangeHistory());
        return jsonObject;
    }

    @Override
    public Stocks deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        int id = jsonObject.get("id").getAsInt();
        int quantity = jsonObject.get("quantity").getAsInt();
        long amountAuthorizedCapital = jsonObject.get("amountAuthorizedCapital").getAsLong();
        double totalNominalValue = jsonObject.get("totalNominalValue").getAsDouble();
        double nominalValue = jsonObject.get("nominalValue").getAsDouble();
        double stateDutyPaid = jsonObject.get("stateDutyPaid").getAsDouble();
        String EDRPOU_institutions = jsonObject.get("EDRPOU_institutions").getAsString();
        String releaseDate = jsonObject.get("releaseDate").getAsString();
        String fullNameOwner = jsonObject.get("fullNameOwner").getAsString();
        String comment = jsonObject.get("comment").getAsString();
        String allChangeHistory = jsonObject.get("allChangeHistory").getAsString();
        return new Stocks(amountAuthorizedCapital, EDRPOU_institutions, quantity, nominalValue, stateDutyPaid, releaseDate, fullNameOwner, comment, id, allChangeHistory);
    }


}
