package com.qatang.team.algorithm.xdd;

/**
 * Created by Popo on 2017/4/20.
 */
public class FinalThree {

    public static void main(String[] args) {
        LinkList linkList = new LinkList();
        linkList.addFirstNode(1);
        linkList.addFirstNode(2);
        linkList.addFirstNode(3);
        //linkList.displayAllNodes();
        linkList.displayAllNodesReverse(linkList.first);
    }
}
