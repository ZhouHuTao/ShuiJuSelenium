import Utils.GetPcode;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author ZhouHuTao
 * @version 1.0
 * @description
 * @date 2021/1/27 14:07
 */
public class Demo1 {
    @Test
    public void test1() throws IOException, InterruptedException {
        new GetPcode().code();

    }
}
