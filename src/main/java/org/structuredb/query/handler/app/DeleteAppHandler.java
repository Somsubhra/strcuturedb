package org.structuredb.query.handler.app;

import org.structuredb.query.data.QueryData;
import org.structuredb.query.handler.QueryHandler;
import org.structuredb.structure.Structure;

public class DeleteAppHandler extends QueryHandler {

    public static DeleteAppHandler getInstance() {
        return new DeleteAppHandler();
    }

    @Override
    public Structure run(QueryData queryData, String dataPath) {
        return null;
    }
}
