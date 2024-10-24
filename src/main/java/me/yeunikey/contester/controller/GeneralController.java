package me.yeunikey.contester.controller;

import com.google.gson.JsonObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GeneralController {

    @GetMapping(path = "/test")
    public ResponseEntity<String> test(@RequestParam String a, @RequestParam String b) {

        JsonObject json = new JsonObject();
        String result = "result";

        try {
            String scriptPath = "src/main/resources/static/code.py";

            ProcessBuilder processBuilder = new ProcessBuilder("python", scriptPath);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            OutputStream os = process.getOutputStream();
            os.write((a + "\n").getBytes());
            os.write((b + "\n").getBytes());
            os.flush();
            os.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            List<String> output = new ArrayList<>();

            String line;

            while ((line = reader.readLine()) != null) {
                output.add(line);
            }

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                result = "Output: " + output;
            } else {
                result = "Error: Python script exited with code " + exitCode;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "An error occurred: " + e.getMessage();
        }

        json.addProperty("result", result);
        return ResponseEntity.ok(json.toString());
    }

}
