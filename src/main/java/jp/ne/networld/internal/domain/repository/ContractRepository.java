package jp.ne.networld.internal.domain.repository;

import java.sql.SQLException;

import jp.ne.networld.internal.domain.model.entity.Contract;

public interface ContractRepository {
	public ContractTx begin() throws SQLException;
	public Contract find(String id, int branch) throws SQLException;
}
