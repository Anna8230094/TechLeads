package com.example.demo.openai.agents;

import java.io.File;
import java.io.IOException;

import 

com.example.demo.openai.OpenAiAssistant;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Extractor extends OpenAiAssistant {

    public static final String INSTRUCTIONS = "I want you to extract as much more data you can in a structured CSV format from the following file (NOT A TABLE or A file), this CSV will be later ingested by a other AI Agent that will be reviewing the outcome file";
    public static final String MODEL = "gpt-4o";
    public static final String NAME = "Extractor";

    public Extractor(String model, String instructions, String name) throws IOException {
        super(model, instructions, name);
    }

    public static String getFile() {
        File file = new File("C:\\Users\\user\\Downloads\\CV - Anna Megalou.pdf");
        if (file.exists()) {
            return file.getAbsolutePath();
        } else {
            return null;
        }
    }

    public static void loadFiletoOpenAi() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");

        @SuppressWarnings("deprecation")
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("purpose", "fine-tune")
                .addFormDataPart("file", getFile(),RequestBody.create(MediaType.parse("application/octet-stream"),
                new File(getFile())))
                .build();
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/files")
                .post(body)
                .addHeader("Authorization", "Bearer "+ getKey())
                .build();
        Response response = client.newCall(request).execute();
        System.out.println("loadfile"+response.body().string());
    }

}
