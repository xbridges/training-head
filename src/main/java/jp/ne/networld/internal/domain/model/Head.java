package jp.ne.networld.internal.domain.model;

import java.io.IOException;

import jp.ne.networld.internal.domain.CommandHead;
import jp.ne.networld.internal.domain.repository.TextRepository;
import jp.ne.networld.internal.infrastructure.persistence.TextFileRepository;

public class Head implements CommandHead{
	private final static int OptionCharLimit = 0;
	private final static int OptionLineLimit = 1;
	
	private TextRepository repo;
	
	public Head() {
		repo = new TextFileRepository();
	}
	
	public String charLimitedHeadCommand(String path, int limit) throws IOException {
		return repo.openAndRead(path, limit, OptionCharLimit, "\\r\\n");
	}
	
	public String lineLimitedHeadCommand(String path, int limit) throws IOException {
		return repo.openAndRead(path, limit, OptionLineLimit, "\\r\\n");
	}
}
