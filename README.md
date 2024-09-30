# Praca dyplomowa - System kliniki medycznej

### Opis aplikacji
Aplikacja przedstawia prosty system kliniki medycznej z podstawowymi funkcjonalnościami. Ma na celu wsparcie zarządzania operacjami i procesami placówki medycznej.

---

#### Zrealizowane cele:
- [x] [Utworzenie DPU](https://github.com/Pawel-Raciborski/system-kliniki-medycznej/tree/main/Koncepcja%20systemu/Diagramy%20przypadk%C3%B3w%20u%C5%BCycia)
- [x] [Podstawowe scenariusze](https://github.com/Pawel-Raciborski/system-kliniki-medycznej/blob/main/Koncepcja%20systemu/Scenariusze/System%20kliniki%20medycznej%20-%20scenariusze.pdf)
- [x] [Utworzenie diagramu związków encji](https://github.com/Pawel-Raciborski/system-kliniki-medycznej/blob/main/Koncepcja%20systemu/Diagram%20zwi%C4%85zk%C3%B3w%20encji/diagram_zwi%C4%85zk%C3%B3w_encji.png)


#### Podstawowe funkcjonalności w systemie:
- Rejestracja pacjenta
- Rejestracja pracowników
- Zarządzanie wizytami
    - wypisywanie recept(w tym wypisywanie lekarstw)
    - wypisywanie skierowań
    - umawianie wizyt(przez recepcjonistę lub pacjenta)
- Generowanie raportów
- Zarządzanie harmonogramem(lekarza, recepcjonisty)
- Realizacja wizyty
- Powiadomienia o nadchodzącej wizycie

---

### Architektura oprogramowania
Aplikacja monolityczna

### Wykorzystane technologie(alpha v0.1):

#### Front-end:
- Angular
- TypeScript
- CSS(lub SCSS)
- HTML5

#### Back-end:
- Java
- Spring Boot

#### Baza danych:
- PostgreSQL

#### Inne technologie:
- Postman
