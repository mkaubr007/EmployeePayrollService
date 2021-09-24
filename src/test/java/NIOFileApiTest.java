import com.biz.employee.payroll.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.IntStream;

public class NIOFileApiTest {
    private static String HOME=System.getProperty("user.home");
    private static String PLAY_WITH_NID="TempPlayGround";
    @Test
    public void givenPathWhenCheckThenConfirm()throws IOException{
        //check file exist
        Path homePath= Paths.get(HOME);
        Assert.assertTrue(Files.exists(homePath));

        //delete file and check file not exist
        Path playPath=Paths.get(HOME+"/"+PLAY_WITH_NID);
        if(Files.exists(playPath)) FileUtils.deleteFiles(playPath.toFile());
        Assert.assertTrue(Files.notExists(playPath));

        //Create directory
        Files.createDirectory(playPath);
        Assert.assertTrue(Files.exists(playPath));

        //Create file
        IntStream.range(1,10).forEach(value -> {
            Path tempFile=Paths.get(playPath+"/temp"+value);
            Assert.assertTrue(Files.notExists(tempFile));
            try {
                Files.createFile(tempFile);
            }catch (IOException e){
                Assert.assertTrue(Files.exists(tempFile));
            }
        });
        //list file ,directory with extension
        Files.list(playPath).filter(Files::isRegularFile).forEach(System.out::println);
        Files.newDirectoryStream(playPath).forEach(System.out::println);
        Files.newDirectoryStream(playPath,path->path.toFile().isFile() &&
                path.toString().startsWith("temp"))
        .forEach(System.out::println);

    }
}
