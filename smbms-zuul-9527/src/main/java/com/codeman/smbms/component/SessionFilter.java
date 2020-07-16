/*
package com.codeman.smbms.component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

*/
/**
 * Created with IntelliJ IDEA.
 *
 * @author 张鸿杰
 * Date：2019-06-15
 * Time:15:27
 *//*

@Configuration
public class SessionFilter extends ZuulFilter {
        private static final Logger log = LoggerFactory.getLogger(SessionFilter.class);

        @Autowired
        HttpServletRequest httpServletRequest;

        @Override
        public String filterType() {
            return "pre";
        }

        */
/**
         * 返回一个值来指定过滤器的执行顺序，不同过滤器允许返回相同的数字，数字越小顺序越靠前
         *//*

        @Override
        public int filterOrder() {
            return 1;
        }

        */
/**
         * 返回一个boolean值来判断该过滤器是否要执行，true：执行，false：不执行
         *//*

        @Override
        public boolean shouldFilter() {
            return true;
        }

        */
/**
         * 过滤器的具体逻辑
         *//*

        @Override
        public Object run() {
            RequestContext ctx = RequestContext.getCurrentContext();
            String sessionId = httpServletRequest.getSession().getId();
            try {

                System.out.println(Optional.of(httpServletRequest.getSession().getAttribute("userSession")).get());
            } catch (NullPointerException e) {
                e = new NullPointerException("UserSession不存在");
                e.printStackTrace();
            }
            log.info("--------------------------------------------------");
            log.info("sessionId: " + sessionId);
            log.info("--------------------------------------------------");
            ctx.addZuulRequestHeader("Cloud-Cookie", "SESSION=" + sessionId);
            ctx.setSendZuulResponse(true);  // 对该请求进行路由
            ctx.setResponseStatusCode(200); // 返回200正确响应
            return null;
        }
    }
*/
