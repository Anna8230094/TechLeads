package com.example.demo.openai.threads;

import java.io.IOException;

import com.example.demo.openai.OpenAiThread;

public class ExtractorThread extends OpenAiThread{
    
    public ExtractorThread(String message, String instructions, String assistantId) throws IOException {
        super(message, instructions, assistantId);
    }

    public void createVectoreStore(){

    }

    public void UploadFile(){
        
    }
    
    
}
