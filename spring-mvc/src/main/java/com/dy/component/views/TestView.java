package com.dy.component.views;

import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class TestView implements View {

    // 响应的contentType
    public String getContentType() {
        return "text/plain";
    }

    // 视图的实际解析流程
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("hello, this is dy study plat, I am ").append(model.get("name")).append(".");
        response.getWriter().print(sb.toString());
    }
}
