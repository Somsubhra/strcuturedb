package org.structuredb.structure;

import com.google.gson.JsonObject;

public class Error extends Structure {

    private Throwable exception;

    public Error(Throwable exception) {
        this.exception = exception;
    }

    @Override
    public String serialize() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("success", false);
        jsonObject.addProperty("result", exception.getMessage());
        return jsonObject.toString();
    }
}
