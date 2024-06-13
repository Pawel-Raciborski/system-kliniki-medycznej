# Podstawowa koncepcja diagramu związków encji

Diagram przedstawia podstawową strukturę bazy danych systemu kliniki medycznej. Jest to moja wizja tego, jak ja widzę tę strukturę. Nie jest to jeszcze oficjalna struktura, może jeszcze ulec zmianie.


### Podstawowe encje

- ***doctor*** - przedstawia obiekt doktora w systemie
- ***schedule*** - przechowuje informacje o grafiku danego lekarza, informacje o godzinach pracy
- ***medical_visit*** - przechowuje informacje nt. wizyty, informacje o postawionej diagnozie oraz wystawionych recept
- ***prescription*** - tabela przechowuje informacje nt recepty, data wypisania recepty oraz datę ważności(TODO dodanie do recepty listy lekarstw)
- ***doctor_specialization*** - informacje nt. specjalizacji lekarza
- ***specialization*** - opis poszczegolnej specjalizacji
- ***receptionist*** - przedstawia obiekt recepcjonisty w systemie
- ***patient*** - przedstawia obiekt pacjenta w systemie
- ***personal_details*** - przechowuje informacje nt. danych osobowych
- ***address*** - przechowuje informacje nt. adresu
- ***city*** - przechowuje informacje nt. miasta
- ***country*** - przechowuje informacje nt. państwa
- ***patient_card*** - jest to karta pacjenta
- ***medical_history_entry*** - wpis historii badań w karcie pacjenta
- ***medical_interview*** - przechowuje informacje wywiadu pacjenta
- ***patient_disease*** - przechowuje wpisy o chorobach pacjenta
- ***disease*** - dane nt. choroby
- ***disease_cure*** - informacje nt rozpoczęcia i zakończenia leczenia
- ***disease_cure_details*** - informacje nt dawkowania leku
- ***cure*** - informacje nt. leku