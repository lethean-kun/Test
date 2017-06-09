package com.zz.blog.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zz.blog.po.User;
/**
 * 登录认证拦截器
 * @author lethean
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

	//进入 Handler方法之前执行
	//用于身份认证、身份授权
	//比如身份认证，如果认证通过表示当前用户没有登陆，需要此方法拦截不再向下执行
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception ex)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView model)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		//获取请求的url
		String url = request.getRequestURI();
		//判断url是否是公开 地址（实际使用时将公开 地址配置配置文件中）
		//这里公开地址是登陆提交的地址
		if(url.indexOf("login.action")>=0||url.indexOf("register.action")>=0||url.indexOf("index.action")>=0){
			//如果进行登陆提交，放行
			return true;
		}

		//判断session
		HttpSession session  = request.getSession();
		//从session中取出用户身份信息
		User user = (User) session.getAttribute("user");
		
		if(user != null){
			//身份存在，放行
			return true;
		}
		//执行这里表示用户身份需要认证，跳转登陆页面
		request.getRequestDispatcher("/WEB-INF/jsp/login/beforeLogin.jsp").forward(request, response);								
		//return false表示拦截，不向下执行
		//return true表示放行
		return false;
	}


}
