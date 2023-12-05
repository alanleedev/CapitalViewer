# WGT Programing Challenge

This app fetches list of countries from a static JSON file and displays it in a list.
It also supports device rotation and error handling.

- Data fetching is done by `CapitalRepository` and converts network model to presentation layer model
- `Dependencies` is used to lazily initialize and store global dependencies
- `ViewModel` uses the Repository to fetch data update the UI State
- For RecyclerView, `DiffUtil` is used to optimize performance
- Error is displayed as a `Toast`
