package com.qatang.team.algorithm.xdd;

/**
 * 节点类
 * Created by Popo on 2017/4/20.
 */
public class Node {
    /**
     * 节点
     */
    protected Node next;

    /**
     * 数据
     */
    protected int data;

    public Node(int data) {
        this.data = data;
    }

    public void display() {
        System.out.print(data + " ");
    }
}