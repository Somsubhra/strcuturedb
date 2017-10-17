package org.structuredb.query.data;

public class RawQuery {

    private String query;

    public RawQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return "<org.structured.query.data.RawQuery> (" + query + ")";
    }
}
