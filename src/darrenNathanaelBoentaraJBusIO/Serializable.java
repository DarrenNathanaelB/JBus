package darrenNathanaelBoentaraJBusIO;

import java.util.HashMap;

public class Serializable implements Comparable<Serializable> {
    public final int id;
    private static HashMap<Class<?>, Integer> mapCounter = new HashMap<>();

    protected Serializable() {
        Class<?> clas = this.getClass();
        int serial = mapCounter.getOrDefault(clas, 0);
        mapCounter.put(clas, serial+1);
        this.id = serial;
    }

    public static <T> Integer setLastAssignedId(Class<?> Obj, int id) {
        Integer newId = Integer.valueOf(id);
        mapCounter.put(Obj, id);
        return newId;
    }

    public static <T> Integer getLastAssignedId(Class<?> Obj) {
        return mapCounter.get(Obj) - 1;
    }

    @Override
    public int compareTo(Serializable other) {
        if (id == other.id)
        {
            return 0;
        }
        else if (id > other.id)
        {
            return 1;
        }
        else {
            return -1;
        }
    }

    public boolean equals(Serializable other)
    {
        if (other.id == id){
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Serializable && ((Serializable)obj).id == this.id )
        {
            return true;
        }
        else return false;
    }
}