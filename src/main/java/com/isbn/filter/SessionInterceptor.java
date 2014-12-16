package com.isbn.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.isbn.util.Constant;

@Service
public class SessionInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		super.afterConcurrentHandlingStarted(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		request.setCharacterEncoding("UTF-8");  
        response.setCharacterEncoding("UTF-8");  
        response.setContentType("text/html;charset=UTF-8");  
        
        String[] noFilters = new String[] { "index.html" ,
        									"/queryParamsByIsbn",
        									"/js",
        									"/images",
        									"/css",
        									"/login",
        									"/auth"
        								   };  
        String uri = request.getRequestURI();  
        boolean beFilter = true; 
        for (String noFilter : noFilters) {
			if(uri.contains(noFilter)){
				beFilter = false;
				break;
			}
		}
        
        if(beFilter){
        	Object obj = request.getSession().getAttribute(Constant.LOGINED);
        	if(obj == null){
        		response.sendRedirect("/isbn/index.html");
        	}
        }
		return super.preHandle(request, response, handler);
	}

}
