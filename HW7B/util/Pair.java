package util;

import java.util.Objects;

public class Pair<T1, T2>
{
    private T1 fst;
    private T2 snd;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(fst, pair.fst) &&
                Objects.equals(snd, pair.snd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fst, snd);
    }

    @Override
    public String toString() {
        return "(" + fst + ", " + snd + ")";
    }

    /**
     * @param fst first element of the pair
     * @param snd second element of the pair
     */
    public Pair(T1 fst, T2 snd) {
        this.fst = fst;
        this.snd = snd;
    }

    /**
     * @return the first element of the pair
     */
    public T1 getFst() {
        return fst;
    }

    /**
     * @return the second element of the pair
     */
    public T2 getSnd() {
        return snd;
    }
}
