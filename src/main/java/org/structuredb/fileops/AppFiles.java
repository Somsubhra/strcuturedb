package org.structuredb.fileops;

import org.structuredb.exception.AppFilesInitializationException;
import org.structuredb.utils.Console;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Paths;
import java.util.Scanner;

public class AppFiles {

    public static void init(String dataPath) throws IOException {

        File file = new File(Paths.get(dataPath, "apps").toString());

        if(!file.exists()) {
            Console.info("App index not initialized. Creating now.");
            if(file.createNewFile()) {
                Console.info("Initialized app index successfully");
            } else {
                throw new AppFilesInitializationException();
            }
        }
    }

    public static boolean appExists(String dataPath, String appName) {
        File appDirectory = new File(Paths.get(dataPath, appName).toString());

        File appIndexFile = new File(Paths.get(dataPath, "apps").toString());

        boolean appInIndex = false;

        try {
            Scanner appIndexScanner = new Scanner(appIndexFile);

            while(appIndexScanner.hasNextLine()) {
                if(appName.equals(appIndexScanner.nextLine().trim())) {
                    appInIndex = true;
                    break;
                }
            }
        } catch (Exception e) {
            Console.error(e.getMessage());
        }

        return appDirectory.exists() && appInIndex;
    }

    public static void createApp(String dataPath, String appName) {
        RandomAccessFile lockAccessFile = null;
        FileChannel lockFileChannel;
        FileLock lock = null;

        try {
            File lockFile = new File(dataPath, "apps.lock");

            lockAccessFile = new RandomAccessFile(lockFile, "rw");
            lockFileChannel = lockAccessFile.getChannel();

            lock = lockFileChannel.tryLock();

            if(lock != null) {
                Console.info("Acquired lock");
                lockFile.deleteOnExit();

                ByteBuffer bytes = ByteBuffer.allocate(4);
                bytes.putInt(1).flip();

                lockFileChannel.write(bytes);
                lockFileChannel.force(false);

                // Rest of the logic here
            } else {
                Console.info("Waiting for lock");
                createApp(dataPath, appName);
            }
        } catch (Exception e) {
            Console.info("Waiting for lock");
            createApp(dataPath, appName);
        } finally {
            if(lock != null && lock.isValid()) {
                try {
                    lock.release();
                } catch (IOException e) {
                    Console.error(e.getMessage());
                }
            }

            if(lockAccessFile != null) {
                try {
                    lockAccessFile.close();
                } catch (IOException e) {
                    Console.error(e.getMessage());
                }
            }
        }
    }
}
