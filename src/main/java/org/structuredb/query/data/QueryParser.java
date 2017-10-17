package org.structuredb.query.data;

import org.structuredb.query.handler.QueryType;

public class QueryParser {

    public QueryParser() {

    }

    public ParsedQuery parse(Query query) {
        return new ParsedQuery(QueryType.CREATE_APP, null);
    }
}
