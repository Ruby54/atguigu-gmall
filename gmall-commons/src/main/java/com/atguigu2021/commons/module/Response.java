package com.atguigu2021.commons.module;

import java.util.Iterator;
import java.util.List;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;

/**
 * @program: gmall
 * @description:
 * @author: Zhao Yi
 * @create: 2021-12-06 16:41
 */
public class Response {
    private int status;
    private String message;
    private Object result;

    public Response() {
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return this.result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Response success() {
        this.status = 200;
        this.message = "Operate success!";
        return this;
    }

    public Response success(String message) {
        this.update(200, message);
        return this;
    }

    public Response fail(String message) {
        this.update(300, message);
        return this;
    }

    public Response fail(Exception e) {
        if (e instanceof BindException) {
            List<FieldError> errors = ((BindException)e).getFieldErrors();
            StringBuilder message = new StringBuilder();
            Iterator var4 = errors.iterator();

            while(var4.hasNext()) {
                FieldError error = (FieldError)var4.next();
                message.append(error.getField() + ": " + error.getDefaultMessage() + "<br/>");
            }

            this.fail(message.toString());
        } else {
            String message = e.getMessage();
            if (message == null || message.isEmpty()) {
                message = "some error occurs";
            }
            this.fail(message);
        }
        return this;
    }
    public void update(int status, String message) {
        this.status = status;
        this.message = htmlEncode(message);
    }

    public static Response build() {
        return new Response();
    }

    public static Response build(Object result) {
        Response response = new Response();
        response.setResult(result);
        return response;
    }

    protected static String htmlEncode(String text) {
        if (text == null) {
            return null;
        } else {
            String result = text.replace("\"", "&quot;");
            result = result.replace("<", "&lt;");
            result = result.replace(">", "&gt;");
            return result;
        }
    }
}
