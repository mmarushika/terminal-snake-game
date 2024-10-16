package application;

public class Screen {
    char[][] grid;
    int row;
    int col;
    Map <Position, Integer> moves;

    Screen(int row, int col) {
        this.grid = new char[row][col];
        this.row = row;
        this.col = col;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(i == 0 || i == row - 1 || j == 0 || j == col - 1) {
                    this.grid[i][j] = 'X';
                } else this.grid[i][j] = ' ';
            }
        }
        this.grid[1][1] = 'S';
        this.moves = new ConcurrentHashMap<Position, Integer>();
    }


    void clear(Position pos) {
        this.grid[pos.getX()][pos.getY()] = ' ';
    }
    void display() {
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                System.out.print(this.grid[i][j] );
            }
            System.out.println();
        }
    }

    boolean hasApple(Position pos) {
        return(this.grid[pos.getX()][pos.getY()] == '@');
    }

    boolean hasSnake(Position pos) {
        return(this.grid[pos.getX()][pos.getY()] == 'S');
    }

    boolean isOutOfBounds(Position pos) {
        return(pos.getX() <= 0 || pos.getX() >= 49 || pos.getY() <= 0 && pos.getY() >= 49);
    }
}
