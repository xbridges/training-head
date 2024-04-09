package jp.ne.networld.internal.infrastructure.persistence;

import java.io.IOException;

import org.junit.Test;

import jp.ne.networld.internal.domain.repository.TextRepository;

public class FileIORepositoryTest {

	private final static String TestFilePath = ".\\etc\\test.txt"; 
	
	@Test
	public void openAndReadTest() {
		TextRepository repo = new TextFileRepository();
		{
			try {
				String buf = repo.openAndRead(TestFilePath, 10, 0, "\\r\\n");
				System.out.println("text: length 10: " + buf);
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
		{
			try {
				String buf = repo.openAndRead(TestFilePath, 1, 1, "\\r\\n");
				System.out.println("text: line 1: " + buf);
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
		
	}
}
