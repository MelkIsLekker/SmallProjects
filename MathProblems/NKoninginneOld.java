import java.util.ArrayList;

public class NKoninginneOld {

    public static ArrayList<int[][]> solutionList;
    public static int totalSolutions = 0;
    public static void main(String[] args) {
        int n = 4;

        solutionList = new ArrayList<>();

        //init board
        int[][][] boardArr = new int[n][n][n];
        for (int i = 0; i < boardArr.length; i++) {
            for (int j = 0; j < boardArr.length; j++) {
                for (int j2 = 0; j2 < boardArr.length; j2++) {
                    boardArr[i][j][j2] = 0;
                }
            }
        }
        int[] posNum = new int[n];
        for (int i = 0; i < posNum.length; i++) {
            posNum[i] = 1;
        }
        
        soekQueenOplossings(boardArr, posNum, 0, 1, n);

    }

    public static int[][][] soekQueenOplossings(int[][][] boardArr, int[] posNum, int currentBoardNum, int queenNum, int totalQueens) {
        System.out.println();
        printBoardArr(boardArr);
        //Basisgeval: laaste posisie klaar bereik
        if (currentBoardNum == 0 && posNum[0] > totalQueens) return boardArr;

        //if queen possibble continue searching
        if (queenNum > totalQueens) {
            solutionList.add(boardArr[queenNum - 1]);
            totalSolutions++;
            System.out.println("Total Solutions: " + totalSolutions);
            posNum[currentBoardNum] = 1;
            posNum[currentBoardNum-1]++;
            boardArr[currentBoardNum - 1] = boardArr[currentBoardNum - 2];
            return soekQueenOplossings(boardArr, posNum, --currentBoardNum, --queenNum, totalQueens);
        }

        int openSpaces = getOpenSpaces(boardArr[currentBoardNum]);
        System.out.println("Open:" + openSpaces + " Current:" + currentBoardNum + " queenNum:" + queenNum + " posNum:" + posNum[currentBoardNum]);
        //backtrack
        if (openSpaces == 0) { //No more open spaces
            System.out.println("No more open spaces\n");
            posNum[currentBoardNum] = 1;
            posNum[currentBoardNum-1]++;
            boardArr[currentBoardNum - 1] = deepCopy(boardArr[currentBoardNum - 2]);
            return soekQueenOplossings(boardArr, posNum, --currentBoardNum, --queenNum, totalQueens);
        }
        else if (posNum[currentBoardNum] > openSpaces) { //Past last open space
            System.out.println("Past last open space\n");
            posNum[currentBoardNum] = 1;
            posNum[currentBoardNum-1]++;
            boardArr[currentBoardNum - 1] = deepCopy(boardArr[currentBoardNum - 2]);
            return soekQueenOplossings(boardArr, posNum, --currentBoardNum, --queenNum, totalQueens);
        }
        else if (invalidRowOrColumn(boardArr[currentBoardNum])) { //At least one row or column with no valid squares
            System.out.println("At least one row or column with no valid squares\n");
            posNum[currentBoardNum] = 1;
            posNum[currentBoardNum-1]++;
            boardArr[currentBoardNum - 1] = deepCopy(boardArr[currentBoardNum - 2]);
            return soekQueenOplossings(boardArr, posNum, --currentBoardNum, --queenNum, totalQueens);
        }

        System.out.println("placing queen at pos " + posNum[currentBoardNum] + " on board " + currentBoardNum);
        boardArr = placeQueen(boardArr, posNum[currentBoardNum], currentBoardNum);
        printBoardArr(boardArr);

        if(currentBoardNum < totalQueens - 2)
            boardArr[currentBoardNum + 1] = deepCopy(boardArr[currentBoardNum]);
        return soekQueenOplossings(boardArr, posNum, ++currentBoardNum, ++queenNum, totalQueens);
    }
        

    //hulp metodes:
    private static int[][] deepCopy(int[][] original) {
        int[][] copy = new int[original.length][original[0].length];
        for (int i = 0; i < original.length; i++) {
            System.arraycopy(original[i], 0, copy[i], 0, original[i].length);
        }
        return copy;
    }

    private static int[][][] placeQueen(int[][][] boardArr, int posNum, int currentBoardNum) {
        for (int i = 0; i < boardArr.length; i++) {
            for (int j = 0; j < boardArr.length; j++) {
                if (posNum < 1) {
                    break;
                }
                if (posNum > 1 && boardArr[currentBoardNum][i][j] == 0) {
                    posNum--;
                }
                if (posNum == 1 && boardArr[currentBoardNum][i][j] == 0) {
                    boardArr[currentBoardNum][i][j] = 5;
                    posNum--;
                    boardArr[currentBoardNum] = placeInvalids(boardArr[currentBoardNum], i, j);
                }
            }
        }
        return boardArr;
    }

    private static int[][] placeInvalids(int[][] board, int i, int j)  {
        for (int counter = 0; counter < board.length; counter++) {
            //row
            if (board[counter][j] == 0) board[counter][j] = 3;
            //column
            if (board[i][counter] == 0) board[i][counter] = 3;
        }

        for (int counter = 0; counter < board.length; counter++) {
            //diagonals
            if (i + counter >= board.length || j + counter >= board.length) break;
            if (counter + i < board.length && counter + j < board.length) {
                if (board[i + counter][j + counter] == 0) board[i + counter][j + counter] = 3;
            }
            if (i - counter > -1 && j - counter > -1) {
                if (board[i - counter][j - counter] == 0) board[i - counter][j - counter] = 3;
            }
            if (counter + i < board.length && j - counter > -1) {
                if (board[i + counter][j - counter] == 0) board[i + counter][j - counter] = 3;
            }
            if (i - counter > -1 && counter + j < board.length) {
                if (board[i - counter][j + counter] == 0) board[i - counter][j + counter] = 3;
            }
        }
        return board;
    }
    
    private static int getOpenSpaces(int[][] board) {
        int openSpaces = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 0) {
                    openSpaces++;
                }
            }
        }
        return openSpaces;
    }

    private static boolean invalidRowOrColumn(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            int counterColumns = 0;
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 3) {
                    counterColumns++;
                }
            }
            if (counterColumns >= board.length) {
                return true;
            }
        }

        for (int i = 0; i < board.length; i++) {
            int counterRows = 0;
            for (int j = 0; j < board.length; j++) {
                if (board[j][i] == 3) {
                    counterRows++;
                }
            }
            if (counterRows >= board.length) {
                return true;
            }
        }

        return false;
    }

    public static void printBoard(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void printBoardArr(int[][][] boardArr) {
        for (int i = 0; i < boardArr.length; i++) {
            for (int j = 0; j < boardArr.length; j++) {
                for (int j2 = 0; j2 < boardArr.length; j2++) {
                    System.out.print(boardArr[j][i][j2] + " ");
                }
                System.out.print("      ");
            }
            System.out.println();
        }
    }

}
