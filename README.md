# Library Manager

## Current System design

### Tools

The library manager is a console app. UIs are built using the Tool class.
Typically, each tool displays information and then queries the user for one or multiple inputs.
To print text, you can use the print methods of the Tool class.
To get an input, you can use the input methods of the Tool class.
The entry point for each tool is the "start" method.
A tool should either resolve and return to the tool that called it or create and start another tool.

The main menu tool is called as the entry point for the app. When subsequent tools resolve, the main menu tool will always be restarted.

### Models

Models are mainly data classes that represent entities.

### Services

Services are  classes that implement business logic.
They usually work with model classes.

The BookService and RentalService are both singletons, they manage the state of the library.

### Util

#### Table String Builder

The table string builder classes are used to create Strings to be printed of tables.

To create a printable table, either create a new instance of RowTableStringBuilder or RowTableStringBuilder.

When you use the RowTableStringBuilder, you can add rows as a list of strings by calling the addDataPoint method.
When you use the RowTableStringBuilder, you can add columns as a list of strings by calling the addDataPoint method.

To set table headers, call the setTableHeaders method.

Note that before printing the table, the amount of headers should equal the amount of columns and the amount of rows/columns should be consistent between entries.

### Tests

There are a few tests to test the business logic and utils.

## Your task

Imagine you are a freelancer and have accepted a gig for a library.
They want you to incorporate the location information of books within the library into their library manager.

Create a system for the library to represent and manage the physical location of books within the library.

The library gave the following requirements:
- The library is split into different regions (e.g. fiction, thriller, children's books, ...)
- Each bookshelf lies within a unique region of the library
- Each bookshelf consists of multiple tiers of shelves
- Multiple bookshelves are typically grouped into a bigger bookshelf
- We want to be able to tell exactly where a book is
- We want to be able to tell which books are next to a book
- We want to be able to tell which books are on the same tier of the bookshelf as the book

You are free to change the entire code base to your liking.\
There is already a basic implementation of the user interface, which you can use and adapt (see [GetLocationTool.java](./src/main/java/LibraryManager/tool/location/GetLocationTool.java) and [ManageLocationTool.java](./src/main/java/LibraryManager/tool/location/ManageLocationTool.java)).\
You don't have to populate the library with a completed dataset.\
You are not allowed to use any external information sources, please use your assistant to gather information.

### Example Floor plan
Brown rectangles are bookshelves.\
The black space between bookshelves are walking paths.\
The labeled frames denote different regions in the library for different kind of books.

![Floor plan of the library](Floorplan.png "Floor plan")