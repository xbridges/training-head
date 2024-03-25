package jp.ne.networld.internal.application.usecase;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jp.ne.networld.cmd.head.Usecase;
import jp.ne.networld.internal.domain.CommandHead;
import jp.ne.networld.internal.domain.CommandHeadShowType;
import jp.ne.networld.internal.domain.CommandHeadType;
import jp.ne.networld.internal.domain.model.Head;
import jp.ne.networld.internal.domain.model.HeadProperties;

public class CommandHeadUsecase implements Usecase{

	private CommandHead head;
	private HeadProperties properties;
	private Map<String, String> result;
	
	public CommandHeadUsecase() {
		this.head = new Head();
	}
	
	public void init(String[] args) {
		CommandHeadInitialize initUsecase = new CommandHeadInitialize();
		this.properties = initUsecase.initHeadCommand(args);
	}
	
	public void fire(){
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			for(String path : properties.getFileList()) {
				String response;
				if(this.properties.getMode()==CommandHeadType.LimitedCharactor) {
					response = this.head.charLimitedHeadCommand(path, this.properties.getLimit());
					resultMap.put(path, response);
				} else {
					response = this.head.lineLimitedHeadCommand(path, this.properties.getLimit());
					resultMap.put(path, response);
				}
			}
		} catch(IOException e) {
			return;
		}
		this.result = resultMap;
	}
	
	public Object getResult() {
		return this.result;
	}
	
	public void showResult() {
		
		for(Map.Entry<String, String> entry : this.result.entrySet()){
			if(entry.getValue() != null && !"".equals(entry.getValue().trim())){
				if(properties.getShow()!=CommandHeadShowType.NoVerbose) {
					System.out.println("==> " + entry.getKey() + " <==");
				}
				System.out.println(entry.getValue());
			} else {
				System.out.println("head: `" + entry.getKey() + "' を 読み込み用に開くことが出来ません: そのようなファイルやディレクトリはありません");
			}
			System.out.println();
		}
	}
}
