package Utils;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author ZhouHuTao
 * @version 1.0
 * @description
 * @date 2021/1/27 17:37
 */
public class Jxl {
    public static void readXls() throws Exception {
        //创建输入流，用于加载要读取的文件
        InputStream in = new FileInputStream("E://JXL_Test/my.xls");
        //从输入流中获得电子表格文档
        Workbook wb = Workbook.getWorkbook(in);
        //获得工作溥中的所有表单
        Sheet[] sheets = wb.getSheets();
        String id = "";
        String password = "";
        for(Sheet st:sheets){
            int rows = st.getRows();//行的长度
            int cols = st.getColumns();//列的长度
            for(int i=0;i<rows;i++){
                for(int j=0;j<cols;j++){
                    if(j%2==0){
                        id = st.getCell(j,i).getContents();
                    }else {
                        password = st.getCell(j,i).getContents();
                    }
                }
                new FuJian().screenshots(id,password);
            }
        }

        in.close();
    }

}
