package DesignModel;

import com.alibaba.fastjson.JSON;

/**
 * 建造者模式,用于构建复杂对象
 * 为什么要使用建造者模式
 * -  当类变内部变量很多时，我们编写构造函数初始化类时，不可能为每一种组合都编写一个构造函数，建造者模式就是解决这个问题
 *
 * 应用实例：
 * 1、去肯德基，汉堡、可乐、薯条、炸鸡翅等是不变的，而其组合是经常变化的，生成出所谓的"套餐"。
 * 2、JAVA 中的 StringBuilder。
 *
 *
 */
public class BuilderDemo {
    private String cpu;
    private String motherboard;
    private String power;
    private String memory;
    private String hardDisk;

    public String getCpu() {
        return cpu;
    }

    public String getMotherboard() {
        return motherboard;
    }

    public String getPower() {
        return power;
    }

    public String getMemory() {
        return memory;
    }

    public String getHardDisk() {
        return hardDisk;
    }

    /**
     * 使用内部类实现建造者模式
     */
    public static class ComputerBuild{
        private BuilderDemo builderDemo;

        ComputerBuild(){
            this.builderDemo = new BuilderDemo();
        }

        public BuilderDemo build(){
            return this.builderDemo;
        }

        public ComputerBuild buildCpu(String cpu){
            this.builderDemo.cpu = cpu;
            return this;
        }
        public ComputerBuild buildMotherBoard(String motherBoard){
            this.builderDemo.motherboard = motherBoard;
            return this;
        }
        public ComputerBuild buildPower(String power){
            this.builderDemo.power = power;
            return this;
        }
        public ComputerBuild buildMemory(String memory){
            this.builderDemo.memory = memory;
            return this;
        }
        public ComputerBuild buildHardDisk(String hardDisk){
            this.builderDemo.hardDisk = hardDisk;
            return this;
        }
    }

    public static void main(String[] args){
        BuilderDemo builderDemo = new BuilderDemo.ComputerBuild()
                .buildCpu("I7-9900K")
                .buildHardDisk("三星970 Pro")
                .buildMemory("海盗船8g*2")
                .buildPower("海盗床650W")
                .buildMotherBoard("华硕Z390")
                .build();
        System.out.println(JSON.toJSONString(builderDemo));
    }
}

