package IOStudy;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Echo {
    /**
     * 标准IO学习
     */
    public static void main(String[] args) throws Exception{
        // System.in是一个没有被包装过的未经加工的InputStream。
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while((s = stdin.readLine()) != null & s.length() != 0)
            System.out.println(s);
    }
}
