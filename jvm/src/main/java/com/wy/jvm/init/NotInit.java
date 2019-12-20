package com.wy.jvm.init;

import com.wy.jvm.init.initObj.Const;
import com.wy.jvm.init.initObj.Sub;
import com.wy.jvm.init.initObj.Super;
import org.junit.jupiter.api.Test;

/**
 * 没有初始化
 * @Author wy
 * @Date 2018/12/15
 */
public class NotInit {

    /**
     * 通过子类直接访问父类静态属性，不会对子类初始化
     */
    @Test
    public void test01(){
        System.out.println(Sub.value);
    }



    /**
     * 通过数组定义来引用类，不会触发该类的初始化
     */
    @Test
    public void test02(){
        Super[] sus = new Super[10];

        System.out.println("---");
        for (Super sup : sus){
            System.out.println();
        }
    }

    /**
     * 通过数组定义来引用类，不会触发该类的初始化
     */
    @Test
    public void test03(){
        System.out.println(Const.helloworld);
    }

    public static void main(String[] args) {
        System.out.println(Const.helloworld);
    }

}
