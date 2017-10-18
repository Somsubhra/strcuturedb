package org.structuredb.fileops.app;

import org.structuredb.exception.app.AppExistsException;
import org.structuredb.utils.Console;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;

public class AppFiles {

    public static void init(String dataPath) throws IOException {
        AppIndex.initAppIndex(dataPath);
    }

    public static boolean appExists(String dataPath, String appName) {
        return AppDirectory.appDirectoryExists(dataPath, appName) && AppIndex.appEntryExists(dataPath, appName);
    }

    public static void createApp(String dataPath, String appName) throws IOException {
        RandomAccessFile lockAccessFile = null;
        FileChannel lockFileChannel;
        FileLock lock = null;

        try {
            File lockFile = new File(dataPath, "apps.lock");

            lockAccessFile = new RandomAccessFile(lockFile, "rw");
            lockFileChannel = lockAccessFile.getChannel();

            lock = lockFileChannel.tryLock();

            if (lock != null) {
                Console.info("Acquired lock for creating app '" + appName + "'");
                lockFile.deleteOnExit();

                ByteBuffer bytes = ByteBuffer.allocate(4);
                bytes.putInt(1).flip();

                lockFileChannel.write(bytes);
                lockFileChannel.force(false);

                if (appExists(dataPath, appName)) {
                    throw new AppExistsException(appName);
                }

                AppIndex.addAppEntry(dataPath, appName);
                AppDirectory.addAppDirectory(dataPath, appName);
            } else {
                Console.info("Waiting for lock for creating app '" + appName + "'");
                createApp(dataPath, appName);
            }
        } catch (OverlappingFileLockException e) {
            Console.info("Waiting for lock for creating app '" + appName + "'");
            createApp(dataPath, appName);
        } catch (Exception e) {
            Console.error(e.getMessage());
            throw e;
        } finally {
            if (lock != null && lock.isValid()) {
                try {
                    lock.release();
                } catch (IOException e) {
                    Console.error(e.getMessage());
                }
            }

            if (lockAccessFile != null) {
                try {
                    lockAccessFile.close();
                } catch (IOException e) {
                    Console.error(e.getMessage());
                }
            }
        }
    }
}
