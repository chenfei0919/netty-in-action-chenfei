import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.channels.FileChannel;

/**
 * @version 2017/6/16 0016.
 * @Author chenfei
 */
public class NioTest {
    public static void main(String[] args) throws FileNotFoundException {

        FileInputStream inputStream= new FileInputStream("1.txt");
        FileChannel channel= inputStream.getChannel();

    }
}
