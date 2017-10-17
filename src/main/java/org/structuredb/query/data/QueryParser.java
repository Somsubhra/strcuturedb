package org.structuredb.query.data;

import org.structuredb.exception.BlankQueryException;
import org.structuredb.exception.UnknownQueryType;
import org.structuredb.query.handler.QueryType;

import java.util.HashMap;
import java.util.Map;

public class QueryParser {

    private Map<String, QueryType> queryTypeMap;

    public QueryParser() {
        queryTypeMap = new HashMap<>();
        queryTypeMap.put("sdb-create-app", QueryType.CREATE_APP);
    }

    public ParsedQuery parse(RawQuery rawQuery) {
        String[] queryComponents = rawQuery.getQuery().split("\\s+");

        if(queryComponents.length == 0) {
            throw new BlankQueryException();
        }

        String rawQueryType = queryComponents[0];

        if(!queryTypeMap.containsKey(rawQueryType)) {
            throw new UnknownQueryType(rawQueryType);
        }

        return new ParsedQuery(queryTypeMap.get(rawQueryType), null);
    }
}
