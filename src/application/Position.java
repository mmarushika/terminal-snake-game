final public class Position {
    int x;
    int y;

    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    int getX() {
        return x;
    }
    int getY() {
        return y;
    }

    @Override
    public int hashCode() {
        //https://www.cs.upc.edu/~alvarez/calculabilitat/enumerabilitat.pdf
        //https://stackoverflow.com/questions/22826326/good-hashcode-function-for-2d-coordinates
        //https://en.wikipedia.org/wiki/Pairing_function
        int temp = this.y + (this.x + 1)/2;
        return this.x + temp * temp;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }
        if(obj.getClass() != this.getClass()) {
            return false;
        }

        final Position other = (Position) obj;
        return (other.x == this.x && other.y == this.y);
    }
}

