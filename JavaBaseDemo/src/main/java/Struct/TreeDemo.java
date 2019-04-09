package Struct;


import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 实现一个树结构
 */
public class TreeDemo {
    private TreeDemo leftChild;
    private TreeDemo rightChild;
    private String value;
    public TreeDemo getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(TreeDemo leftChild) {
        this.leftChild = leftChild;
    }

    public TreeDemo getRightChild() {
        return rightChild;
    }

    public void setRightChild(TreeDemo rightChild) {
        this.rightChild = rightChild;
    }

    TreeDemo(String s){
        this.value = s;
        this.leftChild = null;
        this.rightChild = null;
    }

    /**
     * 先序遍历二叉树
     * @param treeDemo 二叉树的根节点
     */
    public String firstTraverse(TreeDemo treeDemo){
        String result = "";
        if(treeDemo == null)
            return "";
        result += treeDemo.value;
        result += firstTraverse(treeDemo.leftChild);
        result += firstTraverse(treeDemo.rightChild);
        return result;
    }

    /**
     * 中序序遍历二叉树
     * @param treeDemo 二叉树的根节点
     */
    public String middleTraverse(TreeDemo treeDemo){
        String result = "";
        if(treeDemo == null)
            return "";
        result += middleTraverse(treeDemo.leftChild);
        result += treeDemo.value;
        result += middleTraverse(treeDemo.rightChild);
        return result;
    }

    /**
     * 后序遍历二叉树
     * @param treeDemo 二叉树的根节点
     */
    public String afterTraverse(TreeDemo treeDemo){
        String result = "";
        if(treeDemo == null)
            return "";
        result += afterTraverse(treeDemo.leftChild);
        result += afterTraverse(treeDemo.rightChild);
        result += treeDemo.value;
        return result;
    }

    /**
     * 中序遍历非递归(需要借助栈结构)
     * @return
     */
    public void middleTraverseByStack(TreeDemo treeDemo){
        Deque<TreeDemo> stack = new LinkedList<>();
        TreeDemo current = treeDemo;
        while(current!=null || !stack.isEmpty()){
            while(current!=null){
                stack.push(current);
                current = current.leftChild;
            }
            if(!stack.isEmpty()){
                current = stack.pop();
                System.out.print(current.value);
                current = current.rightChild;
            }
        }
    }

    /**
     *  按照层次遍历（需要借助队列）
     * @return
     */
    public String traverseByDeep(TreeDemo treeDemo){
        if(treeDemo == null)
            return "";
        else{
            List<TreeDemo> tempList = new ArrayList<>();
            List<String> resultList = new ArrayList<>();
            tempList.add(treeDemo);
            while (tempList.size() != 0){
                TreeDemo temp = tempList.get(0);
                resultList.add(temp.value);
                if(temp.leftChild != null)
                    tempList.add(temp.leftChild);
                if(temp.rightChild != null)
                    tempList.add(temp.rightChild);
                tempList.remove(temp);
            }
            return resultList.toString();
        }
    }

    /**
     * 获取树深度
     * @return
     */
    public int getDeep(TreeDemo treeDemo){
        if(treeDemo == null)
            return 0;
        else{
            int leftDeep = getDeep(treeDemo.leftChild);
            int rightDeep = getDeep(treeDemo.rightChild);
            return leftDeep > rightDeep ? leftDeep+1 : rightDeep+1;

        }
    }

    @Override
    public String toString() {
        return this.value;
    }

    public static void main(String[] args){
        /**
         * 构造一个树，结构如下图所示
         *                       A
         *                     /  \
         *                    B    C
         *                  /    /  \
         *                 D    E    F
         */
        TreeDemo a = new TreeDemo("A");
        TreeDemo b = new TreeDemo("B");
        TreeDemo c = new TreeDemo("C");
        TreeDemo d = new TreeDemo("D");
        TreeDemo e = new TreeDemo("E");
        TreeDemo f = new TreeDemo("F");
        a.setLeftChild(b);
        b.setLeftChild(d);
        a.setRightChild(c);
        c.setLeftChild(e);
        c.setRightChild(f);

        System.out.println("先序遍历:"+a.firstTraverse(a));
        System.out.println("中序遍历:"+a.middleTraverse(a));
        System.out.println("后序遍历:"+a.afterTraverse(a));
        System.out.println("二叉树高度:"+a.getDeep(a));
        System.out.println("按照层次遍历（需要借助队列）:"+a.traverseByDeep(a));
        System.out.print("中序非递归遍历（需要借助栈）:");
        a.middleTraverseByStack(a);
    }
}
