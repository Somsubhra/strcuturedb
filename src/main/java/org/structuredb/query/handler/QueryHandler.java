package org.structuredb.query.handler;

import org.structuredb.query.data.QueryData;
import org.structuredb.structure.Structure;

public abstract class QueryHandler {

    public abstract Structure run(QueryData queryData, String dataPath);
}
