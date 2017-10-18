package org.structuredb.structure;

import com.google.gson.JsonObject;

public class AppCreation extends Structure {

    private String appName;

    public AppCreation(String appName) {
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
