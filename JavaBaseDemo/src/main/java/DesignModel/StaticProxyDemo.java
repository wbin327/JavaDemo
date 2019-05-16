package DesignModel;

/**
 * 静态代理模式
 */
public class StaticProxyDemo implements PublicMethod{
    private BeProxy beProxy;
    StaticProxyDemo(BeProxy beProxy){
        this.beProxy = beProxy;
    }

    public void hello() {
        System.out.println("hello before");
        this.beProxy.hello();
        System.out.println("hello after");
    }

    public static void main(String[] args){
        BeProxy beProxy = new BeProxy();
        StaticProxyDemo proxyDemo = new StaticProxyDemo(beProxy);
        proxyDemo.hello();
    }
}

class BeProxy implements PublicMethod{

    public void hello() {
        System.out.println("hello");
    }
}

interface PublicMethod{
    public void hello();
}
