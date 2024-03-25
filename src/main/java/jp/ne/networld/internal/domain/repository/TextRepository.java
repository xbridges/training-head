package jp.ne.networld.internal.domain.repository;

import java.io.IOException;

public interface TextRepository {
	public String openAndRead(String filePath, int size, int mode, String delim) throws IOException;	
}
