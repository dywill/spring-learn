package com.dy.component.resolvers;

import com.dy.component.views.TestView;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

public class TestViewResolver implements ViewResolver, Ordered {

    // 视图解析器的优先级
    private int order;

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    // 视图解析器的作用 即为根据视图名，解析得出对应的view对象，有view对象来实际进行渲染
    public View resolveViewName(String viewName, Locale locale) throws Exception {

        if(viewName != null){
            if(viewName.startsWith("dy:")){
                return new TestView();
            }
        }

        // 不能解析，返回null即可，由dispatchServlet来遍历其他视图解析器来进行处理视图
        return null;
    }
}
