package org.structuredb.query.data;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.structuredb.exception.QueryDataParseException;

public class QueryData {

    private String rawData;

    public QueryData(String rawData) {
        this.rawData = rawData;
    }

    public JsonElement toParsedData() {
        try {
            return new JsonParser().parse(this.rawData);
        } catch (Exception e) {
            return new JsonObject();
        }
    }

    @Override
    public String toString() {
        return "<org.structuredb.query.data.QueryData> " + rawData;
    }
}
