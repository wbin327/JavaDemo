package DesignModel;

/**
 * 工厂模式
 */
public class FactoryModelDemo {

    public static void main(String[] args){
        GunFactory factory = new GunFactory();
        AK47 ak = factory.produceAK();
        ak.shoot();
        ak.loading();
    }
}

abstract class Gun{
    String name;      // 枪的名字
    String bullet;    // 子弹的规格

    Gun(){}
    Gun(String name, String bullet){
        this.name = name;
        this.bullet = bullet;
    }

    public abstract void shoot();
    public abstract void loading();
}

class AK47 extends Gun{

    AK47(){}
    AK47(String name, String bullet){
        super(name, bullet);
    }
    @Override
    public void shoot() {
        System.out.println(String.format("%s射击", name));
    }

    @Override
    public void loading() {
        System.out.println(String.format("装载规格为%s的子弹", bullet));
    }
}

class M4A1 extends Gun{

    M4A1(){}
    M4A1(String name, String bullet){
        super(name, bullet);
    }
    @Override
    public void shoot() {
        System.out.println(String.format("%s射击", name));
    }

    @Override
    public void loading() {
        System.out.println(String.format("装载规格为%s的子弹", bullet));
    }
}

interface Factory{
    public Gun produceAK();
    public Gun producerM4();
}

class GunFactory implements Factory{

    @Override
    public AK47 produceAK() {
        return new AK47("AK47", "7.62mm");
    }

    @Override
    public M4A1 producerM4() {
        return new M4A1("M4A1", "5.56mm");
    }
}