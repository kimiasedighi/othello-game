package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

import static Controller.PanelController.p1_name;
import static Controller.PanelController.p2_name;

public class GameWithAIController {

    public Label p1;
    public Label p2;
    public Label turnLabel;
    public ImageView a1_w;
    public ImageView b1_w;
    public ImageView c1_w;
    public ImageView d1_w;
    public ImageView e1_w;
    public ImageView f1_w;
    public ImageView g1_w;
    public ImageView h1_w;
    public ImageView a2_w;
    public ImageView b2_w;
    public ImageView c2_w;
    public ImageView d2_w;
    public ImageView e2_w;
    public ImageView f2_w;
    public ImageView g2_w;
    public ImageView h2_w;
    public ImageView a3_w;
    public ImageView b3_w;
    public ImageView c3_w;
    public ImageView d3_w;
    public ImageView e3_w;
    public ImageView f3_w;
    public ImageView g3_w;
    public ImageView h3_w;
    public ImageView a4_w;
    public ImageView b4_w;
    public ImageView c4_w;
    public ImageView d4_w;
    public ImageView e4_w;
    public ImageView f4_w;
    public ImageView g4_w;
    public ImageView h4_w;
    public ImageView a5_w;
    public ImageView b5_w;
    public ImageView c5_w;
    public ImageView d5_w;
    public ImageView e5_w;
    public ImageView f5_w;
    public ImageView g5_w;
    public ImageView h5_w;
    public ImageView a6_w;
    public ImageView b6_w;
    public ImageView c6_w;
    public ImageView d6_w;
    public ImageView e6_w;
    public ImageView f6_w;
    public ImageView g6_w;
    public ImageView h6_w;
    public ImageView a7_w;
    public ImageView b7_w;
    public ImageView c7_w;
    public ImageView d7_w;
    public ImageView e7_w;
    public ImageView f7_w;
    public ImageView g7_w;
    public ImageView h7_w;
    public ImageView c8_w;
    public ImageView a8_w;
    public ImageView b8_w;
    public ImageView d8_w;
    public ImageView e8_w;
    public ImageView f8_w;
    public ImageView g8_w;
    public ImageView h8_w;
    public ImageView a1_b;
    public ImageView b1_b;
    public ImageView c1_b;
    public ImageView f1_b;
    public ImageView e1_b;
    public ImageView d1_b;
    public ImageView g1_b;
    public ImageView h1_b;
    public ImageView f2_b;
    public ImageView g2_b;
    public ImageView h2_b;
    public ImageView e2_b;
    public ImageView g3_b;
    public ImageView h3_b;
    public ImageView a2_b;
    public ImageView b2_b;
    public ImageView c2_b;
    public ImageView d2_b;
    public ImageView f4_b;
    public ImageView g4_b;
    public ImageView h4_b;
    public ImageView a3_b;
    public ImageView b3_b;
    public ImageView c3_b;
    public ImageView d3_b;
    public ImageView f3_b;
    public ImageView e3_b;
    public ImageView c4_b;
    public ImageView b4_b;
    public ImageView h5_b;
    public ImageView a4_b;
    public ImageView b5_b;
    public ImageView g5_b;
    public ImageView c5_b;
    public ImageView f5_b;
    public ImageView g6_b;
    public ImageView h6_b;
    public ImageView a5_b;
    public ImageView f6_b;
    public ImageView e6_b;
    public ImageView a6_b;
    public ImageView b6_b;
    public ImageView c6_b;
    public ImageView d6_b;
    public ImageView f7_b;
    public ImageView g7_b;
    public ImageView h7_b;
    public ImageView d8_b;
    public ImageView e8_b;
    public ImageView f8_b;
    public ImageView g8_b;
    public ImageView h8_b;
    public ImageView a7_b;
    public ImageView b7_b;
    public ImageView c7_b;
    public ImageView d7_b;
    public ImageView e7_b;
    public ImageView c8_b;
    public ImageView b8_b;
    public ImageView a8_b;
    public ImageView e4_b;
    public ImageView d5_b;
    public ImageView d4_b;
    public ImageView e5_b;

    int turn = 0;
    boolean endGame = false;
    static status[][] chessBoard = new status[8][8];
    static ArrayList<coordinate> blackCoordinates = new ArrayList<>();
    static ArrayList<coordinate> whiteCoordinates = new ArrayList<>();
    static ArrayList<coordinate> permittedMovements = new ArrayList<>();


    boolean no_move_1 = false, no_move_2 = false;

    @FXML
    public void initialize() {
        p1_name = "AI";

        p1.setText(p1_name);
        p2.setText(p2_name);

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
                    switch (j) {
                        case 0:
                            switch (i) {
                                case 0:
                                    a1_w.setVisible(true);
                                    a1_b.setVisible(false);
                                    break;
                                case 1:
                                    b1_w.setVisible(true);
                                    b1_b.setVisible(false);
                                    break;
                                case 2:
                                    c1_w.setVisible(true);
                                    c1_b.setVisible(false);
                                    break;
                                case 3:
                                    d1_w.setVisible(true);
                                    d1_b.setVisible(false);
                                    break;
                                case 4:
                                    e1_w.setVisible(true);
                                    e1_b.setVisible(false);
                                    break;
                                case 5:
                                    f1_w.setVisible(true);
                                    f1_b.setVisible(false);
                                    break;
                                case 6:
                                    g1_w.setVisible(true);
                                    g1_b.setVisible(false);
                                    break;
                                case 7:
                                    h1_w.setVisible(true);
                                    h1_b.setVisible(false);
                                    break;
                            }
                            break;
                        case 1:
                            switch (i) {
                                case 0:
                                    a2_w.setVisible(true);
                                    a2_b.setVisible(false);
                                    break;
                                case 1:
                                    b2_w.setVisible(true);
                                    b2_b.setVisible(false);
                                    break;
                                case 2:
                                    c2_w.setVisible(true);
                                    c2_b.setVisible(false);
                                    break;
                                case 3:
                                    d2_w.setVisible(true);
                                    d2_b.setVisible(false);
                                    break;
                                case 4:
                                    e2_w.setVisible(true);
                                    e2_b.setVisible(false);
                                    break;
                                case 5:
                                    f2_w.setVisible(true);
                                    f2_b.setVisible(false);
                                    break;
                                case 6:
                                    g2_w.setVisible(true);
                                    g2_b.setVisible(false);
                                    break;
                                case 7:
                                    h2_w.setVisible(true);
                                    h2_b.setVisible(false);
                                    break;
                            }
                            break;
                        case 2:
                            switch (i) {
                                case 0:
                                    a3_w.setVisible(true);
                                    a3_b.setVisible(false);
                                    break;
                                case 1:
                                    b3_w.setVisible(true);
                                    b3_b.setVisible(false);
                                    break;
                                case 2:
                                    c3_w.setVisible(true);
                                    c3_b.setVisible(false);
                                    break;
                                case 3:
                                    d3_w.setVisible(true);
                                    d3_b.setVisible(false);
                                    break;
                                case 4:
                                    e3_w.setVisible(true);
                                    e3_b.setVisible(false);
                                    break;
                                case 5:
                                    f3_w.setVisible(true);
                                    f3_b.setVisible(false);
                                    break;
                                case 6:
                                    g3_w.setVisible(true);
                                    g3_b.setVisible(false);
                                    break;
                                case 7:
                                    h3_w.setVisible(true);
                                    h3_b.setVisible(false);
                                    break;
                            }
                            break;
                        case 3:
                            switch (i) {
                                case 0:
                                    a4_w.setVisible(true);
                                    a4_b.setVisible(false);
                                    break;
                                case 1:
                                    b4_w.setVisible(true);
                                    b4_b.setVisible(false);
                                    break;
                                case 2:
                                    c4_w.setVisible(true);
                                    c4_b.setVisible(false);
                                    break;
                                case 3:
                                    d4_w.setVisible(true);
                                    d4_b.setVisible(false);
                                    break;
                                case 4:
                                    e4_w.setVisible(true);
                                    e4_b.setVisible(false);
                                    break;
                                case 5:
                                    f4_w.setVisible(true);
                                    f4_b.setVisible(false);
                                    break;
                                case 6:
                                    g4_w.setVisible(true);
                                    g4_b.setVisible(false);
                                    break;
                                case 7:
                                    h4_w.setVisible(true);
                                    h4_b.setVisible(false);
                                    break;
                            }
                            break;
                        case 4:
                            switch (i) {
                                case 0:
                                    a5_w.setVisible(true);
                                    a5_b.setVisible(false);
                                    break;
                                case 1:
                                    b5_w.setVisible(true);
                                    b5_b.setVisible(false);
                                    break;
                                case 2:
                                    c5_w.setVisible(true);
                                    c5_b.setVisible(false);
                                    break;
                                case 3:
                                    d5_w.setVisible(true);
                                    d5_b.setVisible(false);
                                    break;
                                case 4:
                                    e5_w.setVisible(true);
                                    e5_b.setVisible(false);
                                    break;
                                case 5:
                                    f5_w.setVisible(true);
                                    f5_b.setVisible(false);
                                    break;
                                case 6:
                                    g5_w.setVisible(true);
                                    g5_b.setVisible(false);
                                    break;
                                case 7:
                                    h5_w.setVisible(true);
                                    h5_b.setVisible(false);
                                    break;
                            }
                            break;
                        case 5:
                            switch (i) {
                                case 0:
                                    a6_w.setVisible(true);
                                    a6_b.setVisible(false);
                                    break;
                                case 1:
                                    b6_w.setVisible(true);
                                    b6_b.setVisible(false);
                                    break;
                                case 2:
                                    c6_w.setVisible(true);
                                    c6_b.setVisible(false);
                                    break;
                                case 3:
                                    d6_w.setVisible(true);
                                    d6_b.setVisible(false);
                                    break;
                                case 4:
                                    e6_w.setVisible(true);
                                    e6_b.setVisible(false);
                                    break;
                                case 5:
                                    f6_w.setVisible(true);
                                    f6_b.setVisible(false);
                                    break;
                                case 6:
                                    g6_w.setVisible(true);
                                    g6_b.setVisible(false);
                                    break;
                                case 7:
                                    h6_w.setVisible(true);
                                    h6_b.setVisible(false);
                                    break;
                            }
                            break;
                        case 6:
                            switch (i) {
                                case 0:
                                    a7_w.setVisible(true);
                                    a7_b.setVisible(false);
                                    break;
                                case 1:
                                    b7_w.setVisible(true);
                                    b7_b.setVisible(false);
                                    break;
                                case 2:
                                    c7_w.setVisible(true);
                                    c7_b.setVisible(false);
                                    break;
                                case 3:
                                    d7_w.setVisible(true);
                                    d7_b.setVisible(false);
                                    break;
                                case 4:
                                    e7_w.setVisible(true);
                                    e7_b.setVisible(false);
                                    break;
                                case 5:
                                    f7_w.setVisible(true);
                                    f7_b.setVisible(false);
                                    break;
                                case 6:
                                    g7_w.setVisible(true);
                                    g7_b.setVisible(false);
                                    break;
                                case 7:
                                    h7_w.setVisible(true);
                                    h7_b.setVisible(false);
                                    break;
                            }
                            break;
                        case 7:
                            switch (i) {
                                case 0:
                                    a8_w.setVisible(true);
                                    a8_b.setVisible(false);
                                    break;
                                case 1:
                                    b8_w.setVisible(true);
                                    b8_b.setVisible(false);
                                    break;
                                case 2:
                                    c8_w.setVisible(true);
                                    c8_b.setVisible(false);
                                    break;
                                case 3:
                                    d8_w.setVisible(true);
                                    d8_b.setVisible(false);
                                    break;
                                case 4:
                                    e8_w.setVisible(true);
                                    e8_b.setVisible(false);
                                    break;
                                case 5:
                                    f8_w.setVisible(true);
                                    f8_b.setVisible(false);
                                    break;
                                case 6:
                                    g8_w.setVisible(true);
                                    g8_b.setVisible(false);
                                    break;
                                case 7:
                                    h8_w.setVisible(true);
                                    h8_b.setVisible(false);
                                    break;
                            }
                            break;
                    }
                } else if (chessBoard[i][j] == status.BLACK) {
                    blackCoordinates.add(new coordinate(i, j));
                    switch (j) {
                        case 0:
                            switch (i) {
                                case 0:
                                    a1_w.setVisible(false);
                                    a1_b.setVisible(true);
                                    break;
                                case 1:
                                    b1_w.setVisible(false);
                                    b1_b.setVisible(true);
                                    break;
                                case 2:
                                    c1_w.setVisible(false);
                                    c1_b.setVisible(true);
                                    break;
                                case 3:
                                    d1_w.setVisible(false);
                                    d1_b.setVisible(true);
                                    break;
                                case 4:
                                    e1_w.setVisible(false);
                                    e1_b.setVisible(true);
                                    break;
                                case 5:
                                    f1_w.setVisible(false);
                                    f1_b.setVisible(true);
                                    break;
                                case 6:
                                    g1_w.setVisible(false);
                                    g1_b.setVisible(true);
                                    break;
                                case 7:
                                    h1_w.setVisible(false);
                                    h1_b.setVisible(true);
                                    break;
                            }
                            break;
                        case 1:
                            switch (i) {
                                case 0:
                                    a2_w.setVisible(false);
                                    a2_b.setVisible(true);
                                    break;
                                case 1:
                                    b2_w.setVisible(false);
                                    b2_b.setVisible(true);
                                    break;
                                case 2:
                                    c2_w.setVisible(false);
                                    c2_b.setVisible(true);
                                    break;
                                case 3:
                                    d2_w.setVisible(false);
                                    d2_b.setVisible(true);
                                    break;
                                case 4:
                                    e2_w.setVisible(false);
                                    e2_b.setVisible(true);
                                    break;
                                case 5:
                                    f2_w.setVisible(false);
                                    f2_b.setVisible(true);
                                    break;
                                case 6:
                                    g2_w.setVisible(false);
                                    g2_b.setVisible(true);
                                    break;
                                case 7:
                                    h2_w.setVisible(false);
                                    h2_b.setVisible(true);
                                    break;
                            }
                            break;
                        case 2:
                            switch (i) {
                                case 0:
                                    a3_w.setVisible(false);
                                    a3_b.setVisible(true);
                                    break;
                                case 1:
                                    b3_w.setVisible(false);
                                    b3_b.setVisible(true);
                                    break;
                                case 2:
                                    c3_w.setVisible(false);
                                    c3_b.setVisible(true);
                                    break;
                                case 3:
                                    d3_w.setVisible(false);
                                    d3_b.setVisible(true);
                                    break;
                                case 4:
                                    e3_w.setVisible(false);
                                    e3_b.setVisible(true);
                                    break;
                                case 5:
                                    f3_w.setVisible(false);
                                    f3_b.setVisible(true);
                                    break;
                                case 6:
                                    g3_w.setVisible(false);
                                    g3_b.setVisible(true);
                                    break;
                                case 7:
                                    h3_w.setVisible(false);
                                    h3_b.setVisible(true);
                                    break;
                            }
                            break;
                        case 3:
                            switch (i) {
                                case 0:
                                    a4_w.setVisible(false);
                                    a4_b.setVisible(true);
                                    break;
                                case 1:
                                    b4_w.setVisible(false);
                                    b4_b.setVisible(true);
                                    break;
                                case 2:
                                    c4_w.setVisible(false);
                                    c4_b.setVisible(true);
                                    break;
                                case 3:
                                    d4_w.setVisible(false);
                                    d4_b.setVisible(true);
                                    break;
                                case 4:
                                    e4_w.setVisible(false);
                                    e4_b.setVisible(true);
                                    break;
                                case 5:
                                    f4_w.setVisible(false);
                                    f4_b.setVisible(true);
                                    break;
                                case 6:
                                    g4_w.setVisible(false);
                                    g4_b.setVisible(true);
                                    break;
                                case 7:
                                    h4_w.setVisible(false);
                                    h4_b.setVisible(true);
                                    break;
                            }
                            break;
                        case 4:
                            switch (i) {
                                case 0:
                                    a5_w.setVisible(false);
                                    a5_b.setVisible(true);
                                    break;
                                case 1:
                                    b5_w.setVisible(false);
                                    b5_b.setVisible(true);
                                    break;
                                case 2:
                                    c5_w.setVisible(false);
                                    c5_b.setVisible(true);
                                    break;
                                case 3:
                                    d5_w.setVisible(false);
                                    d5_b.setVisible(true);
                                    break;
                                case 4:
                                    e5_w.setVisible(false);
                                    e5_b.setVisible(true);
                                    break;
                                case 5:
                                    f5_w.setVisible(false);
                                    f5_b.setVisible(true);
                                    break;
                                case 6:
                                    g5_w.setVisible(false);
                                    g5_b.setVisible(true);
                                    break;
                                case 7:
                                    h5_w.setVisible(false);
                                    h5_b.setVisible(true);
                                    break;
                            }
                            break;
                        case 5:
                            switch (i) {
                                case 0:
                                    a6_w.setVisible(false);
                                    a6_b.setVisible(true);
                                    break;
                                case 1:
                                    b6_w.setVisible(false);
                                    b6_b.setVisible(true);
                                    break;
                                case 2:
                                    c6_w.setVisible(false);
                                    c6_b.setVisible(true);
                                    break;
                                case 3:
                                    d6_w.setVisible(false);
                                    d6_b.setVisible(true);
                                    break;
                                case 4:
                                    e6_w.setVisible(false);
                                    e6_b.setVisible(true);
                                    break;
                                case 5:
                                    f6_w.setVisible(false);
                                    f6_b.setVisible(true);
                                    break;
                                case 6:
                                    g6_w.setVisible(false);
                                    g6_b.setVisible(true);
                                    break;
                                case 7:
                                    h6_w.setVisible(false);
                                    h6_b.setVisible(true);
                                    break;
                            }
                            break;
                        case 6:
                            switch (i) {
                                case 0:
                                    a7_w.setVisible(false);
                                    a7_b.setVisible(true);
                                    break;
                                case 1:
                                    b7_w.setVisible(false);
                                    b7_b.setVisible(true);
                                    break;
                                case 2:
                                    c7_w.setVisible(false);
                                    c7_b.setVisible(true);
                                    break;
                                case 3:
                                    d7_w.setVisible(false);
                                    d7_b.setVisible(true);
                                    break;
                                case 4:
                                    e7_w.setVisible(false);
                                    e7_b.setVisible(true);
                                    break;
                                case 5:
                                    f7_w.setVisible(false);
                                    f7_b.setVisible(true);
                                    break;
                                case 6:
                                    g7_w.setVisible(false);
                                    g7_b.setVisible(true);
                                    break;
                                case 7:
                                    h7_w.setVisible(false);
                                    h7_b.setVisible(true);
                                    break;
                            }
                            break;
                        case 7:
                            switch (i) {
                                case 0:
                                    a8_w.setVisible(false);
                                    a8_b.setVisible(true);
                                    break;
                                case 1:
                                    b8_w.setVisible(false);
                                    b8_b.setVisible(true);
                                    break;
                                case 2:
                                    c8_w.setVisible(false);
                                    c8_b.setVisible(true);
                                    break;
                                case 3:
                                    d8_w.setVisible(false);
                                    d8_b.setVisible(true);
                                    break;
                                case 4:
                                    e8_w.setVisible(false);
                                    e8_b.setVisible(true);
                                    break;
                                case 5:
                                    f8_w.setVisible(false);
                                    f8_b.setVisible(true);
                                    break;
                                case 6:
                                    g8_w.setVisible(false);
                                    g8_b.setVisible(true);
                                    break;
                                case 7:
                                    h8_w.setVisible(false);
                                    h8_b.setVisible(true);
                                    break;
                            }
                            break;
                    }
                } else {
                    switch (j) {
                        case 0:
                            switch (i) {
                                case 0:
                                    a1_w.setVisible(false);
                                    a1_b.setVisible(false);
                                    break;
                                case 1:
                                    b1_w.setVisible(false);
                                    b1_b.setVisible(false);
                                    break;
                                case 2:
                                    c1_w.setVisible(false);
                                    c1_b.setVisible(false);
                                    break;
                                case 3:
                                    d1_w.setVisible(false);
                                    d1_b.setVisible(false);
                                    break;
                                case 4:
                                    e1_w.setVisible(false);
                                    e1_b.setVisible(false);
                                    break;
                                case 5:
                                    f1_w.setVisible(false);
                                    f1_b.setVisible(false);
                                    break;
                                case 6:
                                    g1_w.setVisible(false);
                                    g1_b.setVisible(false);
                                    break;
                                case 7:
                                    h1_w.setVisible(false);
                                    h1_b.setVisible(false);
                                    break;
                            }
                            break;
                        case 1:
                            switch (i) {
                                case 0:
                                    a2_w.setVisible(false);
                                    a2_b.setVisible(false);
                                    break;
                                case 1:
                                    b2_w.setVisible(false);
                                    b2_b.setVisible(false);
                                    break;
                                case 2:
                                    c2_w.setVisible(false);
                                    c2_b.setVisible(false);
                                    break;
                                case 3:
                                    d2_w.setVisible(false);
                                    d2_b.setVisible(false);
                                    break;
                                case 4:
                                    e2_w.setVisible(false);
                                    e2_b.setVisible(false);
                                    break;
                                case 5:
                                    f2_w.setVisible(false);
                                    f2_b.setVisible(false);
                                    break;
                                case 6:
                                    g2_w.setVisible(false);
                                    g2_b.setVisible(false);
                                    break;
                                case 7:
                                    h2_w.setVisible(false);
                                    h2_b.setVisible(false);
                                    break;
                            }
                            break;
                        case 2:
                            switch (i) {
                                case 0:
                                    a3_w.setVisible(false);
                                    a3_b.setVisible(false);
                                    break;
                                case 1:
                                    b3_w.setVisible(false);
                                    b3_b.setVisible(false);
                                    break;
                                case 2:
                                    c3_w.setVisible(false);
                                    c3_b.setVisible(false);
                                    break;
                                case 3:
                                    d3_w.setVisible(false);
                                    d3_b.setVisible(false);
                                    break;
                                case 4:
                                    e3_w.setVisible(false);
                                    e3_b.setVisible(false);
                                    break;
                                case 5:
                                    f3_w.setVisible(false);
                                    f3_b.setVisible(false);
                                    break;
                                case 6:
                                    g3_w.setVisible(false);
                                    g3_b.setVisible(false);
                                    break;
                                case 7:
                                    h3_w.setVisible(false);
                                    h3_b.setVisible(false);
                                    break;
                            }
                            break;
                        case 3:
                            switch (i) {
                                case 0:
                                    a4_w.setVisible(false);
                                    a4_b.setVisible(false);
                                    break;
                                case 1:
                                    b4_w.setVisible(false);
                                    b4_b.setVisible(false);
                                    break;
                                case 2:
                                    c4_w.setVisible(false);
                                    c4_b.setVisible(false);
                                    break;
                                case 3:
                                    d4_w.setVisible(false);
                                    d4_b.setVisible(false);
                                    break;
                                case 4:
                                    e4_w.setVisible(false);
                                    e4_b.setVisible(false);
                                    break;
                                case 5:
                                    f4_w.setVisible(false);
                                    f4_b.setVisible(false);
                                    break;
                                case 6:
                                    g4_w.setVisible(false);
                                    g4_b.setVisible(false);
                                    break;
                                case 7:
                                    h4_w.setVisible(false);
                                    h4_b.setVisible(false);
                                    break;
                            }
                            break;
                        case 4:
                            switch (i) {
                                case 0:
                                    a5_w.setVisible(false);
                                    a5_b.setVisible(false);
                                    break;
                                case 1:
                                    b5_w.setVisible(false);
                                    b5_b.setVisible(false);
                                    break;
                                case 2:
                                    c5_w.setVisible(false);
                                    c5_b.setVisible(false);
                                    break;
                                case 3:
                                    d5_w.setVisible(false);
                                    d5_b.setVisible(false);
                                    break;
                                case 4:
                                    e5_w.setVisible(false);
                                    e5_b.setVisible(false);
                                    break;
                                case 5:
                                    f5_w.setVisible(false);
                                    f5_b.setVisible(false);
                                    break;
                                case 6:
                                    g5_w.setVisible(false);
                                    g5_b.setVisible(false);
                                    break;
                                case 7:
                                    h5_w.setVisible(false);
                                    h5_b.setVisible(false);
                                    break;
                            }
                            break;
                        case 5:
                            switch (i) {
                                case 0:
                                    a6_w.setVisible(false);
                                    a6_b.setVisible(false);
                                    break;
                                case 1:
                                    b6_w.setVisible(false);
                                    b6_b.setVisible(false);
                                    break;
                                case 2:
                                    c6_w.setVisible(false);
                                    c6_b.setVisible(false);
                                    break;
                                case 3:
                                    d6_w.setVisible(false);
                                    d6_b.setVisible(false);
                                    break;
                                case 4:
                                    e6_w.setVisible(false);
                                    e6_b.setVisible(false);
                                    break;
                                case 5:
                                    f6_w.setVisible(false);
                                    f6_b.setVisible(false);
                                    break;
                                case 6:
                                    g6_w.setVisible(false);
                                    g6_b.setVisible(false);
                                    break;
                                case 7:
                                    h6_w.setVisible(false);
                                    h6_b.setVisible(false);
                                    break;
                            }
                            break;
                        case 6:
                            switch (i) {
                                case 0:
                                    a7_w.setVisible(false);
                                    a7_b.setVisible(false);
                                    break;
                                case 1:
                                    b7_w.setVisible(false);
                                    b7_b.setVisible(false);
                                    break;
                                case 2:
                                    c7_w.setVisible(false);
                                    c7_b.setVisible(false);
                                    break;
                                case 3:
                                    d7_w.setVisible(false);
                                    d7_b.setVisible(false);
                                    break;
                                case 4:
                                    e7_w.setVisible(false);
                                    e7_b.setVisible(false);
                                    break;
                                case 5:
                                    f7_w.setVisible(false);
                                    f7_b.setVisible(false);
                                    break;
                                case 6:
                                    g7_w.setVisible(false);
                                    g7_b.setVisible(false);
                                    break;
                                case 7:
                                    h7_w.setVisible(false);
                                    h7_b.setVisible(false);
                                    break;
                            }
                            break;
                        case 7:
                            switch (i) {
                                case 0:
                                    a8_w.setVisible(false);
                                    a8_b.setVisible(false);
                                    break;
                                case 1:
                                    b8_w.setVisible(false);
                                    b8_b.setVisible(false);
                                    break;
                                case 2:
                                    c8_w.setVisible(false);
                                    c8_b.setVisible(false);
                                    break;
                                case 3:
                                    d8_w.setVisible(false);
                                    d8_b.setVisible(false);
                                    break;
                                case 4:
                                    e8_w.setVisible(false);
                                    e8_b.setVisible(false);
                                    break;
                                case 5:
                                    f8_w.setVisible(false);
                                    f8_b.setVisible(false);
                                    break;
                                case 6:
                                    g8_w.setVisible(false);
                                    g8_b.setVisible(false);
                                    break;
                                case 7:
                                    h8_w.setVisible(false);
                                    h8_b.setVisible(false);
                                    break;
                            }
                            break;
                    }
                }
            }
        }
    }

    public void newTurn() {

        if ((blackCoordinates.size() + whiteCoordinates.size() == 64) || endGame || (no_move_2 && no_move_1)) {

            String text;
            if (blackCoordinates.size() > whiteCoordinates.size()) {
                text = "GAME OVER \n" + p1_name + " WON";
            } else if (blackCoordinates.size() < whiteCoordinates.size()) {
                text = "GAME OVER \n" + p2_name + " WON";
            } else
                text = "NOBODY WON";
            Alert alert = new Alert(Alert.AlertType.INFORMATION, text);
            alert.showAndWait();
            turnLabel.setVisible(true);
            turnLabel.setText("      " + p1_name + ": " + blackCoordinates.size() + " - " + p2_name + ": " + whiteCoordinates.size());
            return;
        } else {

            if (turn % 2 == 0) {
                //black's turn
                if (!calPlaces("black")) {

                    //turnLabel.setText("sorry " + p1_name + ". You have no possible move");
                    turn++;
                    no_move_1 = true;

                    if (no_move_2)
                        endGame = true;
                    newTurn();
                } else {
                    no_move_1 = false;

                    //turnLabel.setText("     " + p1_name + "'s turn to play");

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
                    //turnLabel.setText("sorry " + p2_name + ". You have no possible move");
                    turn++;
                    no_move_2 = true;

                    if (no_move_1)
                        endGame = true;
                    newTurn();
                } else {
                    no_move_2 = false;
                    //turnLabel.setText("     " + p2_name + "'s turn to play");
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
        for (coordinate coordinate : whiteCoordinates) {
            node.whiteCoordinates.add(new coordinate(coordinate.i, coordinate.j));
        }
        for (coordinate coordinate : blackCoordinates) {
            node.blackCoordinates.add(new coordinate(coordinate.i, coordinate.j));
        }
        node.isMax = true;
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

    // functions for white player to change status board

    public void horizontal_right_wp(int x, int y) {
        if (x == 7)
            return;

        if (chessBoard[x + 1][y] == status.BLACK) {
            // i e zoodtarin khaneye sefid ke mibinad
            int i_white_cell = -1;
            boolean change = false;
            for (int k = x + 1; k <= 7; k++) {
                if (chessBoard[k][y] == status.EMPTY)
                    break;
                if (chessBoard[k][y] == status.WHITE) {
                    i_white_cell = k;
                    change = true;
                    break;
                }
            }
            if (change) {
                for (int k = x + 1; k < i_white_cell; k++) {
                    chessBoard[k][y] = status.WHITE;
                }
            }
        }
    }

    public void horizontal_left_wp(int x, int y) {
        if (x == 0)
            return;

        if (chessBoard[x - 1][y] == status.BLACK) {
            // i e zoodtarin khaneye sefid ke mibinad
            int i_white_cell = -1;
            boolean change = false;
            for (int k = x - 1; k >= 0; k--) {
                if (chessBoard[k][y] == status.EMPTY)
                    break;
                if (chessBoard[k][y] == status.WHITE) {
                    change = true;
                    i_white_cell = k;
                    break;
                }
            }
            if (change) {
                for (int k = x - 1; k > i_white_cell; k--) {
                    chessBoard[k][y] = status.WHITE;
                }
            }
        }
    }

    public void vertical_down_wp(int x, int y) {
        if (y == 7)
            return;

        if (chessBoard[x][y + 1] == status.BLACK) {
            // j e zoodtarin khaneye sefid ke mibinad
            int j_white_cell = -1;
            boolean change = false;
            for (int k = y + 1; k <= 7; k++) {
                if (chessBoard[x][k] == status.EMPTY)
                    break;
                if (chessBoard[x][k] == status.WHITE) {
                    change = true;
                    j_white_cell = k;
                    break;
                }
            }
            if (change) {
                for (int k = y + 1; k < j_white_cell; k++) {
                    chessBoard[x][k] = status.WHITE;
                }
            }
        }
    }

    public void vertical_up_wp(int x, int y) {
        if (y == 0)
            return;

        if (chessBoard[x][y - 1] == status.BLACK) {
            // j e zoodtarin khaneye sefid ke mibinad
            int j_white_cell = -1;
            boolean change = false;
            for (int k = y - 1; k >= 0; k--) {
                if (chessBoard[x][k] == status.EMPTY)
                    break;
                if (chessBoard[x][k] == status.WHITE) {
                    change = true;
                    j_white_cell = k;
                    break;
                }
            }
            if (change) {
                for (int k = y - 1; k > j_white_cell; k--) {
                    chessBoard[x][k] = status.WHITE;
                }
            }
        }
    }

    public void diagonal_right_down_wp(int x, int y) {
        if (x == 7 || y == 7)
            return;

        if (chessBoard[x + 1][y + 1] == status.BLACK) {
            // i , j e zoodtarin khaneye sefid ke mibinad
            int i_white_cell = -1;
            int j_white_cell = -1;
            boolean change = false;
            for (int i = x + 1, j = y + 1; i <= 7 && j <= 7; i++, j++) {
                if (chessBoard[i][j] == status.EMPTY)
                    break;
                if (chessBoard[i][j] == status.WHITE) {
                    change = true;
                    i_white_cell = i;
                    j_white_cell = j;
                    break;
                }
            }
            if (change) {
                for (int i = x + 1, j = y + 1; i < i_white_cell && j < j_white_cell; i++, j++) {
                    chessBoard[i][j] = status.WHITE;
                }
            }
        }
    }

    public void diagonal_right_up_wp(int x, int y) {
        if (x == 7 || y == 0)
            return;

        if (chessBoard[x + 1][y - 1] == status.BLACK) {
            // i , j e zoodtarin khaneye sefid ke mibinad
            int i_white_cell = -1;
            int j_white_cell = -1;
            boolean change = false;
            for (int i = x + 1, j = y - 1; i <= 7 && j >= 0; i++, j--) {
                if (chessBoard[i][j] == status.EMPTY)
                    break;
                if (chessBoard[i][j] == status.WHITE) {
                    change = true;
                    i_white_cell = i;
                    j_white_cell = j;
                    break;
                }
            }
            if (change) {
                for (int i = x + 1, j = y - 1; i < i_white_cell && j > j_white_cell; i++, j--) {
                    chessBoard[i][j] = status.WHITE;
                }
            }
        }
    }

    public void diagonal_left_down_wp(int x, int y) {
        if (x == 0 || y == 7)
            return;

        if (chessBoard[x - 1][y + 1] == status.BLACK) {
            // i , j e zoodtarin khaneye sefid ke mibinad
            int i_white_cell = -1;
            int j_white_cell = -1;
            boolean change = false;
            for (int i = x - 1, j = y + 1; i >= 0 && j <= 7; i--, j++) {
                if (chessBoard[i][j] == status.EMPTY)
                    break;
                if (chessBoard[i][j] == status.WHITE) {
                    change = true;
                    i_white_cell = i;
                    j_white_cell = j;
                    break;
                }
            }
            if (change) {
                for (int i = x - 1, j = y + 1; i > i_white_cell && j < j_white_cell; i--, j++) {
                    chessBoard[i][j] = status.WHITE;
                }
            }
        }
    }

    public void diagonal_left_up_wp(int x, int y) {
        if (x == 0 || y == 0)
            return;

        if (chessBoard[x - 1][y - 1] == status.BLACK) {
            // i , j e zoodtarin khaneye sefid ke mibinad
            int i_white_cell = -1;
            int j_white_cell = -1;
            boolean change = false;
            for (int i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--) {
                if (chessBoard[i][j] == status.EMPTY)
                    break;
                if (chessBoard[i][j] == status.WHITE) {
                    change = true;
                    i_white_cell = i;
                    j_white_cell = j;
                    break;
                }
            }
            if (change) {
                for (int i = x - 1, j = y - 1; i > i_white_cell && j > j_white_cell; i--, j--) {
                    chessBoard[i][j] = status.WHITE;
                }
            }
        }
    }

    // functions for black player to change status board

    public void horizontal_right_bp(int x, int y) {
        if (x == 7)
            return;

        if (chessBoard[x + 1][y] == status.WHITE) {
            // i e zoodtarin khaneye meshki ke mibinad
            int i_black_cell = -1;
            boolean change = false;
            for (int k = x + 1; k <= 7; k++) {
                if (chessBoard[k][y] == status.EMPTY)
                    break;
                if (chessBoard[k][y] == status.BLACK) {
                    i_black_cell = k;
                    change = true;
                    break;
                }
            }
            if (change) {
                for (int k = x + 1; k < i_black_cell; k++) {
                    chessBoard[k][y] = status.BLACK;
                }
            }
        }
    }

    public void horizontal_left_bp(int x, int y) {
        if (x == 0)
            return;

        if (chessBoard[x - 1][y] == status.WHITE) {
            // i e zoodtarin khaneye meshki ke mibinad
            int i_black_cell = -1;
            boolean change = false;
            for (int k = x - 1; k >= 0; k--) {
                if (chessBoard[k][y] == status.EMPTY)
                    break;
                if (chessBoard[k][y] == status.BLACK) {
                    change = true;
                    i_black_cell = k;
                    break;
                }
            }
            if (change) {
                for (int k = x - 1; k > i_black_cell; k--) {
                    chessBoard[k][y] = status.BLACK;
                }
            }
        }
    }

    public void vertical_down_bp(int x, int y) {
        if (y == 7)
            return;

        if (chessBoard[x][y + 1] == status.WHITE) {
            // j e zoodtarin khaneye meshki ke mibinad
            int j_black_cell = -1;
            boolean change = false;
            for (int k = y + 1; k <= 7; k++) {
                if (chessBoard[x][k] == status.EMPTY)
                    break;
                if (chessBoard[x][k] == status.BLACK) {
                    change = true;
                    j_black_cell = k;
                    break;
                }
            }
            if (change) {
                for (int k = y + 1; k < j_black_cell; k++) {
                    chessBoard[x][k] = status.BLACK;
                }
            }
        }
    }

    public void vertical_up_bp(int x, int y) {
        if (y == 0)
            return;

        if (chessBoard[x][y - 1] == status.WHITE) {
            // j e zoodtarin khaneye meshki ke mibinad
            int j_black_cell = -1;
            boolean change = false;
            for (int k = y - 1; k >= 0; k--) {
                if (chessBoard[x][k] == status.EMPTY)
                    break;
                if (chessBoard[x][k] == status.BLACK) {
                    change = true;
                    j_black_cell = k;
                    break;
                }
            }
            if (change) {
                for (int k = y - 1; k > j_black_cell; k--) {
                    chessBoard[x][k] = status.BLACK;
                }
            }
        }
    }

    public void diagonal_right_down_bp(int x, int y) {
        if (x == 7 || y == 7)
            return;

        if (chessBoard[x + 1][y + 1] == status.WHITE) {
            // i , j e zoodtarin khaneye meshki ke mibinad
            int i_black_cell = -1;
            int j_black_cell = -1;
            boolean change = false;
            for (int i = x + 1, j = y + 1; i <= 7 && j <= 7; i++, j++) {
                if (chessBoard[i][j] == status.EMPTY)
                    break;
                if (chessBoard[i][j] == status.BLACK) {
                    change = true;
                    i_black_cell = i;
                    j_black_cell = j;
                    break;
                }
            }
            if (change) {
                for (int i = x + 1, j = y + 1; i < i_black_cell && j < j_black_cell; i++, j++) {
                    chessBoard[i][j] = status.BLACK;
                }
            }
        }
    }

    public void diagonal_right_up_bp(int x, int y) {
        if (x == 7 || y == 0)
            return;

        if (chessBoard[x + 1][y - 1] == status.WHITE) {
            // i , j e zoodtarin khaneye meshki ke mibinad
            int i_black_cell = -1;
            int j_black_cell = -1;
            boolean change = false;
            for (int i = x + 1, j = y - 1; i <= 7 && j >= 0; i++, j--) {
                if (chessBoard[i][j] == status.EMPTY)
                    break;
                if (chessBoard[i][j] == status.BLACK) {
                    change = true;
                    i_black_cell = i;
                    j_black_cell = j;
                    break;
                }
            }
            if (change) {
                for (int i = x + 1, j = y - 1; i < i_black_cell && j > j_black_cell; i++, j--) {
                    chessBoard[i][j] = status.BLACK;
                }
            }
        }
    }

    public void diagonal_left_down_bp(int x, int y) {
        if (x == 0 || y == 7)
            return;

        if (chessBoard[x - 1][y + 1] == status.WHITE) {
            // i , j e zoodtarin khaneye meshki ke mibinad
            int i_black_cell = -1;
            int j_black_cell = -1;
            boolean change = false;
            for (int i = x - 1, j = y + 1; i >= 0 && j <= 7; i--, j++) {
                if (chessBoard[i][j] == status.EMPTY)
                    break;
                if (chessBoard[i][j] == status.BLACK) {
                    change = true;
                    i_black_cell = i;
                    j_black_cell = j;
                    break;
                }
            }
            if (change) {
                for (int i = x - 1, j = y + 1; i > i_black_cell && j < j_black_cell; i--, j++) {
                    chessBoard[i][j] = status.BLACK;
                }
            }
        }
    }

    public void diagonal_left_up_bp(int x, int y) {
        if (x == 0 || y == 0)
            return;

        if (chessBoard[x - 1][y - 1] == status.WHITE) {
            // i , j e zoodtarin khaneye meshki ke mibinad
            int i_black_cell = -1;
            int j_black_cell = -1;
            boolean change = false;
            for (int i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--) {
                if (chessBoard[i][j] == status.EMPTY)
                    break;
                if (chessBoard[i][j] == status.BLACK) {
                    change = true;
                    i_black_cell = i;
                    j_black_cell = j;
                    break;
                }
            }
            if (change) {
                for (int i = x - 1, j = y - 1; i > i_black_cell && j > j_black_cell; i--, j--) {
                    chessBoard[i][j] = status.BLACK;
                }
            }
        }
    }

    public void changeStatusBoard(int x, int y) {
        if (turn % 2 == 0) {
            // black player
            chessBoard[x][y] = status.BLACK;
            horizontal_left_bp(x, y);
            horizontal_right_bp(x, y);
            vertical_down_bp(x, y);
            vertical_up_bp(x, y);
            diagonal_left_down_bp(x, y);
            diagonal_right_down_bp(x, y);
            diagonal_left_up_bp(x, y);
            diagonal_right_up_bp(x, y);

            updateBoard();

            turn++;
            newTurn();

        } else {
            // white player
            chessBoard[x][y] = status.WHITE;
            horizontal_left_wp(x, y);
            horizontal_right_wp(x, y);
            vertical_down_wp(x, y);
            vertical_up_wp(x, y);
            diagonal_left_down_wp(x, y);
            diagonal_right_down_wp(x, y);
            diagonal_left_up_wp(x, y);
            diagonal_right_up_wp(x, y);

            updateBoard();

            turn++;
            newTurn();
        }
    }

    public boolean contains(int x, int y) {
        boolean contains = false;
        for (coordinate p : permittedMovements) {
            if (p.i == x && p.j == y) {
                contains = true;
                break;
            }
        }
        return contains;
    }

    public void a1(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 0;
            int y = 0;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                // newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
            return;
        }
    }

    public void b1(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 1;
            int y = 0;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                // newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
            return;
        }
    }

    public void c1(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 2;
            int y = 0;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                // newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
            return;
        }
    }

    public void d1(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 3;
            int y = 0;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                //newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
            return;
        }
    }

    public void e1(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 4;
            int y = 0;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                // newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
            return;
        }
    }

    public void f1(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 5;
            int y = 0;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                // newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
            return;
        }
    }

    public void g1(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 6;
            int y = 0;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                //newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
            return;
        }
    }

    public void h1(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 7;
            int y = 0;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                // newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
            return;
        }
    }

    public void a2(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 0;
            int y = 1;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                // newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
            return;
        }
    }

    public void b2(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 1;
            int y = 1;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                // newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void c2(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 2;
            int y = 1;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                // newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void d2(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 3;
            int y = 1;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                // newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void e2(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 4;
            int y = 1;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                // newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void f2(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 5;
            int y = 1;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                //  newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void g2(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 6;
            int y = 1;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                //  newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void h2(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 7;
            int y = 1;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                //newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void a3(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 0;
            int y = 2;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                // newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void b3(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 1;
            int y = 2;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                //newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void c3(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 2;
            int y = 2;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                //newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void d3(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 3;
            int y = 2;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                // newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void e3(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 4;
            int y = 2;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                // newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void f3(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 5;
            int y = 2;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                //newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void g3(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 6;
            int y = 2;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                //  newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void h3(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 7;
            int y = 2;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                // newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void a4(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 0;
            int y = 3;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                //newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void b4(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 1;
            int y = 3;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                // newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void c4(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 2;
            int y = 3;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                // newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void d4(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 3;
            int y = 3;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                // newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void e4(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 4;
            int y = 3;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                // newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void f4(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 5;
            int y = 3;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                // newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void g4(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 6;
            int y = 3;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                // newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void h4(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 7;
            int y = 3;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                //newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void a5(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 0;
            int y = 4;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                // newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void b5(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 1;
            int y = 4;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                //newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void c5(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 2;
            int y = 4;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                // newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void d5(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 3;
            int y = 4;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                // newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void e5(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 4;
            int y = 4;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                // newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void f5(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 5;
            int y = 4;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                // newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void g5(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 6;
            int y = 4;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                // newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void h5(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 7;
            int y = 4;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                // newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void a6(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 0;
            int y = 5;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                //newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void b6(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 1;
            int y = 5;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                //newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void c6(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 2;
            int y = 5;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                //newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void d6(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 3;
            int y = 5;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                //  newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void e6(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 4;
            int y = 5;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                // newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void f6(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 5;
            int y = 5;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                //newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void g6(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 6;
            int y = 5;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                // newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void h6(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 7;
            int y = 5;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                // newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void a7(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 0;
            int y = 6;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                //newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void b7(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 1;
            int y = 6;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                //  newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void c7(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 2;
            int y = 6;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                //  newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void d7(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 3;
            int y = 6;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                //newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void e7(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 4;
            int y = 6;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                //newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void f7(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 5;
            int y = 6;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                // newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void g7(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 6;
            int y = 6;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                //newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void h7(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 7;
            int y = 6;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                //newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void a8(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 0;
            int y = 7;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                // newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void b8(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 1;
            int y = 7;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                //newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void c8(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 2;
            int y = 7;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                //newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void d8(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 3;
            int y = 7;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                //newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void e8(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 4;
            int y = 7;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                //newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void f8(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 5;
            int y = 7;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                //newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void g8(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 6;
            int y = 7;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                //newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

    public void h8(MouseEvent mouseEvent) {
        if (turn % 2 == 1) {
            int x = 7;
            int y = 7;
            if (contains(x, y)) {
                changeStatusBoard(x, y);
                //newTurn();
            } else {
                turnLabel.setText("wrong choice. try again!");
            }
        }
    }

}
