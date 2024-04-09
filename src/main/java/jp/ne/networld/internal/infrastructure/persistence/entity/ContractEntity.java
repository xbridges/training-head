package jp.ne.networld.internal.infrastructure.persistence.entity;

import jp.ne.networld.internal.domain.model.entity.Contract;

public class ContractEntity implements Contract{
	private String id;
	private int branch;
	
	public String getId() {
		return id;
	}
	public int getBranch() {
		return branch;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setBranch(int branch) {
		this.branch = branch;
	}
	
	
}
