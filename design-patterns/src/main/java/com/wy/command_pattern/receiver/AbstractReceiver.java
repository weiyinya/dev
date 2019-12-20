package com.wy.command_pattern.receiver;

/**
 * 命令的抽象接收者
 * @author weiyin
 * @date 2019-03-19 13:51
 */
public abstract class AbstractReceiver {

    /**
     * 添加
     * @return
     */
    public abstract void add();

    /**
     * 删除
     * @return
     */
    public abstract void delete();

    /**
     * 修改
     * @return
     */
    public abstract void update();

    /**
     * 给出排期
     */
    public abstract void plan();

}
