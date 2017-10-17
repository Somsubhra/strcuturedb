package org.structuredb.query.data;

public class QueryData {

    private String rawData;

    public QueryData(String rawData) {
        this.rawData = rawData;
    }

    @Override
    public String toString() {
        return "<org.structuredb.query.data.QueryData> " + rawData;
    }
}
