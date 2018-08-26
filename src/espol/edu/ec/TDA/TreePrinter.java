/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.TDA;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Clase util para mostrar gráficamente el arbol binario de huffman por consola
 * @author MiguelPS + otros autores en linea.
 */
public class TreePrinter {

    public static void printNode(Nodo root) {
        int maxLevel = TreePrinter.maxLevel(root);

        printNode(Collections.singletonList(root), 1, maxLevel);
    }

    private static void printNode(List<Nodo> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || TreePrinter.isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        TreePrinter.prinSpaces(firstSpaces);

        List<Nodo> newNodes = new ArrayList<>();
        for (Nodo node : nodes) {
            if (node != null) {
                System.out.print(node.getCaracter());
                newNodes.add(node.getLeft());
                newNodes.add(node.getRight());
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            TreePrinter.prinSpaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                TreePrinter.prinSpaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    TreePrinter.prinSpaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).getLeft() != null)
                    System.out.print("/");
                else
                    TreePrinter.prinSpaces(1);

                TreePrinter.prinSpaces(i + i - 1);

                if (nodes.get(j).getRight() != null)
                    System.out.print("\\");
                else
                    TreePrinter.prinSpaces(1);
                TreePrinter.prinSpaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNode(newNodes, level + 1, maxLevel);
    }

    private static void prinSpaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static <E> int maxLevel(Nodo node) {
        if (node == null)
            return 0;

        return Math.max(TreePrinter.maxLevel(node.getLeft()), TreePrinter.maxLevel(node.getRight())) + 1;
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }

}
