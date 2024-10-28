package me.yeunikey.contester.services.assignments;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import me.yeunikey.contester.entities.assignments.Code;
import me.yeunikey.contester.repositories.assignments.CodeRepository;
import me.yeunikey.contester.services.BaseService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

@Service
public class CodeService extends BaseService<Code, String, CodeRepository> {

    public JsonObject asJson(Code code) {

        JsonObject codeJson = new JsonObject();

        codeJson.addProperty("codeId", code.getUniqueId());
        codeJson.addProperty("languageType", code.getLanguageType().toString());
        codeJson.addProperty("status", code.getStatus().toString());

        return codeJson;
    }

    public byte[] asBytes(String[] code) {
        StringBuilder codeBuilder = new StringBuilder();
        for (String line : code) {
            codeBuilder.append(line).append("\n");
        }
        return codeBuilder.toString().getBytes();
    }

    public void createFile(Code code) throws IOException {
        byte[] codeBytes = code.getBytes();
        try (FileOutputStream fos = new FileOutputStream("codes/" + code.getUniqueId() + ".py")) {
            fos.write(codeBytes);
        }
    }

    public void removeFile(Code code) {
        File file = new File("/codes/" + code.getUniqueId() + ".py");
        if (!file.exists()) return;
        file.delete();
    }

    public JsonArray asArray(Code code) {

        JsonArray array = new JsonArray();
        String formatted = new String(code.getBytes(), StandardCharsets.UTF_8);

        for (String part : formatted.split("\n")) {
            array.add(part);
        }

        return array;
    }

}
