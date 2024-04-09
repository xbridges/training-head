package jp.ne.networld.internal.infrastructure.persistence;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

import jp.ne.networld.internal.domain.model.entity.Contract;
import jp.ne.networld.internal.domain.repository.ContractTx;

public class ContractRepositoryTest {

	String testTarget = "ProfileRepository";

	final String URL = "jdbc:postgresql://10.0.3.10/ktkcondb";
	final String USER = "nssol";
	final String PASS = "nssol";

	private Connection openDb() {
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASS);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		assertThat(conn, notNullValue());
		return conn;
	}
	private void closeDb(Connection conn) {
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
	}
	@Test
	public void TestCaseNormal() {
		int no = 0;
		
		{
			String title = "new ContractRepository";
			no++;
			
			System.out.println(String.format("--- BEGIN --- TestNo: %d Title: %s", no, title));
			ContractPersister persister = new ContractPersister(null);
			assertThat(persister, notNullValue());
			System.out.println(String.format("--- FINISH --- TestNo: %d passed.", no));
		}
		{
			String title = "read one record.";
			no++;
			System.out.println(String.format("--- BEGIN --- TestNo: %d Title: %s", no, title));

			Connection conn = openDb();
			ContractPersister persister = new ContractPersister(conn);
			assertThat(persister, notNullValue());
			
			String id = "P00001";
			int branch = 0;
			try {
				Contract c = persister.find(id, branch);
				assertThat(c, notNullValue());
				System.out.println(String.format("\t Contruct.Id=%s", c.getId()));
				System.out.println(String.format("\t Contruct.Branch=%s", c.getBranch()));
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} finally {
				closeDb(conn);
			}
			System.out.println(String.format("--- FINISH --- TestNo: %d passed.", no));
		}
		{
			String title = "read one record with transaction.";
			no++;
			System.out.println(String.format("--- BEGIN --- TestNo: %d Title: %s", no, title));
			Connection conn = openDb();
			ContractPersister persister = new ContractPersister(conn);
			assertThat(persister, notNullValue());
			ContractTx tx = null;
			
			String id = "P00001";
			int branch = 0;
			
			try {
				tx = persister.begin();
				assertThat(tx, notNullValue());				
				try {
					Contract c = tx.find(id, branch);
					assertThat(c, notNullValue());
					System.out.println(String.format("\t Contruct.Id=%s", c.getId()));
					System.out.println(String.format("\t Contruct.Branch=%s", c.getBranch()));
					tx.rollback();
				} catch (SQLException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} finally {
				try {
					if(tx != null) {
						tx.rollback();
					}
					closeDb(conn);
				} catch (SQLException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			}
			System.out.println(String.format("--- FINISH --- TestNo: %d passed.", no));
		}
	}
	
}
