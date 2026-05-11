# Trial: Observer — Real-Time Weather Station

Build a `WeatherStation` subject that tracks temperature, humidity, and pressure. When any reading changes, notify three observers: `CurrentConditionsDisplay` (shows latest readings), `StatisticsDisplay` (shows min/avg/max over time), and `AlertSystem` (triggers when temperature exceeds 35°C or drops below 0°C). Support subscribe and unsubscribe. Run at least 8 simulated readings.
