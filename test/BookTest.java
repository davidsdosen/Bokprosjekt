import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BookTest {


    /**
     * Tester at jeg kan oprette objekter av klassen book
     */
    @Test
            public void createObjectOfClassTest() {

        Book book1 = new Book("Harry Potter", "J.K. Rowling", "Gyldendal", 1997, 600, 201);
        Book book2 = new Book("Dropper", "Deez", "nuts", 1912397, 6020, 2012);
        Book book3 = new Book("deez", "Jake Paul", "Greves", 12, 33, 201);

        assertEquals(201, book1.getBarcode(), "unexpected behaviour");
        assertEquals("Dropper", book2.getTitle(), "unexpected behaviour");
        assertEquals(33, book3.getPages(), "unexpected behaviour");
    }

    /**
     * Tester aksessormetodene
     */
    @Test
    public void testingAccessorMethods(){

        Book book1 = new Book("Harry Potter", "J.K. Rowling", "Gyldendal", 1997, 600, 201);
        Book book2 = new Book("Dropper", "Deez", "nuts", 1912397, 6020, 2012);
        Book book3 = new Book("deez", "Jake Paul", "Greves", 12, 33, 201);

        assertEquals("Harry Potter",book1.getTitle(),"unexpected behaviour");
        assertEquals("nuts",book2.getPublisher(),"unexpected behaviour");
        assertEquals(201,book3.getBarcode(),"unexpected behaviour");
        assertEquals("J.K. Rowling",book1.getAuthor(),"unexpected behaviour");
        assertEquals(6020,book2.getPages(),"unexpected behaviour");
        assertNotEquals(true,book3.isLent(),"unexpected behaviour");
    }
}
