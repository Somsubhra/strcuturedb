package org.structuredb.query.handler.app;

import com.google.gson.JsonObject;
import org.structuredb.exception.app.AppNameRequiredException;
import org.structuredb.exception.app.AppRenameRequiredException;
import org.structuredb.fileops.app.AppFiles;
import org.structuredb.query.data.QueryData;
import org.structuredb.query.handler.QueryHandler;
import org.structuredb.structure.Error;
import org.structuredb.structure.Structure;
import org.structuredb.structure.app.AppRename;
import org.structuredb.utils.Console;

public class RenameAppHandler extends QueryHandler {

    public static RenameAppHandler getInstance() {
        return new RenameAppHandler();
    }

    @Override
    public Structure run(QueryData queryData, String dataPath) {

        JsonObject parsedData = queryData.toParsedData().getAsJsonObject();

        if (!parsedData.has("app")) {
            return new Error(new AppNameRequiredException());
        }

        if (!parsedData.has("rename")) {
            return new Error(new AppRenameRequiredException());
        }

        String appName = parsedData.get("app").getAsString();
        String newName = parsedData.get("rename").getAsString();

        Console.info("Renaming app '" + appName + "' to '" + newName + "'");

        try {
            AppFiles.renameApp(dataPath, appName, newName);
        } catch (Exception e) {
            return new Error(e);
        }

        return new AppRename(newName);
    }
}
