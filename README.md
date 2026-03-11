# Book Library project in Java
Original version 0.0.1 was made in November 2021 during my first OOP programming class

This Markdown file will explain the refactoring changes made to this project to bring it up to modern java best practices

# Version 0.0.2 changelog (March 2026)

## 1. Refactored everything to Maven project standard

## 2. Added data type final to book parameters to restrict modification, these values are not meant to be mutated

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

## 4. Using Optional class
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

## 5. Refactoring books field
This is a change to the way the bookList is stored in the registry.
- private hides it from other classes
- final is used to prevent accidental modification of the list
- List is used instead of ArrayList which is an interface, standard practice in Java

Old:

```java
ArrayList<Book> books;
```
New:
```java
private final List<Book> books;
```

## 6. Refactoring how all books are listed
Changed the way all books are listed. The registry class is no longer responsible for listing all books, it only returns a list of books.
The app class is responsible for listing all books, the classes now follow the single responsibility principle which is
a core concept in OOP.

#### Old:

Registry class
```java
public void listAllBooks() {
        int index = 0;
        while (index < bookList.size())
        {
            Book book = bookList.get(index);
            System.out.println("----------------------");
            System.out.println("Title :" + book.getTitle());
            System.out.println("Author :" + book.getAuthor());
            System.out.println("Publisher :" + book.getPublisher());
            System.out.println("Release year :" + book.getReleaseYear());
            System.out.println("Pages :" + book.getPages());
            System.out.println("Barcode :" + book.getBarcode());
            System.out.println("Currently lent :" + book.isLent());
            index++;
        }
    }
```
App class
```java
case LIST_ALL_BOOKS:
                    System.out.println("Listed all books:");
                    this.register.listAllBooks();
                    break;
```
#### New:

Registry class
```java
public List<Book> getAllBooks(){
        return books;
    }
```
App class
```java
case LIST_ALL_BOOKS:
        System.out.println("Listing all books:");
        register.getAllBooks().forEach(System.out::println);
        break;   
```

## 7. Using streams and refactoring methods


### Iterating to find a book by name
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
### Iterating to find a book by barcode
Old:
```java
public Optional<Book> findByBarcode(int searchInt){
     int index = 0;
     while (index < bookList.size()){
         Book filename = bookList.get(index);
         if (filename.getBarcode() == searchInt){
             return Optional.of(filename);
         }
         index++;
     }
     return Optional.empty();
}
```
New:
```java
public Optional<Book> findByBarcode(int searchInt){
    return bookList.stream().filter(book -> book.getBarcode() == searchInt).findFirst();
}
```

### Iterating to find all books by author
Old:
```java
public ArrayList<Book> findAllByAuthor(String searchAuthor){
     int index = 0;
     ArrayList<Book> tempBookList = new ArrayList<>();
     while (index < bookList.size()){
         Book filename = bookList.get(index);
         if (filename.getAuthor().contains(searchAuthor)){
             tempBookList.add(filename);
         }
         index++;
     }
     return tempBookList;
}
```
New:
```java
public ArrayList<Book> findAllByAuthor(String searchAuthor){
    return bookList.stream().filter(book -> book.getAuthor().contains(searchAuthor)).collect(Collectors.toCollection(ArrayList::new));
}
```

### Delete book by barcode
Old:
```java
public boolean deleteByBarcode(int barcodeInt){
    for (Book book: bookList) {
        if(book.getBarcode() == barcodeInt){
            bookList.remove(book);
            return true;
        }
    }
        return false;
    }
```
New:
```java
public boolean deleteByBarcode(int barcodeInt){
    return bookList.removeIf(book -> book.getBarcode() == barcodeInt);
}
```