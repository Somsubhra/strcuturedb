package org.structuredb.query.handler;

import org.structuredb.query.data.QueryData;
import org.structuredb.structure.Structure;
import org.structuredb.utils.Console;

public class CreateAppHandler extends QueryHandler {

    public static CreateAppHandler getInstance() {
        return new CreateAppHandler();
    }

    @Override
    public Structure run(QueryData queryData) {
        Console.info("Creating app");
        return null;
    }
}
