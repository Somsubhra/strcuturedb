package org.structuredb.query.handler.app;

import com.google.gson.JsonObject;
import org.structuredb.exception.app.AppNameRequiredException;
import org.structuredb.fileops.app.AppFiles;
import org.structuredb.query.data.QueryData;
import org.structuredb.query.handler.QueryHandler;
import org.structuredb.structure.*;
import org.structuredb.structure.Error;
import org.structuredb.structure.app.AppDeletion;
import org.structuredb.utils.Console;

public class DeleteAppHandler extends QueryHandler {

    public static DeleteAppHandler getInstance() {
        return new DeleteAppHandler();
    }

    @Override
    public Structure run(QueryData queryData, String dataPath) {

        JsonObject parsedData = queryData.toParsedData().getAsJsonObject();

        if(!parsedData.has("app")) {
            return new Error(new AppNameRequiredException());
        }

        String appName = parsedData.get("app").getAsString();

        Console.info("Deleting app '" + appName + "'");

        try {
            AppFiles.deleteApp(dataPath, appName);
        } catch (Exception e) {
            return new Error(e);
        }

        return new AppDeletion(appName);
    }
}
