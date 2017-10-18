package org.structuredb.query.handler.app;

import org.structuredb.query.data.QueryData;
import org.structuredb.query.handler.QueryHandler;
import org.structuredb.structure.Structure;

public class RenameAppHandler extends QueryHandler {

    public static RenameAppHandler getInstance() {
        return new RenameAppHandler();
    }

    @Override
    public Structure run(QueryData queryData, String dataPath) {
        return null;
    }
}
