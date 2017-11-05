package com.faisal.technodhaka.dlight.security;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.DataSource;

/**
 * Created by TD-Android on 10/16/2017.
 * o transport data during sending email, you must implement javax.activation.DataSource
 */
public class ByteArrayDataSource implements DataSource {
    private byte[] data;
    private String type;

    public ByteArrayDataSource(byte[] data, String type) {
        super();
        this.data = data;
        this.type = type;
    }

    public ByteArrayDataSource(byte[] data) {
        this.data = data;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(data);
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        throw new IOException("Not Supported");
    }

    @Override
    public String getContentType() {
        if (type == null)
            return "application/octet-stream";
        return type;
    }

    @Override
    public String getName() {
        return "ByteArrayDataSource";
    }
}
