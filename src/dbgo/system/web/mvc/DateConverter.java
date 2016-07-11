package dbgo.system.web.mvc;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * 日期参数传递格式转换
* @author 戴辉辉 
* @date Jul 6, 2016 8:12:18 PM 
* @version V1.0
 */
public class DateConverter implements Converter<String, Date>{

	@Override
	public Date convert(String source) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");    
	    dateFormat.setLenient(false);    
	    try {    
	        return dateFormat.parse(source);    
	    } catch (Exception e) {    
	        e.printStackTrace();    
	    }           
	    return null; 
	}

}
