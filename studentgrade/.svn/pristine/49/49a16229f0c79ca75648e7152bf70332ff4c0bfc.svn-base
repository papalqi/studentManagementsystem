/**
 * <p>Title: CorsInterceptor.java<／p>
 * <p>Copyright: Copyright (c) 2018<／p>
 * <p>Company: Oracle Group4<／p>
 * @author XuChongtian
 * @date 2018年3月17日
 * @version 1.0
 */
package com.studentgrade.filter;

import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * <p>Title: CorsInterceptor<／p>
 * <p>Description: <／p>
 * <p>Company: Oracle Group4<／p> 
 * @author XuChongtian
 * @date 2018年3月17日
 */
public class CorsInterceptor implements HandlerInterceptor{
	
	
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        if (request.getHeader(HttpHeaders.ORIGIN) != null) {
        	//response.setHeader("Access-Control-Allow-Credentials", "true");  
        	System.out.println("---------check--------");
        	String []  allowDomain= {"http://192.168.1.174:8080","http://127.0.0.1:8020"};
        	Set<String> allowedOrigins= new HashSet<String>(Arrays.asList(allowDomain));  
        	String originHeader=request.getHeader("Origin");
        	if (allowedOrigins.contains(originHeader)) {
        		response.setHeader("Access-Control-Allow-Origin", originHeader);  
                response.addHeader("Access-Control-Allow-Credentials", "true");
                response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT, HEAD");
                response.addHeader("Access-Control-Allow-Headers", "Content-Type");
                response.addHeader("Access-Control-Max-Age", "3600");
			}
//    		response.setHeader("Access-Control-Allow-Origin", "http://192.168.1.174:8080");  
//            response.addHeader("Access-Control-Allow-Credentials", "true");
//            response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT, HEAD");
//            response.addHeader("Access-Control-Allow-Headers", "Content-Type");
//            response.addHeader("Access-Control-Max-Age", "3600");
        }
        return true;
    }

  
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

  
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

}
