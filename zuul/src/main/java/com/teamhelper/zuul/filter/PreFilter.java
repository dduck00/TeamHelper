package com.teamhelper.zuul.filter;

import com.netflix.zuul.Filter;
import com.netflix.zuul.filters.http.HttpInboundSyncFilter;
import com.netflix.zuul.message.http.HttpRequestMessage;
import org.springframework.context.annotation.ComponentScan;

@Filter(order = 20)
public class PreFilter extends HttpInboundSyncFilter {
    @Override
    public HttpRequestMessage apply(HttpRequestMessage httpRequestMessage) {
        return null;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter(HttpRequestMessage httpRequestMessage) {
        return false;
    }
}
