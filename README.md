# Library Manager

## Current System design

### Ui

The library manager is a console app. User interfaces (UIs) are built using the [AbstractUi](./src/main/java/LibraryManager/ui/AbstractUi.java) class.
Typically, each UI displays information and then queries the user for one or multiple inputs.
To print text, you can use the print methods of the [AbstractUi](./src/main/java/LibraryManager/ui/AbstractUi.java) class.
To get an input, you can use the input methods of the [AbstractUi](./src/main/java/LibraryManager/ui/AbstractUi.java) class.
The entry point for each UI is the "start" method.
A UI should either resolve and return to the UI that called it or create and start another UI.

The main menu UI is called as the entry point for the app. When subsequent UIs resolve, the main menu UI will always be restarted.

### Models

Models are mainly data classes that represent entities.

### Services

Services are  classes that implement business logic.
They usually work with model classes.

The BookService and RentalService are both singletons, they manage the state of the library.

### Util

#### Table String Builder

The table string builder classes are used to create Strings to be printed of tables.
Examples of these tables can be found in different UI classes, for example in [ManageBooksUi.java](./src/main/java/LibraryManager/ui/inventory/ManageBooksUi.java),
which calls the 'allBooksAsString' method of the [BookService](./src/main/java/LibraryManager/service/BookService.java).

To create a printable table, either create a new instance of RowTableStringBuilder or RowTableStringBuilder.

When you use the RowTableStringBuilder, you can add rows as a list of strings by calling the addDataPoint method.
When you use the RowTableStringBuilder, you can add columns as a list of strings by calling the addDataPoint method.

To set table headers, call the setTableHeaders method.

Note that before printing the table, the amount of headers should equal the amount of columns and the amount of rows/columns should be consistent between entries.

### Tests

There are a few tests to test the service logic and utils.
