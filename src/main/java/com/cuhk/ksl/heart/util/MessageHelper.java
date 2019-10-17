package com.cuhk.ksl.heart.util;

import com.alibaba.fastjson.JSON;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MessageHelper {

    public static void generalMessage(Object object, HttpServletResponse response) throws IOException {
        String res = JSON.toJSONString(object);
        byte[] bytes = res.getBytes(StandardCharsets.UTF_8);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setContentLength(bytes.length);
        response.getOutputStream().write(bytes);
        response.flushBuffer();
    }
}
