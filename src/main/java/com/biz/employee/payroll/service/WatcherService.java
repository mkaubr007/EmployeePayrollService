package com.biz.employee.payroll.service;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

import  static java.nio.file.StandardWatchEventKinds.*;
public class WatcherService {
    private final WatchService watcher;
    private final Map<WatchKey, Path> dirMatchers;

       public WatcherService(Path dir) throws IOException {
        this.watcher = FileSystems.getDefault().newWatchService();
        this.dirMatchers = new HashMap<WatchKey, Path>();
        scanAndRegisterDirectories(dir);
    }

    private void registerDirMatchers(Path dir) throws IOException {
        WatchKey key = dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        dirMatchers.put(key, dir);
    }

    private void scanAndRegisterDirectories(final Path start) throws IOException {
        Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                registerDirMatchers(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }
    @SuppressWarnings({"rawtypes","unchecked"})
    public void processEvents() {
        while (true) {
            WatchKey key;
            try {
                key = watcher.take();
            } catch (InterruptedException x) {
                return;
            }
            Path dir = dirMatchers.get(key);
            if (dir == null) continue;
            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind kind = event.kind();
                Path name = ((WatchEvent<Path>) event).context();
                Path child = dir.resolve(name);

                System.out.format("%s: %s\n", event.kind().name(), child);

                if (kind == ENTRY_CREATE) {
                    try{
                        if(Files.isDirectory(child))scanAndRegisterDirectories(child);
                    }catch (IOException x){}
                }
                else if (kind.equals(ENTRY_DELETE)) {
                    if(Files.isDirectory(child))dirMatchers.remove(key);
                }
            }
            boolean valid = key.reset();
            if (!valid) {
                dirMatchers.remove(key);
                if (dirMatchers.isEmpty()) {
                    break;
                }
            }
        }
    }
}

