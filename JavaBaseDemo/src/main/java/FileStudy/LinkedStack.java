package FileStudy;

public class LinkedStack<T> {
    /**
     * 泛型学习，内部链式存储机制（模拟一个栈结构）
     */
    private class Node<U>{
        private U item;    //item用于保存当前节点的数据
        private Node<U> next;    //next用于指向下一个元素
        Node(){
            // 空的构造方法
            this.item = null;
            this.next = null;
        }
        Node(U item, Node<U> next){
            //构造函数
            this.item = item;
            this.next = next;
        }
        Boolean isEnd(){
            // 判断当前节点是否是最后一个节点，这里使用一个空节点表示栈底元素
            if(this.item == null && this.next == null)
                return true;
            else
                return false;
        }
    }

    private Node<T> top = new Node<T>(); //创建栈顶元素
    public void push(T item){
        // 往栈顶推入一个元素
         top = new Node<T>(item, top); // 创建一个新的节点，next指向上一个节点，top指向栈顶元素
    }
    public T pop(){
        // 返回栈顶元素的值，并且删除该节点
        T result = top.item;
        if(!top.isEnd()) {
            top = top.next;
            return result;
        }
        return result;
    }
    public boolean isEnd(){
        return top.isEnd();
    }

    public static void main(String[] args){
        LinkedStack<String> ls = new LinkedStack<String>();
        for(String s:"hello world".split(" "))
            ls.push(s);
        while (!ls.top.isEnd())
            System.out.println(ls.pop());
    }
}
