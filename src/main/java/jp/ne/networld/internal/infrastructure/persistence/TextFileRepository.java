package jp.ne.networld.internal.infrastructure.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import jp.ne.networld.internal.domain.repository.TextRepository;

public class TextFileRepository implements TextRepository{

	public String openAndRead(String filePath, int size, int mode, String delim) throws IOException{

		String content = "";
		Path path = Paths.get(filePath);
		
		if(Files.exists(path)) {
			try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath), StandardCharsets.UTF_8);) {
				 try (Scanner scanner = new Scanner(reader);){
					 // 行読込する。
					 scanner.useDelimiter(delim);
					 int lines = 0;
					 while (scanner.hasNext()) {
						 if(content.length()==0) {
							 content = scanner.next();
						 } else {
							 content = content + "\r\n" + scanner.next();
						 }
						 // 文字数カウントモード
						 if(mode == 0) {
							 if(content.length()>=size) {
								 break;
							 }
						 } else {
							 lines++;
							 if(lines>=size) {
								 break;
							 }
						 }
					 }
				 }
			}
		} else {
			return "";
		}
		if(mode == 0) {
			if(content.length() > size) {
				content = content.substring(0, size);
			}
		}
		return content;
	}
}
