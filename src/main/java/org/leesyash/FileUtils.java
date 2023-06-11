package org.leesyash;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtils {

    public String readContent(File sourceFile) throws IOException {
        validateFile(sourceFile);
        StringBuilder fileContent = new StringBuilder();
        BufferedReader reader = Files.newBufferedReader(Paths.get(sourceFile.getAbsolutePath()));

        while (reader.ready()) {
            fileContent.append(reader.readLine());
        }

        reader.close();
        return fileContent.toString();
    }

    public void writeContent(File appointmentFile, String content) throws IOException {
        Writer writer = new FileWriter(appointmentFile.getAbsolutePath());
        writer.write(content);
        writer.close();
    }

    public File appointmentFile(File sourceFile) throws IOException {
        if (!sourceFile.getParentFile().canWrite()) throw new IOException("Cannot write in appointment dir");
        String oldFileName = sourceFile.getName();
        return new File(sourceFile.getAbsolutePath().replace(oldFileName, oldFileName.split("\\.")[0] + "Thread.txt"));
    }

    private void validateFile(File file) throws IOException {
        if (!file.exists()) throw new IOException("File does not exist");
        if (!file.canRead()) throw new IOException("Cannot read file");
    }
}
