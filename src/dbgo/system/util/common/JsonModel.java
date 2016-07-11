package dbgo.system.util.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 将数据转化为json的类
* @author 戴辉辉 
* @date Jun 18, 2016 4:38:22 PM 
* @version V1.0
 */
public class JsonModel {
	protected String error;
	protected Boolean success;
	protected Integer total;
	protected List<String> arrDate=new ArrayList<String>();
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	
	/**
	 * 重置json对象
	* @Title: reset 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void reset(){
		this.setSuccess(true);
		this.setError(null);
		this.arrDate.clear();
	}
	
	public void addItem(String name,String value){
		arrDate.add("\""+name+"\";"+"\""+value+"\"");
	}
	
	public void itemOk(){
		arrDate.add("<br>");
		total++;
	}
	
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append("{");
		sb.append("\total\":"+this.getTotal().toString()+",");
		sb.append("\"rows\":[");
		int index=0;
		if(arrDate.size()<=0){
			sb.append("]");
		}else{
			sb.append("{");
			for (Object object : arrDate) {
				index++;
				if(!"<br>".equals(object)){
					sb.append(object+",");
				}else{
					sb.replace(sb.length()-1, sb.length(),"");
					sb.append("},");
					if(index<arrDate.size()){
						sb.append("{");
					}
				}
			}
			sb.replace(sb.length()-1, sb.length(),"");
			sb.append("]");
		}
		sb.append("}");
		return sb.toString();
	}
}
