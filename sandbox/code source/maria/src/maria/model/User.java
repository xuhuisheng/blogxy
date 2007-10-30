package maria.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * �û�.��jpa annotation�������.
 */
@Entity
@Table(name = "MARIA_USER")
public class User {
	private Integer id;

	//�û���
	private String name;
	
	//����
	private String password;
	
	private List<Role> roles = new ArrayList<Role>();
	//private Role role;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	//@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "MARIA_USER_ROLE", joinColumns = { @JoinColumn(name = "userid") }, inverseJoinColumns = { @JoinColumn(name = "roleid") })
	//@org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	/*public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}*/
}
