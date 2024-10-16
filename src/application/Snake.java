package application;

class Snake {
    //1 = LEFT
    //2 = RIGHT
    //3 = UP
    //4 = DOWN

    boolean status;
    int front_move;
    int rear_move;
    int front_x;
    int front_y;
    int rear_x;
    int rear_y;
    int length;

    Snake() {
        this.status = true;
        this.front_move = 4; //MOVE DOWN BY DEFAULT
        this.rear_move = 4;
        this.front_x = 1;
        this.front_y = 1;
        this.rear_x = 1;
        this.rear_y = 1;
        this.length = 0;
    }

    void grow() {
        this.length++;
    }

    int size() {
        return this.length;
    }
    Position moveFront(int move) {
        if(move >= 1 && move <= 4) {
            this.front_move = move;
        }
        Position next = nextPosition(this.front_move, this.getFrontPosition());
        this.front_x = next.getX();
        this.front_y = next.getY();
        return next;
    }
    
    Position moveRear(int move) {
        if(move >= 1 && move <= 4) {
            this.rear_move = move;
        }
        Position next = nextPosition(this.rear_move, this.getRearPosition());
        this.rear_x = next.getX();
        this.rear_y = next.getY();
        return next;
    }

    Position nextPosition (int move, Position pos) {
        //1 = UP
        //2 = DOWN
        //3 = LEFT
        //4 = RIGHT
        switch(move) {
            case 1 -> {return new Position(pos.getX(), pos.getY() - 1);}
            case 2 -> {return new Position(pos.getX(), pos.getY() + 1);}
            case 3 -> {return new Position(pos.getX() - 1, pos.getY());}
            case 4 -> {return new Position(pos.getX() + 1, pos.getY());}
            default -> {return pos;}
        }
    }

    int getNextMove() throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        try {
            int move = Integer.parseInt(scan.nextLine());
            return move;
        } catch(NumberFormatException e) {
            return -1;
        }
    }

    int getFrontMove() {
        return this.front_move;
    }

    int getRearMove() {
        return this.rear_move;
    }
    Position getRearPosition() {
        return new Position(this.rear_x, this.rear_y);
    }

    Position getFrontPosition() {
        return new Position(this.front_x, this.front_y);
    }

    boolean isAlive() {
        return this.status;
    }  

    void setStatus(boolean status) {
        this.status = status;
    }
}
