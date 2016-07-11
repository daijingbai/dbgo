package dbgo.system.util.common;

import java.util.ArrayList;
import java.util.List;

/**
 * ������ת��Ϊjson����
* @author ���Ի� 
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
	 * ����json����
	* @Title: reset 
	* @Description: TODO(������һ�仰�����������������) 
	* @param     �趨�ļ� 
	* @return void    �������� 
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
