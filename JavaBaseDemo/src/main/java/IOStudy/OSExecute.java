package IOStudy;

import javax.management.RuntimeErrorException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class OSExecute {
    public static void command(String command){
        try{
            Process process = new ProcessBuilder(command.split(" ")).start();
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String s;
            while((s = br.readLine()) != null)
                System.out.println(s);
            BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while((s = error.readLine()) != null)
                System.err.println(s);
        }catch (Exception e){
            if(!command.startsWith("CMD /C"))
                command("CMD /C" + command);
            else
                throw new RuntimeException(e);
        }
    }

    public static void main(String[] args){
        OSExecute.command("python D:\\SoftWare\\PythonWorkSpace\\test\\JavaTransferPython.py 1 2");
    }
}
