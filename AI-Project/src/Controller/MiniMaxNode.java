package Controller;

import java.util.ArrayList;

public class MiniMaxNode {

    status[][] board = new status[8][8];

    MiniMaxNode parent;
    ArrayList<MiniMaxNode> children = new ArrayList<>();

    boolean isMax;    //if true: Max and black else: Min and white

    Integer alpha, beta;

    ArrayList<coordinate> blackCoordinates = new ArrayList<>();
    ArrayList<coordinate> whiteCoordinates = new ArrayList<>();
    ArrayList<coordinate> permittedMovements = new ArrayList<>();

    // be in feauture yek meghdare defalt e hasel az machine learning ra midahim
    Individual feature_individual;

    public int pruning(int depth, int alpha, int beta) {
        int limit_depth = 10;

        if (depth == limit_depth) {
            if (this.isMax) {
                this.alpha = this.getHeuristic();
            } else this.beta = this.getHeuristic();

            return this.getHeuristic();
        }
        if (this.children.size() == 0) {

            if (this.isMax) {
                this.alpha = this.getHeuristic();
            } else this.beta = this.getHeuristic();

            return this.getHeuristic();
        }
        if (this.isMax) {
            int currentAlpha = Integer.MIN_VALUE;
            for (MiniMaxNode child : this.children) {
                child.beta = child.pruning(depth + 1, alpha, beta);
                currentAlpha = Math.max(currentAlpha, child.beta);
                alpha = Math.max(alpha, currentAlpha);
                if (alpha >= beta) {
                    return alpha;
                }
            }
            return currentAlpha;
        }
        // isMax = false;
        int currentBeta = Integer.MAX_VALUE;
        for (MiniMaxNode child : this.children) {
            child.alpha = this.pruning(depth + 1, alpha, beta);
            currentBeta = Math.min(currentBeta, child.alpha);
            beta = Math.min(beta, currentBeta);
            if (beta <= alpha) {
                return beta;
            }
        }
        return currentBeta;
    }

    public int getHeuristic() {

        status ai_status = status.BLACK;
        status person_status = status.WHITE;

        // d baraye arzeshe khaneye haye mokhtalefe safhe
        // p baraye parity va tedade mohreha
        // f baraye frontier disk ha
        // c baraye corners
        // l baraye corner closeness
        // m baraye
        double d = 0, p = 0, f = 0, c = 0, l = 0, m = 0;

        int ai_tiles = 0, person_tiles = 0;
        int ai_front = 0, person_front = 0;

        // khanye haye mokhtalef az nazare value va arzesh
        int[][] value = {
                {20, -3, 11, 8, 8, 11, -3, 20},
                {-3, -7, -4, 1, 1, -4, -7, -3},
                {11, -4, 2, 2, 2, 2, -4, 11},
                {8, 1, 2, -3, -3, 2, 1, 8},
                {8, 1, 2, -3, -3, 2, 1, 8},
                {11, -4, 2, 2, 2, 2, -4, 11},
                {-3, -7, -4, 1, 1, -4, -7, -3},
                {20, -3, 11, 8, 8, 11, -3, 20}
        };

        // baraye 8 khanye atrafe har khane
        int[] x = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] y = {0, 1, 1, 1, 0, -1, -1, -1};

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                if (this.board[i][j] == ai_status) {
                    d += value[i][j];
                    ai_tiles++;
                } else if (this.board[i][j] == person_status) {
                    d -= value[i][j];
                    person_tiles++;
                }

                if (this.board[i][j] != status.EMPTY) {
                    for (int k = 0; k < 8; k++) {
                        int x_neighbor = i + x[k];
                        int y_neighbor = j + y[k];
                        if (x_neighbor >= 0 && x_neighbor < 8
                                && y_neighbor >= 0 && y_neighbor < 8
                                && this.board[x_neighbor][y_neighbor] == status.EMPTY) {
                            if (this.board[i][j] == ai_status) ai_front++;
                            else person_front++;
                            break;
                        }
                    }
                }

            }
        }
        // parity ra be darsad hesab mikonim
        if (ai_tiles + person_tiles != 0) {
            if (ai_tiles > person_tiles)
                p = (100 * ai_tiles) / (ai_tiles + person_tiles);
            if (ai_tiles < person_tiles)
                p = -(100 * person_tiles) / (ai_tiles + person_tiles);
            if (ai_tiles == person_tiles)
                p = 0;
        }

        // emtiyaze frontier ra be darsad hesab mikonim
        if (ai_front + person_front != 0) {
            if (ai_front > person_front)
                f = -(100 * ai_front) / (ai_front + person_front);
            if (ai_tiles < person_tiles)
                f = (100 * person_front) / (ai_front + person_front);
            if (ai_front == person_front)
                f = 0;
        }

        // ai_tiles va person_tiles ra dobare 0 mikonim
        // corners
        ai_tiles = person_tiles = 0;
        if (this.board[0][0] == ai_status)
            ai_tiles++;
        else if (this.board[0][0] == person_status)
            person_tiles++;
        if (this.board[0][7] == ai_status)
            ai_tiles++;
        else if (this.board[0][7] == person_status)
            person_tiles++;
        if (this.board[7][0] == ai_status)
            ai_tiles++;
        else if (this.board[7][0] == person_status)
            person_tiles++;
        if (this.board[7][7] == ai_status)
            ai_tiles++;
        else if (this.board[7][7] == person_status)
            person_tiles++;
        c = 25 * (ai_tiles - person_tiles);

        // ai_tiles va person_tiles ra dobare 0 mikonim
        // corner closeness
        ai_tiles = person_tiles = 0;
        if (this.board[0][0] == status.EMPTY) {
            if (this.board[0][1] == ai_status)
                ai_tiles++;
            else if (this.board[0][1] == person_status)
                person_tiles++;
            if (this.board[1][1] == ai_status) ai_tiles++;
            else if (this.board[1][1] == person_status)
                person_tiles++;
            if (this.board[1][0] == ai_status)
                ai_tiles++;
            else if (this.board[1][0] == person_status)
                person_tiles++;
        }
        if (this.board[0][7] == status.EMPTY) {
            if (this.board[0][6] == ai_status)
                ai_tiles++;
            else if (this.board[0][6] == person_status)
                person_tiles++;
            if (this.board[1][6] == ai_status)
                ai_tiles++;
            else if (this.board[1][6] == person_status)
                person_tiles++;
            if (this.board[1][7] == ai_status)
                ai_tiles++;
            else if (this.board[1][7] == person_status)
                person_tiles++;
        }
        if (this.board[7][0] == status.EMPTY) {
            if (this.board[7][1] == ai_status)
                ai_tiles++;
            else if (this.board[7][1] == person_status)
                person_tiles++;
            if (this.board[6][1] == ai_status)
                ai_tiles++;
            else if (this.board[6][1] == person_status)
                person_tiles++;
            if (this.board[6][0] == ai_status)
                ai_tiles++;
            else if (this.board[6][0] == person_status)
                person_tiles++;
        }
        if (this.board[7][7] == status.EMPTY) {
            if (this.board[6][7] == ai_status)
                ai_tiles++;
            else if (this.board[6][7] == person_status)
                person_tiles++;
            if (this.board[6][6] == ai_status)
                ai_tiles++;
            else if (this.board[6][6] == person_status)
                person_tiles++;
            if (this.board[7][6] == ai_status)
                ai_tiles++;
            else if (this.board[7][6] == person_status)
                person_tiles++;
        }
        l = -12 * (ai_tiles - person_tiles);

        // mobility
        this.calPlaces(this.isMax);
        ai_tiles = this.permittedMovements.size();
        this.calPlaces(!isMax);
        person_tiles = this.permittedMovements.size();
        this.calPlaces(this.isMax);

        if (ai_tiles + person_tiles != 0) {
            if (ai_tiles > person_tiles)
                m = (100.0 * ai_tiles) / (ai_tiles + person_tiles);
            else if (ai_tiles < person_tiles)
                m = -(100.0 * person_tiles) / (ai_tiles + person_tiles);
            else
                m = 0;
        }

//        int score = (int) ((this.feature_individual.genes[0] * d) + (this.feature_individual.genes[1] * p) +
//                (this.feature_individual.genes[2] * f) + (this.feature_individual.genes[3] * c) +
//                (this.feature_individual.genes[4] * l) + (this.feature_individual.genes[5] * m));

        int score =(int) (10 * d + 20 * p + 97 * f + 832 * c + 289 * l + 93 * m);
        return score;
    }

    boolean calPlaces(boolean isMax) {

        boolean flag = true;
        if (blackCoordinates.size() + whiteCoordinates.size() >= 64)
            flag = false;

        permittedMovements.clear();

        if (isMax) {
            // it is black player
            for (coordinate white_house : whiteCoordinates) {
                int i = white_house.i;
                int j = white_house.j;

                // exception forms

                // halat hayi ke dar goosheye safhe hastand
                if ((i == 0 && j == 0) || (i == 7 && j == 0) || (i == 0 && j == 7) || (i == 7 && j == 7)) {
                    continue;
                }

                // halat hayi ke dar satr ya sotoon ebteda ya entehayi hastand vali gooshe nistand
                if (j == 0) {

                    if (board[i - 1][0] == status.EMPTY && board[i + 1][0] == status.EMPTY) {
                        continue;
                    }
                    if (board[i - 1][0] != status.EMPTY && board[i + 1][0] != status.EMPTY) {
                        continue;
                    }
                    if (board[i - 1][0] == status.BLACK) {
                        permittedMovements.add(new coordinate(i + 1, 0));
                        continue;
                    }
                    if (board[i + 1][0] == status.BLACK) {
                        permittedMovements.add(new coordinate(i - 1, 0));
                        continue;
                    }
                    if (board[i - 1][0] == status.WHITE) {
                        for (int k = i - 1; k >= 0; k--) {
                            if (board[k][0] == status.EMPTY)
                                break;
                            if (board[k][0] == status.BLACK) {
                                permittedMovements.add(new coordinate(i + 1, 0));
                                break;
                            }
                        }
                        continue;
                    }

                    if (board[i + 1][0] == status.WHITE) {
                        for (int k = i + 1; k <= 7; k++) {
                            if (board[k][0] == status.EMPTY)
                                break;
                            if (board[k][0] == status.BLACK) {
                                permittedMovements.add(new coordinate(i - 1, 0));
                                break;
                            }
                        }
                        continue;
                    }
                }

                if (j == 7) {

                    if (board[i - 1][7] == status.EMPTY && board[i + 1][7] == status.EMPTY) {
                        continue;
                    }
                    if (board[i - 1][7] != status.EMPTY && board[i + 1][7] != status.EMPTY) {
                        continue;
                    }
                    if (board[i - 1][7] == status.BLACK) {
                        permittedMovements.add(new coordinate(i + 1, 7));
                        continue;
                    }
                    if (board[i + 1][7] == status.BLACK) {
                        permittedMovements.add(new coordinate(i - 1, 7));
                        continue;
                    }
                    if (board[i - 1][7] == status.WHITE) {
                        for (int k = i - 1; k >= 0; k--) {
                            if (board[k][7] == status.EMPTY)
                                break;
                            if (board[k][7] == status.BLACK) {
                                permittedMovements.add(new coordinate(i + 1, 7));
                                break;
                            }
                        }
                        continue;
                    }

                    if (board[i + 1][7] == status.WHITE) {
                        for (int k = i + 1; k <= 7; k++) {
                            if (board[k][7] == status.EMPTY)
                                break;
                            if (board[k][7] == status.BLACK) {
                                permittedMovements.add(new coordinate(i - 1, 7));
                                break;
                            }
                        }
                        continue;
                    }
                }

                if (i == 0) {

                    if (board[0][j + 1] == status.EMPTY && board[0][j - 1] == status.EMPTY) {
                        continue;
                    }
                    if (board[0][j + 1] != status.EMPTY && board[0][j - 1] != status.EMPTY) {
                        continue;
                    }
                    if (board[0][j + 1] == status.BLACK) {
                        permittedMovements.add(new coordinate(0, j - 1));
                        continue;
                    }
                    if (board[0][j - 1] == status.BLACK) {
                        permittedMovements.add(new coordinate(0, j + 1));
                        continue;
                    }
                    if (board[0][j - 1] == status.WHITE) {
                        for (int k = j - 1; k >= 0; k--) {
                            if (board[0][k] == status.EMPTY)
                                break;
                            if (board[0][k] == status.BLACK) {
                                permittedMovements.add(new coordinate(0, j + 1));
                                break;
                            }
                        }
                        continue;
                    }

                    if (board[0][j + 1] == status.WHITE) {
                        for (int k = j + 1; k <= 7; k++) {
                            if (board[0][k] == status.EMPTY)
                                break;
                            if (board[0][k] == status.BLACK) {
                                permittedMovements.add(new coordinate(0, j - 1));
                                break;
                            }
                        }
                        continue;
                    }
                }

                if (i == 7) {

                    if (board[7][j + 1] == status.EMPTY && board[7][j - 1] == status.EMPTY) {
                        continue;
                    }
                    if (board[7][j + 1] != status.EMPTY && board[7][j - 1] != status.EMPTY) {
                        continue;
                    }
                    if (board[7][j + 1] == status.BLACK) {
                        permittedMovements.add(new coordinate(7, j - 1));
                        continue;
                    }
                    if (board[7][j - 1] == status.BLACK) {
                        permittedMovements.add(new coordinate(7, j + 1));
                        continue;
                    }
                    if (board[7][j - 1] == status.WHITE) {
                        for (int k = 0; k < j - 1; k++) {
                            if (board[7][k] == status.EMPTY)
                                break;
                            if (board[7][k] == status.BLACK) {
                                permittedMovements.add(new coordinate(7, j + 1));
                                break;
                            }
                        }
                        continue;
                    }

                    if (board[7][j + 1] == status.WHITE) {
                        for (int k = 7; k > j + 1; k--) {
                            if (board[7][k] == status.EMPTY)
                                break;
                            if (board[7][k] == status.BLACK) {
                                permittedMovements.add(new coordinate(7, j - 1));
                                break;
                            }
                        }
                        continue;
                    }
                }
                // normal forms

                // ghotri ha
                if (board[i - 1][j - 1] == status.EMPTY) {
                    if (board[i + 1][j + 1] == status.BLACK) {
                        permittedMovements.add(new coordinate(i - 1, j - 1));
                    } else if (board[i + 1][j + 1] == status.WHITE) {
                        int x;
                        int y;
                        ArrayList<coordinate> thingsToBeAdded = new ArrayList<coordinate>();
                        for (x = i + 1, y = j + 1; (x <= 7 && y <= 7); x++, y++) {
                            if (board[x][y] == status.EMPTY)
                                break;
                            if (board[x][y] == status.BLACK) {
                                thingsToBeAdded.add(new coordinate(i - 1, j - 1));
                                break;
                            }
                        }
                        permittedMovements.addAll(thingsToBeAdded);
                    }
                }

                if (board[i + 1][j - 1] == status.EMPTY) {
                    if (board[i - 1][j + 1] == status.BLACK) {
                        permittedMovements.add(new coordinate(i + 1, j - 1));
                    } else if (board[i - 1][j + 1] == status.WHITE) {
                        int x;
                        int y;
                        ArrayList<coordinate> thingsToBeAdded = new ArrayList<coordinate>();
                        for (x = i - 1, y = j + 1; (x >= 0 && y <= 7); x--, y++) {
                            if (board[x][y] == status.EMPTY)
                                break;
                            if (board[x][y] == status.BLACK) {
                                thingsToBeAdded.add(new coordinate(i + 1, j - 1));
                                break;
                            }
                        }
                        permittedMovements.addAll(thingsToBeAdded);
                    }
                }

                if (board[i - 1][j + 1] == status.EMPTY) {
                    if (board[i + 1][j - 1] == status.BLACK) {
                        permittedMovements.add(new coordinate(i - 1, j + 1));
                    } else if (board[i + 1][j - 1] == status.WHITE) {
                        int x;
                        int y;
                        for (x = i + 1, y = j - 1; (x <= 7 && y >= 0); x++, y--) {
                            if (board[x][y] == status.EMPTY)
                                break;
                            if (board[x][y] == status.BLACK) {
                                permittedMovements.add(new coordinate(i - 1, j + 1));
                                break;
                            }
                        }
                    }
                }

                if (board[i + 1][j + 1] == status.EMPTY) {
                    if (board[i - 1][j - 1] == status.BLACK) {
                        permittedMovements.add(new coordinate(i + 1, j + 1));
                    } else if (board[i - 1][j - 1] == status.WHITE) {
                        int x;
                        int y;
                        for (x = i - 1, y = j - 1; (x >= 0 && y >= 0); x--, y--) {
                            if (board[x][y] == status.EMPTY)
                                break;
                            if (board[x][y] == status.BLACK) {
                                permittedMovements.add(new coordinate(i + 1, j + 1));
                                break;
                            }
                        }
                    }
                }

                // vertical
                if (board[i][j - 1] == status.EMPTY) {
                    if (board[i][j + 1] == status.BLACK) {
                        permittedMovements.add(new coordinate(i, j - 1));
                    } else if (board[i][j + 1] == status.WHITE) {
                        for (int k = j + 1; k <= 7; k++) {
                            if (board[i][k] == status.EMPTY)
                                break;
                            if (board[i][k] == status.BLACK) {
                                permittedMovements.add(new coordinate(i, j - 1));
                                break;
                            }
                        }
                    }
                }

                if (board[i][j + 1] == status.EMPTY) {
                    if (board[i][j - 1] == status.BLACK) {
                        permittedMovements.add(new coordinate(i, j + 1));
                    } else if (board[i][j - 1] == status.WHITE) {
                        for (int k = j - 1; k >= 0; k--) {
                            if (board[i][k] == status.EMPTY)
                                break;
                            if (board[i][k] == status.BLACK) {
                                permittedMovements.add(new coordinate(i, j + 1));
                                break;
                            }
                        }
                    }
                }

                // horizontal
                if (board[i - 1][j] == status.EMPTY) {
                    if (board[i + 1][j] == status.BLACK) {
                        permittedMovements.add(new coordinate(i - 1, j));
                    } else if (board[i + 1][j] == status.WHITE) {
                        for (int k = i + 1; k <= 7; k++) {
                            if (board[k][j] == status.EMPTY)
                                break;
                            if (board[k][j] == status.BLACK) {
                                permittedMovements.add(new coordinate(i - 1, j));
                                break;
                            }
                        }
                    }
                }

                if (board[i + 1][j] == status.EMPTY) {
                    if (board[i - 1][j] == status.BLACK) {
                        permittedMovements.add(new coordinate(i + 1, j));
                    } else if (board[i - 1][j] == status.WHITE) {
                        for (int k = i - 1; k >= 0; k--) {
                            if (board[k][j] == status.EMPTY)
                                break;
                            if (board[k][j] == status.BLACK) {
                                permittedMovements.add(new coordinate(i + 1, j));
                                break;
                            }
                        }
                    }
                }
            }

        } else {
            for (coordinate black_house : blackCoordinates
            ) {
                int i = black_house.i;
                int j = black_house.j;

                // exception forms

                // halat hayi ke dar goosheye safhe hastand
                if ((i == 0 && j == 0) || (i == 7 && j == 0) || (i == 0 && j == 7) || (i == 7 && j == 7)) {
                    continue;
                }

                // halat hayi ke dar satr ya sotoon ebteda ya entehayi hastand vali gooshe nistand
                if (j == 0) {

                    if (board[i - 1][0] == status.EMPTY && board[i + 1][0] == status.EMPTY) {
                        continue;
                    }
                    if (board[i - 1][0] != status.EMPTY && board[i + 1][0] != status.EMPTY) {
                        continue;
                    }
                    if (board[i - 1][0] == status.WHITE) {
                        permittedMovements.add(new coordinate(i + 1, 0));
                        continue;
                    }
                    if (board[i + 1][0] == status.WHITE) {
                        permittedMovements.add(new coordinate(i - 1, 0));
                        continue;
                    }
                    if (board[i - 1][0] == status.BLACK) {
                        for (int k = i - 1; k >= 0; k--) {
                            if (board[k][0] == status.EMPTY)
                                break;
                            if (board[k][0] == status.WHITE) {
                                permittedMovements.add(new coordinate(i + 1, 0));
                                break;
                            }
                        }
                        continue;
                    }

                    if (board[i + 1][0] == status.BLACK) {
                        for (int k = i + 1; k <= 7; k++) {
                            if (board[k][0] == status.EMPTY)
                                break;
                            if (board[k][0] == status.WHITE) {
                                permittedMovements.add(new coordinate(i - 1, 0));
                                break;
                            }
                        }
                        continue;
                    }
                }

                if (j == 7) {

                    if (board[i - 1][7] == status.EMPTY && board[i + 1][7] == status.EMPTY) {
                        continue;
                    }
                    if (board[i - 1][7] != status.EMPTY && board[i + 1][7] != status.EMPTY) {
                        continue;
                    }
                    if (board[i - 1][7] == status.WHITE) {
                        permittedMovements.add(new coordinate(i + 1, 7));
                        continue;
                    }
                    if (board[i + 1][7] == status.WHITE) {
                        permittedMovements.add(new coordinate(i - 1, 7));
                        continue;
                    }
                    if (board[i - 1][7] == status.BLACK) {
                        for (int k = i - 1; k >= 0; k--) {
                            if (board[k][7] == status.EMPTY)
                                break;
                            if (board[k][7] == status.WHITE) {
                                permittedMovements.add(new coordinate(i + 1, 7));
                                break;
                            }
                        }
                        continue;
                    }

                    if (board[i + 1][7] == status.BLACK) {
                        for (int k = i + 1; k <= 7; k++) {
                            if (board[k][7] == status.EMPTY)
                                break;
                            if (board[k][7] == status.WHITE) {
                                permittedMovements.add(new coordinate(i - 1, 7));
                                break;
                            }
                        }
                        continue;
                    }
                }

                if (i == 0) {

                    if (board[0][j + 1] == status.EMPTY && board[0][j - 1] == status.EMPTY) {
                        continue;
                    }
                    if (board[0][j + 1] != status.EMPTY && board[0][j - 1] != status.EMPTY) {
                        continue;
                    }
                    if (board[0][j + 1] == status.WHITE) {
                        permittedMovements.add(new coordinate(0, j - 1));
                        continue;
                    }
                    if (board[0][j - 1] == status.WHITE) {
                        permittedMovements.add(new coordinate(0, j + 1));
                        continue;
                    }
                    if (board[0][j - 1] == status.BLACK) {
                        for (int k = j - 1; k >= 0; k--) {
                            if (board[0][k] == status.EMPTY)
                                break;
                            if (board[0][k] == status.WHITE) {
                                permittedMovements.add(new coordinate(0, j + 1));
                                break;
                            }
                        }
                        continue;
                    }

                    if (board[0][j + 1] == status.BLACK) {
                        for (int k = j + 1; k <= 7; k++) {
                            if (board[0][k] == status.EMPTY)
                                break;
                            if (board[0][k] == status.WHITE) {
                                permittedMovements.add(new coordinate(0, j - 1));
                                break;
                            }
                        }
                        continue;
                    }
                }

                if (i == 7) {

                    if (board[7][j + 1] == status.EMPTY && board[7][j - 1] == status.EMPTY) {
                        continue;
                    }
                    if (board[7][j + 1] != status.EMPTY && board[7][j - 1] != status.EMPTY) {
                        continue;
                    }
                    if (board[7][j + 1] == status.WHITE) {
                        permittedMovements.add(new coordinate(7, j - 1));
                        continue;
                    }
                    if (board[7][j - 1] == status.WHITE) {
                        permittedMovements.add(new coordinate(7, j + 1));
                        continue;
                    }
                    if (board[7][j - 1] == status.BLACK) {
                        for (int k = 0; k < j - 1; k++) {
                            if (board[7][k] == status.EMPTY)
                                break;
                            if (board[7][k] == status.WHITE) {
                                permittedMovements.add(new coordinate(7, j + 1));
                                break;
                            }
                        }
                        continue;
                    }

                    if (board[7][j + 1] == status.BLACK) {
                        for (int k = 7; k > j + 1; k--) {
                            if (board[7][k] == status.EMPTY)
                                break;
                            if (board[7][k] == status.WHITE) {
                                permittedMovements.add(new coordinate(7, j - 1));
                                break;
                            }
                        }
                        continue;
                    }
                }
                // normal forms

                // ghotri ha
                if (board[i - 1][j - 1] == status.EMPTY) {
                    if (board[i + 1][j + 1] == status.WHITE) {
                        permittedMovements.add(new coordinate(i - 1, j - 1));
                    } else if (board[i + 1][j + 1] == status.BLACK) {
                        int x;
                        int y;
                        for (x = i + 1, y = j + 1; (x <= 7 && y <= 7); x++, y++) {
                            if (board[x][y] == status.EMPTY)
                                break;
                            if (board[x][y] == status.WHITE) {
                                permittedMovements.add(new coordinate(i - 1, j - 1));
                                break;
                            }
                        }
                    }
                }

                if (board[i + 1][j - 1] == status.EMPTY) {
                    if (board[i - 1][j + 1] == status.WHITE) {
                        permittedMovements.add(new coordinate(i + 1, j - 1));
                    } else if (board[i - 1][j + 1] == status.BLACK) {
                        int x;
                        int y;
                        for (x = i - 1, y = j + 1; (x >= 0 && y <= 7); x--, y++) {
                            if (board[x][y] == status.EMPTY)
                                break;
                            if (board[x][y] == status.WHITE) {
                                permittedMovements.add(new coordinate(i + 1, j - 1));
                                break;
                            }
                        }
                    }
                }

                if (board[i - 1][j + 1] == status.EMPTY) {
                    if (board[i + 1][j - 1] == status.WHITE) {
                        permittedMovements.add(new coordinate(i - 1, j + 1));
                    } else if (board[i + 1][j - 1] == status.BLACK) {
                        int x;
                        int y;
                        for (x = i + 1, y = j - 1; (x <= 7 && y >= 0); x++, y--) {
                            if (board[x][y] == status.EMPTY)
                                break;
                            if (board[x][y] == status.WHITE) {
                                permittedMovements.add(new coordinate(i - 1, j + 1));
                                break;
                            }
                        }
                    }
                }

                if (board[i + 1][j + 1] == status.EMPTY) {
                    if (board[i - 1][j - 1] == status.WHITE) {
                        permittedMovements.add(new coordinate(i + 1, j + 1));
                    } else if (board[i - 1][j - 1] == status.BLACK) {
                        int x;
                        int y;
                        for (x = i - 1, y = j - 1; (x >= 0 && y >= 0); x--, y--) {
                            if (board[x][y] == status.EMPTY)
                                break;
                            if (board[x][y] == status.WHITE) {
                                permittedMovements.add(new coordinate(i + 1, j + 1));
                                break;
                            }
                        }
                    }
                }

                // vertical
                if (board[i][j - 1] == status.EMPTY) {
                    if (board[i][j + 1] == status.WHITE) {
                        permittedMovements.add(new coordinate(i, j - 1));
                    } else if (board[i][j + 1] == status.BLACK) {
                        for (int k = j + 1; k <= 7; k++) {
                            if (board[i][k] == status.EMPTY)
                                break;
                            if (board[i][k] == status.WHITE) {
                                permittedMovements.add(new coordinate(i, j - 1));
                                break;
                            }
                        }
                    }
                }

                if (board[i][j + 1] == status.EMPTY) {
                    if (board[i][j - 1] == status.WHITE) {
                        permittedMovements.add(new coordinate(i, j + 1));
                    } else if (board[i][j - 1] == status.BLACK) {
                        for (int k = j - 1; k >= 0; k--) {
                            if (board[i][k] == status.EMPTY)
                                break;
                            if (board[i][k] == status.WHITE) {
                                permittedMovements.add(new coordinate(i, j + 1));
                                break;
                            }
                        }
                    }
                }

                // horizontal
                if (board[i - 1][j] == status.EMPTY) {
                    if (board[i + 1][j] == status.WHITE) {
                        permittedMovements.add(new coordinate(i - 1, j));
                    } else if (board[i + 1][j] == status.BLACK) {
                        for (int k = i + 1; k <= 7; k++) {
                            if (board[k][j] == status.EMPTY)
                                break;
                            if (board[k][j] == status.WHITE) {
                                permittedMovements.add(new coordinate(i - 1, j));
                                break;
                            }
                        }
                    }
                }

                if (board[i + 1][j] == status.EMPTY) {
                    if (board[i - 1][j] == status.WHITE) {
                        permittedMovements.add(new coordinate(i + 1, j));
                    } else if (board[i - 1][j] == status.BLACK) {
                        for (int k = i - 1; k >= 0; k--) {
                            if (board[k][j] == status.EMPTY)
                                break;
                            if (board[k][j] == status.WHITE) {
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

    // functions for white player to change status board
    public void horizontal_right_wp(int x, int y) {
        if (x == 7)
            return;

        if (board[x + 1][y] == status.BLACK) {
            // i e zoodtarin khaneye sefid ke mibinad
            int i_white_cell = -1;
            boolean change = false;
            for (int k = x + 1; k <= 7; k++) {
                if (board[k][y] == status.EMPTY)
                    break;
                if (board[k][y] == status.WHITE) {
                    i_white_cell = k;
                    change = true;
                    break;
                }
            }
            if (change) {
                for (int k = x + 1; k < i_white_cell; k++) {
                    board[k][y] = status.WHITE;
                }
            }
        }
    }

    public void horizontal_left_wp(int x, int y) {
        if (x == 0)
            return;

        if (board[x - 1][y] == status.BLACK) {
            // i e zoodtarin khaneye sefid ke mibinad
            int i_white_cell = -1;
            boolean change = false;
            for (int k = x - 1; k >= 0; k--) {
                if (board[k][y] == status.EMPTY)
                    break;
                if (board[k][y] == status.WHITE) {
                    change = true;
                    i_white_cell = k;
                    break;
                }
            }
            if (change) {
                for (int k = x - 1; k > i_white_cell; k--) {
                    board[k][y] = status.WHITE;
                }
            }
        }
    }

    public void vertical_down_wp(int x, int y) {
        if (y == 7)
            return;

        if (board[x][y + 1] == status.BLACK) {
            // j e zoodtarin khaneye sefid ke mibinad
            int j_white_cell = -1;
            boolean change = false;
            for (int k = y + 1; k <= 7; k++) {
                if (board[x][k] == status.EMPTY)
                    break;
                if (board[x][k] == status.WHITE) {
                    change = true;
                    j_white_cell = k;
                    break;
                }
            }
            if (change) {
                for (int k = y + 1; k < j_white_cell; k++) {
                    board[x][k] = status.WHITE;
                }
            }
        }
    }

    public void vertical_up_wp(int x, int y) {
        if (y == 0)
            return;

        if (board[x][y - 1] == status.BLACK) {
            // j e zoodtarin khaneye sefid ke mibinad
            int j_white_cell = -1;
            boolean change = false;
            for (int k = y - 1; k >= 0; k--) {
                if (board[x][k] == status.EMPTY)
                    break;
                if (board[x][k] == status.WHITE) {
                    change = true;
                    j_white_cell = k;
                    break;
                }
            }
            if (change) {
                for (int k = y - 1; k > j_white_cell; k--) {
                    board[x][k] = status.WHITE;
                }
            }
        }
    }

    public void diagonal_right_down_wp(int x, int y) {
        if (x == 7 || y == 7)
            return;

        if (board[x + 1][y + 1] == status.BLACK) {
            // i , j e zoodtarin khaneye sefid ke mibinad
            int i_white_cell = -1;
            int j_white_cell = -1;
            boolean change = false;
            for (int i = x + 1, j = y + 1; i <= 7 && j <= 7; i++, j++) {
                if (board[i][j] == status.EMPTY)
                    break;
                if (board[i][j] == status.WHITE) {
                    change = true;
                    i_white_cell = i;
                    j_white_cell = j;
                    break;
                }
            }
            if (change) {
                for (int i = x + 1, j = y + 1; i < i_white_cell && j < j_white_cell; i++, j++) {
                    board[i][j] = status.WHITE;
                }
            }
        }
    }

    public void diagonal_right_up_wp(int x, int y) {
        if (x == 7 || y == 0)
            return;

        if (board[x + 1][y - 1] == status.BLACK) {
            // i , j e zoodtarin khaneye sefid ke mibinad
            int i_white_cell = -1;
            int j_white_cell = -1;
            boolean change = false;
            for (int i = x + 1, j = y - 1; i <= 7 && j >= 0; i++, j--) {
                if (board[i][j] == status.EMPTY)
                    break;
                if (board[i][j] == status.WHITE) {
                    change = true;
                    i_white_cell = i;
                    j_white_cell = j;
                    break;
                }
            }
            if (change) {
                for (int i = x + 1, j = y - 1; i < i_white_cell && j > j_white_cell; i++, j--) {
                    board[i][j] = status.WHITE;
                }
            }
        }
    }

    public void diagonal_left_down_wp(int x, int y) {
        if (x == 0 || y == 7)
            return;

        if (board[x - 1][y + 1] == status.BLACK) {
            // i , j e zoodtarin khaneye sefid ke mibinad
            int i_white_cell = -1;
            int j_white_cell = -1;
            boolean change = false;
            for (int i = x - 1, j = y + 1; i >= 0 && j <= 7; i--, j++) {
                if (board[i][j] == status.EMPTY)
                    break;
                if (board[i][j] == status.WHITE) {
                    change = true;
                    i_white_cell = i;
                    j_white_cell = j;
                    break;
                }
            }
            if (change) {
                for (int i = x - 1, j = y + 1; i > i_white_cell && j < j_white_cell; i--, j++) {
                    board[i][j] = status.WHITE;
                }
            }
        }
    }

    public void diagonal_left_up_wp(int x, int y) {
        if (x == 0 || y == 0)
            return;

        if (board[x - 1][y - 1] == status.BLACK) {
            // i , j e zoodtarin khaneye sefid ke mibinad
            int i_white_cell = -1;
            int j_white_cell = -1;
            boolean change = false;
            for (int i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--) {
                if (board[i][j] == status.EMPTY)
                    break;
                if (board[i][j] == status.WHITE) {
                    change = true;
                    i_white_cell = i;
                    j_white_cell = j;
                    break;
                }
            }
            if (change) {
                for (int i = x - 1, j = y - 1; i > i_white_cell && j > j_white_cell; i--, j--) {
                    board[i][j] = status.WHITE;
                }
            }
        }
    }

    // functions for black player to change status board
    public void horizontal_right_bp(int x, int y) {
        if (x == 7)
            return;

        if (board[x + 1][y] == status.WHITE) {
            // i e zoodtarin khaneye meshki ke mibinad
            int i_black_cell = -1;
            boolean change = false;
            for (int k = x + 1; k <= 7; k++) {
                if (board[k][y] == status.EMPTY)
                    break;
                if (board[k][y] == status.BLACK) {
                    i_black_cell = k;
                    change = true;
                    break;
                }
            }
            if (change) {
                for (int k = x + 1; k < i_black_cell; k++) {
                    board[k][y] = status.BLACK;
                }
            }
        }
    }

    public void horizontal_left_bp(int x, int y) {
        if (x == 0)
            return;

        if (board[x - 1][y] == status.WHITE) {
            // i e zoodtarin khaneye meshki ke mibinad
            int i_black_cell = -1;
            boolean change = false;
            for (int k = x - 1; k >= 0; k--) {
                if (board[k][y] == status.EMPTY)
                    break;
                if (board[k][y] == status.BLACK) {
                    change = true;
                    i_black_cell = k;
                    break;
                }
            }
            if (change) {
                for (int k = x - 1; k > i_black_cell; k--) {
                    board[k][y] = status.BLACK;
                }
            }
        }
    }

    public void vertical_down_bp(int x, int y) {
        if (y == 7)
            return;

        if (board[x][y + 1] == status.WHITE) {
            // j e zoodtarin khaneye meshki ke mibinad
            int j_black_cell = -1;
            boolean change = false;
            for (int k = y + 1; k <= 7; k++) {
                if (board[x][k] == status.EMPTY)
                    break;
                if (board[x][k] == status.BLACK) {
                    change = true;
                    j_black_cell = k;
                    break;
                }
            }
            if (change) {
                for (int k = y + 1; k < j_black_cell; k++) {
                    board[x][k] = status.BLACK;
                }
            }
        }
    }

    public void vertical_up_bp(int x, int y) {
        if (y == 0)
            return;

        if (board[x][y - 1] == status.WHITE) {
            // j e zoodtarin khaneye meshki ke mibinad
            int j_black_cell = -1;
            boolean change = false;
            for (int k = y - 1; k >= 0; k--) {
                if (board[x][k] == status.EMPTY)
                    break;
                if (board[x][k] == status.BLACK) {
                    change = true;
                    j_black_cell = k;
                    break;
                }
            }
            if (change) {
                for (int k = y - 1; k > j_black_cell; k--) {
                    board[x][k] = status.BLACK;
                }
            }
        }
    }

    public void diagonal_right_down_bp(int x, int y) {
        if (x == 7 || y == 7)
            return;

        if (board[x + 1][y + 1] == status.WHITE) {
            // i , j e zoodtarin khaneye meshki ke mibinad
            int i_black_cell = -1;
            int j_black_cell = -1;
            boolean change = false;
            for (int i = x + 1, j = y + 1; i <= 7 && j <= 7; i++, j++) {
                if (board[i][j] == status.EMPTY)
                    break;
                if (board[i][j] == status.BLACK) {
                    change = true;
                    i_black_cell = i;
                    j_black_cell = j;
                    break;
                }
            }
            if (change) {
                for (int i = x + 1, j = y + 1; i < i_black_cell && j < j_black_cell; i++, j++) {
                    board[i][j] = status.BLACK;
                }
            }
        }
    }

    public void diagonal_right_up_bp(int x, int y) {
        if (x == 7 || y == 0)
            return;

        if (board[x + 1][y - 1] == status.WHITE) {
            // i , j e zoodtarin khaneye meshki ke mibinad
            int i_black_cell = -1;
            int j_black_cell = -1;
            boolean change = false;
            for (int i = x + 1, j = y - 1; i <= 7 && j >= 0; i++, j--) {
                if (board[i][j] == status.EMPTY)
                    break;
                if (board[i][j] == status.BLACK) {
                    change = true;
                    i_black_cell = i;
                    j_black_cell = j;
                    break;
                }
            }
            if (change) {
                for (int i = x + 1, j = y - 1; i < i_black_cell && j > j_black_cell; i++, j--) {
                    board[i][j] = status.BLACK;
                }
            }
        }
    }

    public void diagonal_left_down_bp(int x, int y) {
        if (x == 0 || y == 7)
            return;

        if (board[x - 1][y + 1] == status.WHITE) {
            // i , j e zoodtarin khaneye meshki ke mibinad
            int i_black_cell = -1;
            int j_black_cell = -1;
            boolean change = false;
            for (int i = x - 1, j = y + 1; i >= 0 && j <= 7; i--, j++) {
                if (board[i][j] == status.EMPTY)
                    break;
                if (board[i][j] == status.BLACK) {
                    change = true;
                    i_black_cell = i;
                    j_black_cell = j;
                    break;
                }
            }
            if (change) {
                for (int i = x - 1, j = y + 1; i > i_black_cell && j < j_black_cell; i--, j++) {
                    board[i][j] = status.BLACK;
                }
            }
        }
    }

    public void diagonal_left_up_bp(int x, int y) {
        if (x == 0 || y == 0)
            return;

        if (board[x - 1][y - 1] == status.WHITE) {
            // i , j e zoodtarin khaneye meshki ke mibinad
            int i_black_cell = -1;
            int j_black_cell = -1;
            boolean change = false;
            for (int i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--) {
                if (board[i][j] == status.EMPTY)
                    break;
                if (board[i][j] == status.BLACK) {
                    change = true;
                    i_black_cell = i;
                    j_black_cell = j;
                    break;
                }
            }
            if (change) {
                for (int i = x - 1, j = y - 1; i > i_black_cell && j > j_black_cell; i--, j--) {
                    board[i][j] = status.BLACK;
                }
            }
        }
    }

    public void changeStatusBoard(int x, int y) {
        if (!isMax) {
            // black player
            board[x][y] = status.BLACK;
            horizontal_left_bp(x, y);
            horizontal_right_bp(x, y);
            vertical_down_bp(x, y);
            vertical_up_bp(x, y);
            diagonal_left_down_bp(x, y);
            diagonal_right_down_bp(x, y);
            diagonal_left_up_bp(x, y);
            diagonal_right_up_bp(x, y);

        } else {
            // white player
            board[x][y] = status.WHITE;
            horizontal_left_wp(x, y);
            horizontal_right_wp(x, y);
            vertical_down_wp(x, y);
            vertical_up_wp(x, y);
            diagonal_left_down_wp(x, y);
            diagonal_right_down_wp(x, y);
            diagonal_left_up_wp(x, y);
            diagonal_right_up_wp(x, y);
        }
    }
}
