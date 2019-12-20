package com.wy.dataStructure.tree.binaryTree.binarySearchTree;

/**
 * @author weiyin
 * @date 2019-12-19 10:00
 */
public interface BinarySearchTreeOperator {
    void insert(Integer value);
    void delete(Integer value);

    /**
     * 前序遍历
     */
    void preorderQuery();

    /**
     * 后续遍历
     */
    void afterQuery();

    /**
     * 中序遍历
     */
    void middleQuery();
}

