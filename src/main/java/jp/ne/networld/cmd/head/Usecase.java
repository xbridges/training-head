package jp.ne.networld.cmd.head;

public interface Usecase {
	public void init(String[] args);
	public void fire();
	public void showResult();
}
