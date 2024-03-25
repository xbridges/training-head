package jp.ne.networld.internal.domain.model;

import java.util.ArrayList;
import java.util.List;

import jp.ne.networld.internal.domain.CommandHeadShowType;
import jp.ne.networld.internal.domain.CommandHeadType;

public class HeadProperties {
	List<String> fileList;
	CommandHeadType mode;
	CommandHeadShowType show;
	int limit;
	
	public HeadProperties() {
		this.fileList = new ArrayList<String>();
	}
	public List<String> getFileList() {
		return fileList;
	}
	public CommandHeadType getMode() {
		return mode;
	}
	public int getLimit() {
		return limit;
	}
	public void add(String path) {
		this.fileList.add(path);
	}
	public void setMode(CommandHeadType mode) {
		this.mode = mode;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public CommandHeadShowType getShow() {
		return show;
	}
	public void setShow(CommandHeadShowType show) {
		this.show = show;
	}

}
