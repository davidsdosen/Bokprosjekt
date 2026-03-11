//import org.junit.jupiter.api.Test;
//
//import static org.junit.Assert.assertNotEquals;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotEquals;
//
//public class RegistryTest {
//
//    /**
//     * I denne testen tester jeg følgende:
//     * - registerobjektet registryTest av klassen com.davidsdosen.bokprosjekt.repository.Registry blir riktig oprettet
//     * - bøkene blir riktig lagt inn
//     * - metoden size() returnerer riktig verdi (antallet bøker i registeret)
//     */
//    @Test
//        public void createObjectsOfClassAndAddBooksTest() {
//        com.davidsdosen.bokprosjekt.repository.Registry registryTest = new com.davidsdosen.bokprosjekt.repository.Registry();
//        registryTest.addBooks();
//        registryTest.listAllBooks();
//        assertEquals(4,registryTest.bookList.size(),"unexpected event");
//    }
//
//    /**
//     * - tester at man ikke kan legge till null som bok
//     */
//    @Test
//    public void addNull() {
//        com.davidsdosen.bokprosjekt.repository.Registry registryTest = new com.davidsdosen.bokprosjekt.repository.Registry();
//        registryTest.addBook(null);
//        registryTest.listAllBooks();
//        assertNotEquals(String.valueOf(1), registryTest.bookList.size(), "unexpected event");
//    }
//
//    /**
//     * Tester søkefunksjon by barcode
//     * setter variabelen barcodeInt = 125; slik jeg hadde skrevet inn manuelt
//     */
//    @Test
//    public void searchByBarcodeTest() {
//        com.davidsdosen.bokprosjekt.repository.Registry registryTestSearchByBarcode = new com.davidsdosen.bokprosjekt.repository.Registry();
//        registryTestSearchByBarcode.addBooks();
//        int barcodeInt = 125;
//        System.out.println(registryTestSearchByBarcode.findByBarcode(barcodeInt).toString());
//    }
//
//    /**
//     * I denne testen tester jeg følgende:
//     * - mulig å slette en bok
//     *
//     * I addBooks() legger den til 4 bøker så da må assertEquals = 3 og assertNotEquals = 4 for at testen skal
//     * være vellykket
//     */
//    @Test
//    public void deleteBookTest() {
//        com.davidsdosen.bokprosjekt.repository.Registry registryTest = new com.davidsdosen.bokprosjekt.repository.Registry();
//        registryTest.addBooks();
//        registryTest.deleteByBarcode(125);
//        assertEquals(3,registryTest.bookList.size(),"unexpected event");
//        assertNotEquals(4,registryTest.bookList.size(),"unexpected event");
//    }
//
//    /**
//     * Prøver å slette en bok som ikke eksisterer, testen kjører fint så da håndterer registeret dette
//     */
//    @Test
//    public void deleteMissingBookTest(){
//        com.davidsdosen.bokprosjekt.repository.Registry registryTest = new com.davidsdosen.bokprosjekt.repository.Registry();
//        registryTest.addBooks();
//        registryTest.deleteByBarcode(999);
//    }
//
//    /**
//     * Tester å søke etter en bok som ikke finnes
//     * Testen er satt opp på samme måte som i user interface (altså i com.davidsdosen.bokprosjekt.app.BookLibraryApp klassen)
//     */
//    @Test
//    public void searchMissingBookByBarcodeTest() {
//        com.davidsdosen.bokprosjekt.repository.Registry registryTest1 = new com.davidsdosen.bokprosjekt.repository.Registry();
//        registryTest1.addBooks();
//        int barcodeInt = 999;
//        if(registryTest1.findByBarcode(barcodeInt) == null){
//            System.out.println("Error: This book does not exist");
//
//        } else {
//            System.out.println(registryTest1.findByBarcode(barcodeInt).toString());
//        }
//    }
//}
