package application;

class SnakeGame {
    public static void main(String[] args) {
        Snake snake = new Snake();
        Screen screen = new Screen(25, 50);
        int score = 0;

        Thread screen_manager = new Thread(new ScreenManager(screen, snake), "screen_manager");
        Thread apple_generator = new Thread(new AppleGenerator(screen, snake), "apple_generator");

        screen_manager.start();
        apple_generator.start();

        try {
            while(snake.isAlive()) {
                int move = snake.getNextMove();
                synchronized(screen) {
                    Position pos = snake.getFrontPosition();
                    screen.moves.put(pos, move);
                }
                Thread.sleep(1000);
            }  

            if(apple_generator.isAlive()) {
                System.out.print("\nExiting...\n");
            }
            apple_generator.join();
            screen_manager.join();
        } catch(InterruptedException e) {}
        System.out.print("\nFINAL SCORE : " + snake.size() + "\n");
        System.out.print("\nTHANK YOU!!!\n\n");
    }
}
