package dbgo.system.web.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;

public class CustomerInterceptor implements HandlerInterceptor{

	private static Logger log=Logger.getLogger(CustomerInterceptor.class);
	
	@Override
	public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object obj, Exception exception)
			throws Exception {
		if(exception!=null){
			log.error(exception.getMessage());
			res.setCharacterEncoding("utf-8");
			res.setContentType("application/json;charset=utf-8");
			JSONObject jsobj=new JSONObject();
			jsobj.put("success",false);
			jsobj.put("msg",exception.getMessage());
			PrintWriter pw=res.getWriter();
			pw.append(jsobj.toString());
			pw.flush();
			pw.close();
		}
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		return true;
	}

}
