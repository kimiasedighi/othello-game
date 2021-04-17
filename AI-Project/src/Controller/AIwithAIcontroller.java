package Controller;

import java.util.ArrayList;

public class AIwithAIcontroller {

    int turn = 0;
    boolean endGame = false;
    static status[][] chessBoard = new status[8][8];
    static ArrayList<coordinate> blackCoordinates = new ArrayList<>();
    static ArrayList<coordinate> whiteCoordinates = new ArrayList<>();
    static ArrayList<coordinate> permittedMovements = new ArrayList<>();

    Individual a;
    Individual b;

    boolean no_move_1 = false, no_move_2 = false;

    public AIwithAIcontroller(Individual a, Individual b) {

        this.a = a;
        this.b = b;

        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                chessBoard[i][j] = status.EMPTY;
            }
        }

        chessBoard[3][3] = status.WHITE;
        chessBoard[3][4] = status.BLACK;
        chessBoard[4][3] = status.BLACK;
        chessBoard[4][4] = status.WHITE;

        updateBoard();
        newTurn();
    }

    public void updateBoard() {
        whiteCoordinates.clear();
        blackCoordinates.clear();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (chessBoard[i][j] == status.WHITE) {
                    whiteCoordinates.add(new coordinate(i, j));
                } else if (chessBoard[i][j] == status.BLACK) {
                    blackCoordinates.add(new coordinate(i, j));
                }
            }
        }
    }

    public void newTurn() {

        if ((blackCoordinates.size() + whiteCoordinates.size() == 64) || endGame || (no_move_2 && no_move_1)) {
            if (blackCoordinates.size() > whiteCoordinates.size()) {
                a.fitness++;
            } else if (blackCoordinates.size() < whiteCoordinates.size())
                b.fitness++;
            System.out.println("end");
            return;

        } else {

            if (turn % 2 == 0) {
                //black's turn
                if (!calPlaces("black")) {
                    turn++;
                    no_move_1 = true;

                    if (no_move_2)
                        endGame = true;
                    newTurn();
                } else {
                    no_move_1 = false;

                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                AIplay();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            super.run();
                        }
                    }.start();

                }
            } else {
                //white's turn
                if (!calPlaces("white")) {
                    turn++;
                    no_move_2 = true;

                    if (no_move_1)
                        endGame = true;
                    newTurn();
                } else {
                    no_move_2 = false;
                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                AIplay();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            super.run();
                        }
                    }.start();

                }
            }
        }
    }

    public void AIplay() throws InterruptedException {

        MiniMaxNode node = new MiniMaxNode();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (chessBoard[i][j] == status.BLACK) {
                    node.board[i][j] = status.BLACK;
                } else if (chessBoard[i][j] == status.WHITE) {
                    node.board[i][j] = status.WHITE;
                } else {
                    node.board[i][j] = status.EMPTY;
                }
            }
        }
        node.parent = null;
        ArrayList<coordinate> thingsToBeAdded = new ArrayList<coordinate>();
        for (int i = 0; i < whiteCoordinates.size(); i++) {
            coordinate c = whiteCoordinates.get(i);
            if (c != null)
                thingsToBeAdded.add(new coordinate(c.i, c.j));
        }
        node.whiteCoordinates.addAll(thingsToBeAdded);
        thingsToBeAdded.clear();
        for (int i = 0; i < blackCoordinates.size(); i++) {
            coordinate c = blackCoordinates.get(i);
            if (c != null)
                thingsToBeAdded.add(new coordinate(c.i, c.j));
        }
        node.blackCoordinates.addAll(thingsToBeAdded);

        if (turn % 2 == 0) {
            node.isMax = true;
            node.feature_individual = this.a;
            new MiniMaxTree(node);
            int alphaNode = node.pruning(0, Integer.MIN_VALUE, Integer.MAX_VALUE);

            if (node.children.size() > 0) {
                MiniMaxNode nextNode = new MiniMaxNode();
                for (MiniMaxNode child : node.children) {
                    if (child.beta == alphaNode) {
                        nextNode = child;
                        break;
                    }
                }
                chessBoard = nextNode.board;
            } else {
                chessBoard = node.board;
            }
        } else {
            node.isMax = false;
            node.feature_individual = this.b;

            new MiniMaxTree(node);
            int betaNode = node.pruning(0, Integer.MIN_VALUE, Integer.MAX_VALUE);

            if (node.children.size() > 0) {
                MiniMaxNode nextNode = new MiniMaxNode();
                for (MiniMaxNode child : node.children) {
                    if (child.alpha == betaNode) {
                        nextNode = child;
                        break;
                    }
                }
                chessBoard = nextNode.board;
            } else {
                chessBoard = node.board;
            }
        }
        Thread.sleep(1000);
        updateBoard();
        turn++;
        newTurn();
    }

    boolean calPlaces(String player) {

        boolean flag = true;
        if (blackCoordinates.size() + whiteCoordinates.size() >= 64)
            flag = false;

        permittedMovements.clear();

        if (player == "black") {

            for (int index = 0; index < blackCoordinates.size(); index++) {
                coordinate white_house;
                try {
                    white_house = blackCoordinates.get(index);
                }
                catch (IndexOutOfBoundsException e) {
                    return false;
                }
                if (white_house == null)
                    return false;
                int i = white_house.i;
                int j = white_house.j;

                // exception forms

                // halat hayi ke dar goosheye safhe hastand
                if ((i == 0 && j == 0) || (i == 7 && j == 0) || (i == 0 && j == 7) || (i == 7 && j == 7)) {
                    continue;
                }

                // halat hayi ke dar satr ya sotoon ebteda ya entehayi hastand vali gooshe nistand
                if (j == 0) {

                    if (chessBoard[i - 1][0] == status.EMPTY && chessBoard[i + 1][0] == status.EMPTY) {
                        continue;
                    }
                    if (chessBoard[i - 1][0] != status.EMPTY && chessBoard[i + 1][0] != status.EMPTY) {
                        continue;
                    }
                    if (chessBoard[i - 1][0] == status.BLACK) {
                        permittedMovements.add(new coordinate(i + 1, 0));
                        continue;
                    }
                    if (chessBoard[i + 1][0] == status.BLACK) {
                        permittedMovements.add(new coordinate(i - 1, 0));
                        continue;
                    }
                    if (chessBoard[i - 1][0] == status.WHITE) {
                        for (int k = i - 1; k >= 0; k--) {
                            if (chessBoard[k][0] == status.EMPTY)
                                break;
                            if (chessBoard[k][0] == status.BLACK) {
                                permittedMovements.add(new coordinate(i + 1, 0));
                                break;
                            }
                        }
                        continue;
                    }

                    if (chessBoard[i + 1][0] == status.WHITE) {
                        for (int k = i + 1; k <= 7; k++) {
                            if (chessBoard[k][0] == status.EMPTY)
                                break;
                            if (chessBoard[k][0] == status.BLACK) {
                                permittedMovements.add(new coordinate(i - 1, 0));
                                break;
                            }
                        }
                        continue;
                    }
                }

                if (j == 7) {

                    if (chessBoard[i - 1][7] == status.EMPTY && chessBoard[i + 1][7] == status.EMPTY) {
                        continue;
                    }
                    if (chessBoard[i - 1][7] != status.EMPTY && chessBoard[i + 1][7] != status.EMPTY) {
                        continue;
                    }
                    if (chessBoard[i - 1][7] == status.BLACK) {
                        permittedMovements.add(new coordinate(i + 1, 7));
                        continue;
                    }
                    if (chessBoard[i + 1][7] == status.BLACK) {
                        permittedMovements.add(new coordinate(i - 1, 7));
                        continue;
                    }
                    if (chessBoard[i - 1][7] == status.WHITE) {
                        for (int k = i - 1; k >= 0; k--) {
                            if (chessBoard[k][7] == status.EMPTY)
                                break;
                            if (chessBoard[k][7] == status.BLACK) {
                                permittedMovements.add(new coordinate(i + 1, 7));
                                break;
                            }
                        }
                        continue;
                    }

                    if (chessBoard[i + 1][7] == status.WHITE) {
                        for (int k = i + 1; k <= 7; k++) {
                            if (chessBoard[k][7] == status.EMPTY)
                                break;
                            if (chessBoard[k][7] == status.BLACK) {
                                permittedMovements.add(new coordinate(i - 1, 7));
                                break;
                            }
                        }
                        continue;
                    }
                }

                if (i == 0) {

                    if (chessBoard[0][j + 1] == status.EMPTY && chessBoard[0][j - 1] == status.EMPTY) {
                        continue;
                    }
                    if (chessBoard[0][j + 1] != status.EMPTY && chessBoard[0][j - 1] != status.EMPTY) {
                        continue;
                    }
                    if (chessBoard[0][j + 1] == status.BLACK) {
                        permittedMovements.add(new coordinate(0, j - 1));
                        continue;
                    }
                    if (chessBoard[0][j - 1] == status.BLACK) {
                        permittedMovements.add(new coordinate(0, j + 1));
                        continue;
                    }
                    if (chessBoard[0][j - 1] == status.WHITE) {
                        for (int k = j - 1; k >= 0; k--) {
                            if (chessBoard[0][k] == status.EMPTY)
                                break;
                            if (chessBoard[0][k] == status.BLACK) {
                                permittedMovements.add(new coordinate(0, j + 1));
                                break;
                            }
                        }
                        continue;
                    }

                    if (chessBoard[0][j + 1] == status.WHITE) {
                        for (int k = j + 1; k <= 7; k++) {
                            if (chessBoard[0][k] == status.EMPTY)
                                break;
                            if (chessBoard[0][k] == status.BLACK) {
                                permittedMovements.add(new coordinate(0, j - 1));
                                break;
                            }
                        }
                        continue;
                    }
                }

                if (i == 7) {

                    if (chessBoard[7][j + 1] == status.EMPTY && chessBoard[7][j - 1] == status.EMPTY) {
                        continue;
                    }
                    if (chessBoard[7][j + 1] != status.EMPTY && chessBoard[7][j - 1] != status.EMPTY) {
                        continue;
                    }
                    if (chessBoard[7][j + 1] == status.BLACK) {
                        permittedMovements.add(new coordinate(7, j - 1));
                        continue;
                    }
                    if (chessBoard[7][j - 1] == status.BLACK) {
                        permittedMovements.add(new coordinate(7, j + 1));
                        continue;
                    }
                    if (chessBoard[7][j - 1] == status.WHITE) {
                        for (int k = 0; k < j - 1; k++) {
                            if (chessBoard[7][k] == status.EMPTY)
                                break;
                            if (chessBoard[7][k] == status.BLACK) {
                                permittedMovements.add(new coordinate(7, j + 1));
                                break;
                            }
                        }
                        continue;
                    }

                    if (chessBoard[7][j + 1] == status.WHITE) {
                        for (int k = 7; k > j + 1; k--) {
                            if (chessBoard[7][k] == status.EMPTY)
                                break;
                            if (chessBoard[7][k] == status.BLACK) {
                                permittedMovements.add(new coordinate(7, j - 1));
                                break;
                            }
                        }
                        continue;
                    }
                }
                // normal forms

                // ghotri ha
                if (chessBoard[i - 1][j - 1] == status.EMPTY) {
                    if (chessBoard[i + 1][j + 1] == status.BLACK) {
                        permittedMovements.add(new coordinate(i - 1, j - 1));
                    } else if (chessBoard[i + 1][j + 1] == status.WHITE) {
                        int x;
                        int y;
                        for (x = i + 1, y = j + 1; (x <= 7 && y <= 7); x++, y++) {
                            if (chessBoard[x][y] == status.EMPTY)
                                break;
                            if (chessBoard[x][y] == status.BLACK) {
                                permittedMovements.add(new coordinate(i - 1, j - 1));
                                break;
                            }
                        }
                    }
                }

                if (chessBoard[i + 1][j - 1] == status.EMPTY) {
                    if (chessBoard[i - 1][j + 1] == status.BLACK) {
                        permittedMovements.add(new coordinate(i + 1, j - 1));
                    } else if (chessBoard[i - 1][j + 1] == status.WHITE) {
                        int x;
                        int y;
                        for (x = i - 1, y = j + 1; (x >= 0 && y <= 7); x--, y++) {
                            if (chessBoard[x][y] == status.EMPTY)
                                break;
                            if (chessBoard[x][y] == status.BLACK) {
                                permittedMovements.add(new coordinate(i + 1, j - 1));
                                break;
                            }
                        }
                    }
                }

                if (chessBoard[i - 1][j + 1] == status.EMPTY) {
                    if (chessBoard[i + 1][j - 1] == status.BLACK) {
                        permittedMovements.add(new coordinate(i - 1, j + 1));
                    } else if (chessBoard[i + 1][j - 1] == status.WHITE) {
                        int x;
                        int y;
                        for (x = i + 1, y = j - 1; (x <= 7 && y >= 0); x++, y--) {
                            if (chessBoard[x][y] == status.EMPTY)
                                break;
                            if (chessBoard[x][y] == status.BLACK) {
                                permittedMovements.add(new coordinate(i - 1, j + 1));
                                break;
                            }
                        }
                    }
                }

                if (chessBoard[i + 1][j + 1] == status.EMPTY) {
                    if (chessBoard[i - 1][j - 1] == status.BLACK) {
                        permittedMovements.add(new coordinate(i + 1, j + 1));
                    } else if (chessBoard[i - 1][j - 1] == status.WHITE) {
                        int x;
                        int y;
                        for (x = i - 1, y = j - 1; (x >= 0 && y >= 0); x--, y--) {
                            if (chessBoard[x][y] == status.EMPTY)
                                break;
                            if (chessBoard[x][y] == status.BLACK) {
                                permittedMovements.add(new coordinate(i + 1, j + 1));
                                break;
                            }
                        }
                    }
                }

                // vertical
                if (chessBoard[i][j - 1] == status.EMPTY) {
                    if (chessBoard[i][j + 1] == status.BLACK) {
                        permittedMovements.add(new coordinate(i, j - 1));
                    } else if (chessBoard[i][j + 1] == status.WHITE) {
                        for (int k = j + 1; k <= 7; k++) {
                            if (chessBoard[i][k] == status.EMPTY)
                                break;
                            if (chessBoard[i][k] == status.BLACK) {
                                permittedMovements.add(new coordinate(i, j - 1));
                                break;
                            }
                        }
                    }
                }

                if (chessBoard[i][j + 1] == status.EMPTY) {
                    if (chessBoard[i][j - 1] == status.BLACK) {
                        permittedMovements.add(new coordinate(i, j + 1));
                    } else if (chessBoard[i][j - 1] == status.WHITE) {
                        for (int k = j - 1; k >= 0; k--) {
                            if (chessBoard[i][k] == status.EMPTY)
                                break;
                            if (chessBoard[i][k] == status.BLACK) {
                                permittedMovements.add(new coordinate(i, j + 1));
                                break;
                            }
                        }
                    }
                }

                // horizontal
                if (chessBoard[i - 1][j] == status.EMPTY) {
                    if (chessBoard[i + 1][j] == status.BLACK) {
                        permittedMovements.add(new coordinate(i - 1, j));
                    } else if (chessBoard[i + 1][j] == status.WHITE) {
                        for (int k = i + 1; k <= 7; k++) {
                            if (chessBoard[k][j] == status.EMPTY)
                                break;
                            if (chessBoard[k][j] == status.BLACK) {
                                permittedMovements.add(new coordinate(i - 1, j));
                                break;
                            }
                        }
                    }
                }

                if (chessBoard[i + 1][j] == status.EMPTY) {
                    if (chessBoard[i - 1][j] == status.BLACK) {
                        permittedMovements.add(new coordinate(i + 1, j));
                    } else if (chessBoard[i - 1][j] == status.WHITE) {
                        for (int k = i - 1; k >= 0; k--) {
                            if (chessBoard[k][j] == status.EMPTY)
                                break;
                            if (chessBoard[k][j] == status.BLACK) {
                                permittedMovements.add(new coordinate(i + 1, j));
                                break;
                            }
                        }
                    }
                }
            }

        } else {
            for (int index = 0; index < blackCoordinates.size(); index++) {
                coordinate black_house;
                try {
                    black_house = blackCoordinates.get(index);
                }
                catch (IndexOutOfBoundsException e) {
                    return false;
                }
                if (black_house == null)
                    return false;
                int i = black_house.i;
                int j = black_house.j;


                // exception forms

                // halat hayi ke dar goosheye safhe hastand
                if ((i == 0 && j == 0) || (i == 7 && j == 0) || (i == 0 && j == 7) || (i == 7 && j == 7)) {
                    continue;
                }

                // halat hayi ke dar satr ya sotoon ebteda ya entehayi hastand vali gooshe nistand
                if (j == 0) {

                    if (chessBoard[i - 1][0] == status.EMPTY && chessBoard[i + 1][0] == status.EMPTY) {
                        continue;
                    }
                    if (chessBoard[i - 1][0] != status.EMPTY && chessBoard[i + 1][0] != status.EMPTY) {
                        continue;
                    }
                    if (chessBoard[i - 1][0] == status.WHITE) {
                        permittedMovements.add(new coordinate(i + 1, 0));
                        continue;
                    }
                    if (chessBoard[i + 1][0] == status.WHITE) {
                        permittedMovements.add(new coordinate(i - 1, 0));
                        continue;
                    }
                    if (chessBoard[i - 1][0] == status.BLACK) {
                        for (int k = i - 1; k >= 0; k--) {
                            if (chessBoard[k][0] == status.EMPTY)
                                break;
                            if (chessBoard[k][0] == status.WHITE) {
                                permittedMovements.add(new coordinate(i + 1, 0));
                                break;
                            }
                        }
                        continue;
                    }

                    if (chessBoard[i + 1][0] == status.BLACK) {
                        for (int k = i + 1; k <= 7; k++) {
                            if (chessBoard[k][0] == status.EMPTY)
                                break;
                            if (chessBoard[k][0] == status.WHITE) {
                                permittedMovements.add(new coordinate(i - 1, 0));
                                break;
                            }
                        }
                        continue;
                    }
                }

                if (j == 7) {

                    if (chessBoard[i - 1][7] == status.EMPTY && chessBoard[i + 1][7] == status.EMPTY) {
                        continue;
                    }
                    if (chessBoard[i - 1][7] != status.EMPTY && chessBoard[i + 1][7] != status.EMPTY) {
                        continue;
                    }
                    if (chessBoard[i - 1][7] == status.WHITE) {
                        permittedMovements.add(new coordinate(i + 1, 7));
                        continue;
                    }
                    if (chessBoard[i + 1][7] == status.WHITE) {
                        permittedMovements.add(new coordinate(i - 1, 7));
                        continue;
                    }
                    if (chessBoard[i - 1][7] == status.BLACK) {
                        for (int k = i - 1; k >= 0; k--) {
                            if (chessBoard[k][7] == status.EMPTY)
                                break;
                            if (chessBoard[k][7] == status.WHITE) {
                                permittedMovements.add(new coordinate(i + 1, 7));
                                break;
                            }
                        }
                        continue;
                    }

                    if (chessBoard[i + 1][7] == status.BLACK) {
                        for (int k = i + 1; k <= 7; k++) {
                            if (chessBoard[k][7] == status.EMPTY)
                                break;
                            if (chessBoard[k][7] == status.WHITE) {
                                permittedMovements.add(new coordinate(i - 1, 7));
                                break;
                            }
                        }
                        continue;
                    }
                }

                if (i == 0) {

                    if (chessBoard[0][j + 1] == status.EMPTY && chessBoard[0][j - 1] == status.EMPTY) {
                        continue;
                    }
                    if (chessBoard[0][j + 1] != status.EMPTY && chessBoard[0][j - 1] != status.EMPTY) {
                        continue;
                    }
                    if (chessBoard[0][j + 1] == status.WHITE) {
                        permittedMovements.add(new coordinate(0, j - 1));
                        continue;
                    }
                    if (chessBoard[0][j - 1] == status.WHITE) {
                        permittedMovements.add(new coordinate(0, j + 1));
                        continue;
                    }
                    if (chessBoard[0][j - 1] == status.BLACK) {
                        for (int k = j - 1; k >= 0; k--) {
                            if (chessBoard[0][k] == status.EMPTY)
                                break;
                            if (chessBoard[0][k] == status.WHITE) {
                                permittedMovements.add(new coordinate(0, j + 1));
                                break;
                            }
                        }
                        continue;
                    }

                    if (chessBoard[0][j + 1] == status.BLACK) {
                        for (int k = j + 1; k <= 7; k++) {
                            if (chessBoard[0][k] == status.EMPTY)
                                break;
                            if (chessBoard[0][k] == status.WHITE) {
                                permittedMovements.add(new coordinate(0, j - 1));
                                break;
                            }
                        }
                        continue;
                    }
                }

                if (i == 7) {

                    if (chessBoard[7][j + 1] == status.EMPTY && chessBoard[7][j - 1] == status.EMPTY) {
                        continue;
                    }
                    if (chessBoard[7][j + 1] != status.EMPTY && chessBoard[7][j - 1] != status.EMPTY) {
                        continue;
                    }
                    if (chessBoard[7][j + 1] == status.WHITE) {
                        permittedMovements.add(new coordinate(7, j - 1));
                        continue;
                    }
                    if (chessBoard[7][j - 1] == status.WHITE) {
                        permittedMovements.add(new coordinate(7, j + 1));
                        continue;
                    }
                    if (chessBoard[7][j - 1] == status.BLACK) {
                        for (int k = 0; k < j - 1; k++) {
                            if (chessBoard[7][k] == status.EMPTY)
                                break;
                            if (chessBoard[7][k] == status.WHITE) {
                                permittedMovements.add(new coordinate(7, j + 1));
                                break;
                            }
                        }
                        continue;
                    }

                    if (chessBoard[7][j + 1] == status.BLACK) {
                        for (int k = 7; k > j + 1; k--) {
                            if (chessBoard[7][k] == status.EMPTY)
                                break;
                            if (chessBoard[7][k] == status.WHITE) {
                                permittedMovements.add(new coordinate(7, j - 1));
                                break;
                            }
                        }
                        continue;
                    }
                }
                // normal forms

                // ghotri ha
                if (chessBoard[i - 1][j - 1] == status.EMPTY) {
                    if (chessBoard[i + 1][j + 1] == status.WHITE) {
                        permittedMovements.add(new coordinate(i - 1, j - 1));
                    } else if (chessBoard[i + 1][j + 1] == status.BLACK) {
                        int x;
                        int y;
                        for (x = i + 1, y = j + 1; (x <= 7 && y <= 7); x++, y++) {
                            if (chessBoard[x][y] == status.EMPTY)
                                break;
                            if (chessBoard[x][y] == status.WHITE) {
                                permittedMovements.add(new coordinate(i - 1, j - 1));
                                break;
                            }
                        }
                    }
                }

                if (chessBoard[i + 1][j - 1] == status.EMPTY) {
                    if (chessBoard[i - 1][j + 1] == status.WHITE) {
                        permittedMovements.add(new coordinate(i + 1, j - 1));
                    } else if (chessBoard[i - 1][j + 1] == status.BLACK) {
                        int x;
                        int y;
                        for (x = i - 1, y = j + 1; (x >= 0 && y <= 7); x--, y++) {
                            if (chessBoard[x][y] == status.EMPTY)
                                break;
                            if (chessBoard[x][y] == status.WHITE) {
                                permittedMovements.add(new coordinate(i + 1, j - 1));
                                break;
                            }
                        }
                    }
                }

                if (chessBoard[i - 1][j + 1] == status.EMPTY) {
                    if (chessBoard[i + 1][j - 1] == status.WHITE) {
                        permittedMovements.add(new coordinate(i - 1, j + 1));
                    } else if (chessBoard[i + 1][j - 1] == status.BLACK) {
                        int x;
                        int y;
                        for (x = i + 1, y = j - 1; (x <= 7 && y >= 0); x++, y--) {
                            if (chessBoard[x][y] == status.EMPTY)
                                break;
                            if (chessBoard[x][y] == status.WHITE) {
                                permittedMovements.add(new coordinate(i - 1, j + 1));
                                break;
                            }
                        }
                    }
                }

                if (chessBoard[i + 1][j + 1] == status.EMPTY) {
                    if (chessBoard[i - 1][j - 1] == status.WHITE) {
                        permittedMovements.add(new coordinate(i + 1, j + 1));
                    } else if (chessBoard[i - 1][j - 1] == status.BLACK) {
                        int x;
                        int y;
                        for (x = i - 1, y = j - 1; (x >= 0 && y >= 0); x--, y--) {
                            if (chessBoard[x][y] == status.EMPTY)
                                break;
                            if (chessBoard[x][y] == status.WHITE) {
                                permittedMovements.add(new coordinate(i + 1, j + 1));
                                break;
                            }
                        }
                    }
                }

                // vertical
                if (chessBoard[i][j - 1] == status.EMPTY) {
                    if (chessBoard[i][j + 1] == status.WHITE) {
                        permittedMovements.add(new coordinate(i, j - 1));
                    } else if (chessBoard[i][j + 1] == status.BLACK) {
                        for (int k = j + 1; k <= 7; k++) {
                            if (chessBoard[i][k] == status.EMPTY)
                                break;
                            if (chessBoard[i][k] == status.WHITE) {
                                permittedMovements.add(new coordinate(i, j - 1));
                                break;
                            }
                        }
                    }
                }

                if (chessBoard[i][j + 1] == status.EMPTY) {
                    if (chessBoard[i][j - 1] == status.WHITE) {
                        permittedMovements.add(new coordinate(i, j + 1));
                    } else if (chessBoard[i][j - 1] == status.BLACK) {
                        for (int k = j - 1; k >= 0; k--) {
                            if (chessBoard[i][k] == status.EMPTY)
                                break;
                            if (chessBoard[i][k] == status.WHITE) {
                                permittedMovements.add(new coordinate(i, j + 1));
                                break;
                            }
                        }
                    }
                }

                // horizontal
                if (chessBoard[i - 1][j] == status.EMPTY) {
                    if (chessBoard[i + 1][j] == status.WHITE) {
                        permittedMovements.add(new coordinate(i - 1, j));
                    } else if (chessBoard[i + 1][j] == status.BLACK) {
                        for (int k = i + 1; k <= 7; k++) {
                            if (chessBoard[k][j] == status.EMPTY)
                                break;
                            if (chessBoard[k][j] == status.WHITE) {
                                permittedMovements.add(new coordinate(i - 1, j));
                                break;
                            }
                        }
                    }
                }

                if (chessBoard[i + 1][j] == status.EMPTY) {
                    if (chessBoard[i - 1][j] == status.WHITE) {
                        permittedMovements.add(new coordinate(i + 1, j));
                    } else if (chessBoard[i - 1][j] == status.BLACK) {
                        for (int k = i - 1; k >= 0; k--) {
                            if (chessBoard[k][j] == status.EMPTY)
                                break;
                            if (chessBoard[k][j] == status.WHITE) {
                                permittedMovements.add(new coordinate(i + 1, j));
                                break;
                            }
                        }
                    }
                }
            }
        }

        if (permittedMovements.size() == 0) {
            flag = false;
        }

        return flag;
    }

}
