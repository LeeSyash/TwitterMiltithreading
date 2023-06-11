package org.leesyash;

import java.awt.*;
import java.io.File;

public class TwitterMultithreadingApp {

    public static void main(String[] args) {
        FileUtils fileUtils = new FileUtils();
        ThreadBuilder threadBuilder = new ThreadBuilder();
        Desktop desktop = Desktop.getDesktop();
        for (String path :
                args) {
            File sourceFile = new File(path);
            try {
                String content = fileUtils.readContent(sourceFile);
                File appointmentFile = fileUtils.appointmentFile(sourceFile);
                fileUtils.writeContent(appointmentFile, threadBuilder.getThread(content));
                desktop.open(appointmentFile);
            } catch (Exception ex) {
                System.out.printf("File %s caused %s : %s", sourceFile.getAbsolutePath(), ex.getClass().getName(), (ex.getMessage() == null) ? "" : ex.getMessage());
            }
        }
    }
}