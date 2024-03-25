package jp.ne.networld.cmd.head;

import jp.ne.networld.internal.application.usecase.CommandHeadUsecase;

public class Main {
		
	public static void main(String[] args) {
		
		Usecase usecase = new CommandHeadUsecase();
		usecase.init(args);
		usecase.fire();
		usecase.showResult();
	}
}