package org.structuredb.structure.app;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.structuredb.structure.Structure;

import java.util.List;

public class AppList extends Structure {

    private List<String> apps;

    public AppList(List<String> apps) {
        this.apps = apps;
    }

    @Override
    public String serialize() {
        JsonObject jsonObject = new JsonObject();

        JsonElement result = new Gson().toJsonTree(apps, List.class);
        jsonObject.add("result", result);
        jsonObject.addProperty("success", true);

        return jsonObject.toString();
    }
}
