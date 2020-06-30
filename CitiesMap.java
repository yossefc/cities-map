/*
 * Represents a graph of cities
 */

package graph;

import java.util.ArrayList;

/**
 *
 * @author yossef cohen zardi 
 */
public class CitiesMap {
    private Graph<City> cities;

    public CitiesMap()
    {
        cities = new Graph<City>();
    }

    public ArrayList<City> getCities()
    {
        return cities.getVertices();
    }

    public ArrayList<City> getWays(City c)
    {
        return cities.getEsges(c);
    }
    public void addCity(City c) throws CityExistException
    {
        try {
            cities.addVertex(c);
        }
        catch(VertexExistException e)
        {
            throw new CityExistException("City " + c.getCityName() + " already exists");
        }
    }

    public void addWay(City c1, City c2) throws CityNotExistException
    {
        try {
            cities.addEdge(c1, c2);
        }
        catch(VertexNotExistException e)
        {
            throw new CityNotExistException("City does not exist");
        }
    }

    public ArrayList<City> findPath(City c1, City c2) throws NoPathException
    {
          ArrayList<City> res = cities.bfs(c1, c2);
          if(res == null)
              throw new NoPathException("No path between " + c1.getCityName() + " and " + c2.getCityName());
          return res;
    }
}
