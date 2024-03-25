package jp.ne.networld.internal.domain;

import java.io.IOException;

public interface CommandHead {
	public String charLimitedHeadCommand(String path, int limit) throws IOException;
	public String lineLimitedHeadCommand(String path, int limit) throws IOException;
}
