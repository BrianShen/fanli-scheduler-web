package com.fanli.scheduler.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wei.shen on 2015/7/15.
 */
public class Result<T> {
    private Boolean isSuccess;
    private String messages;
    private T result;
    private List<T> results = new ArrayList<T>();

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
