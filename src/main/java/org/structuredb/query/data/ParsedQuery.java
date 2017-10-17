package org.structuredb.query.data;

import org.structuredb.query.handler.QueryType;

public class ParsedQuery {

    private QueryType queryType;

    private QueryData queryData;

    public ParsedQuery(QueryType queryType, QueryData queryData) {
        this.queryType = queryType;
        this.queryData = queryData;
    }

    public QueryType getQueryType() {
        return queryType;
    }

    public void setQueryType(QueryType queryType) {
        this.queryType = queryType;
    }

    public QueryData getQueryData() {
        return queryData;
    }

    public void setQueryData(QueryData queryData) {
        this.queryData = queryData;
    }
}
