package me.yeunikey.contester.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import me.yeunikey.contester.ContesterApplication;
import org.springframework.http.ResponseEntity;

import java.time.Instant;

public class ResponseBuilder {

    private JsonObject json = new JsonObject();
    private Instant timestamp = Instant.now();

    private ResponseStatus status = null;
    private String message = null;
    private JsonElement data = null;

    public ResponseBuilder() {

    }

    public ResponseBuilder status(ResponseStatus status) {
        this.status = status;
        return this;
    }

    public ResponseBuilder success() {
        this.status = ResponseStatus.SUCCESS;
        return this;
    }

    public ResponseBuilder error() {
        this.status = ResponseStatus.SUCCESS;
        return this;
    }

    public ResponseBuilder message(String message) {
        this.message = message;
        return this;
    }

    public ResponseBuilder data(JsonElement data) {
        this.data = data;
        return this;
    }

    public ResponseBuilder data(Object object) {
        this.data = ContesterApplication.gson().toJsonTree(object).getAsJsonObject();
        return this;
    }

    public JsonObject asJson() {
        if (status != null) {
            json.addProperty("status", status.toString());
        }

        if (message != null) {
            json.addProperty("message", message);
        }

        json.addProperty("timestampt", timestamp.toString());

        if (data != null) {
            json.add("data", data);
        }

        return json;
    }

    public ResponseEntity<String> build() {
        return ResponseEntity.ok(asJson().toString());
    }

    public static ResponseBuilder builder() {
        return new ResponseBuilder();
    }

}
