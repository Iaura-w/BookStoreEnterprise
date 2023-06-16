# BookStoreEnterprise
Aplikacja webowa do zarządzanie księgarnią internetową.

## Funkcjonalności

1. Zabezpieczenie Spring Security 
   - Podział na role admin i user
   - Logowanie i rejestracja
2. CRUD
   - admin może zarządzać książkami (dodawanie, usuwanie, edycja) i zamówieniami (zmiana statusu)
   - user może przeglądać książki, dodawać do koszyka i składać zamówienia
3. Koszyk
   - user może dodawać i usuwać książki z koszyka
   - user może przeglądać zawartość koszyka
4. Zamówienia
   - user może składać zamówienia na książki znajdujące się w koszyku
   - admin może zmieniać status zamówienia
5. Płatności
   - integracja z systemem płatności PayU
   - user może dokonywać płatności za zamówienie
