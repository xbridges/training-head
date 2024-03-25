package jp.ne.networld.cmd.head;

import jp.ne.networld.internal.application.usecase.CommandHeadInitialize;
import jp.ne.networld.internal.application.usecase.CommandHeadUsecase;
import jp.ne.networld.internal.domain.model.HeadProperties;

public class Main {
		
	public static void main(String[] args) {
		
		CommandHeadInitialize init = new CommandHeadInitialize();
		HeadProperties properties = init.initHeadCommand(args);
		
		CommandHeadUsecase usecase = new CommandHeadUsecase(properties);
		usecase.fire();
		usecase.showResult();
	}
}