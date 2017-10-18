package org.structuredb.query.parser;

import org.structuredb.query.data.QueryData;
import org.structuredb.query.type.QueryType;

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
