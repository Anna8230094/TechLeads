package com.example.demo.openai.threads;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Async;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ExtractorThread extends OpenAiThread {

    private String fileId = "";

    public ExtractorThread(String message, String instructions, String assistantId) throws IOException {
        super(message, instructions, assistantId);
    }

    /*
     * public String getFile() {
     * File file = new File("/C:/Users/user/Downloads/CV - Anna Megalou.pdf");
     * if (file.exists()) {
     * System.out.println("I got the file");
     * return file;
     * } else {
     * return null;
     * }
     * }
     */

    public CompletableFuture<String> uploadFile() throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS).build();

        @SuppressWarnings("deprecation")
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("purpose", "assistants")
                .addFormDataPart("file", System.getProperty("user.dir") + "/demo/src/main/resources/static/CV - Anna Megalou.pdf",
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(System.getProperty("user.dir") + "/demo/src/main/resources/static/CV - Anna Megalou.pdf")))
                .build();

        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/files")
                .post(body)
                .addHeader("Authorization", "Bearer " + getKey())
                .build();
        Response response = client.newCall(request).execute();

        if (response.isSuccessful() && response.body() != null) {
            fileId = extractId(response);
        }
        return CompletableFuture.completedFuture(fileId);
    }

    @Override
    @Async
    public CompletableFuture<String> addMessage() throws IOException {
        JSONObject jsonObject = new JSONObject()
                .put("role", "user")
                .put("content", getMessage())
                .put("attachments",
                        new JSONArray().put(new JSONObject().put("file_id", getFileId()).put("tools",
                                new JSONArray().put(new JSONObject().put("type", "file_search")))));

        String jsonRequest = jsonObject.toString();

        Response response = sendRequest(jsonRequest,
                "https://api.openai.com/v1/threads/" + getThreadId() + "/messages");

        if (response.isSuccessful() && response.body() != null) {
            System.out.println("Message add message successfully.");
            return CompletableFuture.completedFuture(response.body().string());
        } else {
            System.err.println("Failed to add message ");
            throw new Error();
        }
    }

    public String getFileId() {
        return fileId;
    }

}
