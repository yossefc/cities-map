/*
 * A generic graph
 */

package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author yossef cohen zardi 
 */
public class Graph <E> {
    private HashMap<E, ArrayList<E>> data;

    public Graph()
    {
        data = new HashMap<E, ArrayList<E>>();
    }

    public void addVertex(E ver) throws VertexExistException
    {
        if(exists(ver))
            throw new VertexExistException("Vertex " + ver + " already exists");

        ArrayList<E> arr = new ArrayList<E>();
        data.put(ver, arr);
    }

    public void addEdge(E v1, E v2) throws VertexNotExistException
    {
        if(!exists(v1) || !exists(v2))
            throw new VertexNotExistException("One of the vertices " + v1 + ", " + v2 + " does not exist");
        ArrayList<E> arr1 = data.get(v1);
        ArrayList<E> arr2 = data.get(v2);
        arr1.add(v2);
        arr2.add(v1);
    }

    public ArrayList<E> getEsges(E ver)
    {
        ArrayList<E> res;
        res = data.get(ver);
        return res;
    }
    public ArrayList<E> getVertices()
    {
        Set<E> ver = data.keySet();
        ArrayList<E> res = new ArrayList<E>();
        for(E e : ver)
            res.add(e);
        return res;
    }

    public ArrayList<E> bfs(E s, E t)
    {
        ArrayList<ArrayList<E>> list = new ArrayList<ArrayList<E>>();
        ArrayList<E> curr = new ArrayList<E>();
        curr.add(s);
        list.add(curr);

        while(!list.isEmpty())
        {
            // 1. remove first from list
            curr = list.remove(0);
            // 2. get last ver1 from curr
            E ver1 = curr.get(curr.size() - 1);
            // 3. if ver1 == t return curr
            if(ver1.equals(t))
                return curr;
            // 4. get all targets ver2 from ver1
            ArrayList<E> targets = getEsges(ver1);
            // 5. for each ver2
            for(E ver2 : targets)
            {
            // 5.1 if ver2 not in curr
                if(curr.contains(ver2))
                    continue;
            // 5.1.1 build a new array contains curr and ver2
                ArrayList<E> path = new ArrayList<E>(curr);
                path.add(ver2);
            // 5.2.2 add array to list
                list.add(path);
            }
        }
        return null;
    }

    private boolean exists(E ver)
    {
        if(ver == null)
            return false;
        Set<E> s = data.keySet();
        for(E key : s)
            if(key.equals(ver))
                return true;
        return false;
    }
}
