package ThreadStudy;

import java.awt.peer.SystemTrayPeer;

/**
 * 静态代理学习(装饰器模式)
 * 1.真实角色
 * 2.代理角色
 * 上诉两个角色都需要实现公共接口
 */
public class StaticProxy {
    public static void main(String[] args){
        TrueRole trueRole = new TrueRole();
        ProxyRole proxyRole = new ProxyRole(trueRole);
        proxyRole.hello();
    }
}

interface PublicMethod{
    void hello();
}

// 真实角色
class TrueRole implements PublicMethod{

    public void hello() {
        System.out.println("我是真实角色");
    }
}

// 代理角色
class ProxyRole implements PublicMethod{

    // 真实角色
    private TrueRole trueRole;
    public ProxyRole(TrueRole trueRole){
        this.trueRole = trueRole;
    }

    private void ready(){
        System.out.println("准备工作");
    }
    private void after(){
        System.out.println("完成工作");
    }
    public void hello() {
        this.ready();
        this.trueRole.hello();
        this.after();
    }

}