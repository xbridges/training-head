package jp.ne.networld.internal.application.usecase;

import jp.ne.networld.internal.domain.CommandHeadShowType;
import jp.ne.networld.internal.domain.CommandHeadType;
import jp.ne.networld.internal.domain.model.HeadProperties;

public class CommandHeadInitialize {
	
	public HeadProperties initHeadCommand(String[] args) {
		HeadProperties properties= new HeadProperties();
		for(String arg: args) {
			if(arg.startsWith("--bytes=") || arg.startsWith("--bytes") || arg.startsWith("-c=") || arg.startsWith("-c")) {
				String[] charMode = arg.split("=");
				properties.setMode(CommandHeadType.LimitedCharactor);
				try {
					Integer limit = Integer.parseInt(charMode[1].trim());
					properties.setLimit(limit);
				} catch(Exception e) {
					properties.setLimit(5);
				}
			} else if(arg.startsWith("--lines=") || arg.startsWith("--lines") || arg.startsWith("-n=") || arg.startsWith("-n")) {
				String[] charMode = arg.split("=");
				properties.setMode(CommandHeadType.LimitedLine);
				try {
					Integer limit = Integer.parseInt(charMode[1].trim());
					properties.setLimit(limit);
				} catch(Exception e) {
					properties.setLimit(5);
				}
			} else if(arg.startsWith("--quiet") || arg.startsWith("-q")) {
				properties.setShow(CommandHeadShowType.NoVerbose);
			} else if(arg.startsWith("--verbose") || arg.startsWith("-v")) {
				properties.setShow(CommandHeadShowType.NoVerbose);
			} else {
				properties.add(arg.trim());
			}
		}
		if(properties.getLimit() == 0) {
			properties.setLimit(5); // default: 5 lines
		}
		if(properties.getMode()==null) {
			properties.setMode(CommandHeadType.LimitedLine); // default: line
		}
		return properties;
	}
}
