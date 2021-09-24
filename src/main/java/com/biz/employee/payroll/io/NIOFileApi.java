package com.biz.employee.payroll.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.IntStream;

public class NIOFileApi {
    public static void main(String[] args) throws IOException {
        String url = System.getProperty("user.dir");
        String folderPath = url + "src/main/java/com/biz/employee/payroll/io/com.biz.employee.payroll.io.Demo";
        Path path = Paths.get(url + "src/main/java/com/biz/employee/payroll/io/com.biz.employee.payroll.io.Demo");
       System.out.println(Files.exists(path));
        Path directories = Files.createDirectories(path);
        System.out.println(directories);
        if (Files.exists(path)) {
            FileUtils.deleteFiles(path.toFile());
        }
        Files.createFile(path);

        IntStream.range(1, 10).forEach(value -> {
            Path tempFile = Paths.get(folderPath + "/temp" + value+".java");
            try {
                if (Files.notExists(tempFile))
                    Files.createFile(tempFile);
            } catch (IOException e) {
                System.out.println(e);
            }
        });
        Files.list(path)
                .filter(value->Files.isRegularFile(value))
                        .forEach(System.out::println);
         Files.newDirectoryStream(path).forEach(System.out::println);

        Files.newDirectoryStream(path,
                        data->data.toFile().isFile()&& data.getFileName().toString().startsWith("test"))
                .forEach(System.out::println);
    }
}
