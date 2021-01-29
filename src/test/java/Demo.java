import Utils.GetVcode;
import Utils.Jxl;
import jxl.read.biff.BiffException;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.ImageHelper;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @author ZhouHuTao
 * @version 1.0
 * @description
 * @date 2021/1/26 20:54
 */
public class Demo {
    @Test
    public void test1() throws Exception {
        Jxl.readXls();
    }
}
