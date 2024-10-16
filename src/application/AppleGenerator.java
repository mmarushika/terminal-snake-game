package application;

public class AppleGenerator implements Runnable {
    Screen screen;
    Snake snake;

    AppleGenerator(Screen screen, Snake snake) {
        this.screen = screen;
        this.snake = snake;
    }

    public void run() {
        Random rand = new Random();
        Queue <Position> apples = new LinkedList<Position>();
        try {
            while(snake.isAlive()) {
                int pos_x = 1 + Math.abs(rand.nextInt()) % 24;
                int pos_y = 1 + Math.abs(rand.nextInt()) % 49;
                synchronized(screen) {
                    if(screen.grid[pos_x][pos_y] == ' ') {
                        screen.grid[pos_x][pos_y] = '@';
                        apples.add(new Position(pos_x, pos_y));
                    }
                    if(apples.size() >= 5) {
                        Position pos = apples.poll();
                        screen.grid[pos.getX()][pos.getY()] = ' ';
                    }
                }
                Thread.sleep(10000);
            }
        } catch(InterruptedException e) {}
    }
}
