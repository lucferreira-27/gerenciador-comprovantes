package files;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilesMananger {
	private File file;

	public static List<File> findFiles(String path, String text) {
		
		File file = new File(path);
		List<String> listDirectory = Arrays.asList(file.list());
		List<File> listFiles = Arrays.asList(file.listFiles());
		List<File> listFindFiles = new ArrayList<>();
		int i = 0;
		for (String l : listDirectory) {
			if (l.contains(text)) {
				listFindFiles.add(listFiles.get(i));

			}
			i++;
		}

		return listFindFiles;

	}
}
