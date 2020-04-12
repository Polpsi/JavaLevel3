// Цель... Просто цель.
// У нее есть координаты.

package Lesson_3;

import java.io.Serializable;

public class Target implements Serializable {
    private double latitude;
    private double longitude;

    Target(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double[] getCoordinates(){
        return new double[]{this.latitude,this.longitude};
    }

}
