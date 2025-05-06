package com.javatecharc.learning.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class MyLearningInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("PreHandle: Intercepting request - " + request.getRequestURI());

        String department = request.getParameter("department");
        System.out.println(department);

        if (department != null && department.equals("HR")) {
            return true; // Block the request
        }

        System.out.println("Blocking request for other than HR department");
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied for other than HR department");

        return false; // Return true to continue the request, false to block it
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, org.springframework.web.servlet.ModelAndView modelAndView) throws Exception {
        System.out.println("PostHandle: Request processed - " + request.getRequestURI());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("AfterCompletion: Request completed - " + request.getRequestURI());
    }
}