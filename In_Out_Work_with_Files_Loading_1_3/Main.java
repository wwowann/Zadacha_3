package In_Out_Work_with_Files_Loading_1_3;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {
    public static void main(String[] args) {
        GameProgress gameProgress = null;
        String gameAddress = "Games\\savegames\\save3.dat";
        String zipPath = "Games\\savegames\\save.zip";
        openZip(zipPath, "Games\\savegames\\");
        System.out.println(openProgress(gameProgress, gameAddress));
    }

    public static void openZip(String zipPath, String pathOutZip) {
        try (ZipInputStream zis = new ZipInputStream
                (new FileInputStream(zipPath))) {

            ZipEntry entry;
            String name;
            while ((entry = zis.getNextEntry()) != null) {
                name = entry.getName(); // получим название файла
                // распаковка
                FileOutputStream fout = new FileOutputStream(pathOutZip + "\\" + name);
                for (int c = zis.read(); c != -1; c = zis.read()) {
                    fout.write(c);
                }
                fout.flush();
                zis.closeEntry();
                fout.close();
            }
        } catch (
                Exception ex) {
            System.out.println("Трындец всему!");
        }
    }

    public static GameProgress openProgress(GameProgress gameProgress, String gameAddress) {
        try (FileInputStream fis = new FileInputStream(gameAddress);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            gameProgress = (GameProgress) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "ok");
        }
        return gameProgress;
    }
}

