package org.structuredb.structure.app;

import com.google.gson.JsonObject;
import org.structuredb.structure.Structure;

public class AppRename extends Structure {

    private String appName;

    public AppRename(String appName) {
        this.appName = appName;
    }

    @Override
    public String serialize() {
        JsonObject jsonObject = new JsonObject();

        JsonObject result = new JsonObject();
        result.addProperty("app", this.appName);

        jsonObject.add("result", result);
        jsonObject.addProperty("success", true);

        return jsonObject.toString();
    }
}
