import com.biz.employee.payroll.service.WatcherService;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//watcher service
public class WatcherServiceTest {
    private static String HOME=System.getProperty("user.home");
    private static String PLAY_WITH_NID="TempPlayGround";
    @Test
    public void givenDirectoryWhenMatchAllTheActivities()throws IOException {
        Path dir= Paths.get(HOME+"/"+PLAY_WITH_NID);
        Files.list(dir).filter(Files::isRegularFile).forEach(System.out::println);
        new WatcherService(dir).processEvents();
    }
}