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
import java.util.List;

public class AppFiles {

    public static void init(String dataPath) throws IOException {
        AppIndex.initAppIndex(dataPath);
    }

    public static List<String> getAppsList(String dataPath) {
        return AppIndex.getAppsList(dataPath);
    }

    public static boolean appExists(String dataPath, String appName) {
        return AppDirectory.appDirectoryExists(dataPath, appName) && AppIndex.appEntryExists(dataPath, appName);
    }

    public static void createApp(String dataPath, String appName) throws IOException {
        while (true) {
            if (createAppImpl(dataPath, appName)) {
                break;
            }
        }
    }

    private static boolean createAppImpl(String dataPath, String appName) throws IOException {
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
                return false;
            }
        } catch (OverlappingFileLockException e) {
            Console.info("Waiting for lock for creating app '" + appName + "'");
            return false;
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

        return true;
    }

    public static void deleteApp(String dataPath, String appName) throws IOException {
        while (true) {
            if (deleteAppImpl(dataPath, appName)) {
                break;
            }
        }
    }

    private static boolean deleteAppImpl(String dataPath, String appName) throws IOException {
        RandomAccessFile lockAccessFile = null;
        FileChannel lockFileChannel;
        FileLock lock = null;

        try {
            File lockFile = new File(dataPath, "apps.lock");

            lockAccessFile = new RandomAccessFile(lockFile, "rw");
            lockFileChannel = lockAccessFile.getChannel();

            lock = lockFileChannel.tryLock();

            if (lock != null) {
                Console.info("Acquired lock for deleting app '" + appName + "'");
                lockFile.deleteOnExit();

                ByteBuffer bytes = ByteBuffer.allocate(4);
                bytes.putInt(1).flip();

                lockFileChannel.write(bytes);
                lockFileChannel.force(false);

                AppIndex.removeAppEntry(dataPath, appName);
                AppDirectory.removeAppDirectory(dataPath, appName);
            } else {
                Console.info("Waiting for lock for deleting app '" + appName + "'");
                return false;
            }
        } catch (OverlappingFileLockException e) {
            Console.info("Waiting for lock for deleting app '" + appName + "'");
            return false;
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

        return true;
    }

    public static void renameApp(String dataPath, String appName, String newName) throws IOException {
        while (true) {
            if (renameAppImpl(dataPath, appName, newName)) {
                break;
            }
        }
    }

    private static boolean renameAppImpl(String dataPath, String appName, String newName) throws IOException {
        RandomAccessFile lockAccessFile = null;
        FileChannel lockFileChannel;
        FileLock lock = null;

        try {
            File lockFile = new File(dataPath, "apps.lock");

            lockAccessFile = new RandomAccessFile(lockFile, "rw");
            lockFileChannel = lockAccessFile.getChannel();

            lock = lockFileChannel.tryLock();

            if (lock != null) {
                Console.info("Acquired lock for renaming app '" + appName + "'");
                lockFile.deleteOnExit();

                ByteBuffer bytes = ByteBuffer.allocate(4);
                bytes.putInt(1).flip();

                lockFileChannel.write(bytes);
                lockFileChannel.force(false);

                AppIndex.renameAppEntry(dataPath, appName, newName);
                AppDirectory.renameAppDirectory(dataPath, appName, newName);
            } else {
                Console.info("Waiting for lock for renaming app '" + appName + "'");
                return false;
            }
        } catch (OverlappingFileLockException e) {
            Console.info("waiting for lock for renaming app '" + appName + "'");
            return false;
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

        return true;
    }
}
