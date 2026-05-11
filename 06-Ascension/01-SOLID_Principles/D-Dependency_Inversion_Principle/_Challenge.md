# Trial: DIP — Pluggable Data Pipeline

Build a data pipeline where `DataReader`, `DataTransformer`, and `DataWriter` are all interfaces. Create implementations: `CSVReader`/`JSONReader`, `UpperCaseTransformer`/`FilterTransformer`, `ConsoleWriter`/`FileWriter`. Build a `Pipeline` class that accepts any combination via constructor injection and processes data. Show that you can swap any component.
