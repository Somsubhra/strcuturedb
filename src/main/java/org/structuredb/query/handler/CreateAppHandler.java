package org.structuredb.query.handler;

import org.structuredb.query.data.QueryData;
import org.structuredb.structure.*;
import org.structuredb.structure.Error;
import org.structuredb.utils.Console;

public class CreateAppHandler extends QueryHandler {

    public static CreateAppHandler getInstance() {
        return new CreateAppHandler();
    }

    @Override
    public Structure run(QueryData queryData) {
        Console.info("Creating app with " + queryData);
        return new Error(new RuntimeException("Not implemented yet"));
    }
}
