package com.wy.dataStructure.tree.binaryTree.binarySearchTree;

import com.wy.dataStructure.tree.binaryTree.Node;

/**
 * 二叉查找树（不支持重复值）
 * times: 1h 55min
 * insert
 *  像这种有序的数据结构，插入时总要先寻找合适的插入位置，该实现就是先寻找parent节点，然后插入到parent下
 * delete
 *  分 根节点、叶子节点、非叶子节点只包含一个子节点、非叶子节点包含两个子节点 四种情况
 *
 * @author weiyin
 * @date 2019-12-18 18:16
 */
public class BinarySearchTree implements BinarySearchTreeOperator {

    /**
     * 根节点
     */
    private Node rootNode;

    @Override
    public void insert(Integer value) {
        Node node = Node.builder().data(value).build();
        if (rootNode==null){
            rootNode = node;
            return;
        }

        Node parentNode = findParentNode(null, rootNode, value);
        if (value>parentNode.getData()){
            parentNode.setRightNode(node);
        } else {
            parentNode.setLeftNode(node);
        }
    }

    /**
     * 寻找父节点
     * @return
     */
    private Node findParentNode(Node parent, Node node, int value) {
        Node result;
        if (value>node.getData()){
            if (node.getRightNode()!=null) {
                result = findParentNode(node, node.getRightNode(), value);
            } else {
                result = node;
            }
        } else if (value<node.getData()){
            if (node.getLeftNode()!=null) {
                result = findParentNode(node, node.getLeftNode(), value);
            } else {
                result = node;
            }
        } else {
            result = parent;
        }
        return result;
    }

    @Override
    public void delete(Integer value) {

        boolean isDeleteRoot = false;
        //根节点
        if (value.equals(rootNode.getData())){
            isDeleteRoot = true;
        }

        Node node = findNode(rootNode, value);
        if (node==null){
            System.out.println("节点不存在");
            return;
        }

        //叶子节点
        Node parentNode;
        if (node.getLeftNode()==null && node.getRightNode()==null){
            if (isDeleteRoot){
                rootNode = null;
                return;
            }

            parentNode = findParentNode(null, rootNode, value);
            //直接删除父节点的引用
            if (value<parentNode.getData()){
                parentNode.setLeftNode(null);
            } else {
                parentNode.setRightNode(null);
            }
            return;
        }

        //只包含一个节点
        if (node.getLeftNode()==null && node.getRightNode()!=null){
            if (isDeleteRoot){
                rootNode = node.getRightNode();
                return;
            }

            parentNode = findParentNode(null, rootNode, value);
            if (value<parentNode.getData()){
                parentNode.setLeftNode(node.getRightNode());
            } else {
                parentNode.setRightNode(node.getRightNode());
            }
            return;
        }
        if (node.getLeftNode()!=null && node.getRightNode()==null){
            if (isDeleteRoot){
                rootNode = node.getLeftNode();
                return;
            }

            parentNode = findParentNode(null, rootNode, value);
            if (value<parentNode.getData()){
                parentNode.setLeftNode(node.getLeftNode());
            } else {
                parentNode.setRightNode(node.getLeftNode());
            }
            return;
        }

        //包含两个节点
        Node minNode = findMinNode(node.getRightNode());
        Node minNodeParent = findParentNode(null, rootNode, minNode.getData());
        minNode.setLeftNode(node.getLeftNode()); //删除元素-移动元素 交换
        if (minNode.getData()!=node.getRightNode().getData()){
            minNode.setRightNode(node.getRightNode()); //删除元素-移动元素 交换
        }
        if (minNodeParent!=null){ //斩断被移动节点的父节点的引用
            if (minNode.getData()>minNodeParent.getData()){
                minNodeParent.setRightNode(null);
            } else {
                minNodeParent.setLeftNode(null);
            }
        }

        if (isDeleteRoot){
            rootNode = minNode; //删除元素-移动元素 交换
            return;
        }
        parentNode = findParentNode(null, rootNode, value);
        if (value<parentNode.getData()){
            parentNode.setLeftNode(minNode);
        } else {
            parentNode.setRightNode(minNode);
        }
    }

    private Node findMinNode(Node node){
        if (node!=null){
            if (node.getLeftNode()!=null){
                return findMinNode(node.getLeftNode());
            } else {
                return node;
            }
        } else {
            return null;
        }
    }

    private Node findNode(Node node, Integer value){
        if (node!=null){
            if (value.equals(node.getData())){
                return node;
            }
            if (value<node.getData()){
                return findNode(node.getLeftNode(), value);
            } else {
                return findNode(node.getRightNode(), value);
            }
        } else {
            return null;
        }
    }

    @Override
    public void preorderQuery() {
        preorderQuery(rootNode);
    }

    private void preorderQuery(Node node){
        if (node!=null){
            System.out.println(node.getData());
            preorderQuery(node.getLeftNode());
            preorderQuery(node.getRightNode());
        }
    }

    @Override
    public void afterQuery() {
        afterQuery(rootNode);
    }

    private void afterQuery(Node node){
        if (node!=null){
            afterQuery(node.getLeftNode());
            afterQuery(node.getRightNode());
            System.out.println(node.getData());
        }
    }

    @Override
    public void middleQuery() {
        middleQuery(rootNode);
    }

    private void middleQuery(Node node) {
        if (node!=null) {
            middleQuery(node.getLeftNode());
            System.out.println(node.getData());
            middleQuery(node.getRightNode());
        }
    }
}
