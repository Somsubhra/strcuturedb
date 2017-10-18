package org.structuredb.query.handler.app;

import org.structuredb.fileops.app.AppFiles;
import org.structuredb.query.data.QueryData;
import org.structuredb.query.handler.QueryHandler;
import org.structuredb.structure.Error;
import org.structuredb.structure.Structure;
import org.structuredb.structure.app.AppList;
import org.structuredb.utils.Console;

import java.util.List;

public class ListAppsHandler extends QueryHandler {

    public static ListAppsHandler getInstance() {
        return new ListAppsHandler();
    }

    @Override
    public Structure run(QueryData queryData, String dataPath) {

        Console.info("Fetching apps list");

        try {
            List<String> apps = AppFiles.getAppsList(dataPath);
            return new AppList(apps);
        } catch (Exception e) {
            return new Error(e);
        }
    }
}
