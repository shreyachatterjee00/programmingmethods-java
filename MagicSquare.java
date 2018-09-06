
public class MagicSquare {
    public static void main(String[] args) {
        int[][] magicSquare = {{4, 9, 2}, {3, 5, 7}, {8, 1, 6}};
        printSquare(magicSquare);
        System.out.println("Magic Square? " + isMagic(magicSquare));
    }

    final static int FIRST_ROW = 0;
    final static int SECOND_ROW = 1;
    static int THIRD_ROW = 2;
    final static int FIRST_COLUMN = 0;
    final static int SECOND_COLUMN = 1;
    final static int THIRD_COLUMN = 2;

    public static boolean isMagic(int[][] square) {
        int firstRow = square[FIRST_ROW][FIRST_COLUMN] + square[FIRST_ROW][SECOND_COLUMN] + square[FIRST_ROW][THIRD_COLUMN];
        int secondRow = square[SECOND_ROW][FIRST_COLUMN] + square[SECOND_ROW][SECOND_COLUMN] + square[SECOND_ROW][THIRD_COLUMN];
        int thirdRow = square[THIRD_ROW][FIRST_COLUMN] + square[THIRD_ROW][SECOND_COLUMN] + square[THIRD_ROW][THIRD_COLUMN];

        int firstColumn = square[FIRST_ROW][FIRST_COLUMN] + square[SECOND_ROW][FIRST_COLUMN] + square[THIRD_ROW][FIRST_COLUMN];
        int secondColumn = square[FIRST_ROW][SECOND_COLUMN] + square[SECOND_ROW][SECOND_COLUMN] + square[THIRD_ROW][SECOND_COLUMN];
        int thirdColumn = square[FIRST_ROW][THIRD_COLUMN] + square[SECOND_ROW][THIRD_COLUMN] + square[THIRD_ROW][THIRD_COLUMN];

        int firstDiagonal = square[FIRST_ROW][FIRST_COLUMN] + square[SECOND_ROW][SECOND_COLUMN] + square[THIRD_ROW][THIRD_COLUMN];
        int secondDiagonal = square[THIRD_ROW][FIRST_COLUMN] + square[SECOND_ROW][SECOND_COLUMN] + square[FIRST_ROW][THIRD_COLUMN];

        if ((firstRow == secondRow) && (secondRow == thirdRow) && (thirdRow == firstColumn) && (firstColumn == secondColumn) && (secondColumn == thirdColumn) && (thirdColumn == firstDiagonal) && (firstDiagonal == secondDiagonal)) {
            return true;
        } else {
            return false;
        }
    }


    public static void printSquare(int[][] square) {
        for (int rows = 0; rows < square.length; rows++) {
            for (int cols = 0; cols < square[rows].length; cols++) {
                System.out.print(square[rows][cols] + "  ");
            }
            System.out.println();
        }
    }
}


