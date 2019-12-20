package com.wy.dataStructure.tree.binaryTree;

import com.wy.dataStructure.tree.binaryTree.binarySearchTree.BinarySearchTree;
import com.wy.dataStructure.tree.binaryTree.binarySearchTree.BinarySearchTreeOperator;

/**
 * @author weiyin
 * @date 2019-12-19 11:07
 */
public class Test {
    public static void main(String[] args) {
        BinarySearchTreeOperator operator = new BinarySearchTree();
        operator.insert(10);
        operator.insert(5);
        operator.insert(20);
        operator.insert(1);
        operator.insert(15);
        operator.middleQuery();
        operator.delete(10);
        operator.delete(15);
        operator.middleQuery();
    }
}
