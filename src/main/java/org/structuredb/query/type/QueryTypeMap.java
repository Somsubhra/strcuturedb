package org.structuredb.query.type;

import java.util.HashMap;
import java.util.Map;

public class QueryTypeMap {

    public static Map<String, QueryType> get() {
        Map<String, QueryType> queryTypeMap = new HashMap<>();

        queryTypeMap.put("sdb-create-app", QueryType.APP_CREATE);
        queryTypeMap.put("sdb-list-apps", QueryType.APP_LIST);
        queryTypeMap.put("sdb-rename-app", QueryType.APP_RENAME);
        queryTypeMap.put("sdb-delete-app", QueryType.APP_DELETE);

        return queryTypeMap;
    }
}
