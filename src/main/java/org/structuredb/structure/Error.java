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
        jsonObject.addProperty("error", exception.getMessage());
        return jsonObject.toString();
    }
}
