package com.example.demo.openai.threads;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Async;

import com.example.demo.openai.OpenAiThread;

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

    public String getFile() {
        File file = new File("/C:/Users/user/Downloads/CV - Anna Megalou.pdf");
        if (file.exists()) {
            System.out.println("I got the file");
            return file.getAbsolutePath();
        } else {
            return null;
        }
    }

    @Async
    public Response uploadFile() throws IOException, InterruptedException, ExecutionException {

        OkHttpClient client = new OkHttpClient().newBuilder().build();

        @SuppressWarnings("deprecation")
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("purpose", "assistants")
                .addFormDataPart("file", getFile(),
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(getFile())))
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
        return response;

    }

    @Override
    public void addMessage() throws IOException {
        JSONObject jsonObject = new JSONObject()
                .put("role", "user")
                .put("content", getMessage())
                .put("attachments",
                        new JSONArray().put(new JSONObject().put("file_id", getFileId()).put("tools", new JSONArray().put(new JSONObject().put("type", "file_search")))));

        String jsonRequest = jsonObject.toString();

        Response response = sendRequest(jsonRequest,
                "https://api.openai.com/v1/threads/" + getThreadId() + "/messages");

        if (response.isSuccessful() && response.body() != null) {
            System.out.println("Message add message successfully.");
        } else {
            System.out.println("Failed to add message ");
        }
    }

    public String getFileId() {
        return fileId;
    }

}
