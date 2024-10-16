package application;

class ScreenManager implements Runnable {
    
    Screen screen;
    Snake snake;
    ScreenManager(Screen screen, Snake snake) {
        this.screen = screen;
        this.snake = snake;
    }

    public void run()  {
        try {
            while(snake.isAlive()) {
                System.out.println("\nLEFT = 1, RIGHT = 2, UP = 3, DOWN = 4\n");
                System.out.println("\nSCORE : " + snake.size() + "\n");
                screen.display();
                Thread.sleep(500);
                System.out.print("\033[H\033[J");  

                synchronized(screen) {
                    int front_move = snake.getFrontMove();
                    int rear_move = snake.getRearMove();

                    Position front = snake.getFrontPosition();
                    if(screen.moves.get(front) != null) {
                        front_move = screen.moves.get(front);
                    }
                    front = snake.moveFront(front_move);

                    Position rear = snake.getRearPosition();
                    if(screen.moves.get(rear) != null) {
                        rear_move = screen.moves.get(rear);
                        screen.moves.remove(rear);
                    }
                    
                    if(screen.isOutOfBounds(front) || screen.hasSnake(front)) {
                        snake.setStatus(false);
                    } else {
                        if(screen.hasApple(front)) {
                            screen.clear(front);
                            snake.grow();
                        } else {
                            snake.moveRear(rear_move);
                        }
                        screen.grid[front.getX()][front.getY()] = 'S';
                        screen.grid[rear.getX()][rear.getY()] = ' ';
                    }
                }
            }
        } catch (InterruptedException e) {
            snake.setStatus(false);
        } catch(ArrayIndexOutOfBoundsException e) {
            snake.setStatus(false);
        } finally {
            System.out.print("\nGAME OVER\n");
            System.out.print("\nPress and enter any key to exit the game\n");
            try {
                Thread.sleep(500);
            } catch(InterruptedException e) {}
        }
    }
}
