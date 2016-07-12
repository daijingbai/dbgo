package dbgo.system.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	private BigDecimal userId;

    private String userCode;

    private String userName;

    private String pwd;

    private String salt;

    private Short status;
    
    private List<Resource> permissionList;
    
    private List<Resource> menus;
    
    private Date test;
    
    
    public Date getTest() {
		return test;
	}

	public void setTest(Date test) {
		this.test = test;
	}

	public BigDecimal getUserId() {
        return userId;
    }

    public void setUserId(BigDecimal userId) {
        this.userId = userId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    @JsonIgnore
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    @JsonIgnore
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

	public List<Resource> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<Resource> permissionList) {
		this.permissionList = permissionList;
	}

	public List<Resource> getMenus() {
		return menus;
	}

	public void setMenus(List<Resource> menus) {
		this.menus = menus;
	}
}