package datamodel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ProjectTable")
public class Project {
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "Deadline")
	private String deadline;
	
	@Column(name = "MemberList")
	private String memberList;
	
	@Column(name = "Organization")
	private String organization;
	
	public Project() {
		
	}
	
	public Project(String name, String deadline, String memberList, String organization) {
		this.name = name;
		this.deadline = deadline;
		this.memberList = memberList;
		this.organization = organization;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getDeadline() {
		return deadline;
	}
	
	public void setDeadline(String deadline)
	{
		this.deadline = deadline;
	}
	
	public String getMemberList() {
		return memberList;
	}
	
	public void setMemberList(String memberList)
	{
		this.memberList = memberList;
	}
	
	public String getOrganization() {
		return organization;
	}
	
	public void setOrganization(String organization)
	{
		this.organization = organization;
	}
}
