package com.wy.dataStructure.tree.binaryTree;

import lombok.Builder;
import lombok.Data;

/**
 * 二叉树节点
 * @author weiyin
 * @date 2019-12-19 09:56
 */
@Data
@Builder
public class Node {
    private Node leftNode;
    private Node rightNode;
    private Integer data;
}
