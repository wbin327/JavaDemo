package IOStudy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import FileStudy.LinkedStack;

public class BufferedInput implements Iterable {
    /**
     * 读取文件练习
     */
    private List<String> list;

    BufferedInput(String filePath) throws IOException{
        this.list = new ArrayList<String>();
        BufferedReader in = new BufferedReader(new FileReader(filePath));
        String s;
        while((s=in.readLine()) != null){
            this.list.add(s);
        }
        in.close();
    }
    public Iterator iterator() {
        return this.list.iterator();
    }

    public static void main(String[] args) throws Exception{
        BufferedInput input = new BufferedInput("pom.xml");
        LinkedStack<String> stack = new LinkedStack<String>();
        Iterator ite = input.iterator();
        while (ite.hasNext()){
            stack.push((String)ite.next());
        }
        while(!stack.isEnd()){
            System.out.println(stack.pop());
        }

    }
}



