package jp.ne.networld.internal.infrastructure.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.ne.networld.internal.domain.model.entity.Contract;
import jp.ne.networld.internal.domain.repository.ContractRepository;
import jp.ne.networld.internal.domain.repository.ContractTx;
import jp.ne.networld.internal.infrastructure.persistence.entity.ContractEntity;


public class ContractPersister extends DatastorePersisterBase<ContractPersister> implements ContractRepository, ContractTx{
	
	private final static String findByIdSql = "select id, branch from contracts where id = ? and branch = ?";
	
	public ContractPersister(Connection conn) {
		super(conn);
	}
	
	public ContractTx begin() throws SQLException {
		super.beginTx();
		return this;
	}
	
	public Contract find(String id, int branch) throws SQLException {
		
		ContractEntity entity = null;
		ResultSet rs = null;
		try {
			rs = this.read(findByIdSql, id, branch);
			while (rs.next()) {
				entity = this.row2Model(rs);
				break;
			}
		} finally {
			if(rs!=null) {
				rs.close();
			}
		}
		
		return entity;
	}
	
	private ContractEntity row2Model( ResultSet rowData ) throws SQLException {

		ContractEntity entity = new ContractEntity();
		
		if (rowData.getString("id") != null) {
			entity.setId(rowData.getString("id"));
		}
		
		if (rowData.getString("branch") != null) {
			entity.setBranch(rowData.getInt("branch"));
		}
		/*
		if (rowData.getString("subscriber_id") != null) {
			entity.setSubscriberId (rowData.getString("subscriber_id"));
		}
		
		if (rowData.getString("apply_id") != null) {
			entity.setBranch(rowData.getInt("apply_id"));
		}
		if (rowData.getString("applied_at") != null) {
			entity.setId(rowData.getString("applied_at"));
		}
		
		if (rowData.getString("status_id") != null) {
			entity.setBranch(rowData.getInt("status_id"));
		}
		*/
		
		return entity;
	}
}
