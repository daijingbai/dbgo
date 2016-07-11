package dbgo.system.web.mvc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

public class DateEdtor implements WebBindingInitializer{

	@Override
	public void initBinder(WebDataBinder binder, WebRequest arg1) {
		DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");  
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   //true:允许输入空值，false:不能为空值
	}

}
