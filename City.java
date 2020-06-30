/*
 * Represents a city on the map
 */

package graph;

/**
 *
 * @author yossef cohen zardi 
 */
public class City {
    private String cityName;
    private int centerX, centerY;

    public City(String name, int x, int y)
    {
        cityName = name;
        centerX = x;
        centerY = y;
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public boolean equals(Object other)
    {
        return other instanceof City && cityName.equals(((City)other).getCityName());
    }
}
