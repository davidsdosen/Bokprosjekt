## Book Library project in Java
Original version 0.0.1 was made in November 2021 during my first OOP programming class

This Markdown file will explain the refactoring changes made to this project to bring it up to modern java best practices

## Version 0.0.2 changelog (March 2026)

### 1. Refactored everything to Maven project standard

### 2. Added data type final to book parameters to restrict modification, these values are not meant to be mutated

```java
private final String title;
private final String author;
//...
```
### 3. Refactored lending functionality: added encapsulation of state transitions 

This prevents unauthorized change using 
```java
setLent(true)
setLent(false)
```
The better way is to change using lend() and returnBook() methods. Errors are thrown if book is already lent for example.

```java
public void lend() {
    if (lent) {
        throw new IllegalStateException("Book is already lent");
    }
    lent = true;
}

public void returnBook() {
    lent = false;
}
```

### 4. Using Optional class
This best practice prevents unnecessary NullPointerException errors and simplifies code in the UI

Example of usage in the registry when iterating to find a book by barcode
Old:
```java
public Book findByBarcode(int searchInt){
    //...
    return null;
}
```
New:
```java
public Optional<Book> findByBarcode(int searchInt){
    //...
    return Optional.empty();
}
```

Example of usage in the UI

Old:
```java
case FIND_BOOK_BY_BARCODE:
    System.out.println("Enter the barcode");
    int barcodeInt = this.intChoice();
    if(this.register.findByBarcode(barcodeInt).isEmpty()){
        System.out.println("Error: This book does not exist");
    }
    else {
        System.out.println(this.register.findByBarcode(barcodeInt).toString());
    }*/
    break;
```

New:
```java
case FIND_BOOK_BY_BARCODE:
    System.out.println("Enter the barcode");
    int barcodeInt = this.intChoice();
    Optional<Book> result = register.findByBarcode(barcodeInt);
    System.out.println(result.map(Book::toString).orElse("Book not found"));
    break;
```

### 5. Using streams

Demonstrated on the findFirstByName method

Old:
```java
public Optional<Book> findFirstByName(String searchString){
    int index = 0;
    while (index < bookList.size()){
        Book filename = bookList.get(index);
        if (filename.getTitle().contains(searchString)){
            return Optional.of(filename);
        }
        index++;
    }
    return Optional.empty();
}
```
New:
```java
public Optional<Book> findFirstByName(String searchString){

    return bookList.stream().filter(book -> book.getTitle().contains(searchString)).findFirst();
}
```