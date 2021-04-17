package Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class sortbyH implements Comparator<MiniMaxNode> {
    public int compare(MiniMaxNode a, MiniMaxNode b) {
        return a.getHeuristic() - b.getHeuristic();
    }
}


public class MiniMaxTree {

    static int limit_depth = 10; // tanha ta omghe limit_depth bayad be sakhte derakht edame dahim
    MiniMaxNode root;

    public MiniMaxTree(MiniMaxNode root) {
        this.root = root;
        this.root.beta = Integer.MAX_VALUE;
        this.root.alpha = Integer.MIN_VALUE;
        this.makeTree(root, 0); // node root dar omghe 0 gharar darad
    }

    public void makeTree(MiniMaxNode currentNode, int depth) {
        // yani be barg haye derakht residim
        if (depth == limit_depth) {
            return;
        }

        //yni vasate derakhtim maghadir ro darim fght permitted movements ro mehgdar midim
        currentNode.calPlaces(currentNode.isMax);

        ArrayList<MiniMaxNode> temp_children = new ArrayList<>();

        for (int i = 0; i < currentNode.permittedMovements.size(); i++) {
            MiniMaxNode child = new MiniMaxNode();
            child.feature_individual = currentNode.feature_individual;

            for (int k = 0; k < 8; k++) {
                for (int j = 0; j < 8; j++) {
                    if (currentNode.board[k][j] == status.BLACK) {
                        child.board[k][j] = status.BLACK;
                    } else if (currentNode.board[k][j] == status.WHITE) {
                        child.board[k][j] = status.WHITE;
                    } else {
                        child.board[k][j] = status.EMPTY;
                    }
                }
            }

            child.parent = currentNode;

            for (coordinate coordinate : currentNode.whiteCoordinates) {
                child.whiteCoordinates.add(new coordinate(coordinate.i, coordinate.j));
            }
            for (coordinate coordinate : currentNode.blackCoordinates) {
                child.blackCoordinates.add(new coordinate(coordinate.i, coordinate.j));
            }

            child.isMax = !(currentNode.isMax);
            child.changeStatusBoard(currentNode.permittedMovements.get(i).i, currentNode.permittedMovements.get(i).j);
            temp_children.add(child);

        }

        Collections.sort(temp_children, new sortbyH());

        int sum_rank = 0;
        for (int i = 1; i <= temp_children.size(); i++) {
            sum_rank += i;
        }

        ArrayList<Integer> lower = new ArrayList<>();
        ArrayList<Integer> upper = new ArrayList<>();

        for (int i = 0; i < temp_children.size(); i++) {
            if (i == 0) {
                lower.add(0);
                upper.add(1);
                continue;
            }
            lower.add(upper.get(i - 1));
            upper.add(lower.get(i) + i);
        }


        int num_chosen = 3;
        if (temp_children.size() < num_chosen) {
            num_chosen = temp_children.size();
        }

        ArrayList<MiniMaxNode> chosen_children = new ArrayList<>();
        boolean[] isChosen = new boolean[temp_children.size()];

        for (int i = 0; i < num_chosen; i++) {

            double random = Math.random();
            random *= sum_rank;

            int num = 0;
            for (int j = 0; j < lower.size(); j++) {
                if (random >= lower.get(j) && random < upper.get(j)) {
                    num = j;
                    break;
                }
            }

            if (!isChosen[num]) { // agar ta hala entekhab nashode bashad
                chosen_children.add(temp_children.get(num));
                isChosen[num] = true;
            }
        }

        for (MiniMaxNode child : chosen_children
        ) {
            currentNode.children.add(child);
            makeTree(child, depth + 1);
        }

    }
}
