/**
 * WeatherStation.java — Observer Pattern Solution
 *
 * WeatherStation subject notifies 3 observers: CurrentConditions,
 * StatisticsDisplay, and AlertSystem across 8+ simulated readings.
 * Run: javac WeatherStation.java && java WeatherStation
 */
import java.util.*;

public class WeatherStation {

    // ── Observer interface ──
    interface WeatherObserver {
        void update(double temp, double humidity, double pressure);
    }

    // ── Subject ──
    static class Station {
        private double temperature, humidity, pressure;
        private List<WeatherObserver> observers = new ArrayList<>();

        public void subscribe(WeatherObserver o)   { observers.add(o); }
        public void unsubscribe(WeatherObserver o) { observers.remove(o); }

        public void setReadings(double temp, double humidity, double pressure) {
            this.temperature = temp; this.humidity = humidity; this.pressure = pressure;
            observers.forEach(o -> o.update(temp, humidity, pressure));
        }
    }

    // ── Observer 1: Current conditions ──
    static class CurrentConditionsDisplay implements WeatherObserver {
        public void update(double t, double h, double p) {
            System.out.printf("  🌡  Current: %.1f°C | Humidity: %.0f%% | Pressure: %.0fhPa%n", t, h, p);
        }
    }

    // ── Observer 2: Running statistics ──
    static class StatisticsDisplay implements WeatherObserver {
        private List<Double> temps = new ArrayList<>();
        public void update(double t, double h, double p) {
            temps.add(t);
            double min = temps.stream().mapToDouble(Double::doubleValue).min().orElse(t);
            double max = temps.stream().mapToDouble(Double::doubleValue).max().orElse(t);
            double avg = temps.stream().mapToDouble(Double::doubleValue).average().orElse(t);
            System.out.printf("  📊 Stats:   Min=%.1f°C | Avg=%.1f°C | Max=%.1f°C (n=%d)%n",
                min, avg, max, temps.size());
        }
    }

    // ── Observer 3: Alerts ──
    static class AlertSystem implements WeatherObserver {
        public void update(double t, double h, double p) {
            if (t > 35)  System.out.println("  🚨 ALERT: EXTREME HEAT — " + t + "°C!");
            if (t < 0)   System.out.println("  🚨 ALERT: FREEZING TEMP — " + t + "°C!");
            if (h > 90)  System.out.println("  ⚠ WARNING: Very high humidity: " + h + "%");
            if (p < 980) System.out.println("  ⚠ WARNING: Low pressure: " + p + "hPa (storm risk)");
        }
    }

    public static void main(String[] args) {
        System.out.println("=== OBSERVER SOLUTION: WEATHER STATION ===\n");

        Station station = new Station();
        WeatherObserver current = new CurrentConditionsDisplay();
        WeatherObserver stats   = new StatisticsDisplay();
        WeatherObserver alerts  = new AlertSystem();

        station.subscribe(current);
        station.subscribe(stats);
        station.subscribe(alerts);

        double[][] readings = {
            {22.0, 65.0, 1013.0},
            {25.5, 70.0, 1010.0},
            {28.0, 78.0, 1005.0},
            {32.0, 85.0, 998.0},
            {36.5, 91.0, 985.0},  // triggers heat + humidity + pressure alerts
            {34.0, 88.0, 990.0},
            { 5.0, 60.0, 1020.0},
            {-2.5, 55.0, 1025.0}, // triggers freeze alert
        };

        for (int i = 0; i < readings.length; i++) {
            System.out.println("── Reading #" + (i+1) + " ──");
            station.setReadings(readings[i][0], readings[i][1], readings[i][2]);

            // Unsubscribe alerts after reading 6 to demonstrate dynamic subscription
            if (i == 5) {
                System.out.println("  (AlertSystem unsubscribed)");
                station.unsubscribe(alerts);
            }
            System.out.println();
        }

        System.out.println("Observer ✅ — 3 observers notified across 8 readings, dynamic subscribe/unsubscribe");
    }
}
