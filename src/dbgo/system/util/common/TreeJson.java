package dbgo.system.util.common;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 对象结合转为treejson格式
* @author 戴辉辉 
* @date Jun 18, 2016 5:38:07 PM 
* @version V1.0
 */
public class TreeJson implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String id ; 
    private String pid ; 
    private String text ; 
    private String iconCls ;
    private String state ; 
    private String checked ; 
    private Map<String,String> attributes;
    private List<TreeJson> children = new ArrayList<TreeJson>() ;
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public List<TreeJson> getChildren() {
		return children;
	}

	public void setChildren(List<TreeJson> children) {
		this.children = children;
	}

    public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

	public static List<TreeJson> formatTree(List<TreeJson> list) {
        TreeJson root = new TreeJson();
        List<TreeJson> treelist = new ArrayList<TreeJson>();//拼凑好的json格式的数据     
        if (list != null && list.size() > 0) {
        	for(int i= 0; i < list.size(); i++){
        		if("".equals(list.get(i).getPid())){
		            root = list.get(i) ;
		            getChildrenNodes(list,root);
		            treelist.add(root) ;
        		}
        	}
        }      
        return treelist ;
    }

    private static void getChildrenNodes(List<TreeJson> nodes, TreeJson root) {
    	for (TreeJson treeJson : nodes) {
			if(treeJson.getPid().equals(root.getId())){//如果找到root的子结点
				root.getChildren().add(treeJson);
				getChildrenNodes(nodes,treeJson);
			}
		}
    }
}